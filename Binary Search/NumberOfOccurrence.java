/*
Given a sorted array Arr of size N and a number X, you need to find the number of occurrences of X in Arr.

Example 1:

Input:
N = 7, X = 2

Arr[] = {1, 1, 2, 2, 2, 2, 3}

Output: 4
Explanation: 2 occurs 4 times in the
given array.

Example 2:

Input:
N = 7, X = 4

Arr[] = {1, 1, 2, 2, 2, 2, 3}

Output: 0
Explanation: 4 is not present in the
given array.


METHOD:(BINARY SEARCH)

TIME: O(logN).
*/

class Solution {
    
    private int firstOccurance(int[] a, int n, int key) {
        
        int start = 0, end = n-1;
        int idx = -1;
        
        while(start <= end) {
            
            int mid = start + (end - start) / 2;
            
            if(a[mid] == key) {
                idx = mid;
                end = mid - 1;
            }
            else if(key < a[mid]) end = mid - 1;
            
            else start = mid + 1;
        }
        
        return idx;
    }
    
    private int lastOccurance(int[] a, int n, int key) {
        
        int start = 0, end = n-1;
        int idx = -1;
        
        while(start <= end) {
            
            int mid = start + (end - start) / 2;
            
            if(a[mid] == key) {
                idx = mid;
                start = mid + 1;
            }
            else if(key < a[mid]) end = mid - 1;
            
            else start = mid + 1;
        }
        
        return idx;
    }
    
    int count(int[] arr, int n, int x) {
        
        int first = firstOccurance(arr, n, x);
        
        int last = lastOccurance(arr, n, x);
        
        return first == -1 ? 0 : (last - first + 1);
    }
}