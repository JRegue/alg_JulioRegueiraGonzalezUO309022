package p11;

public class Vector6 {
    static int[] v;
    static int[] w;

    public static void main(String arg[]) {
        int repeticiones = Integer.parseInt(arg[0]);

        long t1, t2;

        System.out.println("repeticiones = " + repeticiones);
        System.out.println("n\tTiempo");
        
        for (int n = 10000; n <= 81920000; n *= 2) 
        {
            v = new int[n];
            Vector1.rellena(v);
            
            w = new int[n];
            Vector1.rellena(w);

            t1 = System.currentTimeMillis();

            // Repetimos el proceso a medir
            for (int r = 1; r <= repeticiones; r++) {
                Vector1.coincidencias1(v, w);
            }

            t2 = System.currentTimeMillis();
            System.out.println(n + "\t" + (t2 - t1));

        } // fin de for

        System.out.println("\nFin de la medicion de tiempos *****");

    } // fin de main
} // fin de clase