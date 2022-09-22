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

TIME: O(N + M), where N & M are the length of array1 & array2.

SPACE: O(N + M).
*/

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        int n = nums1.length;
        int m = nums2.length;
        int[] ans = new int[n+m];
        
        int i = 0, j = 0, k = 0;
        
        while(i < n && j < m) {
            if(nums1[i] <= nums2[j]) {
                ans[k++] = nums1[i++];
            }
            else {
                ans[k++] = nums2[j++];
            }
        }
        
	  if(i < n)
        	while(i < n) {
            	ans[k++] = nums1[i++];
		}
        }
        
	  if(j < m) {
        	while(j < m) {
            	ans[k++] = nums2[j++];
        	}
	  }
        
        double median = 0.0;
        int mid = (n+m) / 2;
        
        if((n+m) % 2 == 0) {
            median = (ans[mid-1] + ans[mid]) / 2.0;  
        }
        else {
            median = ans[mid];
        }
        
        return median;
    }
}

/*
METHOD 2:
	APPROACH:
		Similar to the naive approach, instead of storing the final merged sorted array, we can keep a counter to keep track of the required position where the median will exist. 
		First, using the median formula, get a position where the median will exist. Then, follow the above approach and instead of storing elements in another array, we will 
		increase the counter value. When the counter value is equal to the median positions, return element.

TIME: O(N + M).

SPACE: O(1).


METHOD 3:(BINARY SEARCH)

TIME: O(log(N + M)).

SPACE: O(1).
*/

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        int n = nums1.length;
        int m = nums2.length;
        
        // first we have to check whether the array1 is smaller in size or not 
		// if not then we have to make it smaller, bcz we always take the smaller array for BS
        if(n > m) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        // now we are perfoming the BS on the smaller array
        int low = 0, high = n;
        
        while(low <= high) {
            
            // first calculate the middle of the first array 
			// means taking some elements from the left half and remainig from the right half
            int cut1 = (low + high) / 2;
            int cut2 = ((n + m + 1) / 2) - cut1;
            
            int l1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1-1];
            int l2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2-1];
            int r1 = (cut1 == n) ? Integer.MAX_VALUE : nums1[cut1];
            int r2 = (cut2 == m) ? Integer.MAX_VALUE : nums2[cut2];
            
            // now check whether the partitions are valid or not
            // it is valid when all elements in the left half is less than all elements in the right half
            if(l1 <= r2 && l2 <= r1) {
                
                // for even length
                if((n + m) % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                }
                // for odd length
                else {
                    return Math.max(l1, l2);
                }
            }
             // not valid partitions
            // taking more elements from right half to left half of first array
            else if(l2 > r1) {
                low = cut1 + 1;
            }
            else {
                high = cut1 - 1;
            }
        }
        
        return 0.0;
    }
}