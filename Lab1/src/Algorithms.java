import java.util.HashMap;

public class Algorithms {
    private HashMap<Integer, Integer> memo = new HashMap<>();

    public int recursiveApproach(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return recursiveApproach(n - 1) + recursiveApproach(n - 2);
        }
    }

    public int iterativeApproach(int n) {
        int a = 0;
        int b = 1;
        for (int i = 0; i < n; i++) {
            int temp = a;
            a = b;
            b = temp + b;
        }
        return a;
    }

    public int fibonacciMatrix(int n) {
        int[][] baseMatrix = {{1, 1}, {1, 0}};
        int[][] resultMatrix = matrixPower(baseMatrix, n);
        return resultMatrix[0][1];
    }

    private int[][] matrixPower(int[][] matrix, int n) {
        if (n == 1) {
            return matrix;
        } else if (n % 2 == 0) {
            int[][] halfPower = matrixPower(matrix, n / 2);
            return matrixMultiply(halfPower, halfPower);
        } else {
            int[][] halfPower = matrixPower(matrix, n / 2);
            return matrixMultiply(matrixMultiply(halfPower, halfPower), matrix);
        }
    }

    private int[][] matrixMultiply(int[][] a, int[][] b) {
        int[][] result = new int[2][2];
        result[0][0] = a[0][0] * b[0][0] + a[0][1] * b[1][0];
        result[0][1] = a[0][0] * b[0][1] + a[0][1] * b[1][1];
        result[1][0] = a[1][0] * b[0][0] + a[1][1] * b[1][0];
        result[1][1] = a[1][0] * b[0][1] + a[1][1] * b[1][1];
        return result;
    }

    public int fibonacciMemoization(int n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        int fib;
        if (n <= 1) {
            fib = n;
        } else {
            fib = fibonacciMemoization(n - 1) + fibonacciMemoization(n - 2);
        }
        memo.put(n, fib);
        return fib;
    }

    public int fibonacciBinet(int n) {
        double sqrt5 = Math.sqrt(5);
        double phi = (1 + sqrt5) / 2;
        double psi = (1 - sqrt5) / 2;
        return (int) ((Math.pow(phi, n) - Math.pow(psi, n)) / sqrt5);
    }

    public int fibonacciBottomUp(int n) {
        if (n <= 1) {
            return n;
        }
        int[] fib = new int[n + 1];
        fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }
}
