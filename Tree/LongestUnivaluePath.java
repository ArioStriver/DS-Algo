/*
Given the root of a binary tree, return the length of the longest path, where each node in the path has the same value. This path may or may not pass through the root.

The length of the path between two nodes is represented by the number of edges between them.

Example 1:

Input: root = [5,4,5,1,1,null,5]
Output: 2

Explanation: The shown image shows that the longest path of the same value (i.e. 5).

Example 2:

Input: root = [1,4,5,4,4,null,5]
Output: 2

Explanation: The shown image shows that the longest path of the same value (i.e. 4).


METHOD:

TIME: O(N).

SPACE: O(N), at worst case the given tree is skewed.
*/

class Solution {
    
    int len = 0;
    
    public int longestUnivaluePath(TreeNode root) {
        
        if(root == null) return 0;
        
        longestPath(root, root.val);
        
        return len;
    }
    
    private int longestPath(TreeNode root, int prev) {
        
        if(root == null) return 0;
        
        int left = longestPath(root.left, root.val);
        int right = longestPath(root.right, root.val);
        
        len = Math.max(len, left + right);
        
        // if the current value is equal to previous value, then add one 
        if(root.val == prev) return 1 + Math.max(left, right);
        
        return 0;
    }
}