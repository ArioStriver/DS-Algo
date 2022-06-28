/*
Given an array of integers nums and an integer threshold, we will choose a positive integer divisor, divide all the array by it, and sum the division's result. Find the smallest divisor 
such that the result mentioned above is less than or equal to threshold.

Each result of the division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).

The test cases are generated so that there will be an answer.

Example 1:

Input: nums = [1,2,5,9], threshold = 6
Output: 5

Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1. If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2). 

Example 2:

Input: nums = [44,22,33,11,1], threshold = 5
Output: 44
 
Constraints:

1 <= nums.length <= 5 * 10^4
1 <= nums[i] <= 10^6
nums.length <= threshold <= 10^6


METHOD:(BINARY SEARCH)
	They give you an introduction to monotonic functions. That is binary search on answers, here the given array won’t be sorted, or for the fact a portion of the array won’t be sorted 
	either. However here you can get an idea how the answers monotonically increase or decrease according to the increase or decrease in input.

	Binary search the result.
		If the sum > threshold, the divisor is too small.
		If the sum <= threshold, the divisor is big enough.

TIME: O(NlogM), where M = max(A), N is the no. of elements in the array

SPACE: O(1).
*/

class Solution {
    
    private int findSum(int[] a, int div) {
        
        int sum = 0;
        for(int i : a) {
            sum += (i + div - 1) / div; 
        }
        return sum;
    }
    
    public int smallestDivisor(int[] nums, int threshold) {
        
        int maxEle = 0;
        
        for(int i : nums) {
            maxEle = Math.max(maxEle, i);
        }
        
        // the divisor value lies between 1 to max element in the array
        // bcz after that element we will get the same value or sum of the divisor's will be same
        int low = 1, high = maxEle;
        
        while(low <= high) {
            
            int mid = low + (high - low) / 2;
            
            int sum = findSum(nums, mid);
            
            // if the sum is greater than the threshold value
            // it means we have to increase the divisor value in order to get a smaller sum
            // smaller divisor means greater sum and greater divisor means smaller sum
            if(sum > threshold) {
                low = mid + 1;
            }
            // lets try for more smaller values 
            else {
                high = mid - 1;
            }
        }
        
        return low;
    }
}