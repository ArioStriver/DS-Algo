/*
Given a sorted doubly linked list of positive distinct elements, the task is to find pairs in a doubly-linked list whose sum is equal to given value target.

Example 1:

Input:  
1 <-> 2 <-> 4 <-> 5 <-> 6 <-> 8 <-> 9, target = 7
Output: (1, 6), (2,5)

Explanation: We can see that there are two pairs 
(1, 6) and (2,5) with sum 7.
 
Example 2:

Input: 
1 <-> 5 <-> 6
target = 6
Output: (1,5)

Explanation: We can see that there is one pairs  (1, 5) with sum 6.

Your Task:
You don't need to read input or print anything. Your task is to complete the function findPairsWithGivenSum() which takes head node of the doubly linked list and an integer target as input parameter and returns an array of pairs. If there is no such pair return empty array.


METHOD:

TIME: O(N).

SPACE: O(1).
*/


class Solution {
    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, Node head) {
        
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        
        if(head == null) return arr;
        
        // using two pointer apparoach
        Node left = head, right = head;
        
        // setting the right node to the last position
        while(right.next != null) {
            right = right.next;
        }
        
        while(left.data < right.data) {
            int sum = left.data + right.data;
            
            // if the current sum is equal to target sum, store then as a list
            if(sum == target) {
                arr.add(new ArrayList<>(Arrays.asList(left.data, right.data)));
                left = left.next;
                right = right.prev;
            }
            // if greater than, then move towards left
            else if(sum > target) {
                right = right.prev;
            }
            // otherwise move towards right
            else {
                left = left.next;
            }
        }
        
        return arr;
    }
}