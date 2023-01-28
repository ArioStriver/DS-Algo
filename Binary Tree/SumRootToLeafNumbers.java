/*
You are given the root of a binary tree containing digits from 0 to 9 only.

Each root-to-leaf path in the tree represents a number.

For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.

A leaf node is a node with no children.

Example 1:

Input: root = [1,2,3]
Output: 25

Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.


Example 2:

Input: root = [4,9,0,5,1]
Output: 1026

Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.


METHOD:

TIME: O(N).

SPACE: O(N), considering the recursion stack space.
*/

class Solution {
    
    public int sumNumbers(TreeNode root) {
        
        if(root == null) return 0;
        
        int[] totalSum = new int[1];
        
        dfs(root, 0, totalSum);
        
        return totalSum[0];
    }
    
    private void dfs(TreeNode node, int curSum, int[] totalSum) {
        
        if(node == null) return;
        
        // keep track of the current sum
        curSum = (curSum * 10 + node.val);
        
        // if the leaf node
        if(node.left == null && node.right == null) {
            totalSum[0] += curSum;
        }
        
        dfs(node.left, curSum, totalSum);
        dfs(node.right, curSum, totalSum);
    }
}