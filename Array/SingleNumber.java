/*
Given an array Arr of positive integers of size N where every element appears even times except for one. Find that number occuring odd number of times.

Example 1:

Input: 
N = 5
Arr[] = {1, 1, 2, 2, 2}

Output: 2

Explanation: In the given array all element appear three times except 2 which appears once.

Example 2:

Input: 
N = 7
Arr[] = {8, 8, 7, 7, 6, 6, 1}

Output: 1

Explanation: In the given array all element appear three times except 1 which appears once.


METHOD:(USING XOR OPERATION)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    int getSingle(int arr[], int n) {
        
        int res = 0;
        
        for(int i = 0; i < n; i++) {
            res = (res ^ arr[i]);
        }
        
        return res;
    }
}