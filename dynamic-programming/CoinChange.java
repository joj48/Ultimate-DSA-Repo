import java.util.Arrays;

/**
 * Problem:
 * You are given an integer array coins representing coins of different denominations and an integer amount
 * representing a total amount of money. Return the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 */
public class CoinChange {

    // Approach 1: Memoization (Top-Down)
    // Time Complexity: O(amount * n), where n is the number of coins.
    // Space Complexity: O(amount) for the memoization table + O(amount) for recursion stack.
    public int coinChangeMemo(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        int result = solve(coins, amount, memo);
        return (result == Integer.MAX_VALUE) ? -1 : result;
    }

    private int solve(int[] coins, int target, int[] memo) {
        if (target == 0) {
            return 0;
        }
        if (target < 0) {
            return Integer.MAX_VALUE;
        }
        if (memo[target] != 0) {
            return memo[target];
        }

        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = solve(coins, target - coin, memo);
            if (res != Integer.MAX_VALUE) {
                minCoins = Math.min(minCoins, 1 + res);
            }
        }
        memo[target] = minCoins;
        return minCoins;
    }

    // Approach 2: Tabulation (Bottom-Up)
    // Time Complexity: O(amount * n)
    // Space Complexity: O(amount) for the DP table.
    public int coinChangeTab(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // Use a value larger than amount
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }
        return (dp[amount] > amount) ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChange coinChanger = new CoinChange();
        int[] coins = {1, 2, 5};
        int amount = 11;

        System.out.println("Fewest coins for amount " + amount + " (Memoization): " + coinChanger.coinChangeMemo(coins, amount));
        System.out.println("Fewest coins for amount " + amount + " (Tabulation): " + coinChanger.coinChangeTab(coins, amount));

        int[] coins2 = {2};
        int amount2 = 3;

        System.out.println("Fewest coins for amount " + amount2 + " (Memoization): " + coinChanger.coinChangeMemo(coins2, amount2));
        System.out.println("Fewest coins for amount " + amount2 + " (Tabulation): " + coinChanger.coinChangeTab(coins2, amount2));

        int[] coins3 = {1};
        int amount3 = 0;

        System.out.println("Fewest coins for amount " + amount3 + " (Memoization): " + coinChanger.coinChangeMemo(coins3, amount3));
        System.out.println("Fewest coins for amount " + amount3 + " (Tabulation): " + coinChanger.coinChangeTab(coins3, amount3));
    }
}
