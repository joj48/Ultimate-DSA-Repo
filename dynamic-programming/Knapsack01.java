import java.util.Arrays;

/**
 * Problem:
 * Given a set of items, each with a weight and a value, determine the number of each item
 * to include in a collection so that the total weight is less than or equal to a given limit
 * and the total value is as large as possible. You cannot break an item.
 */
public class Knapsack01 {

    // Approach 1: Memoization (Top-Down)
    // Time Complexity: O(N * W)
    // Space Complexity: O(N * W) for memoization table + O(N) for recursion stack.
    private int[][] memo;

    public int knapsackMemo(int[] weights, int[] values, int W) {
        int n = weights.length;
        memo = new int[n][W + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return solveMemo(weights, values, n - 1, W);
    }

    private int solveMemo(int[] weights, int[] values, int index, int capacity) {
        if (index < 0 || capacity <= 0) {
            return 0;
        }
        if (memo[index][capacity] != -1) {
            return memo[index][capacity];
        }

        if (weights[index] > capacity) {
            memo[index][capacity] = solveMemo(weights, values, index - 1, capacity);
        } else {
            int includeItem = values[index] + solveMemo(weights, values, index - 1, capacity - weights[index]);
            int excludeItem = solveMemo(weights, values, index - 1, capacity);
            memo[index][capacity] = Math.max(includeItem, excludeItem);
        }
        return memo[index][capacity];
    }

    // Approach 2: Tabulation (Bottom-Up)
    // Time Complexity: O(N * W)
    // Space Complexity: O(N * W)
    public int knapsackTab(int[] weights, int[] values, int W) {
        int n = weights.length;
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][W];
    }

    // Test cases
    public static void main(String[] args) {
        Knapsack01 knapsackSolver = new Knapsack01();
        int[] values1 = {60, 100, 120};
        int[] weights1 = {10, 20, 30};
        int W1 = 50;

        System.out.println("Maximum value (Memoization): " + knapsackSolver.knapsackMemo(weights1, values1, W1));
        System.out.println("Maximum value (Tabulation): " + knapsackSolver.knapsackTab(weights1, values1, W1));

        int[] values2 = {10, 40, 30, 50};
        int[] weights2 = {5, 4, 6, 3};
        int W2 = 10;

        // Need to re-initialize for memoization as the instance is reused
        knapsackSolver = new Knapsack01(); 
        System.out.println("Maximum value (Memoization): " + knapsackSolver.knapsackMemo(weights2, values2, W2));
        System.out.println("Maximum value (Tabulation): " + knapsackSolver.knapsackTab(weights2, values2, W2));
    }
}