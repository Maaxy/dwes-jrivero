package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDP {

    private static final int PUERTO_SERVER = 6000;
    private static final int PUERTO_CLIENTE = 6001;
    private static final String SERVER = "localhost";
    // Longitud máxima del paquete (número de bytes)
    private static final int MAX_BUFFER = 100;
    // Definición del paquete datagrama y longitud máxima en bytes
    private static byte[] buffer = new byte[MAX_BUFFER];

    public static void main(String[] args) {
        try {
            // 1. Creación del socket datagram (puerto localhost)
            DatagramSocket socket = new DatagramSocket(PUERTO_CLIENTE);

            // 2. Preparación del datagrama
            System.out.println("CLIENTE: Enviando datagrama al puerto "
                    + PUERTO_SERVER + " desde " + PUERTO_CLIENTE + "...");
            InetAddress direccionServer = InetAddress.getByName(SERVER);
            String cadenaAEnviar = "Hola mundo";
            buffer = cadenaAEnviar.getBytes();

            // 3. Construyo y preparo el datagrama a enviar especificando IP
            // destino (tipo InetAddress) y PUERTO
            DatagramPacket mensaje = new DatagramPacket(buffer, buffer.length,
                    direccionServer, PUERTO_SERVER);

            // 4. Envío del paquete datagrama (envío mensaje)
            socket.send(mensaje);
            System.out.println("CLIENTE: Datagrama enviado");

            // 5. Cierre de conexión principal
            socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
