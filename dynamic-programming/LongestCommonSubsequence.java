import java.util.Arrays;

/**
 * Problem:
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * A subsequence of a string is a new string generated from the original string with some 
 * characters (can be none) deleted without changing the relative order of the remaining characters.
 */
public class LongestCommonSubsequence {

    // Approach 1: Memoization (Top-Down)
    // Time Complexity: O(m * n), where m and n are the lengths of the strings.
    // Space Complexity: O(m * n) for the memoization table + O(m + n) for recursion stack.
    public int lcsMemo(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return solve(text1, text2, 0, 0, memo);
    }

    private int solve(String text1, String text2, int i, int j, int[][] memo) {
        if (i >= text1.length() || j >= text2.length()) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (text1.charAt(i) == text2.charAt(j)) {
            memo[i][j] = 1 + solve(text1, text2, i + 1, j + 1, memo);
        } else {
            memo[i][j] = Math.max(solve(text1, text2, i + 1, j, memo), solve(text1, text2, i, j + 1, memo));
        }
        return memo[i][j];
    }

    // Approach 2: Tabulation (Bottom-Up)
    // Time Complexity: O(m * n)
    // Space Complexity: O(m * n) for the DP table.
    public int lcsTab(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcsSolver = new LongestCommonSubsequence();
        String text1 = "abcde";
        String text2 = "ace";

        System.out.println("LCS of '" + text1 + "' and '" + text2 + "' (Memoization): " + lcsSolver.lcsMemo(text1, text2));
        System.out.println("LCS of '" + text1 + "' and '" + text2 + "' (Tabulation): " + lcsSolver.lcsTab(text1, text2));

        text1 = "abc";
        text2 = "abc";

        System.out.println("LCS of '" + text1 + "' and '" + text2 + "' (Memoization): " + lcsSolver.lcsMemo(text1, text2));
        System.out.println("LCS of '" + text1 + "' and '" + text2 + "' (Tabulation): " + lcsSolver.lcsTab(text1, text2));

        text1 = "abc";
        text2 = "def";

        System.out.println("LCS of '" + text1 + "' and '" + text2 + "' (Memoization): " + lcsSolver.lcsMemo(text1, text2));
        System.out.println("LCS of '" + text1 + "' and '" + text2 + "' (Tabulation): " + lcsSolver.lcsTab(text1, text2));
    }
}
