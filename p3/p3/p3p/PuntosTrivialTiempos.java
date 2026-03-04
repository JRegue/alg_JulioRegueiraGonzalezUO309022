package p3p;
import java.util.Random;

public class PuntosTrivialTiempos {

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
        long t1, t2;
        int repeticiones = Integer.parseInt(args[0]);

        for (int n = 1024; n < 268435456; n *= 2) {
            double[][] puntos;
            long t = 0;

            for (int i = 1; i <= repeticiones; i++) {
                puntos = generarPuntos(n);

                t1 = System.currentTimeMillis();
                PuntosTrivial.puntosMasCercanos(puntos);
                t2 = System.currentTimeMillis();
                t = t + (t2 - t1);
            }

            System.out.println("n=" + n + "\tTiempo=" + t + "\tRepeticiones=" + repeticiones);
        }
    }
}