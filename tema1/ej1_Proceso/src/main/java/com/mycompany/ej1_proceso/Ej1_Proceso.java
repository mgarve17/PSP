/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ej1_proceso;

/**
 *
 * @author daw2
 */
public class Ej1_Proceso {

    public static void main(String[] args) {
         System.out.println ("EJECUTANDO CALCULADORA DE WINDOWS EN PROCESO HIJO:");
        
        try {
            //Runtime.getRuntime().exec("calc"); // Se crea proceso hijo y se le indica el comando a ejecutar
            new ProcessBuilder("gnome-calculator").start(); // Se crea proceso hijo y se le indica el comando a ejecutar
            System.out.println("Calculadora ejecutada correctamente.");
            
        }catch(Exception ex){
            System.err.println("Excepción de E/S!!");
            System.exit(-1);
        }
    }
}
