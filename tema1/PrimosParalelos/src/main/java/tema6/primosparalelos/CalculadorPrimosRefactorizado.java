package tema6.primosparalelos;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Reparte el cálculo de números primos entre varios procesos independientes de
 * la JVM y combina sus resultados.
 */
public class CalculadorPrimosRefactorizado {

    public static void main(String[] args) {

        DatosEntrada datos = leerDatosEntrada();//recoger cuantos primos y cuantos procesos
        long tiempoInicioSistema = ObtenerInstanteTiempo();

        List<Intervalo> intervalos = repartirTrabajo(datos.numeroMaximo(), datos.numeroProcesos());
        mostrarIntervalos(intervalos);//muestra la división de bloques e intervalos

        List<Process> procesos = lanzarTrabajadores(intervalos);//empezar los procesos
        List<ResultadoProceso> resultados = recogerResultados(procesos);

        long tiempoTotalSistema = ObtenerInstanteTiempo() - tiempoInicioSistema;
        mostrarResultados(resultados, tiempoTotalSistema);
    }

    private static DatosEntrada leerDatosEntrada() {

        Scanner teclado = new Scanner(System.in);

        long numeroMaximo = leerNumeroMaximo(teclado);
        int numeroProcesos = leerNumeroProcesos(teclado);

        teclado.close();

        return new DatosEntrada(numeroMaximo, numeroProcesos);
    }

    private static long ObtenerInstanteTiempo() {
        return System.currentTimeMillis();
    }

    // ---------------------------------------------------------------
    // Lectura de datos de entrada
    // ---------------------------------------------------------------
    private static long leerNumeroMaximo(Scanner teclado) {
        System.out.print("Número máximo: ");
        return teclado.nextLong();
    }

    private static int leerNumeroProcesos(Scanner teclado) {
        System.out.print("Número de procesos: ");
        return teclado.nextInt();
    }

    // ---------------------------------------------------------------
    // Reparto del trabajo en intervalos
    // ---------------------------------------------------------------
    private static List<Intervalo> repartirTrabajo(long numeroMaximo, int numeroProcesos) {

        long tamanoBloque = numeroMaximo / numeroProcesos;//repartir de forma equitativa la cantidad de nº segun los procesos

        List<Intervalo> intervalos = new ArrayList<>();

        for (int i = 0; i < numeroProcesos; i++) {
            long inicio = calcularInicioBloque(i, tamanoBloque);
            long fin = calcularFinBloque(i, numeroProcesos, tamanoBloque, numeroMaximo);
            intervalos.add(new Intervalo(i + 1, inicio, fin));
        }

        return intervalos;
    }

    private static long calcularInicioBloque(int indice, long tamanoBloque) {
        return indice * tamanoBloque + 1;
    }

    private static long calcularFinBloque(int indice, int numeroProcesos, long tamanoBloque, long numeroMaximo) {

        return indice == numeroProcesos - 1 ? numeroMaximo : (indice + 1) * tamanoBloque;
    }

    // ---------------------------------------------------------------
    // Lanzamiento de los procesos trabajadores (lanza proceso iniciado con el intervalo que le corresponde)
    // ---------------------------------------------------------------
    private static List<Process> lanzarTrabajadores(List<Intervalo> intervalos) {

        List<Process> procesos = new ArrayList<>();

        for (Intervalo intervalo : intervalos) {
            Process proceso = lanzarTrabajador(intervalo);
            procesos.add(proceso);
        }

        return procesos;
    }

    private static Process lanzarTrabajador(Intervalo intervalo) {
        Process proceso = iniciarProcesoTrabajador();//guarda el proceso ya iniciado
        enviarIntervalo(proceso, intervalo);
        return proceso;
    }

    //crea el proceso y lo devuelve inicializado
    private static Process iniciarProcesoTrabajador() {
        try {
            //proceso que lanza la clase indicada en su propio espacio de memoria
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", obtenerClasspath(), "TrabajadorPrimos");
            return pb.start();
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo lanzar TrabajadorPrimos: " + e.getMessage(), e);
        }
    }

    private static String obtenerClasspath() {
        return System.getProperty("java.class.path");
    }
    
    //manda el inicio y el fin a la clase TrabajadorPrimo que está en el proceso ya metida

    private static void enviarIntervalo(Process proceso, Intervalo intervalo) {
        try (PrintWriter escritor = new PrintWriter(proceso.getOutputStream())) {
            escritor.println(intervalo.inicio());
            escritor.println(intervalo.fin());
        }
    }

    // ---------------------------------------------------------------
    // Recogida de resultados
    // ---------------------------------------------------------------
    private static List<ResultadoProceso> recogerResultados(List<Process> procesos) {

        List<ResultadoProceso> resultados = new ArrayList<>();

        for (int i = 0; i < procesos.size(); i++) {
            resultados.add(recogerResultado(i + 1, procesos.get(i)));
        }

        return resultados;
    }

