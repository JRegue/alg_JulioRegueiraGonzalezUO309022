import java.io.PrintStream;
import java.io.OutputStream;

public class NReinasTiempos {

    public static void main(String[] args) {
        int repeticiones = Integer.parseInt(args[0]);

        int[] valoresN = {4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        System.out.println("N\t\tTiempo(ms)\tRepeticiones\tSoluciones");
        System.out.println("------------------------------------------------------------------");

        for (int n : valoresN) {
            long t = 0;
            int numSoluciones = 0;

            PrintStream originalOut = System.out;

            try {
                System.setOut(new PrintStream(new OutputStream() { public void write(int b) { } }));

                for (int i = 1; i <= repeticiones; i++) {
                    NReinas algoritmo = new NReinas();

                    long t1 = System.currentTimeMillis();
                    numSoluciones = algoritmo.resolverNReinas(n).size();
                    long t2 = System.currentTimeMillis();

                    t += (t2 - t1);
                }
            } catch (Exception e) {
                System.setOut(originalOut);
                System.out.println("N=" + n + "\terror durante la ejecución: " + e.getMessage());
                continue;
            } finally {
                System.setOut(originalOut);
            }

            System.out.println(n + "\t\t" + t + "\t\t" + repeticiones + "\t\t" + numSoluciones);
        }
    }
}