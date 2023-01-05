package strongly_connected_components;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Polyakov Dmitry
 * @created 05.01.2023 11:29 PM
 */
public class Main {
    public static final int[][] ADJ_MATRIX = {
            {0, 0, 0, 1, 0, 0, 0},
            {1, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 1, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 0}
    };
    public static final int[][] UNIT_MATRIX = {
            {1, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 1}
    };

    public static final int LEN = ADJ_MATRIX.length;


    public static void main(String[] args) {

        System.out.println("Adjacency matrix: ");

        for (int[] ints : ADJ_MATRIX) {
            for (int j = 0; j < ADJ_MATRIX.length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
        System.out.println("Strongly connected components: " + findStronglyConnectedComponents());
    }

    private static int[][] raiseToPower(int[][] arr) {
        for (int power = 1; power <= LEN; power++) {
            for (int i = 1; i < power; i++) {
                arr = squareArray(ADJ_MATRIX, arr); //recursive call for getting a^{power} array
                sum(arr);
            }
        }
        return arr;
    }

    private static int[][] sumWithUnit() {
        int[][] finalSum = raiseToPower(Main.ADJ_MATRIX);
        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < LEN; j++) {
                finalSum[i][j] = finalSum[i][j] + UNIT_MATRIX[i][j];
            }
        }
        return finalSum;
    }

    private static int[][] squareArray(int[][] a, int[][] b) {
        int[][] sqrArr = new int[LEN][LEN];
        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < LEN; j++) {
                for (int k = 0; k < LEN; k++) {
                    sqrArr[i][j] = a[i][k] & b[k][j] | sqrArr[i][j];
                }
            }
        }
        return sqrArr;
    }

    private static int[][] transportMatrix() {
        int[][] a = sumWithUnit();
        int[][] transMatrix = new int[LEN][LEN];

        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < LEN; j++) {
                transMatrix[i][j] = a[j][i];
            }
        }

        return transMatrix;
    }

    private static int[][] matrixPower(int[][] arr) {
        int[][] a = raiseToPower(arr);
        int[][] b = transportMatrix();
        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < LEN; j++) {
                if (a[i][j] != 0) {
                    a[i][j] = 1;
                }
                if (b[i][j] != 0) {
                    b[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < LEN; j++) {
                for (int k = 0; k < LEN; k++) {
                    if (a[i][j] != 0 && b[i][j] != 0) {
                        arr[i][j] |= a[i][k] & b[k][j];
                    }
                    else arr[i][j] = 0;
                }
            }
        }
        return arr;
    }

    private static void sum(int[][] arr) {
        int[][]sum = new int[LEN][LEN];
        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < LEN; j++) {
                sum[i][j] = 0;
            }
        }
        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < LEN; j++) {
                sum[i][j] = sum[i][j] + arr[i][j];
            }
        }
    }
    private static Set<Integer> findStronglyConnectedComponents(){
        Set<Integer> listOfComponents = new HashSet<>();
        int[][] arr = matrixPower(ADJ_MATRIX);
        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < LEN; j++) {
                if (arr[i][j] != 0 && arr[i][j] == arr[j][i]) {
                    listOfComponents.add(i+1);
                }
            }
        }
        return listOfComponents;
    }
}
