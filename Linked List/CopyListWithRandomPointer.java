/*
A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
Your code will only be given the head of the original linked list.

Example 1:

Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

Example 2:

Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]

Example 3:

Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]


METHOD 1:(USING HASHING)

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public Node copyRandomList(Node head) {
        
        if(head == null) return head;
        
        Map<Node, Node> map = new HashMap<>();
        Node curr = head;
        
        // creating a dummy node
        Node dummy = new Node(0);
        Node prev = dummy;
        
        // 1. copy the list into the map
        while(curr != null){
            Node node = new Node(curr.val);
            
            prev.next = node;
            
            // putting the address into the map
            map.put(curr, node);
            
            curr = curr.next;
            prev = prev.next;
        }
        
        // 2. After copying the list into the map, 
        // now we have to set the random pointer of the copied list accord. to the original list
        // c1 = original list, c2 = copied list
        Node c1 = head;
        Node c2 = dummy.next;
        
        while(c1 != null){
            
            // chech whether the c1 point to null/not, if so then make it null otherwise not
            c2.random = c1.random != null ? map.get(c1.random) : null;
            
            c1 = c1.next;
            c2 = c2.next;
        }
        
        return dummy.next;
    }
}


/*
METHOD 2:(SPACE OPTIMIZED)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public Node copyRandomList(Node head) {
        
        if(head == null) return head;
        
        // STEP 1: 
        // Iterate the original list and duplicate each node. 
        // The duplicate of each node follows its original immediately.
        Node curr = head;
        Node nextNode = head;
        
        while(curr != null){
            nextNode = curr.next;
            Node copyNode = new Node(curr.val);
            curr.next = copyNode;
            copyNode.next = nextNode;
            curr = nextNode;
        }
        
        // STEP 2:
        // Iterate the new list and assign the random pointer for each duplicated node.
        curr = head;
        
        while(curr != null){
            curr.next.random = curr.random != null ? curr.random.next : null;
            curr = curr.next.next;
        }
        
        // STEP 3:
        // Restore the original list and extract the duplicated nodes.
        // basically here we differentiate the original and the duplicated list
        curr = head;
        nextNode = head;
        
        Node dummy =  new Node(0);
        Node Copy = dummy;
        
        while(curr != null){
            nextNode = curr.next.next;
            Copy.next = curr.next;
            curr.next = nextNode;
            curr = nextNode;
            Copy = Copy.next;
        }
        
        return dummy.next;
    }
}