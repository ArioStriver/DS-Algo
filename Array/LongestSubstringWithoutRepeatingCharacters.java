/*
Given a string S, find the length of the longest substring without repeating characters.

Example 1:

Input:
S = "geeksforgeeks"

Output: 7

Explanation: Longest substring is "eksforg".

Example 2:

Input:
S = "abdefgabef"

Output: 6

Explanation: Longest substring are "abdefg" , "bdefga" and "defgab".
 
Your Task:
You don't need to take input or print anything. Your task is to complete the function longestUniqueSubsttr() which takes a string S as and returns the length of the longest substring.


METHOD 1:(BRUTE FORCE)

TIME: O(N^2).

SPACE: O(1).
*/

class Solution{
 
public static int lengthOfLongestSubstring(String S)
{
    int n = S.length();
    int maxLen = 0;
     
    for(int i = 0; i < n; i++)
    {
         
        // Note : Default values in visited are false
        boolean[] visited = new boolean[256];
         
        for(int j = i; j < n; j++)
        {
             
            // If current character is visited Break the loop
            if (visited[S.charAt(j)] == true)
                break;
 
            // Else update the result if this window is larger, and mark current character as visited.
            else
            {
                res = Math.max(res, j - i + 1);
                visited[S.charAt(j)] = true;
            }
        }
 
        // Remove the first character of previous window
        visited[S.charAt(i)] = false;
    }
    return maxLen;
}


/*
METHOD 2:(USING SLIDING WINDOW)

TIME: O(2N), where N is the Length of the given string. Bcz at worst case we may have to traverse the string twice, once by the left and once by the right pointer.

SPACE: O(1).
*/

class Solution {
    public int lengthOfLongestSubstring(String S) {
        
        if(S.length() == 0) return 0;
       
        int maxLen = Integer.MIN_VALUE;
        int[] freq = new int[256];       // total 256 ascii characters
        
        int left = 0, right = 0;
        int repeatingChar = 0;
        
        while(right < S.length()) {
            
            char cur = S.charAt(right);
            
            // counting the duplicate character
            if(freq[cur] > 0) {
                repeatingChar++;
            }
            
            freq[cur]++;    // update the frequency of the current character
            
            // it means that there exists a repeating character in the current substring
            while(repeatingChar > 0) {
                char c = S.charAt(left);
                
                // if the frequency of the left character is > 1, it means that current character is the
                // repeating character
                if(freq[c] > 1) 
                    repeatingChar--;
                
                freq[c]--;
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
            
            right++;
        }
        
        return maxLen;
    }
}

/*
METHOD 3:(SLIDING WINDOW WITH OPTIMIZATION)
	APPROACH:
		Instead of moving the left pointer by one position towards right in order to remove the duplicate character, we can take a direct jump to the index where the duplicate elements
  		occurs last + 1. +1 to surpass the duplicate element.

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public int lengthOfLongestSubstring(String S) {
        
        if(S.length() == 0) return 0;
       
        int maxLen = Integer.MIN_VALUE;
        int[] lastSeenAtIndex = new int[256];
        
        Arrays.fill(lastSeenAtIndex, -1);
        
        int left = 0, right = 0;
        
        while(right < S.length()) {
            
            char cur = S.charAt(right);
            
            // if we have previously seen the current character
            if(lastSeenAtIndex[cur] != -1) {
                
                // if the last index of the current character falls under the current (L - R) range
                // then we have to contract our window 
                if(left <= lastSeenAtIndex[cur]) {
                    
                    // update the left pointer by the (last index current of char + 1)
                    left = lastSeenAtIndex[cur] + 1;
                }
            }
            
            // now update the index of the current characater
            lastSeenAtIndex[cur] = right;
            
            // calculate the longest length of the substring
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        
        return maxLen;
    }
}