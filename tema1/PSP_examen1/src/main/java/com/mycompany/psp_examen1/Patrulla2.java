package com.mycompany.psp_examen1;


import java.util.Random;
import java.util.Scanner;

public class Patrulla2 {

    public static void main(String[] args) throws Exception {

        // Recibir los datos por teclado
        Scanner escaner = new Scanner(System.in);

        String id = escaner.nextLine();

        // Registrar el tiempo de inicio
        long tiempoInicio = System.currentTimeMillis();

        // Simular la intervención: entre 2 y 8 segundos
        Random numeroAleatorio = new Random();
        long tiempoEspera = numeroAleatorio.nextLong(2, 9);

        Thread.sleep(tiempoEspera);

        // Registrar el tiempo de finalización
        long tiempoFin = System.currentTimeMillis();

        // Calcular el tiempo empleado
        long tiempoTotal = tiempoFin - tiempoInicio;

        // Comunicar el resultado
        String cadena = id + ";" + "COMPLETADA" + ";" + tiempoTotal; //falta añadir el estado
        System.out.println(cadena);

        
    }
}
