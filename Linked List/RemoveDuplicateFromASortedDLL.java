/*
Given a doubly linked list of n nodes sorted by values, the task is to remove duplicate nodes present in the linked list.

Example 1:

Input: n = 6, 1<->1<->1<->2<->3<->4

Output: 1<->2<->3<->4

Explanation: Only the first occurance of node with value 1 is retained, rest nodes with value = 1 are deleted.

Example 2:

Input: n = 7, 1<->2<->2<->3<->3<->4<->4

Output: 1<->2<->3<->4

Explanation: Only the first occurance of nodes with values 2,3 and 4 are retained, rest repeating nodes are deleted.

Your Task:
You have to complete the method removeDuplicates() which takes 1 argument: the head of the linked list.  Your function should return a pointer to a linked list with no duplicate element.

METHOD:

TIME: O(N).

SPACE: O(1).
*/



//User function Template for Java

class Solution{
    Node removeDuplicates(Node head){
        
        if(head == null) return null;
        
        Node currN = head, nextN = head;
        
        while(nextN != null) {
		
		// if the values are equal
            if(currN.data == nextN.data) {
                nextN = nextN.next;
            }
		// if the values are not equal
            else {
                currN.next = nextN;
                nextN.prev = currN;
                currN = nextN;
            }
        }
        
	  // if there is a duplicate value in the last node
        if(currN != null) currN.next = null;
        
        return head;
    }
}