/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hilosejem06;

/**
 *
 * @author daw2@iesmhp.local
 */
public class HilosEjem06 {

    public static void main(String[] args) {
        
        int contador = 50;
        
        //crear 3 arrays para prioridades
        Thread[] hiloMIN = crearArray(contador);
        Thread[] hiloNORM = crearArray(contador);
        Thread[] hiloMAX = crearArray(contador);
        
        //inicializar cada lista con una proridad
        inicializarHilo(hiloMIN, Thread.MIN_PRIORITY);
        inicializarHilo(hiloNORM, Thread.NORM_PRIORITY);
        inicializarHilo(hiloMAX, Thread.MAX_PRIORITY);
            
        
        
        System.out.println("""
                           Hilos en proceso, espera......
                           Los de mayor prioridadtienden a terminar antes....
                           """);
        
        startHilo(hiloMIN);
        startHilo(hiloNORM);
        startHilo(hiloMAX);
    }

    private static void startHilo(Thread[] hilo) {
        for (Thread hilo1 : hilo) {
           
            hilo1.start();
        }
    }
    
    private static Thread[] crearArray(int size){
    
        Thread[] lista = new Thread[size];
        return lista;
    }

    private static void inicializarHilo(Thread[] hilo, int prioridad) {
        for (int i = 0; i < hilo.length; i++) hilo[i] = new Hilo(prioridad);
    }

 
}
