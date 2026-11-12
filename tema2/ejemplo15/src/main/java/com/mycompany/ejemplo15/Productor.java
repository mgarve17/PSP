/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejemplo15;

import static java.lang.Thread.sleep;

/**
 *
 * @author dam206@iesmhp.local
 */
public class Productor extends Thread {
    
    private Cola cola;
    private int n;
    
    public Productor(Cola c, int n){
        for (int i = 0; i < 5; i++) {
            cola.put(i);
            System.out.println(i+"=> Prodcutor : " + n + ", produce: " + i);
            
            try{
            
                sleep(100);
            } catch (InterruptedException ex) {
                System.getLogger(Productor.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
}
