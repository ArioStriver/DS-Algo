/*
Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:

Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

Example 2:

Input: head = [1], n = 1
Output: []

Example 3:

Input: head = [1,2], n = 1
Output: [1]


METHOD 1:

TIME: O(2N), O(N) for counting the nodes and another O(N) at worst case to get the Nth node.

SPACE: O(1).
*/

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        int C = 0; 
        ListNode dummy = new ListNode(-1);   // creating a dummy node
        dummy.next = head;
        ListNode curr = head;

        // 1. First counting the no. of nodes 
        while(curr != null) {
            curr = curr.next;
            C++;
        }
        curr = dummy;
        int K = C - n;

        // K == 0, means we have t remove the head node
        if(K == 0) return dummy.next.next;

        // move the current pointer for (n – count) nodes to get to the nth node of the list.
        for(int i = 1; i <= K; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;   // updating the next pointer
        return dummy.next;
    }
}


/*
METHOD 2:(EFFICIENT)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        ListNode dummy = new ListNode(-1);   // creating a dummy node
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

        // 1. First move the fast pointer n steps ahead
        for(int i = 1; i <= n; i++) {
            fast = fast.next;
        }

        // 2. Now move both slow and fast pointers simulataneously one step at a time untill fast 
        // reaches the last node. Why we are doing this? bcz to get the Nth node.
        while(fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;  // upadting the next pointer of the Nth node

        return dummy.next;
    }
}