/*
You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

Example 1:

Input: nums = [2,3,1,1,4]
Output: true

Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:

Input: nums = [3,2,1,0,4]
Output: false

Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 
Constraints:

1 <= nums.length <= 10^4
0 <= nums[i] <= 10^5


METHOD 1:(GREEDY)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public boolean canJump(int[] nums) {
        
        int reacheable = 0;
        
        // traversing through ecah point & will try to see maximum we can go from the current point
        for(int i = 0; i < nums.length; i++) {
            
            // if any time the index is > our maximum reacheable point, it means that 
            // we never able to recah the end of the array
            if(reacheable < i) {
                return false;
            }
            
            reacheable = Math.max(reacheable, i + nums[i]);
        }
        
        return true;
    }
}