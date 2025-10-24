# Longest Common Subsequence - Dynamic Programming

"""
Problem:
Given two strings text1 and text2, return the length of their longest common subsequence.
A subsequence of a string is a new string generated from the original string with some 
characters (can be none) deleted without changing the relative order of the remaining characters.
"""

class LongestCommonSubsequence:
    # Approach 1: Memoization (Top-Down)
    # Time Complexity: O(m * n), where m and n are the lengths of the strings.
    # Space Complexity: O(m * n) for the memoization table + O(m + n) for recursion stack.
    def lcs_memo(self, text1: str, text2: str) -> int:
        m, n = len(text1), len(text2)
        memo = {}

        def solve(i, j):
            if (i, j) in memo:
                return memo[(i, j)]
            
            if i >= m or j >= n:
                return 0

            if text1[i] == text2[j]:
                result = 1 + solve(i + 1, j + 1)
            else:
                result = max(solve(i + 1, j), solve(i, j + 1))
            
            memo[(i, j)] = result
            return result

        return solve(0, 0)

    # Approach 2: Tabulation (Bottom-Up)
    # Time Complexity: O(m * n)
    # Space Complexity: O(m * n) for the DP table.
    def lcs_tab(self, text1: str, text2: str) -> int:
        m, n = len(text1), len(text2)
        dp = [[0 for _ in range(n + 1)] for _ in range(m + 1)]

        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if text1[i-1] == text2[j-1]:
                    dp[i][j] = 1 + dp[i-1][j-1]
                else:
                    dp[i][j] = max(dp[i-1][j], dp[i][j-1])
        
        return dp[m][n]

# Test cases
if __name__ == "__main__":
    lcs_solver = LongestCommonSubsequence()
    text1 = "abcde"
    text2 = "ace"
    
    print(f"LCS of '{text1}' and '{text2}' (Memoization): {lcs_solver.lcs_memo(text1, text2)}")
    print(f"LCS of '{text1}' and '{text2}' (Tabulation): {lcs_solver.lcs_tab(text1, text2)}")

    text1 = "abc"
    text2 = "abc"
    
    print(f"LCS of '{text1}' and '{text2}' (Memoization): {lcs_solver.lcs_memo(text1, text2)}")
    print(f"LCS of '{text1}' and '{text2}' (Tabulation): {lcs_solver.lcs_tab(text1, text2)}")

    text1 = "abc"
    text2 = "def"
    
    print(f"LCS of '{text1}' and '{text2}' (Memoization): {lcs_solver.lcs_memo(text1, text2)}")
    print(f"LCS of '{text1}' and '{text2}' (Tabulation): {lcs_solver.lcs_tab(text1, text2)}")
