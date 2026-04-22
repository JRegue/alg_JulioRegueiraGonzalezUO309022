import java.util.ArrayList;
import java.util.List;

public class LaberintoTodas {
    
    private List<List<String>> soluciones; // cada lista interna de Strings representa un tablero completo donde cada String es una fila.
    private int tamaño;
    private int initialPos;
    private int finalPos;
    private int[] dy = {-1,1,0,0};
    private int[] dx = {0,0,-1,1};

    /**
     * Método principal que inicia el algoritmo.
     * @param n de tipo int. El tamaño del tablero
     * @return soluciones, de tipo List<List<String>>. Una lista con todas las configuraciones válidas del tablero
     */
    public List<List<String>> resolverLaberintoTodas(int n) {
        soluciones = new ArrayList<>();
        // Inicializar el tablero vacío con puntos '.' (os doy un método) 
        int[][] tablero = crearTablero(n);

        // Iniciar el backtracking 
        backtracking(0,0,n,tablero);
        
        return soluciones;
    }

   

    /**
     * Método de backtracking en el tablero.
     * @param fila, de tipo int. La fila actual en la que intentamos colocar una reina.
     * @param columna
     * @param tablero,
     */
    private void backtracking(int fila, int columna, int n, int[][] tablero) {
        if(columna == 6 && fila == 6){
            soluciones.add(construirSolucion(tablero));
            return;
        }

        for(int i = 0; i < 4; i++){
            int y = dy[i];
            int x = dx[i];

            if(esValido(fila+y, columna+x, n, tablero)){
                backtracking(fila+y, columna+x, n, tablero);
            }   
        }
    }

    private boolean esValido(int fila, int columna, int n, int[][] tablero){
        if(fila < n && columna < n && tablero[fila][columna] == 0){
            return true;
        }
        return false;
    }

    /**
     * Método auxiliar para crear un tablero vacío representado como una matriz de caracteres
     * @param n, de tipo int. El tamaño del tablero (NxN)
     * @return tablero, de tipo char[][]. Un tablero inicializado con '.' indicando casillas vacias
     */
     private int[][] crearTablero(int n) {
        int[][] tablero = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tablero[i][j] = '.';
            }
        }
        return tablero;
    }

    /**
     * Método auxiliar para pintar el tablero por consola
     */
    private static void pintarTablero(List<List<String>> resultado) {
        for (int i = 0; i < resultado.size(); i++) {
            System.out.println("Solución " + (i + 1) + ":");
            for (String fila : resultado.get(i)) {
                System.out.println(fila);
            }
            System.out.println();
        }
    }

    /**
     *  Método principal para ejecutar el programa
     *  @param args, el primer argumento es el valor de N, o se usará 4 por defecto.
     */ 
    public static void main(String[] args) {
        LaberintoTodas algoritmo = new LaberintoTodas();
        int n = args != null && args.length > 0 ? Integer.parseInt(args[0]) : 4; 
        List<List<String>> resultado = algoritmo.resolverLaberintoTodas(n);
        
        System.out.println("Se encontraron " + resultado.size() + " soluciones para N = " + n + "\n");
        
        pintarTablero(resultado);
    }

}