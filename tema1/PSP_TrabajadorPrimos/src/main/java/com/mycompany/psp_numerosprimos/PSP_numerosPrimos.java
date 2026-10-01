/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.psp_numerosprimos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author daw2
 */
public class PSP_numerosPrimos {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {

            //recoger los valores
            System.out.println("Valor inicial del intervalo");
            long inicio = Long.parseLong(br.readLine().trim());

            System.out.println("Valor final del intervalo");
            long fin = Long.parseLong(br.readLine().trim());

            long tiempoInicial = System.currentTimeMillis();//pillar el tiempo actual en milisegundos

            //calcular primos en el intervalo
            long contadorPrimos = 0;

            //bucle que recorre de un valor a otro
            for (long i = inicio; i <= fin; i++) {
                if(esPrimo(i)){
                
                    contadorPrimos++;
                }
            }
            
            long tiempoFin = System.currentTimeMillis();//tiempo despues de ejecturar el metodo
            
            System.out.println("Primos encontrados: " + contadorPrimos);
            System.out.println("Tiempo empleado: " + (tiempoFin - tiempoInicial) + "ms");

        } catch (IOException ex) {
            System.getLogger(PSP_numerosPrimos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    private static boolean esPrimo(long numero) {
        boolean esPrimo = numero >= 2;//true si el numero es mayor o igual a 2
        long i = 2;

        while (esPrimo && i * i <= numero) {//evaluar que quedan divisores por comprobar y que el nº es primo
            if (numero % i == 0) {
                esPrimo = false;
            }
            i++;
        }

        return esPrimo;
    }
}

