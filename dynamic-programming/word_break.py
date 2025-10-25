# Word Break - Dynamic Programming

"""
Problem:
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a
space-separated sequence of one or more dictionary words.
Note that the same word in the dictionary may be reused multiple times in the segmentation.
"""

class WordBreak:
    # Approach 1: Memoization (Top-Down)
    # Time Complexity: O(n^2 * m), where n is the length of the string and m is the length of the dictionary.
    # Space Complexity: O(n) for the memoization table + O(n) for recursion stack.
    def wordBreakMemo(self, s: str, wordDict: list[str]) -> bool:
        word_set = set(wordDict)
        memo = {}

        def solve(sub):
            if sub in memo:
                return memo[sub]
            if not sub:
                return True

            for i in range(1, len(sub) + 1):
                if sub[:i] in word_set and solve(sub[i:]):
                    memo[sub] = True
                    return True
            
            memo[sub] = False
            return False

        return solve(s)

    # Approach 2: Tabulation (Bottom-Up)
    # Time Complexity: O(n^2 * m)
    # Space Complexity: O(n) for the DP table.
    def wordBreakTab(self, s: str, wordDict: list[str]) -> bool:
        word_set = set(wordDict)
        n = len(s)
        dp = [False] * (n + 1)
        dp[0] = True

        for i in range(1, n + 1):
            for j in range(i):
                if dp[j] and s[j:i] in word_set:
                    dp[i] = True
                    break
        
        return dp[n]

# Test cases
if __name__ == "__main__":
    word_breaker = WordBreak()
    s = "leetcode"
    wordDict = ["leet", "code"]
    
    print(f"Can '{s}' be segmented? (Memoization): {word_breaker.wordBreakMemo(s, wordDict)}")
    print(f"Can '{s}' be segmented? (Tabulation): {word_breaker.wordBreakTab(s, wordDict)}")

    s2 = "applepenapple"
    wordDict2 = ["apple", "pen"]
    
    print(f"Can '{s2}' be segmented? (Memoization): {word_breaker.wordBreakMemo(s2, wordDict2)}")
    print(f"Can '{s2}' be segmented? (Tabulation): {word_breaker.wordBreakTab(s2, wordDict2)}")

    s3 = "catsandog"
    wordDict3 = ["cats", "dog", "sand", "and", "cat"]
    
    print(f"Can '{s3}' be segmented? (Memoization): {word_breaker.wordBreakMemo(s3, wordDict3)}")
    print(f"Can '{s3}' be segmented? (Tabulation): {word_breaker.wordBreakTab(s3, wordDict3)}")
