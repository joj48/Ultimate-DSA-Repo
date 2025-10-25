import java.util.Arrays;

/**
 * Problem:
 * Given a sequence of matrices, find the most efficient way to multiply these matrices together.
 * The problem is not actually to perform the multiplications, but merely to decide in which order
 * to perform the multiplications.
 */
public class MatrixChainMultiplication {

    // Approach 1: Memoization (Top-Down)
    // Time Complexity: O(n^3)
    // Space Complexity: O(n^2) for the memoization table + O(n) for recursion stack.
    public int matrixChainOrderMemo(int[] p) {
        int n = p.length - 1;
        int[][] memo = new int[n + 1][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return solve(p, 1, n, memo);
    }

    private int solve(int[] p, int i, int j, int[][] memo) {
        if (i == j) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int minCost = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int cost = solve(p, i, k, memo) + solve(p, k + 1, j, memo) + p[i - 1] * p[k] * p[j];
            minCost = Math.min(minCost, cost);
        }
        memo[i][j] = minCost;
        return minCost;
    }

    // Approach 2: Tabulation (Bottom-Up)
    // Time Complexity: O(n^3)
    // Space Complexity: O(n^2) for the DP table.
    public int matrixChainOrderTab(int[] p) {
        int n = p.length;
        int[][] m = new int[n][n];

        for (int L = 2; L < n; L++) {
            for (int i = 1; i < n - L + 1; i++) {
                int j = i + L - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                    }
                }
            }
        }
        return m[1][n - 1];
    }

    public static void main(String[] args) {
        MatrixChainMultiplication mcmSolver = new MatrixChainMultiplication();
        int[] arr = {1, 2, 3, 4};

        System.out.println("Minimum number of multiplications is (Memoization): " + mcmSolver.matrixChainOrderMemo(arr));
        System.out.println("Minimum number of multiplications is (Tabulation): " + mcmSolver.matrixChainOrderTab(arr));

        int[] arr2 = {40, 20, 30, 10, 30};

        System.out.println("Minimum number of multiplications is (Memoization): " + mcmSolver.matrixChainOrderMemo(arr2));
        System.out.println("Minimum number of multiplications is (Tabulation): " + mcmSolver.matrixChainOrderTab(arr2));
    }
}
