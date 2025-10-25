import java.util.HashMap;
import java.util.Map;

/**
 * Problem:
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 */
public class ClimbingStairs {

    // Approach 1: Memoization (Top-Down)
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    private Map<Integer, Integer> memo = new HashMap<>();

    public int climbStairsMemo(int n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        if (n <= 2) {
            return n;
        }
        int result = climbStairsMemo(n - 1) + climbStairsMemo(n - 2);
        memo.put(n, result);
        return result;
    }

    // Approach 2: Tabulation (Bottom-Up)
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public int climbStairsTab(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // Approach 3: Space Optimized Tabulation
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int climbStairsSpaceOptimized(int n) {
        if (n <= 2) {
            return n;
        }
        int prev2 = 1;
        int prev1 = 2;
        for (int i = 3; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

    // Test cases
    public static void main(String[] args) {
        ClimbingStairs stairClimber = new ClimbingStairs();
        int nSteps = 5;
        System.out.println("Ways to climb " + nSteps + " stairs (Memoization): " + stairClimber.climbStairsMemo(nSteps));
        stairClimber.memo.clear(); // Reset for next call
        System.out.println("Ways to climb " + nSteps + " stairs (Tabulation): " + stairClimber.climbStairsTab(nSteps));
        System.out.println("Ways to climb " + nSteps + " stairs (Space Optimized): " + stairClimber.climbStairsSpaceOptimized(nSteps));

        nSteps = 3;
        System.out.println("Ways to climb " + nSteps + " stairs (Memoization): " + stairClimber.climbStairsMemo(nSteps));
        System.out.println("Ways to climb " + nSteps + " stairs (Tabulation): " + stairClimber.climbStairsTab(nSteps));
        System.out.println("Ways to climb " + nSteps + " stairs (Space Optimized): " + stairClimber.climbStairsSpaceOptimized(nSteps));
    }
}