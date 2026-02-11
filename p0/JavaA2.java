package p0;
import java.util.ArrayList;

public class JavaA2 {

    /**
     * Algoritmo A2: Comprueba si m es primo probando divisores desde 2 hasta m-1.
     */
    public static boolean primoA2(int m) {
        for (int i = 2; i < m; i++) {
            if (m % i == 0) {
                return false; 
            }
        }
        return true; 
    }

    public static void listadoPrimos(int n) {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        int contPrimos = 0;

        for (int i = 2; i <= n; i++) {
            if (primoA2(i)) {
                lista.add(i);
                contPrimos++;
            }
        }

        System.out.println("Hay " + contPrimos + " primos hasta " + n);
    }

    public static void main(String arg[]) {

        System.out.println("TIEMPO EN JAVA DEL ALGORITMO A2");

        long t1, t2;

        for (int n = 5000; n <= 640000; n *= 2) {
            t1 = System.currentTimeMillis();

            listadoPrimos(n);

            t2 = System.currentTimeMillis();

            // System.out.println(t1+"///"+t2); 

            System.out.println("n=" + n + "**** tiempo = " + (t2 - t1) + " milisegundos \n");
        }
    } 

} 
