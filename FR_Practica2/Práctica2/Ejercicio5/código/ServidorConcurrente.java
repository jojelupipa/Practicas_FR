package ejercicio5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorConcurrente {

    public static void main(String[] args) {
	
	// Puerto de escucha
	int port=8989;
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
                    ProcesadorBiblioteca hebra = new ProcesadorBiblioteca(socketConexion,n_ejecuciones);
                    System.out.println("Se ha recibido una petición");
                    hebra.run();
                                
                } catch (IOException e){
                    System.err.println("No se pudo aceptar la conexión\n"); 
                }
		//////////////////////////////////////////////////
				
		// Creamos un objeto de la clase ProcesadorYodafy, pasándole como 
		// argumento el nuevo socket, para que realice el procesamiento
		// Este esquema permite que se puedan usar hebras más fácilmente.
		ProcesadorBiblioteca procesador=new ProcesadorBiblioteca(socketConexion,n_ejecuciones);
		procesador.start();
				
            } while (true);
			
        } catch (IOException e) {
            System.err.println("Error al escuchar en el puerto "+port);
	}

    }

}