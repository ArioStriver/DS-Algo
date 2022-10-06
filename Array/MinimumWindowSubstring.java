/*
Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If 
there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

A substring is a contiguous sequence of characters within the string.

Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"

Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

Example 2:

Input: s = "a", t = "a"
Output: "a"

Explanation: The entire string s is the minimum window.

Example 3:

Input: s = "a", t = "aa"
Output: ""

Explanation: Both 'a's from t must be included in the window. Since the largest window of s only has one 'a', return empty string.


METHOD 1:(BRUTE FORCE)

TIME: O(N^2).

SPACE: O(t.length()).
*/

class Solution {
    public String minWindow(String s, String t) {
        
        int n = s.length();
        
        int minWindowSeenSoFar = Integer.MAX_VALUE;
        
        String minWindow = "";
        
        // will generate the each and every substring
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                
                String tempSub = s.substring(i, j+1);
                
                // checking whether the current substring contains all the characters of t or not
                boolean windowContainsAllChars = stringContainsAllCharacters(tempSub, t);
                
                // if contains and the length is small then store it
                if(windowContainsAllChars == true && tempSub.length() < minWindowSeenSoFar) {
                    minWindowSeenSoFar = tempSub.length();
                    minWindow = tempSub;
                }
            }
        }
        
        return minWindow;
    }
    
    private boolean stringContainsAllCharacters(String searchString, String t) {
        
        // first store the frequency of the t string
        Map<Character, Integer> freq = new HashMap<>();
        
        for(char c : t.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        
        for(int i = 0; i < searchString.length(); i++) {
            
            char curChar = searchString.charAt(i);
            
            if(freq.containsKey(curChar)) {
                
                // decreasing the count by one
                int count = freq.get(curChar)-1;
                
                // If we have satisfied all of the characters for this character, remove the key
                // from the hashtable.
                // Otherwise, just update the mapping with 1 less occurrence to satisfy for
                if(count == 0) {
                   freq.remove(curChar); 
                }
                else {
                    freq.put(curChar, count);
                }
            }
        }
        
        // If we satisfied all characters the the required characters hashtable will be empty
        return freq.isEmpty();
    }
}


/*
METHOD 2:(USING SLIDING WINDOW TECHNIQUE)
	APPROACH:
		First create a window which satisfies all the characters in the t string. After that try to find the minimum window that satisfies 't'.
			1) Expand the window
			2) Contract the window

TIME: O(M + N), where M & N is the length of the s and t string.

SPACE: O(M + N), when the window size is equal to the entire string S, when T has all unique characters.
*/

class Solution {
    public String minWindow(String s, String t) {
        
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }
        
        Map<Character, Integer> freqOfT = new HashMap<>();
        
        // 1. storing the frequency of the t string
        for(char c : t.toCharArray()) {
            freqOfT.put(c, freqOfT.getOrDefault(c, 0) + 1);
        }
        
        int required = t.length();         
        int left = 0, right = 0;
        int start = 0, minLen = Integer.MAX_VALUE;
        
        // 2. Traversing through the s string
        while(right < s.length()) {
            
            char c = s.charAt(right);
            int count = freqOfT.get(c) == null ? 0 : freqOfT.get(c);
            
            // First we are finding the window that satisfies all the characters of t
            // if the current character is present in the map and the its frequency is > 0
            if(freqOfT.containsKey(c) && count > 0) {
                 
                // decrease the count 
                required--;
            }
                 
            // also decrease the frequency of the current character
            count--;
                 
            freqOfT.put(c, count);     // update the map
            
            // now we have to contract the window in order to find the minimum window substring
            while(required == 0) {
                
                // storing the minimum length and the start of the substring
                if(right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }
                
                // removing element from left means we have to increase the count for the current character
                char currChar = s.charAt(left);
                int val = freqOfT.get(currChar) == null ? 0 : freqOfT.get(currChar)+1;
                freqOfT.put(currChar, val);
                
                // it means we are removing a required element
                if(freqOfT.get(currChar) > 0) {
                    required++;
                }
                
                left++;
            }
            
            right++;
        }
        
        return minLen != Integer.MAX_VALUE ? s.substring(start, start + minLen + 1) : "";
    }
}


// Slightly Optimed Instead of using hashmap map we can use frequency array

class Solution {
    public String minWindow(String s, String t) {
        
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }
        
        int[] freqOfT = new int[128];
        
        // 1. storing the frequency of the t string
        for(char c : t.toCharArray()) {
            freqOfT[c]++;
        }
        
        int required = t.length();         
        int left = 0, right = 0;
        int start = 0, minLen = Integer.MAX_VALUE;
        
        // 2. Traversing through the s string
        while(right < s.length()) {
            
            char c = s.charAt(right);
            
            // First we are finding the window that satisfies all the characters of t
            if(freqOfT[c] > 0) {
                 
                // decrease the count 
                required--;
            }
                 
            // also decrease the frequency of the current character
            freqOfT[c]--;
            
            // now we have to contract the window in order to find the minimum window substring
            while(required == 0) {
                
                // storing the minimum length and the start of the substring
                if(right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }
                
                // removing element from left means we have to increase the count for the current character
                char leftChar = s.charAt(left);
                freqOfT[leftChar]++;
                
                // it means we are removing a required element
                if(freqOfT[leftChar] > 0) {
                    required++;
                }
                
                // increment the left pointer
                left++;
            }
            
            // increment the right pointer
            right++;
        }
        
        return minLen != Integer.MAX_VALUE ? s.substring(start, start + minLen + 1) : "";
    }
}