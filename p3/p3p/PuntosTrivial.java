package p3p;
import java.io.IOException;

public class PuntosTrivial {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Error en los argumentos, solo pasar el nombre del fichero");
            return;
        }

        double[][] puntos = ParserPuntos.parsearPuntos(args[0]);
        puntosMasCercanos(puntos);
    }

    public static void puntosMasCercanos(double[][] puntos) {
        double minDist = Double.MAX_VALUE;
        double[] p1Min = null, p2Min = null;

        for (int i = 0; i < puntos.length; i++) {
            for (int j = i + 1; j < puntos.length; j++) {
                double dist = distancia(puntos[i], puntos[j]);
                if (dist < minDist) {
                    minDist = dist;
                    p1Min = puntos[i];
                    p2Min = puntos[j];
                }
            }
        }
        System.out.printf("PUNTOS MAS CERCANOS: [%f, %f] [%f, %f]%n",
                p1Min[0], p1Min[1], p2Min[0], p2Min[1]);
        System.out.printf("SU DISTANCIA MINIMA ES= %f%n", minDist);
    }

    private static double distancia(double[] p1, double[] p2) {
        double dx = p1[0] - p2[0];
        double dy = p1[1] - p2[1];
        return Math.sqrt(dx * dx + dy * dy);
    }
}