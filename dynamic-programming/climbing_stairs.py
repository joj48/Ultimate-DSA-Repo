# Climbing Stairs - Dynamic Programming

"""
Problem:
You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
"""

class ClimbingStairs:
    # Approach 1: Memoization (Top-Down)
    # This is essentially a Fibonacci sequence problem.
    # Time Complexity: O(n) - Each step is computed once.
    # Space Complexity: O(n) - For the memoization dictionary and recursion stack.
    def climbStairs_memo(self, n: int) -> int:
        memo = {}
        def solve(i):
            if i in memo:
                return memo[i]
            if i == 0:
                return 1
            if i < 0:
                return 0
            memo[i] = solve(i - 1) + solve(i - 2)
            return memo[i]
        return solve(n)

    # Approach 2: Tabulation (Bottom-Up)
    # Time Complexity: O(n) - We iterate from 2 to n.
    # Space Complexity: O(n) - For the dp array.
    def climbStairs_tab(self, n: int) -> int:
        if n <= 2:
            return n
        dp = [0] * (n + 1)
        dp[1] = 1
        dp[2] = 2
        for i in range(3, n + 1):
            dp[i] = dp[i - 1] + dp[i - 2]
        return dp[n]

    # Approach 3: Space Optimized Tabulation
    # Time Complexity: O(n) - Single loop up to n.
    # Space Complexity: O(1) - Only a few variables are used.
    def climbStairs_space_optimized(self, n: int) -> int:
        if n <= 2:
            return n
        prev2 = 1
        prev1 = 2
        for _ in range(3, n + 1):
            current = prev1 + prev2
            prev2 = prev1
            prev1 = current
        return prev1

# Test cases
if __name__ == "__main__":
    stair_climber = ClimbingStairs()
    n_steps = 5
    print(f"Ways to climb {n_steps} stairs (Memoization): {stair_climber.climbStairs_memo(n_steps)}")
    print(f"Ways to climb {n_steps} stairs (Tabulation): {stair_climber.climbStairs_tab(n_steps)}")
    print(f"Ways to climb {n_steps} stairs (Space Optimized): {stair_climber.climbStairs_space_optimized(n_steps)}")

    n_steps = 3
    print(f"Ways to climb {n_steps} stairs (Memoization): {stair_climber.climbStairs_memo(n_steps)}")
    print(f"Ways to climb {n_steps} stairs (Tabulation): {stair_climber.climbStairs_tab(n_steps)}")
    print(f"Ways to climb {n_steps} stairs (Space Optimized): {stair_climber.climbStairs_space_optimized(n_steps)}")
