package concurrencia;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class Hilo extends Thread {
    private SharedResource resource;
    
    public Hilo(SharedResource resource) {
        this.resource = resource;
    }
    
    public void run() {
        System.out.println("COMIENZA HILO ...");
        
        int aleatorio = new Random().nextInt(2);
        
        if (aleatorio == 0)
            this.resource.inc();
        else
            this.resource.dec();
        
        System.out.println("FINALIZA HILO ...");
    }
}
