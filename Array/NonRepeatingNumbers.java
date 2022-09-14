/*
Given an array A containing 2*N+2 positive numbers, out of which 2*N numbers exist in pairs whereas the other two number occur exactly once and are distinct. Find the other two numbers. 
Return in increasing order.


Example 1:

Input: 
N = 2
arr[] = {1, 2, 3, 2, 1, 4}

Output: 3 4 

Explanation:3 and 4 occur exactly once.

Example 2:

Input:
N = 1
arr[] = {2, 1, 3, 2}

Output: 1 3

Explanation: 1 3 occur exactly once.


METHOD 1:(USING SORTING)

TIME: O(NlogN).

SPACE: O(1).


METHOD 2:(BIT MANUPULATION)

TIME: O(N).

SPACE: O(1).
*/

class Solution
{
    public int[] singleNumber(int[] nums)
    {
        // 1. First get the XOR of all the numbers
        int xor = 0;
        
        for(int num : nums) {
            xor = xor ^ num;
        }
        
        // All the bits that are set in xor will be set in one non-repeating element (x or y) and not in others.
	  // Apply masking
        // 2. Get a number which has only one set bit of the xor.   
        // Since we can easily get the rightmost set bit, let us use it.

        int rightMostSetBit = xor & ~(xor-1);
        
        
        // 3. Now divide the elements in two sets and do xor of elements in each set and we get the non-repeating
        // one set having elements who don't have right most bit set and another set having elements who have 
        // right most bit set
        int x = 0, y = 0;
        
        for(int num : nums) {
            
            // right most bit set
            if((num & rightMostSetBit) > 0) {
                x = (x ^ num);
            }
            // not set
            else {
                y = (y ^ num);
            }
        }
        
        int[] res = new int[2];
        
        if(x < y) {
            res[0] = x;
            res[1] = y;
        }
        else {
            res[0] = y;
            res[1] = x;
        }
        
        return res;
    }
}