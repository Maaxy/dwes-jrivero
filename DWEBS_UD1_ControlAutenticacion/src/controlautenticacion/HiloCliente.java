/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlautenticacion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class HiloCliente extends Thread {

    private Socket clientSocket = null;
    private ObjectOutputStream flujoSalida;
    private static final int LONGITUD_CONTRASENIA = 10; // Longitud máxima de
    // las contraseñas de
    // usuarios

    /* FUNCIÓN DE GENERACIÓN DE CONTRASEÑA PARA LOS NUEVOS USUARIOS */
    public static String generarPassword() {
        char c;
        String password = "";

        for (int i = 0; i < LONGITUD_CONTRASENIA; i++) {
            c = (char) (aleatorio(65, 126));
            password += c;
        }

        return password;
    }
    
    private static int aleatorio(int min, int max) {
        Random r = new Random();
        return min + r.nextInt(max - min + 1); // Entre min y max -> [min,max];
    }

    public HiloCliente(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            // 1. Crear flujo de ENTRADA del cliente (recepción del OBJETO)
            ObjectInputStream flujoEntrada = new ObjectInputStream(
                    clientSocket.getInputStream());
            Usuario user = (Usuario) flujoEntrada.readObject();
            
            // 2. Crear flujo de SALIDA hacia el cliente (envío del OBJETO)
            flujoSalida = new ObjectOutputStream(
                    clientSocket.getOutputStream());
            
            // 3. ENVIO del OBJETO hacia el cliente
            // Se genera un nuevo password para el usuario
            user.setPassword(generarPassword());
            System.out
                    .println("SERVIDOR: Un cliente se ha conectado y sus nuevas credenciales de acceso son: "
                    + user.getUsername() + " - " + user.getPassword());
            flujoSalida.writeObject(user);

            // 4. Crear flujo de ENTRADA del cliente (recepción del OBJETO)
            flujoEntrada = new ObjectInputStream(
                    clientSocket.getInputStream());

            // 5. RECEPCIÓN del OBJETO del cliente
            Usuario newUser = (Usuario) flujoEntrada.readObject();
            System.out.println("SERVIDOR: NUEVAS CREDENCIALES DEL USUARIO: "
                    + newUser.getUsername() + " - " + newUser.getPassword()
                    + " - " + newUser.getIp());

            // 6. Cierre de flujos y conexión del cliente (en orden)
            flujoEntrada.close();
            flujoSalida.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}