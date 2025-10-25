# Coin Change - Dynamic Programming

"""
Problem:
You are given an integer array coins representing coins of different denominations and an integer amount
representing a total amount of money. Return the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.
You may assume that you have an infinite number of each kind of coin.
"""

import sys

class CoinChange:
    # Approach 1: Memoization (Top-Down)
    # Time Complexity: O(amount * n), where n is the number of coins.
    # Space Complexity: O(amount) for the memoization table + O(amount) for recursion stack.
    def coinChangeMemo(self, coins: list[int], amount: int) -> int:
        memo = {}

        def solve(target):
            if target in memo:
                return memo[target]
            if target == 0:
                return 0
            if target < 0:
                return sys.maxsize

            min_coins = sys.maxsize
            for coin in coins:
                res = solve(target - coin)
                if res != sys.maxsize:
                    min_coins = min(min_coins, 1 + res)
            
            memo[target] = min_coins
            return min_coins

        result = solve(amount)
        return result if result != sys.maxsize else -1

    # Approach 2: Tabulation (Bottom-Up)
    # Time Complexity: O(amount * n)
    # Space Complexity: O(amount) for the DP table.
    def coinChangeTab(self, coins: list[int], amount: int) -> int:
        dp = [sys.maxsize] * (amount + 1)
        dp[0] = 0

        for i in range(1, amount + 1):
            for coin in coins:
                if i - coin >= 0 and dp[i - coin] != sys.maxsize:
                    dp[i] = min(dp[i], 1 + dp[i - coin])
        
        return dp[amount] if dp[amount] != sys.maxsize else -1

# Test cases
if __name__ == "__main__":
    coin_changer = CoinChange()
    coins = [1, 2, 5]
    amount = 11
    
    print(f"Fewest coins for amount {amount} (Memoization): {coin_changer.coinChangeMemo(coins, amount)}")
    print(f"Fewest coins for amount {amount} (Tabulation): {coin_changer.coinChangeTab(coins, amount)}")

    coins = [2]
    amount = 3
    
    print(f"Fewest coins for amount {amount} (Memoization): {coin_changer.coinChangeMemo(coins, amount)}")
    print(f"Fewest coins for amount {amount} (Tabulation): {coin_changer.coinChangeTab(coins, amount)}")

    coins = [1]
    amount = 0
    
    print(f"Fewest coins for amount {amount} (Memoization): {coin_changer.coinChangeMemo(coins, amount)}")
    print(f"Fewest coins for amount {amount} (Tabulation): {coin_changer.coinChangeTab(coins, amount)}")
