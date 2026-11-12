/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.ejemplo15;

/**
 *
 * @author dam206@iesmhp.local
 */
public class Ejemplo15 {

    public static void main(String[] args) {
        Cola cola = new Cola();
        Productor p = new Productor(cola, 1);
        Consumidor c = new Consumidor(cola, 1);
        p.start();
        c.start();
    }
}
