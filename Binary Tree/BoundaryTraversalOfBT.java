/*
Given a Binary Tree, find its Boundary Traversal. The traversal should be in the following order: 

Left boundary nodes: defined as the path from the root to the left-most node ie- the leaf node you could reach when you always travel preferring the left subtree over the right subtree. 
Leaf nodes: All the leaf nodes except for the ones that are part of left or right boundary.
Reverse right boundary nodes: defined as the path from the right-most node to the root. The right-most node is the leaf node you could reach when you always travel preferring the right 
subtree over the left subtree. Exclude the root from this as it was already included in the traversal of left boundary nodes.
Note: If the root doesn't have a left subtree or right subtree, then the root itself is the left or right boundary. 

Example 1:

Input:
        1 
      /   \
     2     3  
    / \   / \ 
   4   5 6   7
      / \
     8   9
   
Output: 1 2 4 8 9 6 7 3

Example 2:

Input:
            1
           /
          2
        /  \
       4    9
     /  \    \
    6    5    3
             /  \
            7     8

Output: 1 2 4 6 5 7 8
 
Your Task:
This is a function problem. You don't have to take input. Just complete the function boundary() that takes the root node as input and returns an array containing the boundary values in 
anti-clockwise.


METHOD:
	APPROACH:
		Part 1: Left Boundary of the tree (excluding the leaf nodes).
		Part 2: All the leaf nodes travelled in the left to right direction.
		Part 3: Right Boundary of the tree (excluding the leaf nodes), traversed in the reverse direction.

TIME: O(H) + O(H) + O(N) which is ≈ O(N), 1st O(H) for left boundary and secong one for right boundary and O(N) for leaf nodes traversal.

SPACE: O(N), Space is needed for the recursion stack while adding leaves. In the worst case (skewed tree), space complexity can be O(N).
*/


class Solution
{
    	private boolean isLeaf(Node node) {
        
        		return (node.left == null) && (node.right == null);
    	}
    
   	 private void addLeftBoundary(Node node, ArrayList<Integer> res) {
        
        		Node cur = node.left;
        
       		 while(cur != null) {
            
            			// first check whether it is leaf node or not
            			if(isLeaf(cur) == false) res.add(cur.data);
            
            			if(cur.left != null) cur = cur.left;
            
            			else cur = cur.right;
       		 }
   	 }
    
   	 private void addLeafNodes(Node node, ArrayList<Integer> res) {
        
        		// preorder traversal
        		// if the current node is leaf node then add it to the final array
        		if(isLeaf(node)) {
           	 		res.add(node.data);
            			return;
       		 }
        
        		if(node.left != null) addLeafNodes(node.left, res);
        		if(node.right != null) addLeafNodes(node.right, res);
   	 }
    
    	private void addRightBoundary(Node node, ArrayList<Integer> res) {
        
        		Node cur = node.right;
        		Stack<Node> st = new Stack<>();
        
       		 while(cur != null) {
            
            			// first check whether it is leaf node or not
            			if(isLeaf(cur) == false) st.push(cur);
            
            			if(cur.right != null) cur = cur.right;
            
            			else cur = cur.left;
        		}
        
        		// getting the right boundary in reversed order
       	 	while(!st.isEmpty()) {
            			res.add(st.pop().data);
        		}
    	} 
    
	ArrayList <Integer> boundary(Node node)
	{
	    	ArrayList<Integer> boundaryNodes = new ArrayList<>();
	    
	    	if(isLeaf(node) == false) boundaryNodes.add(node.data);
	    
	   	 // 1. Left Boundary of the tree (excluding the leaf nodes)
	    	addLeftBoundary(node, boundaryNodes);
	    
	    	// 2. All the leaf nodes travelled in the left to right direction
	    	addLeafNodes(node, boundaryNodes);
	    
	    	//3. Right Boundary of the tree (excluding the leaf nodes), traversed in the reverse direction
	   	 addRightBoundary(node, boundaryNodes);
	    
	    	return boundaryNodes;
	}
}