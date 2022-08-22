/*
Given an array of non-negative integers nums, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

You can assume that you can always reach the last index.

Example 1:

Input: nums = [2,3,1,1,4]
Output: 2

Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:

Input: nums = [2,3,0,1,4]
Output: 2
 

Constraints:

1 <= nums.length <= 10^4
0 <= nums[i] <= 1000


METHOD 1;(UISNG DYNAMIC PROGRAMMING)

TIME: O(N^2).

SPACE: O(1).
*/

class Solution {
    public int jump(int[] nums) {
        
        int n = nums.length;
        int[] minJumpsNeeded = new int[n];
        
        Arrays.fill(minJumpsNeeded, Integer.MAX_VALUE);
        minJumpsNeeded[0] = 0;
        
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                
                // check first whether it is possible to reach the ith index from j
                // means whether i is within the range of j or not
                if(i <= j + nums[j]) {
                    minJumpsNeeded[i] = Math.min(minJumpsNeeded[i], minJumpsNeeded[j]+1); 
                }
            }
            
            //System.out.println(minJumpsNeeded[i]);
        }
        
        return minJumpsNeeded[n-1];
    }
}


/*
METHOD 2:(GREEDY)
	APPROACH:
		maxReach = arr[0]; // arr[0] = 1, so the maximum index we can reach at the moment is 1. 
		step = arr[0]; // arr[0] = 1, the amount of steps we can still take is also 1. 
		jump = 1; // we are currently making our first jump.

		Now, starting iteration from index 1, the above values are updated as follows:

			1. First, we test whether we have reached the end of the array, in that case, we just need to return the jump variable.
			2. Next we update the maxReach. This is equal to the maximum of maxReach and i+arr[i](the number of steps we can take from the current position). 
			3. We used up a step to get to the current index, so steps has to be decreased. 
			4. If no more steps are remaining (i.e. steps=0, then we must have used a jump. Therefore increase jump. Since we know that it is possible somehow to reach maxReach, we 
			   again initialize the steps to the number of steps to reach maxReach from position i. But before re-initializing step, we also check whether a step is becoming zero 
			   or negative. In this case, It is not possible to reach further. 
TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public int jump(int[] nums) {
        
        if(nums.length <= 1) return 0;
        
        // if not possible to jump
        if(nums[0] == 0) return 0;
        
        // initialization
        int maxReach = nums[0];
        int steps = nums[0];
        int jumps = 1;
        
        // strat the tarversal from 1
        for(int i = 1; i < nums.length; i++) {
            
            // check whether we reach the end of the array or not
            if(i == nums.length-1) {
                return jumps;
            }
            
            // updating the maximum reach
            maxReach = Math.max(maxReach, i + nums[i]);
            
            // we use a step to get to the current index
            steps--;
            
            // If no further steps left
            if(steps == 0) {
                
                // time for the next jump
                // we must have used a jump
                jumps++;
                
                // check whether the current position is the maximum point we can recha 
                // from the previous index 
                if(i >= maxReach) {
                    return -1;
                }
                
                // re-initialize the steps to the amount
                // of steps to reach maxReach from position i.
                steps = maxReach - i;
            }
        }
        
        return jumps;
    }
}