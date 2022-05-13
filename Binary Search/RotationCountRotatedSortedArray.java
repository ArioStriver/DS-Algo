/*
Given an ascending sorted rotated array Arr of distinct integers of size N. The array is right rotated K times. Find the value of K.

Example 1:

Input:
N = 5
Arr[] = {5, 1, 2, 3, 4}

Output: 1
Explanation: The given array is 5 1 2 3 4. 
The original sorted array is 1 2 3 4 5. 
We can see that the array was rotated 
1 times to the right.

Example 2:

Input:
N = 5
Arr[] = {1, 2, 3, 4, 5}

Output: 0
Explanation: The given array is not rotated


METHOD 1:(USING LINEAR SEARCH)

TIME: O(N).

SPACE: O(1).


METHOD 2:(USING BINARY SERACH)

TIME: O(logN)

SPACE: O(1).
*/

class Solution {
    int findKRotation(int arr[], int n) {
       
       int start = 0, end = n-1;
       
       while(start <= end) {
           
           int mid = start + (end - start) / 2;
           
           // finding the previous element
           // bcz if the mid is 0, then it's previous ele should be the last ele
           // think it as of a circular array
           int prev = (mid + n - 1) % n;
           
           // if the mid is (n-1) then for that its next ele should be first ele
           int next = (mid + 1) % n;
           
           // we also have to check if the current part is sorted already 
           // then the element in its start is the min ele
           if(arr[start] <= arr[mid] && arr[mid] <= arr[end]) {
               return start;
           }
           
           // if the middle ele is less than its prev and next ele
           // then it is the minimum ele
           if(arr[mid] <= arr[prev] && arr[mid] <= arr[next]) {
               return mid;
           }
           // otherwise we have to move to the unsorted part to find the min ele
           // if the left part is sorted, then move to the right part
           else if(arr[start] <= arr[mid]) {
               start = mid + 1;
           }
           // move to the left part
           else if(arr[mid] <= arr[end]) {
               end = mid - 1;
           }
       }
       
       return 0;
    }
}