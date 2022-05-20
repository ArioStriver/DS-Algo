/*
Given an infinite sorted array consisting 0s and 1s. The problem is to find the index of first ‘1’ in that array. As the array is infinite, therefore it is guaranteed that number ‘1’ will 
be present in the array.

Examples: 
 

Input : arr[] = {0, 0, 1, 1, 1, 1} 
Output : 2

Input : arr[] = {1, 1, 1, 1, 1, 1, 1}
Output : 0

Input : arr[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}
Output : 12


METHOD:
	APPROACH:
		The problem is a combination of finding position of an element in a sorted array of infinite numbers & findng the first occurance of an element. As the array is infinite, 
		therefore we do not know the upper and lower bounds between which we have to find the occurrence of first ‘1’. 

*/

class Solution {
    
    public static int indexOfFirstOne(int arr[], int low, int high)
    {
        int mid=0;
        while (low <= high) {
            mid = (low + high) / 2;
      
            // if true, then 'mid' is the index of first '1'
            if (arr[mid] == 1 &&
                (mid == 0 || arr[mid - 1] == 0))
                break;
      
            // first '1' lies to the left of 'mid'
            else if (arr[mid] == 1)
                high = mid - 1;
      
            // first '1' lies to the right of 'mid'
            else
                low = mid + 1;
        }
      
        return mid;
    }
      
    
    public static int posOfFirstOne(int arr[])
    {
        // find the upper and lower bounds between which the first '1' would be present
        int l = 0, h = 1;
      
        // as the array is being considered infinite therefore 'h' index will always exist in the array
        while (arr[h] == 0) {
      
            // lower bound
            l = h;
      
            // upper bound
            h = 2 * h;
        }
      
        // required index of first '1'
        return indexOfFirstOne(arr, l, h);
    }
}