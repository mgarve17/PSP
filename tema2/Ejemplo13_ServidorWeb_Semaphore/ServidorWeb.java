public class ServidorWeb {
    private int cuenta;
    public ServidorWeb() { cuenta = 0; }
    public void incrementaCuenta() {
        System.out.println("hilo " + Thread.currentThread().getName() + "----- Entra en Servidor");
        cuenta++;
        System.out.println(cuenta + " accesos");
    }
}
