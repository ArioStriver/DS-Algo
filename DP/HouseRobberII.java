/* 
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the 
first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken 
into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: nums = [2,3,2]
Output: 3

Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.


Example 2:

Input: nums = [1,2,3,1]
Output: 4

Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.


Example 3:

Input: nums = [1,2,3]
Output: 3
 
Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 1000
*/

// METHOD 1:(USING RECURSION)

class Solution {
    public int rob(int[] nums) {
        
        int n = nums.length;
        
        // creating two arrays
        int[] temp1 = new int[n-1];  // contains element from 1 - (n-1)
        int[] temp2 = new int[n-1];  // contains element from 2 - n
        
        // edge case
        if(n == 1) return nums[0];
        
        for(int i = 0; i < n; i++){
            if(i != 0){
                temp1[i-1] = nums[i];
            }   
            
            if(i != n-1){
                temp2[i] = nums[i];
            }
        }
        
        return Math.max(maxRobbed(temp1, temp1.length-1), maxRobbed(temp2, temp2.length-1));
    }
    
    private int maxRobbed(int[] nums, int index){
        
        if(index == 0) 
            return nums[index];
        
        if(index < 0) return 0;
        
        int pick = nums[index] + maxRobbed(nums, index - 2);
        
        int notPick = maxRobbed(nums, index - 1);
        
        return Math.max(pick, notPick);
    }
}

/*
METHOD 2:(USING DYNAMIC PROGRAMMING)
	APPROACH:
		Suppose there are n houses, since house 0 and n - 1 are now neighbors, we cannot rob them together and thus the solution is now the maximum of

			1. Rob houses 0 to n - 2;
			2. Rob houses 1 to n - 1.

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public int rob(int[] nums) {
        
        int n = nums.length;
        
        // edge case
        if(n == 1) return nums[0];
        
        // else maximum of both houses
        return Math.max(maxRobbed(nums, 0, n-2), maxRobbed(nums, 1, n-1));
    }
    
    private int maxRobbed(int[] nums, int start, int end){
        
        int prev1 = 0;
        int prev2 = 0;
        
        for(int i = start; i <= end; i++){
            
            int pickCurrent = nums[i];
            
            if(i > 1) pickCurrent += prev2;
            
            int notPickCurrent = prev1;
            
            int current_max = Math.max(pickCurrent, notPickCurrent);
            prev2 = prev1;
            prev1 = current_max;
        }
        
        return prev1;
    }
}