package polynomial_formula;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Polyakov Dmitry
 * @created 05.01.2023 10:09 PM
 */
public class Main {
    public static StringBuilder globalStrBuilder = new StringBuilder();
    public static List<Integer> factorialRatios = new ArrayList<>();

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Print degree: ");
            int m = Integer.parseInt(bufferedReader.readLine());

            System.out.print("Print number of sequence members: ");
            int k = Integer.parseInt(bufferedReader.readLine());

            permutation(m, k);
            print(m, k);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void permutation(int m, int k) {
        int[] perm = new int[k];

        //List for ratios
        while (true) {
            StringBuilder sb = new StringBuilder();
            int sum = 0;
            for (int value : perm) {
                sum = value + sum;
            }

            if (sum == m) { //check that sum of n1 + n2 + ... + nk = n
                int fact = 1;
                for (int j : perm) { //cycle of counting factorial ratios
                    if (j != 0) {
                        fact = factorial(j) * fact;
                    }
                }

                int ratio = factorial(m) / fact;
                factorialRatios.add(ratio);

                for (int i = 0; i < perm.length; i++) { //cycle of generating x{n} sequence
                    if (perm[i] != 0) {
                        if (perm[i] == 1) {
                            sb.append("X").append(i + 1);
                        } else {
                            sb.append("X").append(i + 1).append("^").append(perm[i]);
                        }
                    }
                }
                globalStrBuilder.append(sb.append(" + "));
            }

            int i;
            for (i = k - 1; i >= 0; i--) {
                if (perm[i] < m) {
                    break;
                }
            }

            if (i < 0) { // if "i" reaches value < 0  then true boolean value will be false
                break;
            }

            perm[i]++;
            i++;

            while (i < k) {
                perm[i] = 0;
                i++;
            }
        }
    }

    public static void print(int m, int k) {
        //remove an extra "+" in raw decomposition
        String string = String.valueOf(globalStrBuilder.replace(globalStrBuilder.lastIndexOf(" + "),
                globalStrBuilder.lastIndexOf(" "), ""));

        for (int i = 1; i < factorialRatios.size(); i++) {
            // insert factorial ratios in decomposition where it needs;
            string = string.replaceFirst(" \\+ X", " + " + (factorialRatios.get(i)) + "*" + "X");
        }

        System.out.print("(");

        for (int i = 0; i < k; i++) { //cycle for printing collapsed expression
            if (i != k - 1) {
                System.out.print("X" + (i + 1) + " + ");
            }
            else System.out.print("X" + (i + 1) + ")^" + m + " = ");
        }

        // print decomposition without ratio = 1
        System.out.println(string.replace("1*", ""));
    }

    public static int factorial(int n) {
        int result = 1;

        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }
}
