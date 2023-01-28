/*
Given the root of a binary tree, return the maximum width of the given tree.

The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a 
complete binary tree extending down to that level are also counted into the length calculation.

It is guaranteed that the answer will in the range of a 32-bit signed integer.

Example 1:

Input: root = [1,3,2,5,3,null,9]
Output: 4

Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).

Example 2:

Input: root = [1,3,2,5,null,null,9,6,null,7]
Output: 7

Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).

Example 3:

Input: root = [1,3,2,5]
Output: 2

Explanation: The maximum width exists in the second level with length 2 (3,2).


METHOD:

TIME: O(N).

SPACE: O(N).
*/

class Pair {
    TreeNode node;
    int idx;
    
    Pair(TreeNode _node, int _idx) {
        node = _node;
        idx = _idx;
    }
}

class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        
        if(root == null) return 0;
        
        // here we are using level-order traversal
        // this pair class will store the (node's value, it's index)
        Queue<Pair> que = new LinkedList<>();
        que.offer(new Pair(root, 0));
        
        int maxWidth = 0;
        
        while(!que.isEmpty()) {
            
            int size = que.size();
            int min = que.peek().idx;       // to make the index statring from 0
            int firstIdx = 0, lastIdx = 0;
            
            for(int i = 0; i < size; i++) {
                
                Pair it = que.poll();
                
                // in order to avoid the overflow always 
                // subtract the current index from the smallest index in the current level
                int cur_id = it.idx - min;  
                TreeNode node = it.node;
                
                // getting the first index
                if(i == 0) {
                    firstIdx = cur_id;
                }
                
                // getting the last index
                if(i == size-1) {
                    lastIdx = cur_id;
                }
                
                if(node.left != null) {
                    que.offer(new Pair(node.left, 2*cur_id + 1));
                }
                
                if(node.right != null) {
                    que.offer(new Pair(node.right, 2*cur_id + 2));
                }
            }
            
            // calculating the maximum width
            maxWidth = Math.max(maxWidth, (lastIdx - firstIdx + 1));
        }
        
        return maxWidth;
    }
}