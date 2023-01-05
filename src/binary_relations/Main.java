package binary_relations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.abs;
import static java.lang.Math.random;

/**
 * @author Polyakov Dmitry
 * @created 05.01.2023 10:45 PM
 */
public class Main {
    public static void main(String[] args) {
        Set<Integer> numberSet = new HashSet<>();

        while (numberSet.size() < 10) {
            numberSet.add((int) ((random()) * 20) + 1);
        }

        System.out.println("Set of numbers " + numberSet);

        int[][] binaryMatrix = buildBinaryRelationMatrix(toArray(numberSet));

        isWeaklyComplete(binaryMatrix);
        selectUpperCutOfElements(binaryMatrix, toArray(numberSet));
        printBinaryRelationPairs(binaryMatrix, toArray(numberSet));

    }

    private static List<Integer> toArray(Set<Integer> numberSet) {
        return numberSet.stream().toList();
    }

    private static int[][] buildBinaryRelationMatrix(List<Integer> array) {
        int[][] binaryMatrix = new int[10][10];

        for (int i = 0; i < binaryMatrix.length; i++) {
            for (int j = 0; j < binaryMatrix.length; j++) {
                if (abs(array.get(i) - array.get(j)) <= 8) {
                    binaryMatrix[i][j] = 1;
                } else binaryMatrix[i][j] = 0;
            }
        }

        System.out.println("\nBinary matrix: ");
        for (int[] matrix : binaryMatrix) {
            for (int j = 0; j < binaryMatrix.length; j++) {
                System.out.print(matrix[j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        return binaryMatrix;
    }


    private static void isWeaklyComplete(int[][] binaryMatrix) {
        boolean weaklyComplete = true;

        for (int i = 0; i < binaryMatrix.length; i++) {
            for (int j = 0; j < binaryMatrix.length; j++) {
                if (binaryMatrix[i][j] == binaryMatrix[j][i] && binaryMatrix[i][j] == 0) {
                    weaklyComplete = false;
                    break;
                }
            }
        }

        if (weaklyComplete) {
            System.out.println("\nMatrix is weakly complete.");
        } else System.out.println("\nMatrix is not weakly complete.");
    }

    private static void selectUpperCutOfElements(int[][] binaryMatrix, List<Integer> array) {
        Set<Integer> upperCutSet = new HashSet<>();

        for (int i = 0; i < binaryMatrix.length; i++) {
            for (int j = 0; j < binaryMatrix.length; j++) {
                if (array.get(i) % 3 == 0) {
                    upperCutSet.add(array.get(j));
                }
            }
            if (array.get(i) % 3 == 0) {
                System.out.println("\nUpper cut of elements for " + array.get(i) + ": " + upperCutSet);
                break;
            }
        }
        //Upper cut should contain all elements that satisfies given terms,
        // because there were built every binary relation pairs for elements in the set.
        //In this case, if set consists of n elements, then number of binary relation matrix elements = n^2
    }

    private static void printBinaryRelationPairs(int[][] binaryMatrix, List<Integer> array) {
        System.out.println("\nAll binary relation pairs: ");
        for (int i = 0; i < binaryMatrix.length; i++) {
            for (int j = 0; j < binaryMatrix.length; j++) {
                System.out.print("(" + array.get(i) + ", " + array.get(j) + ") ");
            }
            System.out.println();
        }
    }
}
