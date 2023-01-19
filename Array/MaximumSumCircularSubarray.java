/*
Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.

A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is 
nums[(i - 1 + n) % n].

A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with 
k1 % n == k2 % n.

Example 1:

Input: nums = [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3.

Example 2:

Input: nums = [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.

Example 3:

Input: nums = [-3,-2,-3]
Output: -2
Explanation: Subarray [-2] has maximum sum -2.


METHOD 1:(KADANE'S ALGORITHM)
	APPROACH:
		We can use simple kadane's algortihm but we have to check for eacj and every subarray as the array is circular in nature.

TIME: O(N^2).

METHOD 2:(KADANE'S ALGORITHM WITH A TWIST)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        
        int totalSum = 0;
        int curMaxSum = 0, maxSubarraySum = nums[0];
        int curMinSum = 0, maxMinSubarraySum = nums[0];

        for(int num : nums) {
            curMaxSum = Math.max(curMaxSum + num, num); // continue with the previous sum or start a new subarray sum
            maxSubarraySum = Math.max(maxSubarraySum, curMaxSum);
            curMinSum = Math.min(curMinSum + num, num);
            maxMinSubarraySum = Math.min(maxMinSubarraySum, curMinSum); // keep track of the maximum min subarray sum
            totalSum += num;
        }
	   
	   // CORNER CASE:
         // difference b/w (totalSum - maximum minimum subarray sum) = 0, it means that the subarray contains negative
        // elements only, it that case return the maximum Subarray sum as we can't return 0
        if(totalSum - maxMinSubarraySum == 0) {
            return maxSubarraySum;
        }

        return Math.max(maxSubarraySum, totalSum - maxMinSubarraySum);
    }
}