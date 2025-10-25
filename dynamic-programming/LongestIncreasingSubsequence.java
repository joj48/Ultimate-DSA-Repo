import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Problem:
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements
 * without changing the order of the remaining elements.
 */
public class LongestIncreasingSubsequence {

    // Approach 1: Dynamic Programming (Tabulation)
    // Time Complexity: O(n^2), where n is the length of the array.
    // Space Complexity: O(n) for the DP table.
    public int lengthOfLISTab(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int maxLength = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }

    // Approach 2: Optimized with Binary Search
    // Time Complexity: O(n log n)
    // Space Complexity: O(n) for storing the subsequence.
    public int lengthOfLISOptimized(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        ArrayList<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > sub.get(sub.size() - 1)) {
                sub.add(num);
            } else {
                // Find the first element in sub that is >= num
                int j = Collections.binarySearch(sub, num);
                if (j < 0) {
                    j = -(j + 1);
                }
                sub.set(j, num);
            }
        }
        return sub.size();
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence lisSolver = new LongestIncreasingSubsequence();
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};

        System.out.println("LIS of " + Arrays.toString(nums1) + " (Tabulation): " + lisSolver.lengthOfLISTab(nums1));
        System.out.println("LIS of " + Arrays.toString(nums1) + " (Optimized): " + lisSolver.lengthOfLISOptimized(nums1));

        int[] nums2 = {0, 1, 0, 3, 2, 3};

        System.out.println("LIS of " + Arrays.toString(nums2) + " (Tabulation): " + lisSolver.lengthOfLISTab(nums2));
        System.out.println("LIS of " + Arrays.toString(nums2) + " (Optimized): " + lisSolver.lengthOfLISOptimized(nums2));

        int[] nums3 = {7, 7, 7, 7, 7, 7, 7};

        System.out.println("LIS of " + Arrays.toString(nums3) + " (Tabulation): " + lisSolver.lengthOfLISTab(nums3));
        System.out.println("LIS of " + Arrays.toString(nums3) + " (Optimized): " + lisSolver.lengthOfLISOptimized(nums3));
    }
}
