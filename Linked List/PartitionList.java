/*
Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example 1:

Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]

Example 2:

Input: head = [2,1], x = 2
Output: [1,2]


METHOD:(USING TWO POINTER)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public ListNode partition(ListNode head, int x) {
        
        // creating two lists, one for values < x, another for values >= x
        ListNode lessHead = new ListNode(-1);
        ListNode equalOrGreaterHead = new ListNode(-1);
        ListNode lessTail = lessHead, equalOrGreaterTail = equalOrGreaterHead;
        ListNode curr = head;

        // 1. Creating the two lists
        while(curr != null) {
            // for less than values
            if(curr.val < x) {
                lessTail.next = curr;
                lessTail = lessTail.next;
            }
            // for greater or equal values
            else {
                equalOrGreaterTail.next = curr;
                equalOrGreaterTail = equalOrGreaterTail.next;
            }
            curr = curr.next;
        }

        // 2. Merging the two list
        lessTail.next = (equalOrGreaterHead.next != null) ? equalOrGreaterHead.next : null;
        equalOrGreaterTail.next = null;
    
        head = lessHead.next;
        
        return head;
    }
}