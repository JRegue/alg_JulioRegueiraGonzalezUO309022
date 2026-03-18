package algstudent.s4;

import java.util.*;
public class ColoreoGrafo {

    private static final String[] COLORES = {"red", "blue", "green", "yellow", "orange",
        "purple", "cyan", "magenta", "lime"
    };

    public static Map<String, String> realizarVoraz(Map<String,List<String>> grafo) {
        Map<String, String> resultado = new HashMap<>();

        for (Object nodoG : grafo.keySet()) {
            String nodo = String.valueOf(nodoG);
            Set<String> coloresAdyacentes = new HashSet<>();
            for (Object adyacenteG : grafo.get(nodo)) {
                String adyacente = String.valueOf(adyacenteG);
                if (resultado.containsKey(adyacente)) {
                    coloresAdyacentes.add(resultado.get(adyacente));
                }
            }

            for (String color : COLORES) {
                if (!coloresAdyacentes.contains(color)) {
                    resultado.put(nodo, color);
                    break;
                }
            }
        }
        return resultado;
    }
    
}
