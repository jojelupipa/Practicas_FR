package fr_practica2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
//import java.io.InputStream;
//import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
public class YodafyServidorIterativo {

	public static void main(String[] args) {
	
		// Puerto de escucha
		int port=8989;
		// array de bytes auxiliar para recibir o enviar datos.
		
                DatagramSocket socketServidor;
		
		try {
			// Abrimos el socket en modo pasivo, escuchando el en puerto indicado por "port"
			//////////////////////////////////////////////////
			socketServidor = new DatagramSocket(port);
			//////////////////////////////////////////////////
			
                        // Mientras ... siempre!
			do {
				
				ProcesadorYodafy procesador=new ProcesadorYodafy(socketServidor);
				procesador.procesa();
				
			} while (true);
			
		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}

	}

}
