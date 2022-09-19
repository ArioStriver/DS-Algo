/*
Given an array of integers. Find the Inversion Count in the array. 

Inversion Count: For an array, inversion count indicates how far (or close) the array is from being sorted. If array is already sorted then the inversion count is 0. If an array is sorted 
in the reverse order then the inversion count is the maximum. Formally, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j.
 
Example 1:

Input: N = 5, arr[] = {2, 4, 1, 3, 5}
Output: 3

Explanation: The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).

Example 2:

Input: N = 5
arr[] = {2, 3, 4, 5, 6}
Output: 0

Explanation: As the sequence is already sorted so there is no inversion count.

Example 3:

Input: N = 3, arr[] = {10, 10, 10}
Output: 0

Explanation: As all the elements of array are same, so there is no inversion count.

Your Task:
You don't need to read input or print anything. Your task is to complete the function inversionCount() which takes the array arr[] and the size of the array as inputs and returns the 
inversion count of the given array.


METHOD 1:(BRUTE FORCE)

TIME: O(N^2).

SPACE: O(1).
*/

class Solution
{
    static long inversionCount(long arr[], long N)
    {
        int count = 0;
        
        for(int i = 0; i < N-1; i++) {
            for(int j = i+1; j < N; j++) {
                if(arr[i] > arr[j]) {
                    count++;
                }
            }
        }
        
        return count;
    }
}


/*
METHOD 2:(USING MERGE SORT)

TIME: O(NlogN).

SPACE: O(N).
*/

class Solution
{
    private static long merge(long[] a, long left, long mid, long right, long[] temp){
        
        long i, j, k;
        
        i = left; // starting point of the left array
        j = mid; // starting point of the right array
        k = left;
        long countInversion = 0;
        
        while((i <= mid - 1) && (j <= right)){
            
            // if the ith element is < jth elemnt, then simply copy the element into temp
            if(a[(int)i] <= a[(int)j]){
                temp[(int)k++] = a[(int)i++];
            }
            // it means ith element > jth element, now we have to count the no. of inversions
            else{
                temp[(int)k++] = a[(int)j++];
                
                /* count the no. of inversions
                   as the current ith elemnt > jth elemnt
                   it means that the element resent it its right part will also make a
                   pair with current jth elemnt, bcz the left array is in sorted array
                   so we need to cosider them all & as we know that the right array
                   is starting from the mid, so we need to cosider all the elements
                   b/w from (i to mid-1) */
                   
                countInversion += (mid - i);
            }
        }
        
        // copy the remaining  elements
        while(i <= mid-1){
            temp[(int)k++] = a[(int)i++];
        }
        
        while(j <= right){
            temp[(int)k++] = a[(int)j++];
        }
        
        // copy the elements back to the original array
        for(i = left; i <= right; i++){
            a[(int)i] = temp[(int)i];
        }
        
        return countInversion;
    }
    
    private static long mergeSort(long[] a, long left, long right, long[] temp){
        
        long mid, countInversion = 0;
        
        if(right > left){
            
            mid = left + (right - left) / 2;
            
            countInversion += mergeSort(a, left, mid, temp); // for left part
            
            countInversion += mergeSort(a, mid+1, right, temp); // for right part
            
            countInversion += merge(a, left, mid+1, right, temp);  // mergeing operation
        }
        
        return countInversion;
    }
    
    static long inversionCount(long arr[], long N)
    {
        long[] temp = new long[(int)N];
        
        return mergeSort(arr, 0, N-1, temp);
    }
}