package p3p;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class PuntosDyV {

    private static double minDistGlobal = Double.MAX_VALUE;
    private static double[] p1MinGlobal = null;
    private static double[] p2MinGlobal = null;

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Error en los argumentos, solo pasar el nombre del fichero");
            return;
        }

        double[][] puntos = ParserPuntos.parsearPuntos(args[0]);
        puntosMasCercanos(puntos);
        
        System.out.printf("PUNTOS MAS CERCANOS: [%f, %f] [%f, %f]%n",
                p1MinGlobal[0], p1MinGlobal[1], p2MinGlobal[0], p2MinGlobal[1]);
        System.out.printf("SU DISTANCIA MINIMA ES= %f%n", minDistGlobal);
    }

    public static void puntosMasCercanos(double[][] puntos) {
        minDistGlobal = Double.MAX_VALUE;
        p1MinGlobal = null;
        p2MinGlobal = null;

        double[][] puntosX = puntos.clone();
        Arrays.sort(puntosX, new Comparator<double[]>() {
            public int compare(double[] a, double[] b) {
                return Double.compare(a[0], b[0]);
            }
        });
        
        recursivoDyV(puntosX, 0, puntosX.length - 1);
    }

    private static double recursivoDyV(double[][] puntosX, int inicio, int fin) {
        int n = fin - inicio + 1;

        if (n <= 3) {
            return fuerzaBruta(puntosX, inicio, fin);
        }
        
        int medio = inicio + (fin - inicio) / 2;
        double[] puntoMedio = puntosX[medio];

        double distIzq = recursivoDyV(puntosX, inicio, medio);
        double distDer = recursivoDyV(puntosX, medio + 1, fin);

        double minLocal = Math.min(distIzq, distDer);

        double[][] franja = new double[n][];
        int j = 0;
        for (int i = inicio; i <= fin; i++) {
            if (Math.abs(puntosX[i][0] - puntoMedio[0]) < minLocal) {
                franja[j++] = puntosX[i];
            }
        }

        double[][] franjaValida = Arrays.copyOf(franja, j);
        Arrays.sort(franjaValida, new Comparator<double[]>() {
            public int compare(double[] a, double[] b) {
                return Double.compare(a[1], b[1]);
            }
        });

        for (int i = 0; i < j; i++) {
            for (int k = i + 1; k < j && (franjaValida[k][1] - franjaValida[i][1]) < minLocal; k++) {
                double d = distancia(franjaValida[i], franjaValida[k]);
                if (d < minLocal) {
                    minLocal = d;
                }
                actualizarGlobal(franjaValida[i], franjaValida[k], d);
            }
        }

        return minLocal;
    }

    private static double fuerzaBruta(double[][] puntos, int inicio, int fin) {
        double minB = Double.MAX_VALUE;

        for (int i = inicio; i <= fin; i++) {
            for (int j = i + 1; j <= fin; j++) {
                double dist = distancia(puntos[i], puntos[j]);
                if (dist < minB) {
                    minB = dist;
                }
                actualizarGlobal(puntos[i], puntos[j], dist);
            }
        }
        return minB;
    }

    private static void actualizarGlobal(double[] p1, double[] p2, double dist) {
        if (dist < minDistGlobal) {
            minDistGlobal = dist;
            p1MinGlobal = p1;
            p2MinGlobal = p2;
        }
    }

    private static double distancia(double[] p1, double[] p2) {
        double dx = p1[0] - p2[0];
        double dy = p1[1] - p2[1];
        return Math.sqrt(dx * dx + dy * dy);
    }
}