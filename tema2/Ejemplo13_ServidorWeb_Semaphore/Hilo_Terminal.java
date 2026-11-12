import java.util.concurrent.Semaphore;
public class Hilo_Terminal extends Thread {
    private ServidorWeb servidor;
    private Semaphore semaforo;
    public Hilo_Terminal(ServidorWeb s, Semaphore se) { this.servidor = s; this.semaforo = se; }
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try { semaforo.acquire(); }
            catch (InterruptedException ex) { Thread.currentThread().interrupt(); return; }
            try { servidor.incrementaCuenta(); }
            finally { semaforo.release(); }
            Thread.yield();
        }
    }
}
