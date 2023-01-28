/*
Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example 1:


Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]

Example 2:

Input: root = [1,null,3]
Output: [1,3]

Example 3:

Input: root = []
Output: []


METHOD: (REVERSE-PREORDER TRAVERSAL)

TIME: O(N).

SPACE: O(H), where H is the height of the tree.
*/


class Solution {
    public List<Integer> rightSideView(TreeNode root) {
      
        List<Integer> res = new ArrayList<>();
        
        rightView(root, res, 0);
        
        return res;
    }
    
    private void rightView(TreeNode node, List<Integer> res, int level) {
        
        if(node == null) return;
        
        // reverse pre-order (Root-R-L)
        // if the current level equal to array.size, it means that we need the ode of that level
        // array.size denoting the node of the current level
        if(level == res.size()) {
            res.add(node.val);
        }
        
        rightView(node.right, res, level + 1);
        rightView(node.left, res, level + 1);
    }
}