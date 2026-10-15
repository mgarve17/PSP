/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.psp_examen1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daw2
 */
public class CentralEmergenciasParalelo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        //crear incidencias
        List<Incidencia> incidencias = generarIncidencias(tamanoDeListas());

        //crear los procesos segun el nº de incidencias
        List<Process> procesos = generarProcesos(tamanoDeListas());
        //generar patrullas
        List<Patrulla> patrullas = generarPatrullas(tamanoDeListas(), incidencias, procesos);
        //mandar los procesos al Hijo 
        for (Patrulla patrulla : patrullas) {
            
            mandarProceso(patrulla.proceso(), patrulla.incidencia().id());
        }
        //recoger resultados
        List<String> resultados = new ArrayList<>();
        for (String resultado : resultados) {
            
            //resultado = recogerResultado(proceso);
        }
    }
    
    //tamaño fijo para las incidencias y patrullas
    private static int tamanoDeListas(){
    
        return 5;
    }

    //crear patrullas con un id, la incidencia y el proceso que les corresponde
    private static List<Patrulla> generarPatrullas(int size, List<Incidencia> incidencias, List<Process> procesos) {

        List<Patrulla> patrullas = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            
            patrullas.add(new Patrulla((i +1), incidencias.get(i), procesos.get(i)));
        }
        return patrullas;
    }

    //metodo para generar 5 incidencias pendientes con su id
    private static List<Incidencia> generarIncidencias(int tamano) {

        List<Incidencia> incidencias = new ArrayList<>();

        for (int i = 0; i < tamano; i++) {
            //crear incidencias pendientes
            incidencias.add(new Incidencia("INC-" + (i + 1), "PENDIENTE", 0));
        }

        return incidencias;
    }

    //metodo para generar patrullas segun el nº de incidencias
    private static List<Process> generarProcesos(int numeroIncidencias) {

        List<Process> procesos = new ArrayList<>();

        for (int i = 0; i < numeroIncidencias; i++) {

            procesos.add(lanzarProceso());//almacenar procesos ya iniciados
        }

        return procesos;
    }

    //lanza el proceso iniciado con el comando que llama a la clase hija
    private static Process lanzarProceso() {

        try {
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", classPath(), "Patrulla");
            return pb.start();

        } catch (IOException ex) {

            throw new IllegalStateException("no se pudo lanzar el proceso " + ex.getMessage());
        }
    }

    private static String classPath() {

        return System.getProperty("java.class.path");
    }

    //mandar al proceso hijo los datos de la incidencia
    private static void mandarProceso(Process proceso, String idIncidencia) {

        try (PrintWriter escritor = new PrintWriter(proceso.getOutputStream())) {

            escritor.println(idIncidencia);
        }
    }

    //metodo que deberia devolver la línea leida desde la clase hija QUITAR EL VOID LUEGO
    private static String recogerResultado(Process proceso) {

        String linea= "";
        try (BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {

            
            while (lector.readLine() != null) {

               linea = lector.readLine();
            }
            
        } catch (IOException ex) {

            throw new IllegalStateException("no se pudo leer el proceso");
        }
        
        return linea;
    }

    //clase incidencia con su id, el estado y el tiempo que tardó en resolverse
    private record Incidencia(String id, String estado, long tiempoResolucion) {}

//    //clase Patrulla para guardar su id y la incidencia que resuelve
    private record Patrulla(int id, Incidencia incidencia, Process proceso) {}
}
