/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejemplo15;

/**
 *
 * @author dam206@iesmhp.local
 */
public class Cola {

    private int numero;
    private boolean disponible = false; //inicialmente cola vacia

    public synchronized int get() {
        while (disponible) { //hay numero en la cola?
            try {
                wait();
            } catch (InterruptedException e) {
                return (-1); //no hay numero disponible, cola vacia
            }
        }
        return numero;
    }

    public synchronized void put(int valor) {
        while (disponible) {
            try {
                wait();
            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

            }
        }
    }
}
