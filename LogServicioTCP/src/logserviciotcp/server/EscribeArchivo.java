/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logserviciotcp.server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase que escribe una cadena de texto en un archivo
 * @author Jesús Rivero Muñiz
 */
public class EscribeArchivo {
    
    private FileWriter fw;
    
    /**
     * Método que escribe una cadena de texto en un archivo
     * @param cadena Cadena de texto a escribir
     * @param rutaArchivo Nombre del archivo
     * @throws IOException 
     */
    public void escribir(String cadena, String rutaArchivo) throws IOException {
        try {
            // Se crea el directorio de los mensajes
            creaDirectorio("mensajes");
            
            // Se crea un flujo de salida de tipo Writer
            fw = new FileWriter(new File(rutaArchivo), true);
            
            // Se escribe la cadena de texto en la siguiente línea del archivo
            fw.write(cadena + "\r\n");
            
            // Se cierra el flujo de salida
            fw.close();
        } catch (IOException ex) {
            throw new IOException("Ha ocurrido un error al escribir el archivo");
        }
    }

    private void creaDirectorio(String nombreDir) {
        File dir = new File(nombreDir);
        if (!dir.exists())
            dir.mkdir();
    }
}
