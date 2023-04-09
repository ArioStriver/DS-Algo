/*
Given a linked list of N nodes where nodes can contain values 0s, 1s, and 2s only. The task is to segregate 0s, 1s, and 2s linked list such that all zeros segregate to head side, 2s at the end of the linked list, and 1s in the mid of 0s and 2s.

Example 1:

Input: N = 8, value[] = {1,2,2,1,2,0,2,2}
Output: 0 1 1 2 2 2 2 2

Explanation: All the 0s are segregated to the left end of the linked list, 2s to the right end of the list, and 1s in between.

Example 2:

Input: N = 4, value[] = {2,2,0,1}
Output: 0 1 2 2

Explanation: After arranging all the 0s,1s and 2s in the given format, the output will be 0 1 2 2.

Your Task:
The task is to complete the function segregate() which segregates the nodes in the linked list as asked in the problem statement and returns the head of the modified linked list. The printing is done automatically by the driver code.


METHOD 1:(USING COUNT)

TIME: O(2N).

SPACE: O(1).
*/


class Solution
{
    static Node segregate(Node head)
    {
        int zeros = 0, ones = 0, twos = 0;
        
        Node curr = head;
        while(curr != null) {
            if(curr.data == 0) {
                zeros++;
            }
            else if(curr.data == 1) {
                ones++;
            }
            else {
                twos++;
            }
            curr = curr.next;
        }
        
        curr = head;
        
        while(zeros-- > 0) {
            curr.data = 0;
            curr = curr.next;
        }
        
        while(ones-- > 0) {
            curr.data = 1;
            curr = curr.next;
        }
        
        while(twos-- > 0) {
            curr.data = 2;
            curr = curr.next;
        }      
        return head;
    }
}

/*
METHOD 2:(USING DUMMY NODE)(IF THE REPLACEMENT OF VALUE IS NOT ALLOWED)

TIME: O(N).

SPACE: O(1).
*/


class Solution
{
    //Function to sort a linked list of 0s, 1s and 2s.
    static Node segregate(Node head)
    {
        Node zeroHead = new Node(-1);
        Node oneHead = new Node(-1);
        Node twoHead = new Node(-1);
        
        Node zeroTail = zeroHead, oneTail = oneHead, twoTail = twoHead, curr = head;
        
        // creating sperate list for 0s, 1s and 2s
        while(curr != null) {
            if(curr.data == 0) {
                zeroTail.next = curr;
                zeroTail = zeroTail.next;
            }
            else if(curr.data == 1) {
                oneTail.next = curr;
                oneTail = oneTail.next;
            }
            else {
                twoTail.next = curr;
                twoTail = twoTail.next;
            }
            curr = curr.next;
        }
        
        // Merging the sublists
        // if any 1's persent in the list then merge 1s list to 0's list, otherwise merge it with 2's list
        zeroTail.next = (oneHead.next != null) ? oneHead.next : twoHead.next;
        oneTail.next = twoHead.next;
        twoTail.next = null;
        
        head = zeroHead.next;  // pointing the head of the head of the 0's list
        
        return head;
    }
}
