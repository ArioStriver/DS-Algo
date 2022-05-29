/*
A peak element is an element that is strictly greater than its neighbors.

Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that nums[-1] = nums[n] = -∞.

You must write an algorithm that runs in O(log n) time.

Example 1:

Input: nums = [1,2,3,1]
Output: 2

Explanation: 3 is a peak element and your function should return the index number 2.

Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 5

Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.


METHOD 1: (LINEAR SEARCH)

TIME: O(N).

SPACE: O(1).
/*

public class Solution {
    public int findPeakElement(int[] nums) {

        for (int i = 0; i < nums.length - 1; i++) {

            if (nums[i] > nums[i + 1])
                	return i;
            }
        
    return nums.length - 1;
      }
}

/*
METHOD 2:(BINARY SEARCH)

TIME: O(logN).

SPACE: O(1).
*/

class Solution {
    public int findPeakElement(int[] nums) {
        
        int low = 0, high = nums.length - 1;
        
        while(low < high){
            
            int mid = (low + high) / 2;
            
	// rising slope
             // so the peak lies on its right part
            if(nums[mid] < nums[mid+1]){
                low = mid + 1;
            }
     // falling slope
            // so the peak lies on its left part
            else{
                high = mid;
            }
        }
        
        return low;
    }
}