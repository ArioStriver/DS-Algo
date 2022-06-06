/*
Given a sorted array A and a target value B, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Problem Constraints
1 <= |A| <= 100000

1 <= B <= 10^9

Example Input
Input 1:

 A = [1, 3, 5, 6]
B = 5
Input 2:

 A = [1, 3, 5, 6]
B = 2


Example Output
Output 1:

 2
Output 2:

 1


METHOD:

TIME: O(logN).
*/

public class Solution {
	public int searchInsert(ArrayList<Integer> a, int key) {

        int low = 0, high = a.size()-1, pos = -1;

        while(low <= high) {

            int mid = low + (high - low) / 2;
            
            // if the element is present then return its position
            if(a.get(mid) == key) return mid;

            // if the element is not present then, find the position of the largest smallest elemet < key
            // and the key element will be at that element's postion + 1 
            if(a.get(mid) < key) {
                pos = mid;
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        return pos+1;
	}
}