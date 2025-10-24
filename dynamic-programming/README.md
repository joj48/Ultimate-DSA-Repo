# Dynamic Programming

## Overview
Dynamic Programming (DP) is a powerful algorithmic technique for solving complex problems by breaking them down into simpler, overlapping subproblems. Solutions to subproblems are stored to avoid redundant computations, leading to significant performance improvements.

## Key Concepts
- **Overlapping Subproblems**: A problem has overlapping subproblems if it can be broken down into subproblems that are reused multiple times.
- **Optimal Substructure**: A problem has optimal substructure if an optimal solution can be constructed from optimal solutions of its subproblems.

## Two Main Approaches
1.  **Memoization (Top-Down)**: A recursive approach where solutions to subproblems are cached. The function is called recursively, and if a subproblem's solution is already in the cache, it's returned directly.
2.  **Tabulation (Bottom-Up)**: An iterative approach where solutions are built from the ground up. Subproblems are solved in a specific order, and their results are stored in a table (usually an array or matrix).

## Common Problem Patterns
- 0/1 Knapsack
- Unbounded Knapsack
- Longest Common Subsequence
- Longest Increasing Subsequence
- Matrix Chain Multiplication
- Fibonacci and other sequence-based problems
- Problems on grids (e.g., unique paths)
- **Longest Increasing Subsequence**
    -   [Python](./longest_increasing_subsequence.py)
    -   [Java](./longest_increasing_subsequence.java)
- **Rod Cutting**
    -   [Python](./rod_cutting.py)
    -   [Java](./rod_cutting.java)
- **Matrix Chain Multiplication**
    -   [Python](./matrix_chain_multiplication.py)
    -   [Java](./matrix_chain_multiplication.java)
- **Egg Dropping Puzzle**
    -   [Python](./egg_dropping.py)
    -   [Java](./egg_dropping.java)
- **Word Break**
    -   [Python](./word_break.py)
    -   [Java](./word_break.java)
- **Partition Equal Subset Sum**
    -   [Python](./partition_equal_subset_sum.py)
    -   [Java](./partition_equal_subset_sum.java)
- **Minimum Path Sum**
    -   [Python](./minimum_path_sum.py)
    -   [Java](./minimum_path_sum.java)

## Files in this directory
- `fibonacci_number.py` / `fibonacci_number.java` - Solutions for the Fibonacci sequence using memoization and tabulation.
- `climbing_stairs.py` / `climbing_stairs.java` - Classic DP problem for counting ways to climb stairs.
- `knapsack_01.py` / `knapsack_01.java` - Solutions for the 0/1 Knapsack problem.
- `longest_common_subsequence.py` / `longest_common_subsequence.java` - Finding the longest common subsequence between two strings.
- `coin_change.py` / `coin_change.java` - Finding the number of ways to make change for a given amount.
- `longest_increasing_subsequence.py` / `longest_increasing_subsequence.java` - Finding the longest increasing subsequence in an array.