/*
Given a string you need to print the size of the longest possible substring that has exactly K unique characters. If there is no possible substring then print -1.

Example 1:

Input:
S = "aabacbebebe", K = 3
Output: 7

Explanation: "cbebebe" is the longest substring with K distinct characters.

Example 2:

Input: 
S = "aaaa", K = 2
Output: -1

Explanation: There's no substring with K distinct characters.


METHOD 1:(BRUTE FORCE)

TIME: O(N^2), where N is the length of the given string S.

SPACE: O(K), where K is the no. of unique characters.
*/

class Solution {
    public int longestkSubstr(String s, int k) {
        
        Map<Character, Integer> freq = new HashMap<>();
        int maxLen = -1;
        
        for(int i = 0; i < s.length(); i++) {
            
            for(int j = i; j < s.length(); j++) {
                
                char c = s.charAt(j);
                
                freq.put(c, freq.getOrDefault(c, 0) + 1);
                
                // when the no. of charcaters in the map == to the k, then calculate the maximum length
                if(freq.size() == k) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
                
                // if more than k distinct characters
                // if at any point the length of the map is > k, then no need to check further for later substrings
                if(freq.size() > k) {
                    break;
                }
            }
        }
        
        return maxLen != -1 ? maxLen : -1;
    }
}

/*
METHOD 2:(USING SLIDING WINDOW)

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public int longestkSubstr(String s, int k) {
        
        int left = 0, right = 0;
        int maxLen = Integer.MIN_VALUE;
        int distinct = 0;
        int[] freq = new int[128];
        
        while(right < s.length()) {
            
            char cur = s.charAt(right);
            
            // a character is distinct when its count is 0
            if(freq[cur] == 0) {
                distinct++;
            }
            
            freq[cur]++;    // update the frequency of the current character
            
            // in case distinct > k, then in that case we need reomve elements from left
            // in order to make distinct characters == k
            while(distinct > k) {
                char c = s.charAt(left);
                
                // we are loosing one distinct element
                if(freq[c] == 1) {
                    distinct--;
                }
                freq[c]--;
                left++;
            }
            
            // keep on adding characters from right to get the longest substring with k unique characters
            // untill (distinct == k)
            right++;
            
            maxLen = Math.max(maxLen, right - left);
        }
        
        // if it not satisfies the k distinct characters then return -1 otherwise maxLength of substring
        return distinct < k ? -1 : maxLen;
    }
}