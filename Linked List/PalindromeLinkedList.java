/*
Given the head of a singly linked list, return true if it is a palindrome or false otherwise.

Example 1:

Input: head = [1,2,2,1]
Output: true

Example 2:

Input: head = [1,2]
Output: false

METHOD:

TIME: O(N).

SPACE: O(1).
*/

class Solution {

    private ListNode reverse(ListNode head) {
        ListNode next = null, prev = null, curr = head;

        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public boolean isPalindrome(ListNode head) {

        if(head == null || head.next == null) return true;
        
        // 1. First find the middle of the list
        ListNode fast = head, slow = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Now reverse the right part means that comes after middle
        slow.next = reverse(slow.next);
        slow = slow.next;
        ListNode head1 = head, head2 = slow;

        // 3. now check for whether they are palindrome or not by comapring the values in the left and rigth half
        while(head2 != null) {
            if(head1.val != head2.val) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }

        return true;
    }
}