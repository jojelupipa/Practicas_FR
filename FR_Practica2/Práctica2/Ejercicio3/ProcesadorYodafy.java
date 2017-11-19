package fr_practica2;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;


//
// Nota: si esta clase extendiera la clase Thread, y el procesamiento lo hiciera el método "run()",
// ¡Podríamos realizar un procesado concurrente! 
//
public class ProcesadorYodafy extends Thread {
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
	public ProcesadorYodafy(Socket socketServicio, int numero) {
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
			
			
			// Lee la frase a Yodaficar:
			////////////////////////////////////////////////////////
			cadenaRecibida = inReader.readLine();
			////////////////////////////////////////////////////////
			
			// Yoda hace su magia:
			
			// Yoda reinterpreta el mensaje:
                        String respuesta = "";
                        try{
                            respuesta = yodaDo(cadenaRecibida);
                        } catch (NullPointerException a){
                        
                        }
			// Enviamos la traducción de Yoda:
			////////////////////////////////////////////////////////
			outPrinter.println(respuesta);
			////////////////////////////////////////////////////////
			
			
			
		} catch (IOException e) {
			System.err.println("Error al obtener los flujos de entrada/salida.");
		}

	}

	// Yoda interpreta una frase y la devuelve en su "dialecto":
	private String yodaDo(String peticion) {
		// Desordenamos las palabras:
		String[] s = peticion.split(" ");
		String resultado="";
		
		for(int i=0;i<s.length;i++){
			int j=random.nextInt(s.length);
			int k=random.nextInt(s.length);
			String tmp=s[j];
			
			s[j]=s[k];
			s[k]=tmp;
		}
		
		resultado=s[0];
		for(int i=1;i<s.length;i++){
		  resultado+=" "+s[i];
		}
		
		return resultado;
	}
}
