import java.util.Arrays;

/**
 * Problem:
 * Given a non-empty array nums containing only positive integers, find if the array can be
 * partitioned into two subsets such that the sum of elements in both subsets is equal.
 */
public class PartitionEqualSubsetSum {

    // Approach 1: Memoization (Top-Down)
    // Time Complexity: O(n * sum), where n is the number of elements and sum is the total sum.
    // Space Complexity: O(n * sum) for the memoization table + O(n) for recursion stack.
    public boolean canPartitionMemo(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        if (totalSum % 2 != 0) {
            return false;
        }

        int target = totalSum / 2;
        Boolean[][] memo = new Boolean[nums.length][target + 1];
        return solve(nums, 0, target, memo);
    }

    private boolean solve(int[] nums, int index, int target, Boolean[][] memo) {
        if (target == 0) {
            return true;
        }
        if (index >= nums.length || target < 0) {
            return false;
        }
        if (memo[index][target] != null) {
            return memo[index][target];
        }

        // Include nums[index] or not
        boolean result = solve(nums, index + 1, target - nums[index], memo) || solve(nums, index + 1, target, memo);
        memo[index][target] = result;
        return result;
    }

    // Approach 2: Tabulation (Bottom-Up)
    // Time Complexity: O(n * sum)
    // Space Complexity: O(sum) for the DP table.
    public boolean canPartitionTab(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        if (totalSum % 2 != 0) {
            return false;
        }

        int target = totalSum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        PartitionEqualSubsetSum partitionSolver = new PartitionEqualSubsetSum();
        int[] nums1 = {1, 5, 11, 5};

        System.out.println("Can partition " + Arrays.toString(nums1) + "? (Memoization): " + partitionSolver.canPartitionMemo(nums1));
        System.out.println("Can partition " + Arrays.toString(nums1) + "? (Tabulation): " + partitionSolver.canPartitionTab(nums1));

        int[] nums2 = {1, 2, 3, 5};

        System.out.println("Can partition " + Arrays.toString(nums2) + "? (Memoization): " + partitionSolver.canPartitionMemo(nums2));
        System.out.println("Can partition " + Arrays.toString(nums2) + "? (Tabulation): " + partitionSolver.canPartitionTab(nums2));
    }
}
