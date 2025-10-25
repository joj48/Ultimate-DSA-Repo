# Longest Increasing Subsequence - Dynamic Programming

"""
Problem:
Given an integer array nums, return the length of the longest strictly increasing subsequence.
A subsequence is a sequence that can be derived from an array by deleting some or no elements
without changing the order of the remaining elements.
"""

class LongestIncreasingSubsequence:
    # Approach 1: Dynamic Programming (Tabulation)
    # Time Complexity: O(n^2), where n is the length of the array.
    # Space Complexity: O(n) for the DP table.
    def lengthOfLIS_tab(self, nums: list[int]) -> int:
        if not nums:
            return 0
        
        n = len(nums)
        dp = [1] * n

        for i in range(1, n):
            for j in range(i):
                if nums[i] > nums[j]:
                    dp[i] = max(dp[i], 1 + dp[j])
        
        return max(dp)

    # Approach 2: Optimized with Binary Search
    # Time Complexity: O(n log n)
    # Space Complexity: O(n) for storing the subsequence.
    def lengthOfLIS_optimized(self, nums: list[int]) -> int:
        if not nums:
            return 0
        
        sub = []
        for num in nums:
            if not sub or num > sub[-1]:
                sub.append(num)
            else:
                # Find the first element in sub that is >= num
                # and replace it with num. This keeps the sub array
                # sorted and allows for a longer subsequence to be formed.
                import bisect
                i = bisect.bisect_left(sub, num)
                sub[i] = num
        
        return len(sub)

# Test cases
if __name__ == "__main__":
    lis_solver = LongestIncreasingSubsequence()
    nums1 = [10, 9, 2, 5, 3, 7, 101, 18]
    
    print(f"LIS of {nums1} (Tabulation): {lis_solver.lengthOfLIS_tab(nums1)}")
    print(f"LIS of {nums1} (Optimized): {lis_solver.lengthOfLIS_optimized(nums1)}")

    nums2 = [0, 1, 0, 3, 2, 3]
    
    print(f"LIS of {nums2} (Tabulation): {lis_solver.lengthOfLIS_tab(nums2)}")
    print(f"LIS of {nums2} (Optimized): {lis_solver.lengthOfLIS_optimized(nums2)}")

    nums3 = [7, 7, 7, 7, 7, 7, 7]
    
    print(f"LIS of {nums3} (Tabulation): {lis_solver.lengthOfLIS_tab(nums3)}")
    print(f"LIS of {nums3} (Optimized): {lis_solver.lengthOfLIS_optimized(nums3)}")
