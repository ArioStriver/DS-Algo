/*
Given a characters array letters that is sorted in non-decreasing order and a character target, return the smallest character in the array that is larger than target.

Note that the letters wrap around.

For example, if target == 'z' and letters == ['a', 'b'], the answer is 'a'.
 
Example 1:

Input: letters = ["c","f","j"], target = "a"
Output: "c"

Example 2:

Input: letters = ["c","f","j"], target = "c"
Output: "f"

Example 3:

Input: letters = ["c","f","j"], target = "d"
Output: "f"


METHOD: (USING BINARY SEARCH)

TIME: O(logN).

SPACE: O(1).
*/

class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        
        int n = letters.length;
        int start = 0, end = n-1;
        int res = -1;
        
        while(start <= end) {
            
            int mid = (start + end) / 2;
            
            // if we find the current character, then search for the  smallest character 
            // in the array that is larger than target in it's right part as it is sorted
            if(letters[mid] == target) start = mid + 1;
            
            if(letters[mid] < target) {
                start = mid + 1;
            }
            // if the (current character > target), it means that there is a chance that this 
            // charcater might be our answer, so store it
            else if(letters[mid] > target){
                res = mid;
                end = mid - 1;
            }
        }
        
        // if we are not able to find and charcater that is larger than target
        // it means that next smallest element for the target should be the 0th character in the array
        return res != -1 ? letters[res] : letters[0];
    }
}