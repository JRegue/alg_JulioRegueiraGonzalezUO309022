package p5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Ferry {

    private int length;
    private List<Integer> vehicles;
    private boolean[][] dp;
    private int[] sumatorio;
    private List<Step> path; // variable para guardar el camino seleccionado

    public Ferry(int length, List<Integer> vehicles) {
        this.length = length;
        this.vehicles = vehicles;
        this.dp = new boolean[vehicles.size() + 1][length + 1];

        this.sumatorio = new int[vehicles.size() + 1];
        this.sumatorio[0] = 0;
        for (int i = 1; i <= vehicles.size(); i++) {
            this.sumatorio[i] = sumatorio[i - 1] + vehicles.get(i - 1);
        }

        this.path = new ArrayList<Step>();
    }

    public static void main(String[] args) {
    if (args.length < 1) {
        System.err.println("Error: Indica la ruta del fichero.");
        return;
    }

    int boatLength = 0;
    List<Integer> vehicles = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
        String line = reader.readLine();
        if (line != null) {
            boatLength = Integer.parseInt(line.trim());
        }

        line = reader.readLine();
        if (line != null) {
            for (String s : line.trim().split(" ")) {
                vehicles.add(Integer.valueOf(s));
            }
        }
        
        Ferry ferry = new Ferry(boatLength, vehicles);
        ferry.run();
        
        System.out.printf("Número máximo (k): %d\n", ferry.getMaximumNumberOfVehicles());
        ferry.printSolutionTable();
        ferry.printPossibleAssignation();

    } catch (Exception e) {
        System.err.println("Error al procesar el fichero: " + e.getMessage());
    }
    }

    public void run() {
        dp[0][0] = true;

        for (int i = 1; i <= vehicles.size(); i++) {
            boolean possibleToLoad = false;
            int v = vehicles.get(i - 1);

            for (int p = 0; p <= length; p++) {
                if (dp[i - 1][p]) {
                    if (p + v <= length) {
                        dp[i][p + v] = true;
                        possibleToLoad = true;
                    }
                    if ((sumatorio[i] - p) <= length) {
                        dp[i][p] = true;
                        possibleToLoad = true;
                    }
                }
            }
            if (!possibleToLoad) {
                break;
            }
        }
    }

    /**
     * Devuelve el numero máximo de vehiculos posibles
     * l (siendo l < boatlength) con dp[i][l] = true.
     */
    public int getMaximumNumberOfVehicles() {
        for (int i = vehicles.size(); i >= 0; i--) {
            for (int j = 0; j <= length; j++) {
                if (dp[i][j]) {
                    return i;
                }
            }
        }
        return 0;
    }

    public void printData() {
        System.out.printf("Length of parallel lanes for starboard and port on the ferry: %d\n", length);
        System.out.printf("The vehicles have the following lengths:\n");
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.printf("\tVehicle %d: %d\n", i + 1, vehicles.get(i));
        }
    }

    public void printPossibleAssignation() {
        boolean found = false;
        System.out.printf("\nPossible assignation:\n");
        int maxVehicles = getMaximumNumberOfVehicles();

        if (maxVehicles == 0)
            return;

        for (int p = 0; p <= length; p++) {
            if (found)
                break; // si found es true -> rompo la ejecución
            if (dp[maxVehicles][p]) {
                found = true;
                processAssignation(maxVehicles, p); // llamo a processAssignation()
            }
        }
    }

    private void processAssignation(int i, int l) {
        if (i == 0) {
            printPath(); // llamo a printPath y acabo la ejecución (return)
            return;
        }

        int v = vehicles.get(i - 1);

        // Comprobamos si vino de estribor
        if (dp[i - 1][l] && (sumatorio[i] - l) <= length) {
            path.add(0, new Step(i - 1, l, i, l, i, "estribor")); // añado al path un nuevo Step llamado estribor
            processAssignation(i - 1, l); // llamo a processAssignation(i-1, l)
            return;
        }

        // Comprobamos si vino de babor
        if (l - v >= 0 && dp[i - 1][l - v]) {
            path.add(0, new Step(i - 1, l - v, i, l, i, "babor")); // añado al path un nuevo Step llamado babor
            processAssignation(i - 1, l - v); // llamo a processAssignation(i-1, l-vehicles.get(i-1))
        }
    }

    public void printSolutionTable() {
        System.out.printf("\nTable with calculations:\n");
        System.out.printf("%4s", "V/L");
        for (int i = 0; i <= length; i++) {
            System.out.printf("%4d", i);
        }
        System.out.printf("\n");

        for (int i = 0; i <= vehicles.size(); i++) {
            System.out.printf("%4d", i);
            for (int l = 0; l <= length; l++) {
                if (dp[i][l]) {
                    System.out.printf("%4s", "T");
                } else {
                    System.out.printf("%4s", "F");
                }
            }
            System.out.printf("\n");
        }
    }

    private void printPath() {
        int portLength = 0;
        int starboardLength = 0;
        for (var step : path) {
            if (step.movement().equals("babor")) {
                portLength += vehicles.get(step.vehicle() - 1);
            } else {
                starboardLength += vehicles.get(step.vehicle() - 1);
            }
            System.out.printf(
                    "Vehicle %d (length %d) -- From (%d, %d) -- To (%d, %d) -- Position: %s -- Port lengh: %d -- Starboard length: %d\n",
                    step.vehicle(), vehicles.get(step.vehicle() - 1),
                    step.previousI(), step.previousL(),
                    step.currentI(), step.currentL(),
                    step.movement(), portLength, starboardLength);
        }
    }
}

record Step(int previousI, int previousL,
        int currentI, int currentL,
        int vehicle, String movement) {
}