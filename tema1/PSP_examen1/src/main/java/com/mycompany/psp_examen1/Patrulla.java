package com.mycompany.psp_examen1;


import java.util.Random;
import java.util.Scanner;

public class Patrulla {

    public static void main(String[] args) throws Exception {

        // Recibir los datos por teclado
        Scanner escaner = new Scanner(System.in);

        String id = escaner.nextLine();

        long tiempoInicio = obtenerTiempoInicial();

        long tiempoEspera = tiempoEsperaAleatorio();

        Thread.sleep(tiempoEspera);

        long tiempoFin = tiempoFinal();

        // Calcular el tiempo empleado
        long tiempoTotal = tiempoFin - tiempoInicio;

        // Comunicar el resultado
        String cadena = id + ";" + "COMPLETADA" + ";" + tiempoTotal; //falta añadir el estado
        System.out.println(cadena);

        
    }

    private static long tiempoFinal() {
        // Registrar el tiempo de finalización
        long tiempoFin = System.currentTimeMillis();
        return tiempoFin;
    }

    private static long tiempoEsperaAleatorio() {
        // Simular la intervención: entre 2 y 8 segundos
        Random numeroAleatorio = new Random();
        long tiempoEspera = numeroAleatorio.nextLong(2, 9);
        return tiempoEspera;
    }

    private static long obtenerTiempoInicial() {
        // Registrar el tiempo de inicio
        long tiempoInicio = tiempoFinal();
        return tiempoInicio;
    }
}
