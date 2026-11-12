public class Main {

    public static void main(String[] args) {

        Recurso impresora = new Recurso("Impresora");
        Recurso escaner = new Recurso("Escaner");

        Thread trabajador1 = new Trabajador( "Trabajador 1", impresora, escaner);
        Thread trabajador2 = new Trabajador( "Trabajador 2", impresora, escaner);

        trabajador1.start();
        trabajador2.start();
    }
}