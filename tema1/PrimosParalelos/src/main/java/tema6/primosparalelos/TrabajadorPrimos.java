package tema6.primosparalelos;

import java.util.Scanner;

public class TrabajadorPrimos {

    public static void main(String[] args) {
               
        Scanner sc = new Scanner(System.in);

        long inicio = sc.nextLong();
        long fin = sc.nextLong();

        long tiempoInicio = System.currentTimeMillis();

        int cantidadPrimos = contarPrimos(inicio, fin);

        long tiempoFin = System.currentTimeMillis();

        long tiempoEmpleado = tiempoFin - tiempoInicio;

        System.out.println(cantidadPrimos);
        System.out.println(tiempoEmpleado);

        sc.close();
    }

    private static int contarPrimos(long inicio, long fin) {

        int contador = 0;

        for (long numero = inicio; numero <= fin; numero++) {

            if (esPrimo(numero)) {
                contador++;
            }
        }

        return contador;
    }

    private static boolean esPrimo(long numero) {

        if (numero < 2) {
            return false;
        }

        for (long i = 2; i * i <= numero; i++) {

            if (numero % i == 0) {
                return false;
            }
        }

        return true;
    }
}