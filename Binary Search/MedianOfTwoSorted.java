/*
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000

Explanation: merged array = [1,2,3] and median is 2.

Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000

Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.


METHOD 1:(BRUTE FORCE)
	APPROACH:
		Take two pointers, each pointing to each array. Take an array of size (m+n) to store the final sorted array. If the first pointed element is smaller than the second one, store 
		that value in an array and move the first pointer ahead by one. Else do the same for the second pointer when the case is vice-versa. Then use the formula to get the median 
		position and return the element present at that position.		

TIME: O(N1 + N2), where N1 & N2 are the lengths of both the arrays.

SPACE: O(N1 + N2).
*/

public class Sloution{

static double median(int arr1[],int arr2[],int m,int n) {

    int finalArray[]=new int[n+m];

    int i=0,j=0,k=0;

    while(i<m && j<n) {
        if(arr1[i]<arr2[j]) {
            finalArray[k++] = arr1[i++];
        }
        else {
            finalArray[k++] = arr2[j++];
        }
    }

    if(i<m) {
        while(i<m) 
            finalArray[k++] = arr1[i++];
    }

    if(j<n) {
        while(j<n)
            finalArray[k++] = arr2[j++];
    }

    n = n+m;
    if(n%2==1) {
        return finalArray[((n+1)/2)-1];
    }
    else {
	  return ((double)finalArray[(n/2)-1]+(double)finalArray[(n/2)])/2.0;
    }
}


/*
METHOD 2:(OPTIMIZED SPACE)
	APPROACH:
		Similar to the naive approach, instead of storing the final merged sorted array, we can keep a counter to keep track of the required position where the median will exist. First,
		using the median formula, get a position where the median will exist. Then, follow the above approach and instead of storing elements in another array, we will increase the 
		counter value. When the counter value is equal to the median positions, return element.

TIME: O(N1 + N2).

SPACE: O(1).
*/

/*
METHOD 3:(BINARY SEARCH)

TIME: O(log(min(N1, N2)), whichever array is minimum.

SPACE: O(1).
*/

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        // first we have to check whether the array1 is smaller in size or not 
		// if not then we have to make it sammler, bcz we always take the smaller array for BS
        if(nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        // now we are perfoming the BS on the smaller array means the first one
        int n1 = nums1.length;
        int n2 = nums2.length;
        int low = 0, high = n1;
        
        // calculating the median position
        int medianPos = (n1 + n2 + 1) / 2;
        
        while(low <= high) {
            
            // first calculate the middle of the samller array 
			// means taking some elements form the left half and remainig form the right half 
            int cut1 = (low + high) / 2;
            int cut2 = medianPos - cut1;    // remaining form right half 
            
            int l1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1-1];
            int l2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2-1];
            int r1 = (cut1 == n1) ? Integer.MAX_VALUE : nums1[cut1];
            int r2 = (cut2 == n2) ? Integer.MAX_VALUE : nums2[cut2];
            
            // now check whether the partitions are valid or not
            if(l2 <= r1 && l1 <= r2) {
                // then check if the length is even, then apply median formula for even, otherwise odd formula
                if((n1 + n2) % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                }
                else {
                    return Math.max(l1, l2);
                }
            }
            // not valid partitions
            else if(l1 > r2) {
                high = cut1 - 1;
            }
            else {
                // taking more elements from right half to left half
                low = cut1 + 1;
            }
        }
        
        return 0.0;
     }
}