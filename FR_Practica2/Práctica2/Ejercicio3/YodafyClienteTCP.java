package fr_practica2;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class YodafyClienteTCP {

	public static void main(String[] args) {
		
		
		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;
		
		// Socket para la conexión TCP
		Socket socketServicio=null;
                
		
		try {
			// Creamos un socket que se conecte a "host" y "port":
			//////////////////////////////////////////////////////
			socketServicio = new Socket(host,port);
			//////////////////////////////////////////////////////			
                        
                        // Implementación de los PrintWriter BufferedReader
                        PrintWriter outPrinter = new PrintWriter(socketServicio.getOutputStream(), true);
                        BufferedReader inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));   
			
			// Si queremos enviar una cadena de caracteres por un OutputStream, hay que pasarla primero
			// a un array de bytes:
			String cadenaEnvio = new String("Al monte del volcan debes ir sin demora");
			
			// Enviamos el array por el outputStream;
			//////////////////////////////////////////////////////
			outPrinter.println(cadenaEnvio);
			//////////////////////////////////////////////////////
			
			// Aunque le indiquemos a TCP que queremos enviar varios arrays de bytes, sólo
			// los enviará efectivamente cuando considere que tiene suficientes datos que enviar...
			// Podemos usar "flush()" para obligar a TCP a que no espere para hacer el envío:
			//////////////////////////////////////////////////////
			outPrinter.flush();
			//////////////////////////////////////////////////////
			
			// Recibimos la cadena en nuestro string
                        //////////////////////////////////////////////////////
                        String cadenaRecibida = inReader.readLine();
			
			//////////////////////////////////////////////////////
                        
                        try{
                            inReader.close();
                            outPrinter.close();
                        } catch (IOException e){
                            System.err.println("No se pudo cerrar la conexión");
                        }
			
			// Mostremos la cadena de caracteres recibidos:
			System.out.println("Recibido: " + cadenaRecibida);
			
			
			// Una vez terminado el servicio, cerramos el socket (automáticamente se cierran
			// el inpuStream  y el outputStream)
			//////////////////////////////////////////////////////
			socketServicio.close();
			//////////////////////////////////////////////////////
			
			// Excepciones:
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
