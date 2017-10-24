package logserviciotcp.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase servidor de la aplicación.
 * @author Jesús Rivero
 */
public class LogServicioTcp {
    
    // Puerto de escucha
    private static final int PUERTO = 8000;
    
    // Número máximo de conexiones permitidas por el servidor
    private static final int N_CONEXIONES = 5;
    
    // Archivo en el que se escribirá la cadena de texto
    private static final String RUTA_ARCHIVO = "./mensajes/mensajes.csv";
    
    public static void main (String[] args) {
        try {
            // Se crea el socket del servidor por el puerto establecido
            ServerSocket serverSocket = new ServerSocket(PUERTO);
            
            int nConexiones = 0;
            
            while (nConexiones < N_CONEXIONES) {
                // Creamos un objeto que nos permite saber la fecha actual
                Calendario calendario = new Calendario();
                
                // Montamos una cadena con la fecha actual
                String fecha = calendario.getDia() + "/" + calendario.getMes()
                        + "/" + calendario.getAnio();
                
                // Montamos una cadena con la hora actual
                String hora = calendario.getHora() + ":"
                        + calendario.getMinutos() + ":"
                        + calendario.getSegundos();
                
                System.out.println("SERVIDOR: Escuchando por el puerto "
                        + PUERTO + " ...");
                Socket clientSocket = serverSocket.accept();
                
                // Se crea el flujo de entrada del cliente
                DataInputStream flujoEntrada = new DataInputStream(
                        clientSocket.getInputStream());
                
                /* Se recibe el mensaje del cliente y se monta una cadena
                de caracteres con la fecha, la hora y el mensaje recibido */
                String mensaje = fecha + ";" + hora + ";" +
                        flujoEntrada.readUTF();
                System.out.println(mensaje);
                
                // Se inicializa la clase que escribe la cadena en el archivo
                EscribeArchivo escribeArchivo = new EscribeArchivo();
                
                // Se escribe la cadena en el archivo
                escribeArchivo.escribir(mensaje, RUTA_ARCHIVO);
                
                // Cierre de la conexión del cliente
                clientSocket.close();
                nConexiones++;
            }
            
            // Cierre de la conexión principal del servidor
            serverSocket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
