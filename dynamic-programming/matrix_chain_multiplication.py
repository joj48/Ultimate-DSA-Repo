# Matrix Chain Multiplication - Dynamic Programming

"""
Problem:
Given a sequence of matrices, find the most efficient way to multiply these matrices together.
The problem is not actually to perform the multiplications, but merely to decide in which order
to perform the multiplications.
"""

import sys

class MatrixChainMultiplication:
    # Approach 1: Memoization (Top-Down)
    # Time Complexity: O(n^3)
    # Space Complexity: O(n^2) for the memoization table + O(n) for recursion stack.
    def matrixChainOrderMemo(self, p: list[int]) -> int:
        n = len(p) - 1
        memo = {}

        def solve(i, j):
            if (i, j) in memo:
                return memo[(i, j)]
            if i == j:
                return 0

            min_cost = sys.maxsize
            for k in range(i, j):
                cost = solve(i, k) + solve(k + 1, j) + p[i-1] * p[k] * p[j]
                min_cost = min(min_cost, cost)
            
            memo[(i, j)] = min_cost
            return min_cost

        return solve(1, n)

    # Approach 2: Tabulation (Bottom-Up)
    # Time Complexity: O(n^3)
    # Space Complexity: O(n^2) for the DP table.
    def matrixChainOrderTab(self, p: list[int]) -> int:
        n = len(p)
        m = [[0] * n for _ in range(n)]

        for L in range(2, n):
            for i in range(1, n - L + 1):
                j = i + L - 1
                m[i][j] = sys.maxsize
                for k in range(i, j):
                    q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j]
                    if q < m[i][j]:
                        m[i][j] = q
        
        return m[1][n - 1]

# Test cases
if __name__ == "__main__":
    mcm_solver = MatrixChainMultiplication()
    arr = [1, 2, 3, 4]
    
    print(f"Minimum number of multiplications is (Memoization): {mcm_solver.matrixChainOrderMemo(arr)}")
    print(f"Minimum number of multiplications is (Tabulation): {mcm_solver.matrixChainOrderTab(arr)}")

    arr2 = [40, 20, 30, 10, 30]
    
    print(f"Minimum number of multiplications is (Memoization): {mcm_solver.matrixChainOrderMemo(arr2)}")
    print(f"Minimum number of multiplications is (Tabulation): {mcm_solver.matrixChainOrderTab(arr2)}")
