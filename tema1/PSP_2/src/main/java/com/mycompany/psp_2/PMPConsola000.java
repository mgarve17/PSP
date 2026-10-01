package com.mycompany.psp_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class PMPConsola000 {

    public static void main(String[] args) {

        String comandoNotepad = "xed";//notepad

        Path rutaExcel = Path.of("libreoffice");//ruta del libre ofice supogno

        try {

            //lanzar el editor
            System.out.println("Lanzando el editor de texto" + comandoNotepad);
            ProcessBuilder processB1 = new ProcessBuilder(comandoNotepad);
            Process proceso1 = processB1.start();//iniciar proceso del Process Builder

            System.out.println("El editor esta corriendo pero el programa sigue");
            int resultadoP1 = proceso1.waitFor();

            try (BufferedReader br1 = new BufferedReader(new InputStreamReader(
                    (resultadoP1 == 0) ? proceso1.getInputStream() : proceso1.getErrorStream(),
                    StandardCharsets.UTF_8))) {
                
                String linea;
                while ((linea = br1.readLine()) != null) {
                    System.out.println("[Editor]: " + linea);
                }

            } 
            
            System.out.println("Lanzando libreOffice");
            
            ProcessBuilder processB2 = new ProcessBuilder(rutaExcel.toString(), "--calc");
            Process proceso2 = processB2.start();
            
            System.out.println("Esperando a que termine la ejecución de libreOffice");
            int resultadoP2 = proceso2.waitFor();
            
            if (resultadoP2 == 0) {
                
                System.out.println("El proceso ha terminado correctamente");
            } else {
            
                System.out.println("ERROr en la ejecución de libre Office");
            }
             try (BufferedReader br2 = new BufferedReader(new InputStreamReader(
                    (resultadoP1 == 0) ? proceso1.getInputStream() : proceso1.getErrorStream(),
                    StandardCharsets.UTF_8))) {
                
                String linea;
                while ((linea = br2.readLine()) != null) {
                    System.out.println("[Calc]: " + linea);
                }

            }
            
            
            
            }catch (IOException ex) {
               System.err.println("Error de E/S: " + ex.getMessage());
            } catch (InterruptedException ex) {
             System.err.println("Proceso interrumpido" + ex.getMessage());
             Thread.currentThread().interrupt();
        }
            

        } 

    }


