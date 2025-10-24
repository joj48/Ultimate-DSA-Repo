import java.util.HashMap;
import java.util.Map;

/**
 * Problem:
 * The Fibonacci numbers, commonly denoted F(n), form a sequence, called the Fibonacci sequence,
 * such that each number is the sum of the two preceding ones, starting from 0 and 1.
 *
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 *
 * Given n, calculate F(n).
 */
public class FibonacciNumber {

    // Approach 1: Memoization (Top-Down)
    // Time Complexity: O(n) - Each number from 2 to n is computed once.
    // Space Complexity: O(n) - For the memoization map and recursion stack.
    private Map<Integer, Integer> memo = new HashMap<>();

    public int fibMemo(int n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        if (n <= 1) {
            return n;
        }
        int result = fibMemo(n - 1) + fibMemo(n - 2);
        memo.put(n, result);
        return result;
    }

    // Approach 2: Tabulation (Bottom-Up)
    // Time Complexity: O(n) - We iterate from 2 to n.
    // Space Complexity: O(n) - For the dp array.
    public int fibTab(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // Approach 3: Space Optimized Tabulation
    // Time Complexity: O(n) - Single loop up to n.
    // Space Complexity: O(1) - Only three variables are used.
    public int fibSpaceOptimized(int n) {
        if (n <= 1) {
            return n;
        }
        int prev2 = 0;
        int prev1 = 1;
        for (int i = 2; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

    // Test cases
    public static void main(String[] args) {
        FibonacciNumber fibSolver = new FibonacciNumber();
        int n = 10;
        System.out.println("Fibonacci of " + n + " using Memoization: " + fibSolver.fibMemo(n));
        
        // Reset memo for the next call if the instance is reused.
        fibSolver.memo.clear();

        System.out.println("Fibonacci of " + n + " using Tabulation: " + fibSolver.fibTab(n));
        System.out.println("Fibonacci of " + n + " using Space Optimized Tabulation: " + fibSolver.fibSpaceOptimized(n));

        n = 2;
        System.out.println("Fibonacci of " + n + " using Memoization: " + fibSolver.fibMemo(n));
        System.out.println("Fibonacci of " + n + " using Tabulation: " + fibSolver.fibTab(n));
        System.out.println("Fibonacci of " + n + " using Space Optimized Tabulation: " + fibSolver.fibSpaceOptimized(n));
    }
}