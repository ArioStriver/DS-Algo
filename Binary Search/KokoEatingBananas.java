/*
Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of 
them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4

Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30

Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23


METHOD:

TIME: O(NlogM), where M = max(A).

SPACE: O(1).
*/

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        
        int maxEle = 0;
        
        // maximum how many bananas koko acn eat in a hour
        for(int i : piles) {
            maxEle = Math.max(maxEle, i);
        }
        
        if(piles.length == h) {
            return maxEle;
        }
        
        
        int low = 0, high = maxEle;
        
        // now we have to find the mimimum speed koko need to finish all the bananas 
        while(low <= high) {
            
            int mid = low + (high - low) / 2;
            
            // caculating how many bananas koko can eat
            // If the pile has less than k bananas, she eats all of them instead 
            // and will not eat any more bananas during this hour
            int hoursTaken = 0;
            for(int i : piles) {
                // here we have to take ceil bcz after eating k no. of bananas on a hour, th rest will 
                // be for the next hour
                hoursTaken += Math.ceil((double) i / mid);
            }
            
            // checking keeping this current speed whether koko will able to finish all bananas before
            // the guard will come back
            if(hoursTaken > h) {
                low  = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        
        return low;
    }
}