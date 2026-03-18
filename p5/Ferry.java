package p5;

import java.util.*;

public class Ferry {
    
    private int boatLength; //longitud del barco 
    private List<Integer> vehicules; //lista de vehiculos
    private boolean[][] dp; //matriz con las posibles soluciones
    private int[] sumatorio; //suma acumulada de las longitudes de los vehiculos

    public Ferry(int boatLength, List<Integer> vehicules){
        this.boatLength = boatLength;
        this.vehicules = vehicules;
        this.dp = new boolean[vehicules.size()+1][boatLength];

        this.sumatorio = new int[vehicules.size() +1];

        this.sumatorio[0] = 0;
        for(int i = 1; i < vehicules.size(); i++){
            this.sumatorio[i] = sumatorio[i-1] + vehicules.get(i-1);
        }
    }

    public void run(){
        dp[0][0] = true; //0 metros usados para 0 coches 
        for(int i = 1; i < vehicules.size()+1; i++){
            for(int p = 0; i < boatLength+1; p++){

                if(!dp[i-1][p]){
                    continue;
                }

                if(p + vehicules.get(i) <= boatLength){ //babor
                    dp[i][p + vehicules.get(i)] = true;
                }
            
                if(sumatorio[i] - p <= boatLength){ //estribor
                    dp[i][p] = true;
                }
            }
        }
    }

    public void printData(){
        System.out.println("Longitud de los carriles: " + boatLength);
        System.out.println("Longitud de los vehiculos:\n\n");
        for(int i = 0; i < vehicules.size(); i++){
            System.out.println("Vehiculo " + i+1 + ": " + vehicules.get(i) + " unidades.");
        }
    }
}
