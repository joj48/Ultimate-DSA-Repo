# Minimum Path Sum - Dynamic Programming

"""
Problem:
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
which minimizes the sum of all numbers along its path.
You can only move either down or right at any point in time.
"""

class MinimumPathSum:
    # Approach 1: Memoization (Top-Down)
    # Time Complexity: O(m * n)
    # Space Complexity: O(m * n) for the memoization table + O(m + n) for recursion stack.
    def minPathSumMemo(self, grid: list[list[int]]) -> int:
        m, n = len(grid), len(grid[0])
        memo = {}

        def solve(i, j):
            if (i, j) in memo:
                return memo[(i, j)]
            if i >= m or j >= n:
                return float('inf')
            if i == m - 1 and j == n - 1:
                return grid[i][j]

            result = grid[i][j] + min(solve(i + 1, j), solve(i, j + 1))
            memo[(i, j)] = result
            return result

        return solve(0, 0)

    # Approach 2: Tabulation (Bottom-Up)
    # Time Complexity: O(m * n)
    # Space Complexity: O(m * n) for the DP table. Can be optimized to O(n).
    def minPathSumTab(self, grid: list[list[int]]) -> int:
        m, n = len(grid), len(grid[0])
        dp = [[0] * n for _ in range(m)]
        dp[0][0] = grid[0][0]

        for i in range(1, m):
            dp[i][0] = dp[i-1][0] + grid[i][0]
        for j in range(1, n):
            dp[0][j] = dp[0][j-1] + grid[0][j]

        for i in range(1, m):
            for j in range(1, n):
                dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1])
        
        return dp[m-1][n-1]

# Test cases
if __name__ == "__main__":
    path_solver = MinimumPathSum()
    grid1 = [[1,3,1],[1,5,1],[4,2,1]]
    
    print(f"Minimum path sum for grid {grid1} (Memoization): {path_solver.minPathSumMemo(grid1)}")
    print(f"Minimum path sum for grid {grid1} (Tabulation): {path_solver.minPathSumTab(grid1)}")

    grid2 = [[1,2,3],[4,5,6]]
    
    print(f"Minimum path sum for grid {grid2} (Memoization): {path_solver.minPathSumMemo(grid2)}")
    print(f"Minimum path sum for grid {grid2} (Tabulation): {path_solver.minPathSumTab(grid2)}")
