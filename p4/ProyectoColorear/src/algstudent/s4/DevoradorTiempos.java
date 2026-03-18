package algstudent.s4;
 
import java.io.FileReader;
import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
public class DevoradorTiempos {
 
    public static void main(String[] args) {
        long t1, t2;
        int repeticiones = Integer.parseInt(args[0]);
 
        int[] tamaños = {4, 8, 16, 32, 64, 100, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536};
 
        JSONParser parser = new JSONParser();
 
        for (int n : tamaños) {
            String archivo = "../sols/g" + n + ".json";
            long t = 0;
 
            try (FileReader reader = new FileReader(archivo)) {
                JSONObject jsonObject = (JSONObject) parser.parse(reader);
 
                @SuppressWarnings("unchecked")
                Map<String, List<String>> grafo = (Map<String, List<String>>) jsonObject.get("grafo");
 
                for (int i = 1; i <= repeticiones; i++) {
                    t1 = System.currentTimeMillis();
                    ColoreoGrafo.realizarVoraz(grafo);
                    t2 = System.currentTimeMillis();
                    t = t + (t2 - t1);
                }
 
                System.out.println("n=" + n + "\tTiempo=" + t + "\tRepeticiones=" + repeticiones);
 
            } catch (Exception e) {
                System.out.println("n=" + n + "\tarchivo no encontrado");
            }
        }
    }
}