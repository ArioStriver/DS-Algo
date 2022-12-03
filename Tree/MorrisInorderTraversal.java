/*
Given the root of a binary tree, return the inorder traversal of its nodes' values.

Example 1:

Input: root = [1,null,2,3]
Output: [1,3,2]

Example 2:

Input: root = []
Output: []

Example 3:

Input: root = [1]
Output: [1]


METHOD: (USING MORRIS INORDER TRAVERSAL)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        
        List<Integer> inorder = new ArrayList<>();
        TreeNode curr = root;
        
        while(curr != null) {
            
            // if the current node's left is null 
            // it means that that the current node itself is the root so print it
            if(curr.left == null) {
                inorder.add(curr.val);
                curr = curr.right;
            }
            // otherwise it has a left, then find the rightmost node in that subtree and 
            // create a thread to point the root 
            else {
                TreeNode temp = curr.left;
                
                // finding the righmost node of the current subtree
                while(temp.right != null && temp.right != curr) {
                    temp = temp.right;
                }
                
                // now there are two cases 
                // 1. a thread is already present 
                // 2. we have to initialize a new thread
                
                // if case 2, then after initializing a thread then we have to move left
                if(temp.right == null) {
                    temp.right = curr;
                    curr = curr.left;
                }
                // if case 1, it means we already explored the left subtree
                // then remove the thread first, store the root value and then move to right subtree
                else {
                    temp.right = null;
                    inorder.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        
        return inorder;
    }
}