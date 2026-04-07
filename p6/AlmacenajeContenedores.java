package p6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class AlmacenajeContenedores {
    private static int capacity;
    private static int[] objetos;

    static int[] mejorAsignacion;
    static int[] asignacionActual;
    static int[] espacioLibrePorContenedor;

    static int mejorNumContenedores = Integer.MAX_VALUE;

    static int llamadas = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        capacity = Integer.parseInt(br.readLine().trim());
        String[] partes = br.readLine().trim().split("\\s+");
        br.close();

        int n = partes.length;
        objetos = new int[n];
        for (int i = 0; i < n; i++) {
            objetos[i] = Integer.parseInt(partes[i]);
        }

        asignacionActual = new int[n];
        espacioLibrePorContenedor = new int[n];

        for(int i = 0; i < n; i++){
            espacioLibrePorContenedor[i] = capacity;
        }
        
        mejorNumContenedores = n;
        mejorAsignacion = new int[n];

        backtracking(0, 0);
        imprimirSolucion();
    }

    public static void backtracking(int objIndex, int numContenedores){
        llamadas++;

        if(objIndex == objetos.length){
            if(numContenedores < mejorNumContenedores){
                mejorNumContenedores = numContenedores;
                for(int i = 0; i < objetos.length; i++){
                    mejorAsignacion[i] = asignacionActual[i];
                }
            }
            return;
        }

        if(numContenedores >= mejorNumContenedores){
            return;
        }

        for (int j = 0; j < numContenedores; j++){
            if (espacioLibrePorContenedor[j] >= objetos[objIndex]){
                asignacionActual[objIndex] = j;
                espacioLibrePorContenedor[j] -= objetos[objIndex];
                backtracking(objIndex+1, numContenedores);
                espacioLibrePorContenedor[j] += objetos[objIndex];
            }
        }

        if (numContenedores < mejorNumContenedores){
            asignacionActual[objIndex] = numContenedores;
            espacioLibrePorContenedor[numContenedores] -= objetos[objIndex];
            backtracking(objIndex+1, numContenedores+1);
            espacioLibrePorContenedor[numContenedores] += objetos[objIndex];
        }
    }

    static void imprimirSolucion() {
        List<List<Integer>> contenedores = new ArrayList<>();

        for (int i = 0; i < mejorNumContenedores; i++) {
            contenedores.add(new ArrayList<>());
        }

        for (int i = 0; i < objetos.length; i++) {
            contenedores.get(mejorAsignacion[i]).add(objetos[i]);
        }

        System.out.println("Lista de contenedores y objetos contenidos:");
        for (int j = 0; j < mejorNumContenedores; j++) {
            System.out.print("      Contenedor " + (j + 1) + ": ");
            for (int s : contenedores.get(j)) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
        System.out.println("El número de contenedores necesario es " + mejorNumContenedores + ".");
    }
}