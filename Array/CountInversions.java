/*
Given an array of integers. Find the Inversion Count in the array. 

Inversion Count: For an array, inversion count indicates how far (or close) the array is from being sorted. If array is already sorted then the inversion count is 0. 
If an array is sorted in the reverse order then the inversion count is the maximum. 
Formally, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j.
 
Example 1:

Input: N = 5, arr[] = {2, 4, 1, 3, 5}
Output: 3

Explanation: The sequence 2, 4, 1, 3, 5 
has three inversions (2, 1), (4, 1), (4, 3).

Example 2:

Input: N = 5
arr[] = {2, 3, 4, 5, 6}

Output: 0

Explanation: As the sequence is already 
sorted so there is no inversion count.

Example 3:

Input: N = 3, arr[] = {10, 10, 10}
Output: 0

Explanation: As all the elements of array 
are same, so there is no inversion count


METHOD 1:(BRUTE FORCE)

TIME: O(N^2).

SPACE: O(1).
*/

class Solution
{
    static long inversionCount(long arr[], long N)
    {
        int count = 0;
        for(int i = 0; i < N; i++){
            for(int j = i; j < N; j++){
                if(arr[i] > arr[j]){
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
    private static int merge(long[] a, int left, int mid, int right){
        
        int i = left; // starting point of the left array
        int j = mid; // starting point of the right array
        int k = left;
        int[] temp = new int[a.length];
        int countInversion = 0;
        
        while(i <= (mid - 1) && j <= right){
            
            // if the ith element is < jth elemnt, then simply copy the element into temp
            if(a[i] <= a[j]){
                temp[k++] = a[i++];
            }
            // it means ith element > jth element, now we have to count the no. of inversions
            else{
                temp[k++] = a[j++];
                
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
            temp[k++] = a[i++];
        }
        
        while(j <= right){
            temp[k++] = a[j++];
        }
        
        // copy the elements back to the original array
        for(i = left; i <= right; i++){
            a[i] = temp[i];
        }
        
        return countInversion;
    }
    
    private static int mergeSort(int[] a, int left, int right){
        
        int countInversion = 0;
        
        if(right > left){
            
            int mid = (left + right) / 2;
            
            countInversion += mergeSort(a, left, mid); // for left part
            
            countInversion += mergeSort(a, mid+1, right); // for right part
            
            countInversion += merge(a, left, mid+1, right);  // mergeing operation
        }
        
        return countInversion;
    }
    
    static int inversionCount(int arr[], int N)
    {
        return mergeSort(arr, 0, N-1);
    }
}