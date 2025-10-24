# Rod Cutting - Dynamic Programming

"""
Problem:
Given a rod of length n inches and an array of prices that includes prices of all pieces of size smaller than n.
Determine the maximum value obtainable by cutting up the rod and selling the pieces.
"""

class RodCutting:
    # Approach 1: Memoization (Top-Down)
    # Time Complexity: O(n^2)
    # Space Complexity: O(n) for the memoization table + O(n) for recursion stack.
    def cutRodMemo(self, prices: list[int], n: int) -> int:
        memo = {}

        def solve(length):
            if length in memo:
                return memo[length]
            if length <= 0:
                return 0

            max_val = -1
            for i in range(length):
                max_val = max(max_val, prices[i] + solve(length - (i + 1)))
            
            memo[length] = max_val
            return max_val

        return solve(n)

    # Approach 2: Tabulation (Bottom-Up)
    # Time Complexity: O(n^2)
    # Space Complexity: O(n) for the DP table.
    def cutRodTab(self, prices: list[int], n: int) -> int:
        val = [0] * (n + 1)

        for i in range(1, n + 1):
            max_val = -1
            for j in range(i):
                max_val = max(max_val, prices[j] + val[i - (j + 1)])
            val[i] = max_val
        
        return val[n]

# Test cases
if __name__ == "__main__":
    rod_cutter = RodCutting()
    prices = [1, 5, 8, 9, 10, 17, 17, 20]
    n = 8
    
    print(f"Maximum obtainable value for rod of length {n} (Memoization): {rod_cutter.cutRodMemo(prices, n)}")
    print(f"Maximum obtainable value for rod of length {n} (Tabulation): {rod_cutter.cutRodTab(prices, n)}")

    prices2 = [3, 5, 8, 9, 10, 17, 17, 20]
    n2 = 8
    
    print(f"Maximum obtainable value for rod of length {n2} (Memoization): {rod_cutter.cutRodMemo(prices2, n2)}")
    print(f"Maximum obtainable value for rod of length {n2} (Tabulation): {rod_cutter.cutRodTab(prices2, n2)}")
