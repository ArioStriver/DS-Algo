/*
Given an sorted array A of size N. Find number of elements which are less than or equal to B.

NOTE: Expected Time Complexity O(log N)

Example Input
Input 1:

 A = [1, 3, 4, 4, 6]
 B = 4

Input 2:

 A = [1, 2, 5, 5]
 B = 3

Example Output
Output 1:

 4

Output 2:

 2


METHOD:

TIME: O(logN).
*/

public class Solution {
    public int solve(int[] A, int B) {

        int count = 0;
        int low = 0, high = A.length - 1;
        int res = -1;

        while(low <= high) {

            int mid = low + (high - low) / 2;

            // storing the middle element position. bcz if the current element is less than the key
            // it means that elements present before it will also be smaller, as the array is sorted 
            if(A[mid] <= B) {
                res = mid;
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        // lastly return the (position + 1), will give us the number of elements
        return res + 1;
    }
}
