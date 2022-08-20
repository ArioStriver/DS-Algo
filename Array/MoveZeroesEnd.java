/*
Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.

Example 1:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]

Example 2:

Input: nums = [0]
Output: [0]
 
Constraints:

1 <= nums.length <= 10^4
-2^31 <= nums[i] <= 2^31 - 1


METHOD:

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    
    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void moveZeroes(int[] array) {
        
        int n = array.length;
        
        if(n == 1) return;
        
        int lastNonZeroFoundAt = 0;
        
        for(int i = 0; i < n; i++) {
           if(array[i] != 0) {
               swap(array, lastNonZeroFoundAt, i);
               low++;
           }
       }
    }
}