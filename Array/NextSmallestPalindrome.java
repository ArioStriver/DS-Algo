/*
Given a number, in the form of an array Num[] of size N containing digits from 1 to 9(inclusive). The task is to find the next smallest palindrome strictly larger than the given number.

Example 1:

Input:
N = 11
Num[] = {9, 4, 1, 8, 7, 9, 7, 8, 3, 2, 2}

Output: 9 4 1 8 8 0 8 8 1 4 9

Explanation: Next smallest palindrome is 94188088149.

Example 2:

Input:
N = 5
Num[] = {2, 3, 5, 4, 5}

Output: 2 3 6 3 2

Explanation: Next smallest palindrome is 23632.


METHOD 1: (BRUTE FORCE)
	APPROACH:
		Keep on incrementing the number by one untill we find the next smallest palindrome.

TIME: O(num * |num|).

SPACE: O(1).

METHOD 2: (OPTIMIZED)
	
TIME: O(N).

SPACE: O(1).
*/

class Solution {
    
    private Boolean isAll9s(int[] nums, int n) {
        
        for(int num : nums) {
            if(num != 9)
                return false;
        }
        return true;
    }

    Vector<Integer> generateNextPalindrome(int num[], int n) {
        
        Vector<Integer> res = new Vector<Integer>();
        
        if(isAll9s(num, n)) {
            
            res.add(1);
            
            for(int i = 0; i < n-1; i++) {
                res.add(0);
            }
            res.add(1);
        }
        else {
        
        
            // s1: calculate mid
            int mid = n / 2;
            
            // end of left side is always 'mid -1'
            int i = mid-1;
            
            // Beginning of right side depends if n is odd or even
            int j = n % 2 == 0 ? mid : mid + 1;
            
            // s2: first ignore the same digits
            while(i >= 0 && num[i] == num[j]) {
                i--;
                j++;
            }
            
            // s3: then check whether the middle left element is smaller than the middle right element or not
            boolean isLeftSmaller = false;
            
            if(i < 0 || num[i] < num[j]) {
                isLeftSmaller = true;
            }
            
            // Also why we are overriding the right part with left part only ?
            // bcz here we have to find the next smallest palindrome greater than the current one,
            // in that case we need to increase the digits from LSB (means right to left)
            // if the middle left is not smaller then just simply override the right part of the middle element
            // with the left part
            while(i >= 0) {
                num[j++] = num[i--];
            }
            
            // now if the left middle is smaller we have to do some computation
            // if so then we have to add 1 to the middle left element for even length and middle element for the
            // odd length
            
            if(isLeftSmaller == true) {
                
                int carry = 1;
                
                // for odd length
                if(n % 2 == 1) {
                    num[mid] += 1;
                    carry = num[mid] / 10;
                    num[mid] = num[mid] % 10;
                }
                
                i = mid - 1;
                j = (n % 2 == 0 ? mid : mid + 1);
                
                // Add 1 to the rightmost digit of the left side, propagate the carry towards MSB digit
                // and simultaneously copying mirror of the left side to the right side.
                // when carry is zero no need to loop through till i>=0
                while(i >= 0 && carry > 0) {
                    num[i] += carry;
                    carry = num[i] / 10;
                    num[i] = num[i] % 10;
                    num[j] = num[i];
                    i--;
                    j++;
                }
                
            }
            
            for(int a : num) {
                res.add(a);
            }
        }
        
        return res;
    }
}
