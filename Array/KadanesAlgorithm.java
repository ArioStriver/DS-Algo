/*
Given an array Arr[] of N integers. Find the contiguous sub-array(containing at least one number) which has the maximum sum and return its sum.

Example 1:

Input:
N = 5
Arr[] = {1,2,3,-2,5}

Output:
9

Explanation:
Max subarray sum is 9
of elements (1, 2, 3, -2, 5) which 
is a contiguous subarray.

Example 2:

Input:
N = 4
Arr[] = {-1,-2,-3,-4}

Output:
-1

Explanation:
Max subarray sum is -1 
of element (-1)


METHOD 1: Just caluclate some for each subarray, and find the maximum from it.

TIME: O(N^2).


METHOD 2:(USING DP)

TIME: O(N).

SPACE: O(N).
*/

class Solution{
    
    long maxSubarraySum(int arr[], int n){
        
        int[] curSum = new int[n];
        
        // initialization
        curSum[0] = arr[0];
        
        int maxContiguousSum = arr[0];
        
        for(int i = 1; i < n; i++){
            
            // should we start a new subarray from the current index
            // or continue with the previous subarray sum
            // whichever maximum just take that
            curSum[i] = Math.max(curSum[i-1] + arr[i], arr[i]);
            maxContiguousSum = Math.max(maxContiguousSum, curSum[i]);
        }
        
        return maxContiguousSum;
    }   
}


/*
METHOD 3:(USING KADANE'S ALGORITHM)

TIME: O(N).

SPACE: O(1).
*/

class Solution{
    
    long maxSubarraySum(int arr[], int n){
        
        int sum = 0;
        int maxContiguousSum = arr[0];
        
        for(int i = 0; i < n; i++){
            
            //  current summation
            sum += arr[i];
            
            maxContiguousSum = Math.max(maxContiguousSum, sum);
            
            // if the sum > 0, then we continue the current subarray sum
            // there is no reason to carry a negative sum, bcz if we carry it 
            // it will automatically decrease our max summation
            // basically we end the current subarray at that index
            // we no more continue it
            if(sum < 0) sum = 0;
        }
        
        return maxContiguousSum;
    }
}