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

    public int get() {
        if (disponible) { //hay numero en la cola?
            disponible = false; //se pone cola vacia
            return numero; //se devuelve
        }
        return (-1); //no hay numero disponible, cola vacia
    }

    public void put(int valor) {
        numero = valor; //coloca valor en la cola
        disponible = true; //disponible para consumir, cola llena
    }
}
