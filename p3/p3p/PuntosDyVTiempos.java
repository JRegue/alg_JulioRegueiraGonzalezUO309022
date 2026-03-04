package p3p;

import java.util.Random;

public class PuntosDyVTiempos {

    public static double[][] generarPuntos(int n) {
        Random rand = new Random();
        double[][] puntos = new double[n][2];
        for (int i = 0; i < n; i++) {
            puntos[i][0] = Math.round(rand.nextDouble() * 100 * 1000000.0) / 1000000.0;
            puntos[i][1] = Math.round(rand.nextDouble() * 100 * 1000000.0) / 1000000.0;
        }
        return puntos;
    }

    public static void main(String[] args) {
        System.out.println("n\t\ttiempo");
        System.out.println("-------------------------");

        for (int n = 1024; n < Integer.MAX_VALUE; n *= 2) {
            double[][] puntos = generarPuntos(n);

            long t1 = System.currentTimeMillis();
            PuntosDyV.puntosMasCercanos(puntos); 
            long t2 = System.currentTimeMillis();
            
            long tiempo = (t2 - t1);

            System.out.println(n + "\t\t" + tiempo);
        }
    }
}