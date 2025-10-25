# Egg Dropping Puzzle - Dynamic Programming

"""
Problem:
You are given k identical eggs and a building with n floors. You need to find the minimum
number of attempts to find the critical floor in the worst case. The critical floor is the
highest floor from which an egg will not break when dropped.
"""

import sys

class EggDropping:
    # Approach 1: Memoization (Top-Down)
    # Time Complexity: O(k * n^2)
    # Space Complexity: O(k * n) for the memoization table + O(k + n) for recursion stack.
    def eggDropMemo(self, k: int, n: int) -> int:
        memo = {}

        def solve(eggs, floors):
            if (eggs, floors) in memo:
                return memo[(eggs, floors)]
            if floors == 0 or floors == 1:
                return floors
            if eggs == 1:
                return floors

            min_attempts = sys.maxsize
            for f in range(1, floors + 1):
                # Worst case: egg breaks or doesn't break
                attempts = 1 + max(solve(eggs - 1, f - 1), solve(eggs, floors - f))
                min_attempts = min(min_attempts, attempts)
            
            memo[(eggs, floors)] = min_attempts
            return min_attempts

        return solve(k, n)

    # Approach 2: Tabulation (Bottom-Up)
    # Time Complexity: O(k * n^2)
    # Space Complexity: O(k * n) for the DP table.
    def eggDropTab(self, k: int, n: int) -> int:
        dp = [[0] * (n + 1) for _ in range(k + 1)]

        for i in range(1, k + 1):
            dp[i][1] = 1
        for j in range(1, n + 1):
            dp[1][j] = j

        for i in range(2, k + 1):
            for j in range(2, n + 1):
                dp[i][j] = sys.maxsize
                for f in range(1, j + 1):
                    res = 1 + max(dp[i - 1][f - 1], dp[i][j - f])
                    if res < dp[i][j]:
                        dp[i][j] = res
        
        return dp[k][n]

# Test cases
if __name__ == "__main__":
    egg_dropper = EggDropping()
    k = 2
    n = 10
    
    print(f"Minimum attempts with {k} eggs and {n} floors (Memoization): {egg_dropper.eggDropMemo(k, n)}")
    print(f"Minimum attempts with {k} eggs and {n} floors (Tabulation): {egg_dropper.eggDropTab(k, n)}")

    k2 = 3
    n2 = 14
    
    print(f"Minimum attempts with {k2} eggs and {n2} floors (Memoization): {egg_dropper.eggDropMemo(k2, n2)}")
    print(f"Minimum attempts with {k2} eggs and {n2} floors (Tabulation): {egg_dropper.eggDropTab(k2, n2)}")
