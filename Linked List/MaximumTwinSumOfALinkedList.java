/*
In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.

For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
The twin sum is defined as the sum of a node and its twin.

Given the head of a linked list with even length, return the maximum twin sum of the linked list.

Example 1:

Input: head = [5,4,2,1]
Output: 6

Explanation: Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6. There are no other nodes with twins in the linked list. Thus, the maximum twin sum of the linked list is 6. 

Example 2:

Input: head = [4,2,2,3]
Output: 7

Explanation:
The nodes with twins present in this linked list are:
- Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
- Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
Thus, the maximum twin sum of the linked list is max(7, 4) = 7. 

Example 3:

Input: head = [1,100000]
Output: 100001

Explanation: There is only one node with a twin in the linked list having twin sum of 1 + 100000 = 100001.


METHOD 1:(USING EXTRA SPACE)

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public int pairSum(ListNode head) {
       
       List<Integer> nums = new ArrayList<>();
       int maximumTwinSum = 0;

       while(head != null) {
           nums.add(head.val);
           head = head.next;
       }

       int L = 0, R = nums.size()-1;
       while(L < R) {
           maximumTwinSum = Math.max(maximumTwinSum, nums.get(L)+nums.get(R));
           L++;
           R--;
       }

       return maximumTwinSum;
    }
}

/*
METHOD 2:(SPACE OPTIMIZED)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    private ListNode reverse(ListNode head) {

        ListNode curr = head, nextNode = null, prev = null;
        while(curr != null) {
            nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }

    public int pairSum(ListNode head) {

       // 1.First find the middle of the linked list
       ListNode first = head, slow = head;
       while(first.next.next != null) {
           first = first.next.next;
           slow = slow.next;
       }

       // 2. Now reverse the 2nd half of the linked list
       ListNode h2 = reverse(slow.next);

       // 3. Calculating the maximum twin sum
       ListNode h1 = head;
       int maximumTwinSum = 0;

       while(h2 != null) {
           maximumTwinSum = Math.max(maximumTwinSum, h1.val + h2.val);
           h1 = h1.next;
           h2 = h2.next;
       }
       
       return maximumTwinSum;
    }
}