package graph_repsesentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Polyakov Dmitry
 * @created 05.01.2023 11:05 PM
 */

public class Main {
    public static int number;

    public static void main(String[] args) throws InvalidNumberException {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Print number of nodes for undirected graph: ");
            number = Integer.parseInt(reader.readLine());

            if (number < 5) {
                throw new InvalidNumberException("Number of nodes must be greater than 4.");
            } else {
                int[][] adjacencyMatrix = fillAdjacencyMatrix();

                System.out.println("\nAll numbered edges and links node: ");
                //if there is only one number of node, then node is making a loop
                printDifferentEdges(adjacencyMatrix);

                System.out.println("\nAll nodes and every edges that links this and another node: ");
                printNodeWithExistingEdges(adjacencyMatrix);

                System.out.println("\nNew adjacency matrix with deleted linked edges: ");
                printAdjacencyMatrixWithDeletedEdges(adjacencyMatrix);
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    private static int[][] fillAdjacencyMatrix() {
        int[][] adjacencyMatrix = new int[number][number];

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                int randomValue = ((int) (Math.random() * 2));
                adjacencyMatrix[j][i] = randomValue;
                adjacencyMatrix[i][j] = randomValue;
            }
        }

        System.out.println("\nAdjacency matrix:");
        for (int[] row : adjacencyMatrix) {
            System.out.println(Arrays.toString(row));
        }

        return adjacencyMatrix;
    }

    private static void printDifferentEdges(int[][] adjacencyMatrix) {
        ArrayList<Integer> tempArray = new ArrayList<>();
        int countEdges = 0;

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[i][j] == adjacencyMatrix[j][i] && adjacencyMatrix[i][j] == 1) {
                    if (i == j) {
                        countEdges++;
                        System.out.println(countEdges + " -> " + (i + 1));
                    } else {
                        tempArray.add(i);
                        if (!tempArray.contains(j)) {
                            countEdges++;
                            System.out.println(countEdges + " -> " + (i + 1) + ", " + (j + 1));
                        }
                    }
                }
            }
        }
    }

    private static void printNodeWithExistingEdges(int[][] adjacencyMatrix) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    if (!arrayList.contains(i)) {
                        System.out.print((i + 1) + " -> " + (i + 1) + "-" + (j + 1) + " ");
                        arrayList.add(i);
                    } else {
                        System.out.print((i + 1) + "-" + (j + 1) + " ");
                    }
                }
            }
            if (!arrayList.contains(i)) {
                System.out.print((i + 1) + " -> no edges");
            }
            System.out.println();
        }
    }


    private static void printAdjacencyMatrixWithDeletedEdges(int[][] updatedAdjacencyMatrix) {
        for (int i = 0; i < updatedAdjacencyMatrix.length; i++) {
            for (int j = 0; j < updatedAdjacencyMatrix.length; j++) {
                if (updatedAdjacencyMatrix[i][j] == 1 && i == 0 || i == 1 || i == 2 || i == 3) {
                    updatedAdjacencyMatrix[i][j] = 0;
                }
            }
        }

        for (int[] row : updatedAdjacencyMatrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
