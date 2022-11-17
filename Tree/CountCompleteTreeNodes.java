/*
Given the root of a complete binary tree, return the number of the nodes in the tree.

According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have 
between 1 and 2h nodes inclusive at the last level h.

Design an algorithm that runs in less than O(n) time complexity.

Example 1:

Input: root = [1,2,3,4,5,6]
Output: 6

Example 2:

Input: root = []
Output: 0

Example 3:

Input: root = [1]
Output: 1


METHOD 1:(USING ANY TRAVERSAL TECHNIQUE)
	APPROACH:
		Traverse the tree and count the no. of nodes in a tree.

TIME: O(N), where N is the no. of nodes.

SPACE: O(H), where H is the height of tree.


METHOD 2:

TIME: O((logN)^2).

SPACE: O(logN), considering the recursive stack space.
*/

class Solution {
    public int countNodes(TreeNode root) {
        
        if(root == null) return 0;
        
        // finding the left height
        TreeNode curr = root.left;
        int leftHeight = 1;
        
        while(curr != null) {
            leftHeight++;
            curr = curr.left;
        }
        
        // finding the right height
        curr = root.right;
        int rightHeight = 1;
        
        while(curr != null) {
            rightHeight++;
            curr = curr.right;
        }
        
        // if the left and right height are equal it means that both the subtrees are a full BT
        if(leftHeight == rightHeight) {
            return (int)(Math.pow(2, leftHeight) - 1);
        }
        
        // otherwisw again go to left and right to find the sub tree heights
        return 1 + countNodes(root.left) + countNodes(root.right);  
    }
} 