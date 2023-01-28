/*
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

Example 1:

Input: root = [1,2,2,3,4,4,3]
Output: true

Example 2:

Input: root = [1,2,2,null,3,null,3]
Output: false

METHOD:

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public boolean isSymmetric(TreeNode root) {
        
        if(root == null) return true;
        
        return checkSymmetric(root.left, root.right);
    }
    
    private boolean checkSymmetric(TreeNode n1, TreeNode n2) {
        
        if(n1 == null && n2 == null) return true;
        
        // if one of them are null
        if(n1 == null || n2 == null) return false;
        
        // checking for node1.left to nod2.right && node2.left to node1.right
        return n1.val == n2.val && checkSymmetric(n1.left, n2.right) && checkSymmetric(n1.right, n2.left);
    }
}