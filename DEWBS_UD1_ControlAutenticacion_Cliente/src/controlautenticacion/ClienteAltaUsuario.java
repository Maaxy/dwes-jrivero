package controlautenticacion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteAltaUsuario {

    private static final int PUERTO = 6000;
    private static final String SERVER = "localhost";

    public static void main(String[] args) throws ClassNotFoundException {
        try {
            // Flujo de entrada para solicitar la nueva contraseña de usuario
            // por teclado
            Scanner sc = new Scanner(System.in);

            Socket clientSocket = new Socket();
            InetSocketAddress addr = new InetSocketAddress(SERVER, PUERTO);
            clientSocket.connect(addr);
            
            // 1. Crear flujo de SALIDA hacia el servidor (envío del OBJETO)
            ObjectOutputStream flujoSalida = new ObjectOutputStream(
                    clientSocket.getOutputStream());
            System.out.println("Introduzca el nombre de usuario :");
            String newUsername= sc.nextLine();
            // Se genera un usuario con una contraseña temporal junto con el nombre propocionado
            Usuario newUser = new Usuario(newUsername, "secreta");
            
            flujoSalida.writeObject(newUser);
            
            // 2. Crear flujo de ENTRADA al servidor (recepción del OBJETO)
            ObjectInputStream flujoEntrada = new ObjectInputStream(
                    clientSocket.getInputStream());

            // 3. RECEPCION del OBJETO del servidor
            newUser = (Usuario) flujoEntrada.readObject();
            System.out
                    .println("CLIENTE: Recibiendo del SERVIDOR las nuevas credenciales de acceso: "
                    + newUser.getUsername()
                    + " - "
                    + newUser.getPassword());

            // 4. Crear flujo de SALIDA hacia el servidor (envío del OBJETO)
            flujoSalida = new ObjectOutputStream(
                    clientSocket.getOutputStream());

            // 5. ENVIO del OBJETO hacia el servidor
            System.out.println("Introduce la nueva contraseña del usuario "
                    + newUser.getUsername() + ":");
            String newPassword = sc.nextLine();
            // Se modifican los valores del objeto
            newUser.setPassword(newPassword);
            newUser.setIp(InetAddress.getLocalHost().getHostAddress());
            // Se escribe el objeto
            flujoSalida.writeObject(newUser);

            // 6. Cierre de flujos de datos y conexión (en orden)
            sc.close();
            flujoSalida.close();
            flujoEntrada.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}