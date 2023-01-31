/*
Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we 
allow a node to be a descendant of itself).”

Example 1:

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6

Explanation: The LCA of nodes 2 and 8 is 6.

Example 2:

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2

Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.

Example 3:

Input: root = [2,1], p = 2, q = 1
Output: 2


METHOD 1:(RECURSIVE)

TIME: O(H), where H is the height of the tree.

SPACE: O(H), (Recursive Stack Space)
*/

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode n1, TreeNode n2) {
        
       // if the current value is greater than the n1 & n2 
       //then the node n1 & n2 lies in the left subtree
        
       if(root.val > n1.val && root.val > n2.val){
           return lowestCommonAncestor(root.left, n1, n2);
        }
        // else if the current value is less than n1 & n2 
        // then the node n1 & n2 lies in the right subtree
        
        else if(root.val < n1.val && root.val < n2.val){
            return lowestCommonAncestor(root.right, n1, n2);
        }
        
        // otherWise it is the LCA
        return root;
    }
}


/*
METHOD 2:(ITERATIVE)

TIME: O(H).

SPACE: O(1).
*/

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        if(root == null) return null;

        while(root != null) {

            int curr = root.val;

            // if both of them are in the left subtree
            if(curr > p.val && curr > q.val) {
                root = root.left;
            }
            // if both of them are in the right subtree
            else if(curr < p.val && curr < q.val) {
                root = root.right;
            }
            // both of them in different subtree / one of them is equal to current node
            else {
                return root;
            }
        }

        // otherwise return null
        return null;
    }
}