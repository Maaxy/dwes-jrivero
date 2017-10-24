package ip;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestIP_2 {

    private static void visualizarDatosIP(InetAddress dir) {
        System.out.println("\tMétodo getHostName(): " + dir.getHostName());
        System.out.println("\tMétodo getHostAddress(): " + dir.getHostAddress());
        System.out.println("\tMétodo getCanonicalHostName(): " + dir.getCanonicalHostName());
        System.out.println("\tMétodo toString(): " + dir.toString());
    }

    public static void main(String args[]) {
        InetAddress dir = null;
        System.out.println("======================================");
        System.out.println("SALIDA PARA LOCALHOST: ");

        try {
            // Datos de direccionamiento del local
            dir = InetAddress.getLocalHost();
            visualizarDatosIP(dir);

            System.out.println("\n\n======================================");
            System.out.println("SALIDA PARA UNA URL: ");

            // Datos de una dirección web concreta
            dir = InetAddress.getByName("www.google.es");
            visualizarDatosIP(dir);

        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
    }
}
