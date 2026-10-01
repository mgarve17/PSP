package com.mycompany.psp_3;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class PMPConsola001 {

    public static void main(String[] args) {
        ProcessBuilder procesoBuilder = new ProcessBuilder("ls", "-l");
        try {
           
            System.out.println("Lanzando el comando 'ls -l' ...");
            Process proceso = procesoBuilder.start();
            
            System.out.println("Esperando a que termine la ejecución");
            int resultado = proceso.waitFor();
            
            InputStream input = (resultado == 0) ? proceso.getInputStream() : proceso.getErrorStream();
            
            if (resultado == 0) {
                
                System.out.println("el comando 'ls' se lanzó con éxito: ");
            } else {
            
                System.out.println("ERROR en la ejecución del comando 'ls': ");
            }
            
            try(BufferedReader br = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))){
            
                String linea;
                while((linea = br.readLine()) != null){
                
                    System.out.println(linea);
                }
            }
            
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (InterruptedException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
}
