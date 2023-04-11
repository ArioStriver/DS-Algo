/*
You are given the head of a doubly Linked List and a Key. Your task is to delete all occurrences of the given key and return the new DLL.

Note :- There exists atleast 1 distinct element other than key.

Example:

Input:  2<->2<->10<->8<->4<->2<->5<->2
	  Key = 2

Output: 10<->8<->4<->5

Explanation: All Occurences of 2 have been deleted.

Your Task: Complete the function void deleteAllOccurOfX(struct Node** head_ref, int key), which takes the reference of the head pointer and an integer value key. Delete all occurrences of the key from the given DLL.


METHOD:

TIME: O(N).

SPACE: O(1).
*/


class Solution {
    static Node deleteAllOccurOfX(Node head, int key) {
        
        if(head == null) return null;
        
        // deleting the all front nodes whose value is equal to key
        while(head != null && head.data == key) {
            head = head.next;
        }
        
        Node curr = head;
        
        while(curr != null) {
            if(curr.data == key) {
                
                curr.prev.next = curr.next;    // moving the next pointer of the current's previous node 
                
                if(curr.next != null)
                    curr.next.prev = curr.prev;
            }
            curr = curr.next;
        }
        
        return head;
    }
}