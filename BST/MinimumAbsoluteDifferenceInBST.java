/*
Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.

Example 1:

Input: root = [4,2,6,1,3]
Output: 1

Example 2:

Input: root = [1,0,48,null,null,12,49]
Output: 1


METHOD: (USING INORDER TRAVERSAL)

TIME: O(N).

SPACE: O(1).
*/

class Solution {

    int minDiff = Integer.MAX_VALUE;
    TreeNode prev = null;
    public int getMinimumDifference(TreeNode root) {
        
        if(root == null) return minDiff;

        // doing a inorder traversal, bcz inorder traversal gives us the elements in sorted manner in a BST
        getMinimumDifference(root.left);

        if(prev != null) {
            minDiff = Math.min(minDiff, root.val - prev.val);
        }
        prev = root;  // updating the prev node

        getMinimumDifference(root.right);
        
        return minDiff;
    }
}