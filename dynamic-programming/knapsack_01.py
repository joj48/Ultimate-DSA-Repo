# 0/1 Knapsack Problem - Dynamic Programming

"""
Problem:
Given a set of items, each with a weight and a value, determine the number of each item 
to include in a collection so that the total weight is less than or equal to a given limit 
and the total value is as large as possible. You cannot break an item.
"""

class Knapsack01:
    # Approach 1: Memoization (Top-Down)
    # Time Complexity: O(N * W), where N is the number of items and W is the capacity.
    # Space Complexity: O(N * W) for the memoization table + O(N) for recursion stack.
    def knapsack_memo(self, weights, values, W):
        n = len(weights)
        memo = {}

        def solve(index, capacity):
            if (index, capacity) in memo:
                return memo[(index, capacity)]
            
            if index < 0 or capacity <= 0:
                return 0

            # If weight of the current item is more than knapsack capacity, then
            # this item cannot be included in the optimal solution.
            if weights[index] > capacity:
                result = solve(index - 1, capacity)
            else:
                # Return the maximum of two cases:
                # (1) nth item included
                # (2) not included
                include_item = values[index] + solve(index - 1, capacity - weights[index])
                exclude_item = solve(index - 1, capacity)
                result = max(include_item, exclude_item)
            
            memo[(index, capacity)] = result
            return result

        return solve(n - 1, W)

    # Approach 2: Tabulation (Bottom-Up)
    # Time Complexity: O(N * W)
    # Space Complexity: O(N * W) for the DP table.
    def knapsack_tab(self, weights, values, W):
        n = len(weights)
        dp = [[0 for _ in range(W + 1)] for _ in range(n + 1)]

        for i in range(1, n + 1):
            for w in range(1, W + 1):
                # If the current item's weight is less than or equal to the current capacity
                if weights[i-1] <= w:
                    # Max of including the item and not including it
                    dp[i][w] = max(values[i-1] + dp[i-1][w - weights[i-1]], dp[i-1][w])
                else:
                    # If the item's weight is more than capacity, we can't include it
                    dp[i][w] = dp[i-1][w]
        
        return dp[n][W]

# Test cases
if __name__ == "__main__":
    knapsack_solver = Knapsack01()
    values = [60, 100, 120]
    weights = [10, 20, 30]
    W = 50
    
    print(f"Maximum value (Memoization): {knapsack_solver.knapsack_memo(weights, values, W)}")
    print(f"Maximum value (Tabulation): {knapsack_solver.knapsack_tab(weights, values, W)}")

    values = [10, 40, 30, 50]
    weights = [5, 4, 6, 3]
    W = 10
    
    print(f"Maximum value (Memoization): {knapsack_solver.knapsack_memo(weights, values, W)}")
    print(f"Maximum value (Tabulation): {knapsack_solver.knapsack_tab(weights, values, W)}")
