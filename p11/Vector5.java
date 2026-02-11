package p11;

/** * Clase para medir tiempos del metodo maximo()
 */
public class Vector5 {
    static int[] v;

    public static void main(String arg[]) {
        int repeticiones = Integer.parseInt(arg[0]); // veces que se repite la operacion

        long t1, t2;
        int[] m = new int[2]; // Array auxiliar necesario para el metodo maximo

        System.out.println("repeticiones = " + repeticiones);
        System.out.println("n\tTiempo");
        
        for (int n = 10000; n <= 81920000; n *= 2) // n se va duplicando
        {
            v = new int[n];
            Vector1.rellena(v);

            t1 = System.currentTimeMillis();

            // Repetimos el proceso a medir
            for (int r = 1; r <= repeticiones; r++) {
                Vector1.maximo(v, m);
            }

            t2 = System.currentTimeMillis();
            System.out.println(n + "\t" + (t2 - t1));

        } // fin de for

        System.out.println("\nFin de la medicion de tiempos *****");

    } // fin de main
} // fin de clase