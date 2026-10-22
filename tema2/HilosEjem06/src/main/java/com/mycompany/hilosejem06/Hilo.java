/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hilosejem06;

/**
 *
 * @author daw2@iesmhp.local
 */
public class Hilo extends Thread {

    public Hilo() {

    }

    public Hilo(int prioridad) {

        this.setPriority(prioridad);
    }

    @Override
    public void run() {

        String strCadena = "";
                
        for (int i = 0; i < 2000; ++i) {
            
            strCadena += "A";
            Thread.yield();
        }
        
        System.out.println("Hilo de prioridad " + this.getPriority()
        + "termina ahora");
                
                
    }

}
