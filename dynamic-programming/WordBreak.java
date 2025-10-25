import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Problem:
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a
 * space-separated sequence of one or more dictionary words.
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 */
public class WordBreak {

    // Approach 1: Memoization (Top-Down)
    // Time Complexity: O(n^2 * m), where n is the length of the string and m is the length of the dictionary.
    // Space Complexity: O(n) for the memoization table + O(n) for recursion stack.
    public boolean wordBreakMemo(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[s.length()];
        return solve(s, wordSet, 0, memo);
    }

    private boolean solve(String s, Set<String> wordSet, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }

        for (int end = start + 1; end <= s.length(); end++) {
            if (wordSet.contains(s.substring(start, end)) && solve(s, wordSet, end, memo)) {
                memo[start] = true;
                return true;
            }
        }
        memo[start] = false;
        return false;
    }

    // Approach 2: Tabulation (Bottom-Up)
    // Time Complexity: O(n^2 * m)
    // Space Complexity: O(n) for the DP table.
    public boolean wordBreakTab(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        WordBreak wordBreaker = new WordBreak();
        String s = "leetcode";
        List<String> wordDict = List.of("leet", "code");

        System.out.println("Can '" + s + "' be segmented? (Memoization): " + wordBreaker.wordBreakMemo(s, wordDict));
        System.out.println("Can '" + s + "' be segmented? (Tabulation): " + wordBreaker.wordBreakTab(s, wordDict));

        String s2 = "applepenapple";
        List<String> wordDict2 = List.of("apple", "pen");

        System.out.println("Can '" + s2 + "' be segmented? (Memoization): " + wordBreaker.wordBreakMemo(s2, wordDict2));
        System.out.println("Can '" + s2 + "' be segmented? (Tabulation): " + wordBreaker.wordBreakTab(s2, wordDict2));

        String s3 = "catsandog";
        List<String> wordDict3 = List.of("cats", "dog", "sand", "and", "cat");

        System.out.println("Can '" + s3 + "' be segmented? (Memoization): " + wordBreaker.wordBreakMemo(s3, wordDict3));
        System.out.println("Can '" + s3 + "' be segmented? (Tabulation): " + wordBreaker.wordBreakTab(s3, wordDict3));
    }
}
