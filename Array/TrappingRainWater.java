/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

Example 1:

Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9


METHOD 1:(BRUTE FORCE)
	APPROACH:
		For each element in the array, we find the maximum level of water it can trap after the rain, which is equal to the minimum of maximum height of bars on both the sides minus 
		its own height.

TIME: O(N^2).

SPACE: O(1).
*/

class Solution {
    public int trap(int[] height) {
        
        int total_trapped_water = 0;
        
        for(int i = 1; i < height.length-1; i++) {
            
            int left_max = height[i]; 
            
            // finding the left boundary of the current height
            for(int j = 0; j < i; j++) {
                left_max = Math.max(left_max, height[j]);
            }
            
            int right_max = height[i];
            
            // finding the right boundary of the current height
            for(int j = i+1; j < height.length; j++) {
                right_max = Math.max(right_max, height[j]);
            }
            
            // calculating the trapped water
            total_trapped_water += Math.min(left_max, right_max) - height[i];
        }
        
        return total_trapped_water;
    }
}

/*
METHOD 2:(USING TWO ARRAYS)

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public int trap(int[] height) {
        
        int n = height.length;
        
        int total_trapped_water = 0;
        int[] left_max = new int[n];
        int[] right_max = new int[n];
        
        left_max[0] = height[0];
        
        // keep track of the left max
        for(int i = 1; i < height.length; i++) {
            left_max[i] = Math.max(left_max[i-1], height[i]);
        }
        
        right_max[n-1] = height[n-1];
        
        // keep track of the right max
        for(int i = n-2; i >= 0; i--) {
            right_max[i] = Math.max(right_max[i+1], height[i]);
        }
        
        // calculating the trapped water
        for(int i = 1; i < n-1; i++) {
            total_trapped_water += Math.min(left_max[i], right_max[i]) - height[i];
        }
        
        return total_trapped_water;
    }
}

/*
METHOD 3:(USING TWO POINTER)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public int trap(int[] height) {
      
        int n = height.length;
        int left = 0, right = n - 1,leftMaxSoFar = 0, rightMaxSoFar = 0;
        int totalTrappedWater = 0;
        
        while(left <= right){
            
            // this condition makes sure that there exists a block on current block's 
            // right side which has a length >= to current block
            if(height[left] <= height[right]){
                
                // now make sure whether it has a greater block on its left or not
                // or whether itself of the leftmax
                if(height[left] >= leftMaxSoFar){
                    leftMaxSoFar = height[left];
                }
                else{
                    // we able to trap water
                    totalTrappedWater += (leftMaxSoFar - height[left]);
                }
                left++;
            }
            // this condition makes sure that there exists a block on current block's 
            // left side which has a length > current block
            else{
                // now make sure whether it has a greater block on its right or not
                // or whether itself of the rightmax
                if(height[right] >= rightMaxSoFar){
                    rightMaxSoFar = height[right];
                }
                else{
                    // we able to trap water
                    totalTrappedWater += (rightMaxSoFar - height[right]);
                }
                right--;
            }
        }
        
        return totalTrappedWater;
    }
}
 