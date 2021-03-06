package fr_practica2;

import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
public class YodafyServidorConcurrente {

	public static void main(String[] args) {
	
		// Puerto de escucha
		int port=8989;
		// array de bytes auxiliar para recibir o enviar datos.
		
                /*
                byte []buffer=new byte[256];
		// Número de bytes leídos
		int bytesLeidos=0;
                */
                ServerSocket socketServidor;
		
		try {
			// Abrimos el socket en modo pasivo, escuchando el en puerto indicado por "port"
			//////////////////////////////////////////////////
			socketServidor = new ServerSocket(port);
			//////////////////////////////////////////////////
			int n_ejecuciones = 0;
			// Mientras ... siempre!
			do {
				n_ejecuciones++;
				// Aceptamos una nueva conexión con accept()
				/////////////////////////////////////////////////
                                Socket socketConexion = null;
				try {
                                    
                                    socketConexion = socketServidor.accept();
                                    ProcesadorYodafy hebra = new ProcesadorYodafy(socketConexion,n_ejecuciones);
                                    System.out.println("Se ha recibido una petición");
                                    hebra.run();
                                
                                } catch (IOException e){
                                   System.err.println("No se pudo aceptar la conexión\n"); 
                                }
				//////////////////////////////////////////////////
				
				// Creamos un objeto de la clase ProcesadorYodafy, pasándole como 
				// argumento el nuevo socket, para que realice el procesamiento
				// Este esquema permite que se puedan usar hebras más fácilmente.
				ProcesadorYodafy procesador=new ProcesadorYodafy(socketConexion,n_ejecuciones);
				procesador.start();
				
			} while (true);
			
		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}

	}

}
