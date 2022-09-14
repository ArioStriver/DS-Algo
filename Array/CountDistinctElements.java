/*
Given an array of size N and an integer K, return the count of distinct numbers in all windows of size K. 

Examples: 

Input: arr[] = {1, 2, 1, 3, 4, 2, 3}, K = 4
Output: 3 4 4 3

Explanation: First window is {1, 2, 1, 3}, count of distinct numbers is 3, Second window is {2, 1, 3, 4} count of distinct numbers is 4, Third window is {1, 3, 4, 2} count of distinct 
		 numbers is 4, Fourth window is {3, 4, 2, 3} count of distinct numbers is 3

Input: arr[] = {1, 2, 4, 4}, K = 2
Output: 2 2 1

Explanation: First window is {1, 2}, count of distinct numbers is 2, First window is {2, 4}, count of distinct numbers is 2, First window is {4, 4}, count of distinct numbers is 1.


METHOD 1:(BRUTE FORCE)
	APPROACH:
		Traverse the given array considering every window of size K in it and keeping a count on the distinct elements of the window

TIME: O(N * K^2).

SPACE: O(1).


METHOD 2:(USING HASHING)

TIME: O(N).

SPACE: O(N).
*/

class Solution
{
    ArrayList<Integer> countDistinct(int A[], int n, int k)
    {
        ArrayList<Integer> res = new ArrayList<>();
        Map<Integer, Integer> freq = new HashMap<>();
        int count = 0;
        
        // 1. first we will count the distinct elements in first window
        for(int i = 0; i < k; i++) {
            freq.put(A[i], freq.getOrDefault(A[i], 0) + 1);
            
            // distinct element always has a frequecy 1
            if(freq.get(A[i]) == 1) {
                count++;
            }
        }
        
        res.add(count);
        
        // now traverse for the rest
        // one by one eleminate the element from left and add element from right in the window
        for(int i = k; i < n; i++) {
            
            // eleminating element from left
            freq.put(A[i-k], freq.get(A[i-k])-1);
            
            // after elemination if it's frequency becomes 0, it means it's a distinct element
            if(freq.get(A[i-k]) == 0) {
                count--;
            }
            
            // adding element in the window from right
            freq.put(A[i], freq.getOrDefault(A[i], 0) + 1);
            
            // distinct element always has a frequecy 1
            if(freq.get(A[i]) == 1) {
                count++;
            }
            
            res.add(count);
        }
        
        return res;
    }
}
