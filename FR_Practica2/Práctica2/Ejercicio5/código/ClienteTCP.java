package ejercicio5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteTCP {

    public static void main(String[] args) {
		
		
	// Nombre del host donde se ejecuta el servidor:
	String host="localhost";
	// Puerto en el que espera el servidor:
	int port=8989;
		
	// Socket para la conexión TCP
	Socket socketServicio=null;
                
		
	try {
            // Creamos un socket que se conecte a "host" y "port":
	
            socketServicio = new Socket(host,port);
			                
            // Implementación de los PrintWriter BufferedReader
            PrintWriter outPrinter = new PrintWriter(socketServicio.getOutputStream(), true);
            BufferedReader inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));   
           
            String cadenaEnvio = "002 d";
            
            // Enviamos el array por el outputStream;
			
            outPrinter.println(cadenaEnvio);
           	
            outPrinter.flush();
           
            // Recibimos la cadena en nuestro string
            //////////////////////////////////////////////////////
            String cadenaRecibida = inReader.readLine();
			         
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

