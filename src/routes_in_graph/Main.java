package routes_in_graph;

/**
 * @author Polyakov Dmitry
 * @created 05.01.2023 11:45 PM
 */
public class Main {
    public static final int POWER = 3;
    public static final int[][] ADJ_MATRIX = {
            {0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1},
            {0, 1, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0},
            {0, 1, 1, 0, 0, 1},
            {1, 0, 0, 0, 0, 0}
    };
    public static final int LEN = ADJ_MATRIX.length;


    public static void main(String[] args) {
        print();
    }

    public static int[][] raiseToPower(int[][] arr) {
        int[][] original = arr;

        for (int i = 1; i < POWER ; i++) {
            arr = squareArray(original, arr); //recursive call for getting a^{power} array
        }
        return arr;
    }

    private static int[][] squareArray(int[][] a, int[][] b) {
        int[][] sqrArr = new int[LEN ][LEN ];

        for (int i = 0; i < LEN ; i++) {
            for (int j = 0; j < LEN ; j++) {
                for (int k = 0; k < LEN ; k++) {
                    sqrArr[i][j] = a[i][k] * b[k][j] + sqrArr[i][j];
                }
            }
        }

        return sqrArr;
    }

    private static int countAllPaths(int[][] a) {
        int counter = 0;

        for (int[] ints : a) {
            for (int j = 0; j < LEN ; j++) {
                if (ints[j] != 0) {
                    counter = ints[j] + counter;
                }
            }
        }

        return counter;
    }

    private static void print() {
        System.out.println("Adjacency matrix: ");

        for (int[] ints : ADJ_MATRIX) {
            for (int i = 0; i < LEN ; i++) {
                System.out.print(ints[i] + " ");
            }
            System.out.println();
        }

        int[][] poweredArr = raiseToPower(ADJ_MATRIX);

        System.out.println("\nMatrix of all distinct paths: ");
        for (int[] ints : poweredArr) {
            for (int i = 0; i < poweredArr.length; i++) {
                System.out.print(ints[i] + " ");
            }
            System.out.println();
        }
        System.out.println("\nNumber of all distinct paths: " + countAllPaths(raiseToPower(ADJ_MATRIX)));
    }
}
