/*
Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.

Example 1:

Input: root = [3,1,4,null,2], k = 1
Output: 1

Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3

METHOD 1:(RECURSIVE APPROACH)

TIME: O(min(K, N)).

SPACE: O(min(K, N)).
*/

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        
        if(root == null) return 0;
        TreeNode node = kthSmallestElement(root, new int[]{k});
        return node.val;
    } 

    private TreeNode kthSmallestElement(TreeNode node, int[] K) {
        if(node == null) return null;

        // in most cases the the smallets element will be in the left side unless the left child != null
        TreeNode left = kthSmallestElement(node.left, K);
        if(left != null) {
            return left;
        }
        K[0]--;   // update the K position

        // if K is 0, it means we got our anwer
        if(K[0] == 0) {
            return node;
        }

        // otherwisr find in the right subtree if left is null
        return kthSmallestElement(node.right, K);
    }
}


/*
METHOD 2: (USING MORRISH TRAVERSAL)

TIME: O(min(K, N)).

SPACE: O(1).
*/

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        
        if(root == null) return 0;
        
        TreeNode curr = root;
        int count = 0;

        while(curr != null) {
            if(curr.left == null) {
                count++;
                if(count == k) break;
                curr = curr.right;
            }
            else {
                TreeNode temp = curr.left;

                while(temp.right != null && temp.right != curr) {
                    temp = temp.right;
                }

                // no thread, then create a thread
                if(temp.right == null) {
                    temp.right = curr;
                    curr = curr.left;
                }
                // if had a thread, then break it so that we don't have to reiterate
                else {
                    temp.right = null;
                    count++;
                    if(count == k) break;
                    curr = curr.right;
                }
            }
        }

        return curr.val;
    } 
}