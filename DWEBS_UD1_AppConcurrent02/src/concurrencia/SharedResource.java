/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrencia;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class SharedResource {
    public synchronized void inc() {
        m("INC ");
    }
    
    public synchronized void dec() {
        m("DEC ");
    }
    
    private void m(String fr) {
        try {
            int aleatorio = new Random().nextInt(10) + 1;
            System.out.println(fr + aleatorio + " seg.");
            Thread.sleep(aleatorio * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SharedResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
