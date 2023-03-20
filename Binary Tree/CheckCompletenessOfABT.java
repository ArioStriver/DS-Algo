/*
Given the root of a binary tree, determine if it is a complete binary tree.

In a complete binary tree, every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example 1:

Input: root = [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.

Example 2:

Input: root = [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.


METHOD 1:(USING BFS)

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    public boolean isCompleteTree(TreeNode root) {
        
        if(root == null) return true;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean seenNullNodeBefore = false;  // to keep track of whether we have seen any null node so far or not

        while(!q.isEmpty()) {
            TreeNode curr = q.poll();
            if(curr == null) {
                seenNullNodeBefore = true;
            }
            else {
                q.offer(curr.left);
                q.offer(curr.right);

                // checking whether we have seen any null node or not so far, if so then it is not a complete BT
                if(seenNullNodeBefore) {
                    return false;
                }
            }
        }
        return true;
    }
}


/*METHOD 2:(USING DFS)

TIME: O(N).

SPACE: O(H), at worst case it is O(N).
*/

class Solution {

    private int countTotalNodes(TreeNode root) {
        if(root == null) return 0;
        return 1 + countTotalNodes(root.left) + countTotalNodes(root.right);
    }

    private boolean dfs(TreeNode root, int index, int N) {
        if(root == null) return true;

        // if the current index is greater than or equal to N
        // then it means a node is missing from the first n indices
        if(index >= N) return false;

        // if index < n, we proceed to its children. 
        // We use index as (2 * index + 1) for the left child and (2 * index + 2) for the right child
        return dfs(root.left, 2*index+1, N) && dfs(root.right, 2*index+2, N);
    }

    public boolean isCompleteTree(TreeNode root) {
        if(root == null) return true;
        int totalNodes = countTotalNodes(root);
        return dfs(root, 0, totalNodes);
    }
}
