import java.util.Arrays;

/**
 * Problem:
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
 * which minimizes the sum of all numbers along its path.
 * You can only move either down or right at any point in time.
 */
public class MinimumPathSum {

    // Approach 1: Memoization (Top-Down)
    // Time Complexity: O(m * n)
    // Space Complexity: O(m * n) for the memoization table + O(m + n) for recursion stack.
    public int minPathSumMemo(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return solve(grid, 0, 0, memo);
    }

    private int solve(int[][] grid, int i, int j, int[][] memo) {
        int m = grid.length;
        int n = grid[0].length;

        if (i >= m || j >= n) {
            return Integer.MAX_VALUE;
        }
        if (i == m - 1 && j == n - 1) {
            return grid[i][j];
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int down = solve(grid, i + 1, j, memo);
        int right = solve(grid, i, j + 1, memo);

        memo[i][j] = grid[i][j] + Math.min(down, right);
        return memo[i][j];
    }

    // Approach 2: Tabulation (Bottom-Up)
    // Time Complexity: O(m * n)
    // Space Complexity: O(m * n) for the DP table. Can be optimized to O(n).
    public int minPathSumTab(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        MinimumPathSum pathSolver = new MinimumPathSum();
        int[][] grid1 = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};

        System.out.println("Minimum path sum for grid (Memoization): " + pathSolver.minPathSumMemo(grid1));
        System.out.println("Minimum path sum for grid (Tabulation): " + pathSolver.minPathSumTab(grid1));

        int[][] grid2 = {{1, 2, 3}, {4, 5, 6}};

        System.out.println("Minimum path sum for grid (Memoization): " + pathSolver.minPathSumMemo(grid2));
        System.out.println("Minimum path sum for grid (Tabulation): " + pathSolver.minPathSumTab(grid2));
    }
}
