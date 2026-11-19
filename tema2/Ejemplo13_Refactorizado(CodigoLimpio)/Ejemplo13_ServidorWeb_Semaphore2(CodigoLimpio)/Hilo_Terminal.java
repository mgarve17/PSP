public class Hilo_Terminal extends Thread {

    private ServidorWeb servidor;

    public Hilo_Terminal(ServidorWeb servidor) {
        this.servidor = servidor;
    }

    @Override
    public void run() {

        for (int i = 1; i <= 10; i++) {

            servidor.incrementaCuenta();

            Thread.yield();
        }
    }
}