/*
Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.

The length of each part should be as equal as possible: no two parts should have a size differing by more than one. This may lead to some parts being null.

The parts should be in the order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal to parts occurring later.

Return an array of the k parts.

Example 1:

Input: head = [1,2,3], k = 5
Output: [[1],[2],[3],[],[]]
Explanation:The first element output[0] has output[0].val = 1, output[0].next = null. The last element output[4] is null, but its string representation as a ListNode is [].

Example 2:

Input: head = [1,2,3,4,5,6,7,8,9,10], k = 3
Output: [[1,2,3,4],[5,6,7],[8,9,10]]

Explanation: The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.


METHOD:

TIME: O(2N) == O(N).

SPACE: O(1), excluding the ans listnode.
*/

class Solution {

    private int getLength(ListNode head) {
        int len = 0;
        while(head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    public ListNode[] splitListToParts(ListNode head, int K) {
        
        int length = getLength(head);
        int partSize = length / K;

        // if there is any extra nodes then we divide those nodes in each part equally starting from left
        int extraNodes = length % K;
        ListNode[] ans = new ListNode[K];
        int i = 0;
        ListNode curr = head, prev = null;

        while(curr != null) {
            int eachPartSize = partSize;
            ans[i] = curr;              // storing the head of current part

            // 1. check whether the current part size == eachpart size
            while(eachPartSize > 0) {
                prev = curr;
                curr = curr.next;
                eachPartSize--;
            }

            // 2. check for extra node, will add one node at a time for each part
            if(extraNodes != 0) {
                extraNodes--;
                prev = curr;       // prev = last node of the current part
                curr = curr.next;  // curr = point the starting of the next node
            }

            // 3. make the last node of the current part to point null
            prev.next = null;
            i++;
        }

        return ans;
    }
}