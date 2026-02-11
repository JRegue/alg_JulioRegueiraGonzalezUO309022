package p0;

import java.util.ArrayList;

public class JavaA3 {

    /**
     * Algoritmo A3:
     * Devuelve si m es primo o no.
     * Optimización: Recorre solo hasta la mitad (m/2).
     */
    public static boolean primoA3(int m) {
        for (int i = 2; i <= m / 2; i++) {
            if (m % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calcula y escribe todos los primos hasta n.
     * Optimización: Trata el 2 aparte y solo recorre impares.
     */
    public static void listadoPrimos(int n) {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        
        lista.add(2); 
        int contPrimos = 1;

        for (int i = 3; i <= n; i += 2) {
            if (primoA3(i)) {
                lista.add(i);
                contPrimos++;
            }
        }

        System.out.println("Hay " + contPrimos + " primos hasta " + n);
    }

    public static void main(String arg[]) {

        System.out.println("TIEMPO EN JAVA DEL ALGORITMO A3");

        long t1, t2; 

        for (int n = 5000; n <= 640000; n *= 2) {
            t1 = System.currentTimeMillis(); 

            listadoPrimos(n);

            t2 = System.currentTimeMillis();

            System.out.println(t1 + "///" + t2);
            System.out.println("n=" + n + "**** tiempo = " + (t2 - t1) + " milisegundos \n");
        }
    } 
} 