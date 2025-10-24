import java.util.Arrays;

/**
 * Problem:
 * You are given k identical eggs and a building with n floors. You need to find the minimum
 * number of attempts to find the critical floor in the worst case. The critical floor is the
 * highest floor from which an egg will not break when dropped.
 */
public class EggDropping {

    // Approach 1: Memoization (Top-Down)
    // Time Complexity: O(k * n^2)
    // Space Complexity: O(k * n) for the memoization table + O(k + n) for recursion stack.
    public int eggDropMemo(int k, int n) {
        int[][] memo = new int[k + 1][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return solve(k, n, memo);
    }

    private int solve(int eggs, int floors, int[][] memo) {
        if (floors == 0 || floors == 1) {
            return floors;
        }
        if (eggs == 1) {
            return floors;
        }
        if (memo[eggs][floors] != -1) {
            return memo[eggs][floors];
        }

        int minAttempts = Integer.MAX_VALUE;
        for (int f = 1; f <= floors; f++) {
            // Worst case: egg breaks or doesn't break
            int attempts = 1 + Math.max(solve(eggs - 1, f - 1, memo), solve(eggs, floors - f, memo));
            minAttempts = Math.min(minAttempts, attempts);
        }
        memo[eggs][floors] = minAttempts;
        return minAttempts;
    }

    // Approach 2: Tabulation (Bottom-Up)
    // Time Complexity: O(k * n^2)
    // Space Complexity: O(k * n) for the DP table.
    public int eggDropTab(int k, int n) {
        int[][] dp = new int[k + 1][n + 1];

        for (int i = 1; i <= k; i++) {
            dp[i][1] = 1;
        }
        for (int j = 1; j <= n; j++) {
            dp[1][j] = j;
        }

        for (int i = 2; i <= k; i++) {
            for (int j = 2; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int f = 1; f <= j; f++) {
                    int res = 1 + Math.max(dp[i - 1][f - 1], dp[i][j - f]);
                    if (res < dp[i][j]) {
                        dp[i][j] = res;
                    }
                }
            }
        }
        return dp[k][n];
    }

    public static void main(String[] args) {
        EggDropping eggDropper = new EggDropping();
        int k = 2;
        int n = 10;

        System.out.println("Minimum attempts with " + k + " eggs and " + n + " floors (Memoization): " + eggDropper.eggDropMemo(k, n));
        System.out.println("Minimum attempts with " + k + " eggs and " + n + " floors (Tabulation): " + eggDropper.eggDropTab(k, n));

        int k2 = 3;
        int n2 = 14;

        System.out.println("Minimum attempts with " + k2 + " eggs and " + n2 + " floors (Memoization): " + eggDropper.eggDropMemo(k2, n2));
        System.out.println("Minimum attempts with " + k2 + " eggs and " + n2 + " floors (Tabulation): " + eggDropper.eggDropTab(k2, n2));
    }
}
