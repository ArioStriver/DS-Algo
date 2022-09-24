/*
Given two sorted arrays arr1 and arr2 of size N and M respectively and an element K. The task is to find the element that would be at the k’th position of the final sorted array.
 
Example 1:

Input:
arr1[] = {2, 3, 6, 7, 9}
arr2[] = {1, 4, 8, 10}
k = 5

Output:
6

Explanation: The final sorted array would be - 1, 2, 3, 4, 6, 7, 8, 9, 10. The 5th element of this array is 6.

Example 2:

Input:
arr1[] = {100, 112, 256, 349, 770}
arr2[] = {72, 86, 113, 119, 265, 445, 892}
k = 7

Output:
256
Explanation:
Final sorted array is - 72, 86, 100, 112,
113, 119, 256, 265, 349, 445, 770, 892
7th element of this array is 256.

Your Task:  
You don't need to read input or print anything. Your task is to complete the function kthElement() which takes the arrays arr1[], arr2[], its size N and M respectively and an integer K as 
inputs and returns the element at the Kth position.


METHOD 1:(MERGE TECHNIQUE)

TIME: O(N).

SPACE: O(M + N).
*/

class Solution
{
    static int kth(int arr1[], int arr2[], int m, int n, int k)
    {
        int[] sorted1 = new int[m + n];
        int i = 0, j = 0, d = 0;
        while (i < m && j < n)
        {
            if (arr1[i] < arr2[j])
                sorted1[d++] = arr1[i++];
            else
                sorted1[d++] = arr2[j++];
        }
        while (i < m)
            sorted1[d++] = arr1[i++];
        while (j < n)
            sorted1[d++] = arr2[j++];
        return sorted1[k - 1];
    }
}

/*
METHOD 2: 

TIME: O(K).

SPACE: O(1).
*/

class Solution {
    public static int find(int A[], int B[], int m, int n, int k_req)
    {
        int k = 0, i = 0, j = 0;
 
        // Keep taking smaller of the current
        // elements of two sorted arrays and
        // keep incrementing k
        while (i < m && j < n) {
            if (A[i] < B[j]) {
                k++;
                if (k == k_req)
                    return A[i];
                i++;
            }
            else {
                k++;
                if (k == k_req)
                    return B[j];
                j++;
            }
        }
 
        // If array B[] is completely traversed
        while (i < m) {
            k++;
            if (k == k_req)
                return A[i];
            i++;
        }
 
        // If array A[] is completely traversed
        while (j < n) {
            k++;
            if (k == k_req)
                return B[j];
            j++;
        }
        return -1;
    }
}

/*
METHOD 3: (MEDIAN OF TWO SORTED ARRAY TECHNIQUE)

TIME: O(log(min(N, M)), bcz we are applying BS always on the smaller array.

SPACE: O(1).
*/

class Solution {
    public long kthElement( int arr1[], int arr2[], int n, int m, int k) {
        
        // if the length of the first array > second array, then we have to interchange the array
        /// so that the first array always be small
        if(n > m) {
            return kthElement(arr2, arr1, m, n, k);
        }
        
        // now taking two pointers
        int low = Math.max(0, k - m);  // if k > arr2.length, then we have to take the remaining from first array
        int high = Math.min(k, n);   // if k < arr1.length, the we can take that many elements in the left part
        
        while(low <= high) {
            
            int cut1 = (low + high) >> 1;
            int cut2 = k - cut1;   // remaining elements
            
            int l1 = (cut1 == 0) ? Integer.MIN_VALUE : arr1[cut1 - 1];
            int l2 = (cut2 == 0) ? Integer.MIN_VALUE : arr2[cut2 - 1];
            int r1 = (cut1 == n) ? Integer.MAX_VALUE : arr1[cut1];
            int r2 = (cut2 == m) ? Integer.MAX_VALUE : arr2[cut2];
            
            // now we have to check for the vaidity condition, whether the parts are valid or not
            if(l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            }
            // in this case we have to decrease l2 & increase r1
            // so in order to increase r1 we have to move towards right
            else if(l2 > r1) {
               low = cut1 + 1; 
            }
            // (l1 > r2), then we have to move towrds left in order to increase r2 & decrease l1
            else {
                high = cut1 - 1;
            }
        }
        
        return 1;
    }
}