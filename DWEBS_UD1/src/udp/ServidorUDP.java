package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {

    private static final int PUERTO = 6000;
    // Longitud máxima del paquete (número de bytes)
    private static final int MAX_BUFFER = 100;
    // Definición del paquete datagrama y longitud máxima en bytes
    private static byte[] buffer = new byte[MAX_BUFFER];

    public static void main(String[] args) {
        try {
            // 1. Creación del socket datagram (puerto localhost)
            DatagramSocket socket = new DatagramSocket(PUERTO);

            // 2. Construyo y preparo el datagrama (buffer máximo)
            System.out.println("SERVIDOR: Esperando datagrama por el puerto "
                    + PUERTO + " ...");
            DatagramPacket mensaje = new DatagramPacket(buffer, buffer.length);

            // 3. Espera de recepción del paquete datagrama (recepción mensaje)
            socket.receive(mensaje);
            int numeroBytesRecibidos = mensaje.getLength(); // Longitud recibida
            // Se codifica de bytes a String
            String mensajeRecibido = new String(mensaje.getData());
            System.out.println("MENSAJE RECIBIDO: " + mensajeRecibido
                    + "\nLONGITUD: " + numeroBytesRecibidos + "\nIP ORIGEN: "
                    + mensaje.getAddress().getHostAddress());

            // 4. Cierre de conexión principal del servidor
            socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
