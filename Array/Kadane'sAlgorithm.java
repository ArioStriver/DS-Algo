/*
Given an array Arr[] of N integers. Find the contiguous sub-array(containing at least one number) which has the maximum sum and return its sum.

Example 1:

Input:
N = 5
Arr[] = {1,2,3,-2,5}

Output: 9

Explanation: Max subarray sum is 9 of elements (1, 2, 3, -2, 5) which  is a contiguous subarray.

Example 2:

Input:
N = 4
Arr[] = {-1,-2,-3,-4}

Output: -1

Explanation: Max subarray sum is -1 of element (-1).


METHOD: (UISNG KADANE'S ALGORITHM)

TIME: O(N).

SPACE: O(1).
*/

class Solution{

    long maxSubarraySum(int arr[], int n){
        
        int start = 0, end = 0, maxSum = Integer.MIN_VALUE, curSum = 0, s = 0;
        
        for(int i = 0; i < n; i++) {
            
            curSum += arr[i];
            
            // storing the starting and the ending index
            if(curSum > maxSum) {
                maxSum = curSum;
                start = s;
                end = i;
            }
            
            if(curSum < 0) {
                curSum = 0;
                s = i + 1;
            }
        }
        
        System.out.println("Starting index: "+start);
        System.out.print("Ending index: "+end);
        
        return maxSum;
    }
}