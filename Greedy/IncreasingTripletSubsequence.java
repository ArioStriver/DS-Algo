/*
Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.

Example 1:

Input: nums = [1,2,3,4,5]
Output: true

Explanation: Any triplet where i < j < k is valid.

Example 2:

Input: nums = [5,4,3,2,1]
Output: false

Explanation: No triplet exists.

Example 3:

Input: nums = [2,1,5,0,4,6]
Output: true

Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.


METHOD:(GREEDY)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public boolean increasingTriplet(int[] nums) {
        
        // there are three elements 
        // left(i) < mid(j) < right(k)
        // accd. to the question the valid triplet is when (left < mid && mid < right)
        
        int left = Integer.MAX_VALUE, mid = Integer.MAX_VALUE;
        
        // what we are doing here we are trying to decrease left & mid as much as possible 
        // so that right has a bigger range to be true
        for(int cur : nums) {
            
            // if the current element is greater than the mid element it means that it is the probable
            // element for the right and we found our triplet
            if(cur > mid) {
                return true;
            }
            // probable element for mid
            else if(cur < mid && cur > left) {
                mid = cur;
            }
            // find the left element first
            else if(cur < left) {
                left = cur;
            }
        }
        
        return false;
    }
}