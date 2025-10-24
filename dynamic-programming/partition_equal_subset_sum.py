# Partition Equal Subset Sum - Dynamic Programming

"""
Problem:
Given a non-empty array nums containing only positive integers, find if the array can be
partitioned into two subsets such that the sum of elements in both subsets is equal.
"""

class PartitionEqualSubsetSum:
    # Approach 1: Memoization (Top-Down)
    # Time Complexity: O(n * sum), where n is the number of elements and sum is the total sum.
    # Space Complexity: O(n * sum) for the memoization table + O(n) for recursion stack.
    def canPartitionMemo(self, nums: list[int]) -> bool:
        total_sum = sum(nums)
        if total_sum % 2 != 0:
            return False
        
        target = total_sum // 2
        memo = {}

        def solve(index, current_sum):
            if (index, current_sum) in memo:
                return memo[(index, current_sum)]
            if current_sum == target:
                return True
            if current_sum > target or index >= len(nums):
                return False

            # Include nums[index] or not
            result = solve(index + 1, current_sum + nums[index]) or solve(index + 1, current_sum)
            memo[(index, current_sum)] = result
            return result

        return solve(0, 0)

    # Approach 2: Tabulation (Bottom-Up)
    # Time Complexity: O(n * sum)
    # Space Complexity: O(sum) for the DP table.
    def canPartitionTab(self, nums: list[int]) -> bool:
        total_sum = sum(nums)
        if total_sum % 2 != 0:
            return False
        
        target = total_sum // 2
        dp = [False] * (target + 1)
        dp[0] = True

        for num in nums:
            for i in range(target, num - 1, -1):
                dp[i] = dp[i] or dp[i - num]
        
        return dp[target]

# Test cases
if __name__ == "__main__":
    partition_solver = PartitionEqualSubsetSum()
    nums1 = [1, 5, 11, 5]
    
    print(f"Can partition {nums1}? (Memoization): {partition_solver.canPartitionMemo(nums1)}")
    print(f"Can partition {nums1}? (Tabulation): {partition_solver.canPartitionTab(nums1)}")

    nums2 = [1, 2, 3, 5]
    
    print(f"Can partition {nums2}? (Memoization): {partition_solver.canPartitionMemo(nums2)}")
    print(f"Can partition {nums2}? (Tabulation): {partition_solver.canPartitionTab(nums2)}")
