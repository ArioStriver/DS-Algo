/*
Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.

Example 1:

Input: root = [1,2,3,4,5]
Output: 3

Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].

Example 2:

Input: root = [1,2]
Output: 1


METHOD 1:(BRUTE FORCE)
	APPROACH:
		Here we are finding the height of the left and right subtree for each and every node and calculating the diameter and storing the maximum deimeter.

TIME: O(N^2), where O(N) for traversal and another O(N) for find the left and right height.

SPACE: O(N).
*/

class Solution {
    
    int max = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        
        if(root == null) return 0;
        
        findMax(root);
        
        return max;
    }
    
    private void findMax(TreeNode root) {
        
        if(root == null) return;
        
        // find the left and right height of the tree
        int lh = treeHeight(root.left);
        int rh = treeHeight(root.right);
        
        // find the maximum path from each and every node
        max = Math.max(max, lh+rh);
        
        // traversing each and every node
        findMax(root.left);
        findMax(root.right);
    }
    
    private int treeHeight(TreeNode root) {
        
        if(root == null) return 0;
        
        int l = treeHeight(root.left);
        int r = treeHeight(root.right);
        
        return 1 + Math.max(l, r);
    }
}

/*
METHOD 2:(OPTOMISED)
	APPROACH:
		Instead of calculating the height repeatedly, we can do one thing while returning from a particular node we can calculate the diamater of the node by simply adding the height of 
		its left and right subtree.

TIME: O(N).

SPACEl O(N).
*/

class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        
        int[] diameter = new int[1];
        
        findHeight(root, diameter);
        
        return diameter[0];
    }
    
    private int findHeight(TreeNode root, int[] diameter) {
        
        if(root == null) return 0;
        
        // find the left and right height of a tree
        int lh = findHeight(root.left, diameter);
        int rh = findHeight(root.right, diameter);
        
        // calculating the diameter of tree
        diameter[0] = Math.max(diameter[0], lh+rh);
        
        return 1 + Math.max(lh, rh);
    }
}