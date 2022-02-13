/* Find the Duplicates

Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

There is only one repeated number in nums, return this repeated number.

You must solve the problem without modifying the array nums and uses only constant extra space.

Example 1:

Input: nums = [1,3,4,2,2]
Output: 2
Example 2:

Input: nums = [3,1,3,4,2]
Output: 3

METHOD 1:(USING SORTING)
	APPROACH:
		Simply sort the array  and then find the duplicate element.
TIME: O(NlogN).


METHOD 2: (USING HASHING)
	APPROACH:
		Simply count the frequency of each element and if any element having frequency greater than 1 it means it is the duplicate element.

TIME: O(N).

SPACE: O(N).

METHOD 3:(USING CYCLE DETECTION ALGO)
	
TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public int findDuplicate(int[] nums) {
        
        // phase 1:
        // so first we need to find the intersection point
        // where they meet each other
        int tortoise = nums[0], hare = nums[0];
        
        do{
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        }
        while(tortoise != hare);
        
        // in phase 2: we are giving another chace to tortoise
        // finding the entrance of the cycle
        tortoise = nums[0];
        
        while(tortoise != hare){
            tortoise = nums[tortoise];
            hare = nums[hare];
        }
        
        return hare;
    }
}
