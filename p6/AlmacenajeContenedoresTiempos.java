package p6;

import java.io.File;
import java.io.PrintStream;
import java.io.OutputStream;
import java.util.*;

public class AlmacenajeContenedoresTiempos {

    public static void main(String[] args) {
        long t1, t2;
        
        int repeticiones = Integer.parseInt(args[0]); 

        String[] archivos = {
            "test00.txt", "test01.txt", "test02.txt", "test03.txt", "test04.txt",
            "test05.txt", "test06.txt", "test07.txt", "test08.txt", "test09.txt"
        };

        System.out.println("Fichero\t\tTiempo(ms)\tRepeticiones\tLlamadas");
        System.out.println("------------------------------------------------------------------");

        for (String nombreArchivo : archivos) {
            String ruta = "CasosPrueba/" + nombreArchivo; 
            long t = 0;

            try {
                if (!new File(ruta).exists()) {
                    ruta = "p6/CasosPrueba/" + nombreArchivo;
                }

                PrintStream originalOut = System.out;
                
                try {
                    System.setOut(new PrintStream(new OutputStream() { public void write(int b) { } }));

                    for (int i = 1; i <= repeticiones; i++) {
                        AlmacenajeContenedores.llamadas = 0;
                        
                        t1 = System.currentTimeMillis();
                        
                        AlmacenajeContenedores.main(new String[]{ruta});
                        
                        t2 = System.currentTimeMillis();
                        t = t + (t2 - t1);
                    }
                } finally {
                    System.setOut(originalOut);
                }

                System.out.println(nombreArchivo + "\t" + t + "\t\t" + repeticiones + "\t\t" + AlmacenajeContenedores.llamadas);

            } catch (Exception e) {
                System.out.println(nombreArchivo + "\tarchivo no encontrado o error");
            }
        }
    }
}