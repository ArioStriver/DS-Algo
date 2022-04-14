/*
Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

A subarray is a contiguous subsequence of the array.

Example 1:

Input: nums = [2,3,-2,4]
Output: 6

Explanation: [2,3] has the largest product 6.

Example 2:

Input: nums = [-2,0,-1]
Output: 0

Explanation: The result cannot be 2, because [-2,-1] is not a subarray.


METHOD 1:(BRUTE FORCE)
	APPROACH:
		Check for each and every subarray and finad their max product. TIME: O(N^2).

METHOD 2:
	APPROACH:
		This is very similar to the " max cumulative sum subarray" problem. here you keep 2 values: the max cumulative product UP TO current element starting 
		from SOMEWHERE in the past, and the minimum cumuliative product UP TO current element . it would be easier to see the DP structure if we store these 2 
		values for each index, like maxProduct[i], minProduct[i] .

		At each new element, u could either add the new element to the existing product, or start fresh the product from current index (wipe out previous 
		results), hence the 2 Math.max() lines.

		If we see a negative number, the "candidate" for max should instead become the previous min product, because a bigger number multiplied by negative 
		becomes smaller, hence the swap().

TIME: O(N).

SPACE: O(1).
*/

class Solution {

    public int maxProduct(int[] nums) {
        
         // store the result that is the max we have found so far
        int maxProd = nums[0];
        
        // max_so_far/min_so_far stores the max/min product of
        // subarray that ends with the current number nums[i]
        int max_so_far = nums[0];
        int min_so_far = nums[0];
        
        for(int i = 1; i < nums.length; i++) {
            
            // multiplied by a negative makes big number smaller, small number bigger
            // so we redefine the extremums by swapping them
            if(nums[i] < 0){
                int temp = max_so_far;
                max_so_far = min_so_far;
                min_so_far = temp;
            }
            // max/min product for the current number is either the current number itself
            // or the max/min by the previous number times the current one
            max_so_far = Math.max(nums[i], max_so_far * nums[i]);
            min_so_far = Math.min(nums[i], min_so_far * nums[i]);
            
            // the newly computed max value is a candidate for our global result
            maxProd = Math.max(maxProd, max_so_far);
        }
        
        return maxProd;
    }
}