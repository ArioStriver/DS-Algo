/*
A number N is represented in Linked List such that each digit corresponds to a node in linked list. You need to add 1 to it.

Example 1:

Input: LinkedList: 4->5->6
Output: 457 

Example 2:

Input: LinkedList: 1->2->3
Output: 124 

Your Task: Your task is to complete the function addOne() which takes the head of the linked list as the only argument and returns the head of the modified linked list. The driver code prints the number.

Note: The head represents the left-most digit of the number.


METHOD:

TIME: O(3N) == O(N).

SPACE: O(1).
*/

class Solution
{
    public static Node reverse(Node head) {
        Node prev = null;
        Node current = head;
        Node next;
        
        while (current != null) 
        { 
            next = current.next;    
            current.next = prev;    
            prev = current;           
            current = next;        
        }
        
        return prev; 
    } 

    public static Node addOne(Node head) { 
	  
	  // 1. reversing list to make addition easy
        head = reverse(head);           
        
        Node current = head;
        int carry = 1;
        
	  // 2. Performing the addition operation
        while(carry!=0)
        {
            current.data += 1;         // adding one to current node
            
		// if no carry we can reverse back list and return it
            if(current.data < 10) 
			return reverse(head);
                
		// else we continue with taking carry forward
            else 
			current.data = 0;
                
            // if, end of list, we break from loop
            if(current.next == null) break;
            
		// else we move to next node
            else current = current.next;
                
        }
        
	  // adding new node for the carried 1
        current.next = new Node(1); 

	  // 3. Now again reverse the list 
        return reverse(head);
    }
}