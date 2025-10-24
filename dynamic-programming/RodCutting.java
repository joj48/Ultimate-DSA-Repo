import java.util.Arrays;

/**
 * Problem:
 * Given a rod of length n inches and an array of prices that includes prices of all pieces of size smaller than n.
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
 */
public class RodCutting {

    // Approach 1: Memoization (Top-Down)
    // Time Complexity: O(n^2)
    // Space Complexity: O(n) for the memoization table + O(n) for recursion stack.
    public int cutRodMemo(int[] prices, int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return solve(prices, n, memo);
    }

    private int solve(int[] prices, int length, int[] memo) {
        if (length <= 0) {
            return 0;
        }
        if (memo[length] != -1) {
            return memo[length];
        }

        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            maxVal = Math.max(maxVal, prices[i] + solve(prices, length - (i + 1), memo));
        }
        memo[length] = maxVal;
        return maxVal;
    }

    // Approach 2: Tabulation (Bottom-Up)
    // Time Complexity: O(n^2)
    // Space Complexity: O(n) for the DP table.
    public int cutRodTab(int[] prices, int n) {
        int[] val = new int[n + 1];
        val[0] = 0;

        for (int i = 1; i <= n; i++) {
            int maxVal = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                maxVal = Math.max(maxVal, prices[j] + val[i - (j + 1)]);
            }
            val[i] = maxVal;
        }
        return val[n];
    }

    public static void main(String[] args) {
        RodCutting rodCutter = new RodCutting();
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20};
        int n = 8;

        System.out.println("Maximum obtainable value for rod of length " + n + " (Memoization): " + rodCutter.cutRodMemo(prices, n));
        System.out.println("Maximum obtainable value for rod of length " + n + " (Tabulation): " + rodCutter.cutRodTab(prices, n));

        int[] prices2 = {3, 5, 8, 9, 10, 17, 17, 20};
        int n2 = 8;

        System.out.println("Maximum obtainable value for rod of length " + n2 + " (Memoization): " + rodCutter.cutRodMemo(prices2, n2));
        System.out.println("Maximum obtainable value for rod of length " + n2 + " (Tabulation): " + rodCutter.cutRodTab(prices2, n2));
    }
}
