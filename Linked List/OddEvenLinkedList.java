/*
Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.

The first node is considered odd, and the second node is even, and so on.

Note that the relative order inside both the even and odd groups should remain as it was in the input.

You must solve the problem in O(1) extra space complexity and O(n) time complexity.

Example 1:

Input: head = [1,2,3,4,5]
Output: [1,3,5,2,4]

Example 2:

Input: head = [2,1,3,5,6,4,7]
Output: [2,3,6,7,1,5,4]


METHOD:

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public ListNode oddEvenList(ListNode head) {

        if(head == null) return null;
        
        // creating two list : Odd list and even list
        ListNode evenHead = head.next;
        ListNode odd = head, even = head.next;

        while(even != null && even.next != null) {
            // set the odd's next to the even's next
            odd.next = even.next;
            odd = odd.next;

            // set the even's next to the odd's next
            even.next = odd.next;
            even = even.next;
        }
        // pointing the tail of the odd list to the head of the even list
        odd.next = evenHead;
        return head;
    }
}