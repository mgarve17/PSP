import java.util.concurrent.Semaphore;
public class Main {
    public static void main(String[] args) {
        Semaphore semaforo = new Semaphore(1);
        ServidorWeb servidor = new ServidorWeb();
        Hilo_Terminal hterminal1 = new Hilo_Terminal(servidor, semaforo);
        Hilo_Terminal hterminal2 = new Hilo_Terminal(servidor, semaforo);
        Hilo_Terminal hterminal3 = new Hilo_Terminal(servidor, semaforo);
        Hilo_Terminal hterminal4 = new Hilo_Terminal(servidor, semaforo);
        hterminal1.start(); hterminal2.start(); hterminal3.start(); hterminal4.start();
    }
}
