/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlautenticacion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorAltaUsuarios {

    private static final int PUERTO = 6000;

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
        try {
            ServerSocket serverSocket = new ServerSocket(PUERTO);
            
            while (true) {
                System.out.println("SERVIDOR: Escuchando por el puerto " + PUERTO
                        + " ...");
                Socket clientSocket = serverSocket.accept();

                // 1. Se crea el hilo dedicado al cliente
                HiloCliente hilo = new HiloCliente(clientSocket);

                // 2. El hilo pasa a estado RUNNABLE
                hilo.start();
            }

            // 7. Cierre de conexión principal del servidor
            // serverSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}