/*
(AMAZON)
We are given a row-wise sorted matrix of size r*c, we need to find the median of the matrix given. It is assumed that r*c is always odd.
Examples: 

Input : 1 3 5
        2 6 9
        3 6 9

Output : Median is 5

If we put all the values in a sorted array A[] = 1 2 3 3 5 6 6 9 9)

Input: 1 3 4
       2 5 6
       7 8 9

Output: Median is 5


METHOD 1:(BRUTE FORCE)
	APPROACH:
		We can think of a very simple approach where we will store all the elements of the matrix in a 1-Dimensional array and after sorting we will output the element at 
		[(N*M) / 2]th index.

TIME: (N*M*log(N*M))

SPACE: O(N*M).


METHOD 2: (BINARY SERACH)

TIME: O(32 * N * log(M)). The countSmallerThanOrEqualtoMid function will take log(M) time and is performed for N no. of rows. And since the numbers will be max of 32 bit, so binary search 
	of numbers from low to high will be performed in at most 32 ( log2(2^32) = 32 ) operations. 

SPACE: O(1).
*/

public class Solution {
    
    private int countSmallerThanOrEqualtoMid(int[] a, int key) {
        
        int l = 0, h = a.length-1;
        
        while(l <= h) {
            
            int mid = l + (h - l) / 2;
            
            if(a[mid] > key) {
                h = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        
        // it will determine the no. of elements samller or equal to key(mid)
        return l;
    }
    
    public int findMedian(int[][] A) {
        
        int n = A.length;
        int m = A[0].length;
        
        
        // #1. finding the max and min element in the matrix
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        int desired_count = (n * m) / 2;
        
        for(int i = 0; i < n; i++) {
            low = Math.min(low, A[i][0]);
            high = Math.max(high, A[i][m-1]);
        }
        
        // #2. now will perfrom the binary search in the range 
        while(low <= high) {
            
            int mid = low + (high - low) / 2;
            
            // count how many no. are there in the matrix that are less than or equal to mid
            int count = 0;
            
            for(int i = 0; i < n; i++) {
                count += countSmallerThanOrEqualtoMid(A[i], mid);
            }
            
            // #3. be able to median, there should be (n*m)/2 numbers smaller than current number
            if(count <= desired_count) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        
        // at last low will indicate the median  element
        return low;
    }
}