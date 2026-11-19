import java.util.concurrent.Semaphore;

public class ServidorWeb {

    private int cuenta;

    private final Semaphore semaforo;

    public ServidorWeb() {

        cuenta = 0;
        semaforo = new Semaphore(1);
    }

    public void incrementaCuenta() {

        try {

            semaforo.acquire();

            System.out.println(
                    "hilo "
                            + Thread.currentThread().getName()
                            + "----- Entra en Servidor");

            cuenta++;

            System.out.println(cuenta + " accesos");

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

        } finally {

            semaforo.release();
        }
    }
}