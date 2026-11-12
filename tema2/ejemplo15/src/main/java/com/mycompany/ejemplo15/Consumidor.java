/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejemplo15;

/**
 *
 * @author dam206@iesmhp.local
 */
public class Consumidor extends Thread{

    private Cola cola;
    private int n;

    public Consumidor(Cola c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() {
        int valor = 0;
        for (int i = 0; i < 5; i++) {
            valor = cola.get(); //recoge el numero
            System.out.println(i + "=>Consumidor : " + n + ", consume:" + valor);
        }
    }
}
