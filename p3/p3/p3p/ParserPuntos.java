package p3p;
import java.io.*;

public class ParserPuntos {

    public static double[][] parsearPuntos(String rutaArchivo) throws IOException {
        int n;
        double[][] puntos;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            n = Integer.parseInt(br.readLine().trim()); 
            puntos = new double[n][2];

            for (int i = 0; i < n; i++) {
                String[] partes = br.readLine().trim().split(",");
                puntos[i][0] = Double.parseDouble(partes[0]);
                puntos[i][1] = Double.parseDouble(partes[1]);
            }
        }

        return puntos;
    }
}