/*
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.

Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]

METHOD:(USING DUTCH NATIONAL FLAG ALGO)

TIME: O(N), where N is the length of the array.

SPACE: O(1).
*/

class Solution {
    public void sortColors(int[] nums) {
       
        int n = nums.length;
        int low = 0, mid = 0, high = n-1;
        
        // dutch national flag problem basically tells us that
        // shift all 0's to the left of the low pointer
        // and shift all 2's to the right of the high pointer
        while(mid <= high){
            
            // if the current value is 0
            // then swap it with the low value
            if(nums[mid] == 0){
                swap(nums, low, mid);
                low++;
                mid++;
            }
            else if(nums[mid] == 2){
                swap(nums, mid, high);
                high--;
            }
            // for  1
            else{
                mid++;
            }
        }
    }
    
    private void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}