# Fibonacci Number - Dynamic Programming

"""
Problem:
The Fibonacci numbers, commonly denoted F(n), form a sequence, called the Fibonacci sequence, 
such that each number is the sum of the two preceding ones, starting from 0 and 1.

F(0) = 0, F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n > 1.

Given n, calculate F(n).
"""

class Fibonacci:
    # Approach 1: Memoization (Top-Down)
    # Time Complexity: O(n) - Each number from 2 to n is computed once.
    # Space Complexity: O(n) - For the memoization dictionary and recursion stack.
    def fib_memo(self, n: int) -> int:
        memo = {}
        def solve(i):
            if i in memo:
                return memo[i]
            if i <= 1:
                return i
            memo[i] = solve(i - 1) + solve(i - 2)
            return memo[i]
        return solve(n)

    # Approach 2: Tabulation (Bottom-Up)
    # Time Complexity: O(n) - We iterate from 2 to n.
    # Space Complexity: O(n) - For the dp array.
    def fib_tab(self, n: int) -> int:
        if n <= 1:
            return n
        dp = [0] * (n + 1)
        dp[1] = 1
        for i in range(2, n + 1):
            dp[i] = dp[i - 1] + dp[i - 2]
        return dp[n]

    # Approach 3: Space Optimized Tabulation
    # Time Complexity: O(n) - Single loop up to n.
    # Space Complexity: O(1) - Only three variables are used.
    def fib_space_optimized(self, n: int) -> int:
        if n <= 1:
            return n
        prev2 = 0
        prev1 = 1
        for _ in range(2, n + 1):
            current = prev1 + prev2
            prev2 = prev1
            prev1 = current
        return prev1

# Test cases
if __name__ == "__main__":
    fib_solver = Fibonacci()
    n = 10
    print(f"Fibonacci of {n} using Memoization: {fib_solver.fib_memo(n)}")
    print(f"Fibonacci of {n} using Tabulation: {fib_solver.fib_tab(n)}")
    print(f"Fibonacci of {n} using Space Optimized Tabulation: {fib_solver.fib_space_optimized(n)}")
    
    n = 2
    print(f"Fibonacci of {n} using Memoization: {fib_solver.fib_memo(n)}")
    print(f"Fibonacci of {n} using Tabulation: {fib_solver.fib_tab(n)}")
    print(f"Fibonacci of {n} using Space Optimized Tabulation: {fib_solver.fib_space_optimized(n)}")
