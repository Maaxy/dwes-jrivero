/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrencia;

/**
 *
 * @author Administrador
 */
public class App {
    
    public static void main (String a[]) {
        int MAX = 10;
        Thread t[] = new Hilo[MAX];
        SharedResource resource = new SharedResource();
        
        for (int i = 0; i < MAX; i++) {
            t[i] = new Hilo(resource);
            t[i].start();
        }
        
        // t.start();
    }
}
