public class Trabajador extends Thread {

    private Recurso primerRecurso;
    private Recurso segundoRecurso;

    public Trabajador(
            String nombre,
            Recurso primerRecurso,
            Recurso segundoRecurso) {

        setName(nombre);
        this.primerRecurso = primerRecurso;
        this.segundoRecurso = segundoRecurso;
    }

    @Override
    public void run() {

        synchronized (primerRecurso) {

            System.out.println(
                    getName()
                    + " obtiene "
                    + primerRecurso.getNombre());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println(
                    getName()
                    + " espera "
                    + segundoRecurso.getNombre());

            synchronized (segundoRecurso) {

                System.out.println(
                        getName()
                        + " obtiene "
                        + segundoRecurso.getNombre());
            }
        }
    }
}