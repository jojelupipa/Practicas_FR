package ejercicio5;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class ProcesadorBiblioteca extends Thread {
	// Referencia a un socket para enviar/recibir las peticiones/respuestas
	private Socket socketServicio;
	// stream de lectura (por aquí se recibe lo que envía el cliente)
	private InputStream inputStream;
	// stream de escritura (por aquí se envía los datos al cliente)
	private OutputStream outputStream;
        private int n_ejecucion;
	
	// Para que la respuesta sea siempre diferente, usamos un generador de números aleatorios.
	private Random random;
	
	// Constructor que tiene como parámetro una referencia al socket abierto en por otra clase
	public ProcesadorBiblioteca(Socket socketServicio, int numero) {
            this.socketServicio=socketServicio;
            n_ejecucion=numero;
            random=new Random();
	}
	
	
	// Aquí es donde se realiza el procesamiento realmente:
	@Override
        public void run(){
		
            String cadenaRecibida;
           	
            try {
                    
                // Implementación de los PrintWriter BufferedReader
                PrintWriter outPrinter = new PrintWriter(socketServicio.getOutputStream(), true);
                BufferedReader inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
                
                ////////////////////////////////////////////////////////
		cadenaRecibida = inReader.readLine();
		////////////////////////////////////////////////////////
			
                String respuesta = "No hay respuesta.\n";
                
		if (cadenaRecibida.contains(" d"))
                    respuesta=devolverLibro(cadenaRecibida);
                else if (cadenaRecibida.contains(" s"))
                    respuesta=buscarLibro(cadenaRecibida);
                else
                    System.err.println("Error en la opción seleccionada.\n");
			
		System.out.println("Trabajando en la hebra número " + n_ejecucion);
		// Enviamos el mensaje de salida:
		////////////////////////////////////////////////////////
		outPrinter.println(respuesta);
		////////////////////////////////////////////////////////
			
			
			
            } catch (IOException e) {
		System.err.println("Error al obtener los flujos de entrada/salida.");
            }

	}

	// Función que busca el libro solicitado a partir de su código
	private String buscarLibro(String peticion) throws FileNotFoundException, IOException {
            File f = new File("/home/aurelia/Documentos/Ej5_pr2_fr/ServidorCliente/src/ejercicio5/libros.txt");
            BufferedReader leer = new BufferedReader(new FileReader(f));
            String lineaExtraida = "",linea = "", fichero="";
            String mensaje = "El libro solicitado no está disponible";
            
            while ((linea = leer.readLine()) != null){
                if(linea.contains(peticion) && linea.contains("disponible")){
                    lineaExtraida = linea;    
                }else{
                    fichero += linea + "\n";
                }
            }
                
            leer.close();
            
            BufferedWriter escribir = new BufferedWriter(new FileWriter(f));
            escribir.write(fichero);
            escribir.flush();
          
            if (lineaExtraida.contains("disponible")){
              escribir.write(lineaExtraida.replaceAll("disponible", "prestado").replaceAll(" s ", " d "));
              escribir.flush();
              mensaje = "Sacado: " + lineaExtraida;
            } 
                   
            return mensaje;
        }
        // Función que devuelve el libro solicitado a partir de su código
        private String devolverLibro(String peticion) throws IOException{
            File f = new File("/home/aurelia/Documentos/Ej5_pr2_fr/ServidorCliente/src/ejercicio5/libros.txt");
            boolean exito = false;
            
            BufferedReader leer = new BufferedReader(new FileReader(f));
            String lineaExtraida = "",linea = "", fichero="";
           
            while ((linea = leer.readLine()) != null){
                if(linea.contains(peticion) && linea.contains("prestado")){
                    lineaExtraida = linea;    
                }else{
                    fichero += linea + "\n";
                }
            }
              
            leer.close();
            
            BufferedWriter escribir = new BufferedWriter(new FileWriter(f));
            escribir.write(fichero);
            escribir.flush();
            
            if (lineaExtraida.contains("prestado")){
              escribir.write(lineaExtraida.replaceAll("prestado", "disponible").replaceAll(" d ", " s "));
              escribir.flush();
              exito = true;
            } 
            
            if(exito){
                String mensaje = "Devuelto: ";
                return mensaje + lineaExtraida;
            }else
                return "Se ha producido un error en la devolución.\n";
        }
}