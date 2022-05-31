/*
Given a bitonic sequence of n distinct elements, write a program to search a given element k in the bitonic sequence.

Problem Note →

A Bitonic Sequence is a sequence of numbers that is first strictly increasing then after a point strictly decreasing.
It is guaranteed that there are no duplicates in the input array.
If the element is found then return the index otherwise return -1.
You are expected to solve this problem in O(log N) time complexity.

Example 1

Input: nums[] = [-3, 8, 9, 20, 17, 5, 1], k = 20
Output: 3 

Explanation: Element k Found at index 3

Example 2

Input: nums[] = [5, 6, 7, 8, 9, 10, 3, 2, 1], k = 30
Output: -1


METHOD 1:(LINEAR SEARCH)

TIME: O(N).

SPACE: O(1).
*/

int findBitonic(int[] nums, int size, int k) {

    for (i = 0 to i < size ) {
        if (nums[i] == k)
            return i
    }
    return -1
}


/*
METHOD 2:(BINARY SEARCH)

TIME: O(logN).

SPACE: O(1).
*/

class Solution {
    
    static int findPeakElement(int[] nums, int n) {
        
        int low = 0, high = n-1;
        
        while(low < high) {
            
            int mid = low + (high - low) / 2;
            
            if(nums[mid] < nums[mid + 1]) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        
        return low;
    }
    
    static int ascendingBinarySearch(int[] nums, int low, int high, int key) {
        
        while(low <= high) {
            
            int mid = low + (high - low) / 2;
            
            if(nums[mid] == key) {
                return mid;
            }
            
            if(nums[mid] < key) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        
        return -1;
    }
    
    static int descendingBinarySearch(int[] nums, int low, int high, int key) {
        
        while(low <= high) {
            
            int mid = low + (high - low) / 2;
            
            if(nums[mid] == key) {
                return mid;
            }
            
            if(nums[mid] < key) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        
        return -1;
    }
    
    static int searchBitonic(int[] nums, int n, int key) {
        
        int peak_index = findPeakElement(nums, n);
        
        // key > peak element, then the key is not present in the array 
        if(key > nums[peak_index]) {
            return -1;
        }
        
        // key == peak element, return peak index element
        if(key == nums[peak_index]) {
            return peak_index;
        }
        // otherwise check for the element in both the sides of the peak element
        else {
            
            // check whether the element presnt in it's left part means ascending part
            int index = ascendingBinarySearch(nums, 0, peak_index-1, key);
            
            if(index != -1) return index;
            
            index = descendingBinarySearch(nums, peak_index+1, n, key);
            
            if(index != -1) return index;
        }
        
        return -1;
    }
}