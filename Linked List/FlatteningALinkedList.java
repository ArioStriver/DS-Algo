/*
Given a Linked List of size N, where every node represents a sub-linked-list and contains two pointers:
(i) a next pointer to the next node,
(ii) a bottom pointer to a linked list where this node is head.
Each of the sub-linked-list is in sorted order.
Flatten the Link List such that all the nodes appear in a single level while maintaining the sorted order. 

Note: The flattened list will be printed using the bottom pointer instead of the next pointer.
For more clarity have a look at the printList() function in the driver code.

Example 1:

Input:
5 -> 10 -> 19 -> 28
|     |     |     | 
7     20    22   35
|           |     | 
8          50    40
|                 | 
30               45

Output:  5-> 7-> 8- > 10 -> 19-> 20->22-> 28-> 30-> 35-> 40-> 45-> 50.

Explanation: The resultant linked lists has every node in a single level.
(Note: | represents the bottom pointer.)
 

Example 2:

Input:
5 -> 10 -> 19 -> 28
|          |                
7          22   
|          |                 
8          50 
|                           
30              

Output: 5->7->8->10->19->22->28->30->50

Explanation:The resultant linked lists has every node in a single level.

(Note: | represents the bottom pointer.)
 
Your Task:
You do not need to read input or print anything. Complete the function flatten() that takes the head of the linked list as input parameter and returns the head of flattened link list.


METHOD:(USING MERGE OPERATION)

TIME: O(N*N*M)

SPACE: O(1)
*/

class GfG
{
    Node mergeTwoList(Node a, Node b){
        
        Node temp = new Node(0);
        Node res = temp;
        
        // if a and are not null
        // simply performing the mergr operation
        while(a != null && b != null){
            
            // checks whether a < b or b < a
            // whichever is minimum we put that in the bottom part of the temp
            // then move temp and the current pointer
            if(a.data < b.data){
                temp.bottom = a;
                temp = temp.bottom;
                a = a.bottom;
            }
            else{
                temp.bottom = b;
                temp = temp.bottom;
                b = b.bottom;
            }
        }
        
        // if a is still not null  but b == null then copy the remaining elements of a
        while(a != null){
            temp.bottom = a;
            temp = temp.bottom;
            a = a.bottom;
        }
        
        // if b is still not null  but a == null then copy the remaining elements of b
        while(b != null){
            temp.bottom = b;
            temp = temp.bottom;
            b = b.bottom;
        }
        
        return res.bottom;
    }
    
    Node flatten(Node root)
    {
	    //base case
	    if(root == null || root.next == null) return root;
	    
	    // traverse till the last node
	    root.next = flatten(root.next);
	    
	    //calling the merge function
	    // sending current node and its next node bcz we mege two list at a time
	    root = mergeTwoList(root, root.next);
	    
	    
	    return root;
    }
}