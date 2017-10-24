package ip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ReadURL {

    public static void main(String args[]) {
        try {
            URL url = new URL("http://www.google.es");
            URLConnection urlConexion = url.openConnection();

            // Obtención del flujo
            InputStream inputstream = urlConexion.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(inputstream));

            // Lectura del flujo
            String line;

            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }

            // Cierre
            inputstream.close();
            input.close();
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
