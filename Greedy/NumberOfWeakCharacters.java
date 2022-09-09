/*
You are playing a game that contains multiple characters, and each of the characters has two main properties: attack and defense. You are given a 2D integer array properties where 
properties[i] = [attacki, defensei] represents the properties of the ith character in the game.

A character is said to be weak if any other character has both attack and defense levels strictly greater than this character's attack and defense levels. More formally, a character i is 
said to be weak if there exists another character j where attackj > attacki and defensej > defensei.

Return the number of weak characters.

Example 1:

Input: properties = [[5,5],[6,3],[3,6]]
Output: 0

Explanation: No character has strictly greater attack and defense than the other.

Example 2:

Input: properties = [[2,2],[3,3]]
Output: 1

Explanation: The first character is weak because the second character has a strictly greater attack and defense.

Example 3:

Input: properties = [[1,5],[10,4],[4,3]]
Output: 1

Explanation: The third character is weak because the second character has a strictly greater attack and defense.
 
Constraints:

2 <= properties.length <= 10^5
properties[i].length == 2
1 <= attacki, defensei <= 10^5


METHOD:(GREEDY & SORTING)

TIME: O(NlogN).

SPACE: O(1).
*/

class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        
        // attacks in increasing order, but if there are any attacks whose values are equal
        // then sort the array depending on the defenece value in decreaing order
        Arrays.sort(properties, (a,b)-> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        
        int noOfWeakCharacters = 0;
        
        // now traversing the arrat from back why ? 
        // bcz we know that if we go from right to left we will surely get the attacks from higher to lower
        // or equal, and if they are equal then we will see the defence value
        // we will not consider the values having same attacks
        
        int maxDefence = Integer.MIN_VALUE;   // to keep track of the max defence we have seen so far
        
        for(int i = properties.length-1; i >= 0; i--) {
            if(properties[i][1] < maxDefence) {
                noOfWeakCharacters++;
            }
            else {
                maxDefence = Math.max(maxDefence, properties[i][1]);
            }
        }
        
        return noOfWeakCharacters;
    }
}