    private static ResultadoProceso recogerResultado(int numeroProceso, Process proceso) {

        esperarFinalizacion(proceso);

        try {
            SalidaProceso salidaProceso = leerSalidaProceso(proceso);

            return errorSalidaProceso(salidaProceso) ? resultadoFallido(numeroProceso, proceso) : resultadoExitoso(numeroProceso, salidaProceso);

        } catch (IOException e) {
            return resultadoFallido(numeroProceso, proceso);
        }
    }

    private static ResultadoProceso resultadoExitoso(int numeroProceso, SalidaProceso salidaProceso) {
        int primos = Integer.parseInt(salidaProceso.lineaPrimos());
        long tiempoMs = Long.parseLong(salidaProceso.lineaTiempo());
        return new ResultadoProceso(numeroProceso, primos, tiempoMs, true);
    }

    private static SalidaProceso leerSalidaProceso(Process proceso) throws IOException {

        BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

        String lineaPrimos = lector.readLine();
        String lineaTiempo = lector.readLine();

        return new SalidaProceso(
                lineaPrimos,
                lineaTiempo);
    }

    private static boolean errorSalidaProceso(SalidaProceso salidaProceso) {
        return salidaProceso.lineaPrimos() == null || salidaProceso.lineaTiempo() == null;
    }

    private static void esperarFinalizacion(Process proceso) {
        try {
            proceso.waitFor();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrumpido esperando al proceso: " + e.getMessage(), e);
        }
    }

    private static ResultadoProceso resultadoFallido(int numeroProceso, Process proceso) {
        System.out.println("Exit code proceso " + numeroProceso
                + ": " + proceso.exitValue());
        System.out.println("Error del proceso hijo:\n" + leerErrorCompleto(proceso));
        return new ResultadoProceso(numeroProceso, 0, 0, false);
    }

    private static String leerErrorCompleto(Process proceso) {
        try {
            BufferedReader lector = new BufferedReader(
                    new InputStreamReader(proceso.getErrorStream()));

            StringBuilder sb = new StringBuilder();

            String linea;
            while ((linea = lector.readLine()) != null) {
                sb.append(linea).append('\n');
            }

            return sb.toString();

        } catch (IOException e) {
            return e.getMessage();
        }
    }

    // ---------------------------------------------------------------
    // Presentación de resultados
    // ---------------------------------------------------------------
    private static void mostrarIntervalos(List<Intervalo> intervalos) {
        for (Intervalo intervalo : intervalos) {
            System.out.println("Proceso " + intervalo.idIntervalo()
                    + " -> [" + intervalo.inicio() + " - " + intervalo.fin() + "]");
        }
    }

    private static void mostrarResultados(List<ResultadoProceso> resultados, long tiempoTotalSistema) {
        mostrarResultadoProcesos(resultados);
        mostrarResumenFinal(resultados, tiempoTotalSistema);
    }

    private static void mostrarResultadoProcesos(List<ResultadoProceso> resultados) {
        for (ResultadoProceso resultado : resultados) {
            if (resultado.exito()) {
                mostrarResultadoExitoso(resultado);
            }
        }
    }

    private static void mostrarResultadoExitoso(ResultadoProceso resultado) {
        System.out.println("Proceso " + resultado.numeroProceso() + " encontró " + resultado.primos() + " primos.");
        System.out.println("Tiempo proceso " + resultado.numeroProceso() + ": " + resultado.tiempoMs() + " ms");
    }

    private static void mostrarResumenFinal(List<ResultadoProceso> resultados, long tiempoTotalSistema) {

        int totalPrimos = sumarPrimos(resultados);

        System.out.println();
        System.out.println("TOTAL DE PRIMOS: " + totalPrimos);
        System.out.println("TIEMPO TOTAL DEL SISTEMA: " + tiempoTotalSistema + " ms");
    }

    private static int sumarPrimos(List<ResultadoProceso> resultados) {
        int total = 0;
        for (ResultadoProceso resultado : resultados) {
            total += resultado.primos();
        }
        return total;
    }

    // Clases portadoras de datos inmutables que se declaran con record
    //estas clases generan un constructor, getter y setters, toString, hashCode e equals.
    private record DatosEntrada(long numeroMaximo, int numeroProcesos) {

    }

    private record SalidaProceso(String lineaPrimos, String lineaTiempo) {

    }

    private record Intervalo(int idIntervalo, long inicio, long fin) {

    }

    private record ResultadoProceso(int numeroProceso, int primos, long tiempoMs, boolean exito) {

    }
}
