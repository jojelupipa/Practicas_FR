package fr_practica2;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class YodafyClienteUDP {

	public static void main(String[] args) {
		
		
		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;
                InetAddress direccion = null;
                DatagramPacket paquete;
                byte[] buffer = new byte[256];
		
		// Socket para la conexión UDP
		DatagramSocket socketServicio;
                
		
		try {
			// Creamos un socket que se conecte a "host" y "port":
			//////////////////////////////////////////////////////
			socketServicio = new DatagramSocket();
			//////////////////////////////////////////////////////			
                        
                        // Obtenemos la dirección
                        try {
                            direccion = InetAddress.getByName(host);
                        } catch (UnknownHostException ex) {             // Excepción en caso de lectura errónea de host< nbe
                            Logger.getLogger(YodafyClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                        
                        //Enviamos nuestro Datagrama
			String cadenaEnvio = new String("Al monte del volcan debes ir sin demora");
			
                        buffer = cadenaEnvio.getBytes();
                        paquete = new DatagramPacket(buffer, buffer.length, direccion, port);
                        try {
                            socketServicio.send(paquete);
                        } catch (IOException ex) {
                            Logger.getLogger(YodafyClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        
                        // Esperamos el recibimiento
			
                        paquete = new DatagramPacket(buffer, buffer.length);
                        try {
                            socketServicio.receive(paquete);
                        } catch (IOException ex) {
                            Logger.getLogger(YodafyClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
                        byte [] cadenaLeida = paquete.getData();

                        
                        
			//////////////////////////////////////////////////////
                        System.out.println("Recibido: ");
                        for (int i = 0; i < cadenaLeida.length; i++) {
                            System.out.print((char) cadenaLeida[i]);
                        }

                        // Cerramos el socket al finalizar
                        socketServicio.close(); 
			
			
			// Excepciones:
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
