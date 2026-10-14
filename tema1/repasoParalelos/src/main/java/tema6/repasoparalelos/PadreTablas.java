/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package tema6.repasoparalelos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M
 */
public class PadreTablas {

    public static void main(String[] args) {

        System.out.println("Nº base");
        int numeroBase = new Scanner(System.in).nextInt();
        System.out.println("Nº límite");
        int numeroLimite = new Scanner(System.in).nextInt();

        DatosTabla datos = new DatosTabla(numeroBase, numeroLimite);

        //crear un objeto proceso que recibe el proceso ya iniciado por el metodo 
        Process proceso = iniciarProceso();

        //enviar el proceso a la otra clase con los datos que tiene que manejar
        enviarProceso(proceso, datos);
        
        //imprimir los datos recogidos
        List<String> tablaMultiplicar = recogerSalida(proceso);
        
        for (String string : tablaMultiplicar) {
            
            System.out.println(string);
        }
        

    }

    //metodo para iniciar un proceso en la clase hija con el comando definido dentro
    private static Process iniciarProceso() {

        try {

            ProcessBuilder pb = new ProcessBuilder("java", "-cp", classPath(), "tema6.repasoparalelos.HijoTablas");
            return pb.start();//devuelve el proceso ya iniciado

        } catch (IOException e) {

            throw new IllegalStateException("no pudo lanzarse HijosTablas" + e.getMessage());
        }
    }

    //metodo que manda el proceso a la otra clase para que trabaje con ella
    private static void enviarProceso(Process proceso, DatosTabla datos) {

        //este outputstream pilla el escaner de la clase hijo y le escribe los atributos de datos
        try (PrintWriter escritor = new PrintWriter(proceso.getOutputStream())) {

            escritor.println(datos.numeroBase());
            escritor.println(datos.numeroLimite());
        }
    }

    //esperar salida
    private static List<String> recogerSalida(Process proceso) {

        List<String> tabla = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {

            String linea;
            //añadir los resultados de la multiplicacion linea por linea
            while ((linea = lector.readLine()) != null) {

                tabla.add(linea);
            }

        } catch (IOException ex) {
            Logger.getLogger(PadreTablas.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tabla;
    }

    private static String classPath() {

        return System.getProperty("java.class.path");
    }

    private record DatosTabla(int numeroBase, int numeroLimite) {

    }

}
