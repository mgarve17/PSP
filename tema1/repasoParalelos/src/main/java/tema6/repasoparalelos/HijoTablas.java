/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tema6.repasoparalelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author M
 */
public class HijoTablas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //escaner que recoge lo que le llega desde la otra clase (System.in)
        try ( Scanner escaner = new Scanner(System.in)) {
            int numeroBase = escaner.nextInt();
            int numeroLimite = escaner.nextInt();
            
            List<String> tabla = calcularTabla(numeroBase, numeroLimite);
            
            for (String string : tabla) {
                
                System.out.println(string);
            }
        }

    }
    
    //multiplciar numeroBase hasta llegar al numero limite
    private static List<String> calcularTabla(int numeroBase, int numeroLimite){
    
        List<String> tablaMultiplicar = new ArrayList();
        //multiplicar y mostrar
        for (int i = 0; i < numeroLimite; i++) {
            
           tablaMultiplicar.add(numeroBase + " X " + (i + 1) + " = " + (numeroBase * (i + 1)));
        }
        
        return tablaMultiplicar;
 
    }
    
}
