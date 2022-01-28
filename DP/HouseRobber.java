// Q. House Robber

// You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that 
// adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

//Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

//Example 1:

// Input: nums = [1,2,3,1]

// Output: 4

// Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
// Total amount you can rob = 1 + 3 = 4.


//Example 2:

// Input: nums = [2,7,9,3,1]
// Output: 12

// Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
// Total amount you can rob = 2 + 9 + 1 = 12.


// METHOD 1:(USING RECURSION)(TLE)

class Solution {
    public int rob(int[] nums) {
        
        int n = nums.length;
        
        return maxRobbed(nums, n-1);
    }
    
    private int maxRobbed(int[] nums, int index){
        
        // base case
        // if at any point the in the index reaches 0 it means that it not picked the
        // the value int the 1st index, if so then the pick the current one
        if(index == 0) return nums[index];
        
        // if we reach out of bound
        if(index < 0) return 0;
        
        
        // we have two choices either we can pick the current one 
        // or move to the next one
        // picking the current element means adding the current one to our subsequence
        // move to the next one avoiding the adjacent ones
        int pickCurrent = nums[index] + maxRobbed(nums, index - 2);
        
        
        // not picking the current one
        int notpickCurrent = maxRobbed(nums, index - 1);
        
        
        return Math.max(pickCurrent, notpickCurrent);
    }
}


// METHOD 2:(USING MEMOIZATION)
	
//TIME: O(N).

//SPACE: O(N) + O(N). first one is for recusion stack and second one is for memoization.


class Solution {
    public int rob(int[] nums) {
        
        int n = nums.length;
        int[] memo = new int[n];
        
        return maxRobbed(nums, n-1, memo);
    }
    
    private int maxRobbed(int[] nums, int index, int[] memo){
        
        // base case
        // if at any point the in the index reaches 0 it means that it not picked the
        // the value int the 1st index, if so then the pick the current one
        if(index == 0) return nums[index];
        
        // if we reach out of bound
        if(index < 0) return 0;
        
        
        if(memo[index] != 0) return memo[index];
        
        
        // we have two choices either we can pick the current one 
        // or move to the next one
        // picking the current element means adding the current one to our subsequence
        // move to the next one avoiding the adjacent ones
        int pickCurrent = nums[index] + maxRobbed(nums, index - 2, memo);
        
        
        // not picking the current one
        int notpickCurrent = maxRobbed(nums, index - 1, memo);
        
        
        return memo[index] = Math.max(pickCurrent, notpickCurrent);
    }
}


//METHOD 3:(USING DYNAMIC PROGRAMMING)

//TIME: O(N).

//SPACE: O(N).

class Solution {
    public int rob(int[] nums) {
        
        int n = nums.length;
        int[] dp = new int[n];
        
        // base case
        dp[0] = nums[0];
        
        // start traversing from the first index
        for(int i = 1; i < n; i++){
            
            // taking the current one
            int pickCurrent = nums[i];
            
            // out of bound check
            if(i > 1) pickCurrent += dp[i-2];
            
            // not picking the current one
            int notPickCurrent = dp[i-1];
            
            // taking the maximum
            dp[i] = Math.max(pickCurrent, notPickCurrent);
        }
        
        return dp[n-1];
    }
}


//METHOD 4:(SPACE OPTIMIZED)

//TIME: O(N).

//SPACE: O(1).

class Solution {
    public int rob(int[] nums) {
        
        int n = nums.length;
        
        int prev1 = nums[0];
        int prev2 = 0;
        
        for(int i = 0; i < n; i++){
            
            // picking 
            int pickCurrent = nums[i];
            
            // out of bound
            if(i > 1) pickCurrent += prev2;
            
            // not picking
            int notPickCurrent = prev1;
            
            // maximum
            int current_max = Math.max(pickCurrent, notPickCurrent);
            prev2 = prev1;
            prev1 = current_max;
        }
        
        return prev1;
    }
}
