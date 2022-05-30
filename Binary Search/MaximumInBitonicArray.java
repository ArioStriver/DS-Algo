/*
\Given a bitonic array find the maximum value of the array. An array is said to be bitonic if it has an increasing sequence of integers followed immediately by a decreasing sequence of 
integers.

Example:

Input: arr[] = {1, 4, 8, 3, 2}
Output: 8

Input: arr[] = {8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1}
Output: 500

Input: arr[] = {1, 3, 50, 10, 9, 7, 6}
Output: 50

Corner case (No decreasing part)
Input: arr[] = {10, 20, 30, 40, 50}
Output: 50

Corner case (No increasing part)
Input: arr[] = {120, 100, 80, 20, 0}
Output: 120


METHOD:(BINARY SEARCH)
	APPROACH:
		It is a variation of a Peak Element probelm, but the small change is that here is only one peak element present in the array instead of more than one peak element in that problem.

TIME: O(logN).

SPACE: O(1).
*/

class Solution {
    int findMaximum(int[] arr, int n) {
       
       int start = 0, end = n-1;
       
       while(start < end) {
           
           int mid = start + (end - start) / 2;
           
	     // rising slope
           if(arr[mid] < arr[mid+1]) {
               start = mid + 1;
           }
	     // falling slope
           else {
               end = mid;
           }
       }
       
       return arr[start];
    }
}
