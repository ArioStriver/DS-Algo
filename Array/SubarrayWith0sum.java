/*
(ADOBE, PAYTM)

Given an array of positive and negative numbers. Find if there is a subarray (of size at-least one) with 0 sum.

Example 1:

Input:
5
4 2 -3 1 6

Output: 
Yes

Explanation: 
2, -3, 1 is the subarray 
with sum 0.

Example 2:

Input:
5
4 2 0 1 6

Output: 
Yes

Explanation: 
0 is one of the element 
in the array so there exist a 
subarray with sum 0.


METHOD 1:(BRUTE FORCE)
	APPROACH:
		We can check for each subarray and if the sum of the subarray is 0, it means there exists an subarray with sum 0. TIME : O(N^2).

METHOD 2:(USING HASHING)

TIME: O(N).

SPACE: O(1).
*/

class Solution{
    
    static boolean findsum(int arr[], int n)
    {
        if(arr[0] == 0){
            return true;
        }
        
        /* suppose the sum upto the current element is C.
           now if the (curSum - K)[here K = 0] exists in the map it means the remaining part will
           represent the k, 
           so   X = K + (X - K)
             => K = X - (X - K) 
           here we are using hashmap is store the frequecy of sum */

        Map<Integer, Integer> sum = new HashMap<>();

        int curSum = 0;  // to keep track of the current sum
        
        // when there are nothing then the sum is 0
        sum.put(0, 1);
        
        for(int i = 0; i < n; i++){
            
            /* again if 0 is present in the array, so the single element itself is a subarray
               and accd. to the question the size of the subarray should be atleast one */
            if(arr[i] == 0) return true;
            
            // prefix sum
            curSum += arr[i];
            
            /* if prefix sum is 0 or if it is already present in Hashmap 
              then it is repeated which means there is a subarray whose 
              summation is 0, so we return true. */
            if(sum.containsKey(curSum)){
                return true;
            }
            
            sum.put(curSum, sum.getOrDefault(curSum, 0) + 1);
        }
        
        return false;
    }
}