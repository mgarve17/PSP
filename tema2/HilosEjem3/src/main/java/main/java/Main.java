package main.java;



public class Main {
    public static void main(String[] args) {
        
        UD2Ej03 miRunnable = new UD2Ej03();
        Thread hilo1 = new Thread(miRunnable);
        hilo1.start();

        
    }
}