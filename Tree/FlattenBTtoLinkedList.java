/*
Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 
Example 1:

Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]

Example 2:

Input: root = []
Output: []

Example 3:

Input: root = [0]
Output: [0]


METHOD 1:

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    
    TreeNode prev = null;
    
    public void flatten(TreeNode root) {
        
        if(root == null) return;
        
	  // reverse postorder traversal
        flatten(root.right);
        flatten(root.left);
        
        root.right = prev;
        root.left = null;
        
        prev = root;
    }
}

/*
METHOD 2: (APPLICATION OF MORRIS TRAVERSAL)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    
    public void flatten(TreeNode root) {
        
        if(root == null) return;
        
        TreeNode curr = root;
        
        while(curr != null) {
            
            // if the current node has left, only then we able to connect it's left to right
            if(curr.left != null) {
                
                TreeNode prev = curr.left;
                
                // find the last node(means rightmost node) of the preorder traversal of the left subtree
                while(prev.right != null) {
                    prev = prev.right;
                }
                
                // pointing the previous to the right subtree of the current node
                prev.right = curr.right;
                
                // making the current's left to its current right 
                // make left to point to null
                curr.right = curr.left;
                curr.left = null;       
            }
            
            // otherwise move to the right
            curr = curr.right;
        }
    }
}