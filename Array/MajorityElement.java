/*
Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

Example 1:

Input: nums = [3,2,3]
Output: 3

Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2
 
Constraints:

n == nums.length
1 <= n <= 5 * 10^4
-10^9 <= nums[i] <= 10^9

METHOD: (USING MOORE'S VOTING ALGORITHM)
	APPROACH:
		If the majority element was X, and it by any chance appears on the left prefix, then it counts at max can be (lenOfLeftPrefix / 2). So since it has to appear more than
		N / 2 times, it is bound to appear as a majority in the last suffix. If it does not appear on left as majority, then it is quite clear that it will be majority at right.

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public int majorityElement(int[] nums) {
        
        int count = 0, ele = 0;
        
        for(int i = 0; i < nums.length; i++) {
            
            if(count == 0) {
                ele = nums[i];
            }
            
		// if current element is equal to majority element, then increment the count
            if(nums[i] == ele) {
                count++;
            }
		// otherwise decrement 
            else {
                count--;
            }
        }
        
        return ele;
    }
}