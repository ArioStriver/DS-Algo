/*
Given a string s, partition the string into one or more substrings such that the characters in each substring are unique. That is, no letter appears in a single substring more than once.

Return the minimum number of substrings in such a partition.

Note that each character should belong to exactly one substring in a partition.

Example 1:

Input: s = "abacaba"
Output: 4

Explanation: Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba"). It can be shown that 4 is the minimum number of substrings needed.

Example 2:

Input: s = "ssssss"
Output: 6

Explanation: The only valid partition is ("s","s","s","s","s","s").


METHOD:

TIME: O(N).

SPACE: O(26) == O(1).
*/

class Solution {
    public int partitionString(String s) {

        if(s.length() == 1) return 1;
        
        int l = 0, r = 0;
        int[] lastSeen = new int[26];  // keep track of the last index of each character 
        Arrays.fill(lastSeen, -1);
        int minNoOfSubstrings = 1;   // a single character itself is a substring

        while(r < s.length()) {
            char c = s.charAt(r);

            // already seen this current character and it falls within the range(l to r)
            // if so then update l, and increase the no. of substrings
            if(lastSeen[c-'a'] != -1 && l <= lastSeen[c-'a']) {
                minNoOfSubstrings++;
                l = r;
            }

            lastSeen[c-'a'] = r;  // updating the index of the current character
            r++;
        }

        return minNoOfSubstrings;
    }
}