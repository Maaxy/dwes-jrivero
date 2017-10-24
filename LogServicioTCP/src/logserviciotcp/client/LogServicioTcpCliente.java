package logserviciotcp.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Clase cliente de la aplicación.
 * @author Jesús Rivero
 */
public class LogServicioTcpCliente {
    
    // Puerto de escucha
    private static final int PUERTO = 8000;
    
    // Dirección del servidor
    private static final String SERVER = "192.168.11.207";
    
    public static void main (String[] args) {
        try {
            Socket clientSocket = new Socket();
            InetSocketAddress addr = new InetSocketAddress(SERVER, PUERTO);
            clientSocket.connect(addr);
            
            // Se crea el flujo de salida hacia el servidor
            DataOutputStream flujoSalida = new DataOutputStream(
                    clientSocket.getOutputStream());

            // Se envía el mensaje hacia el servidor
            String mensaje = "Hola soy el CLIENTE con dirección "
                    + InetAddress.getLocalHost().getHostAddress();
            flujoSalida.writeUTF(mensaje);
            
            // Se informa del envío al cliente
            System.out.println("Mensaje enviado correctamente.");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
