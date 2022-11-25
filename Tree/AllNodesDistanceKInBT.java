/*
Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]

Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.

Example 2:

Input: root = [1], target = 1, k = 3
Output: []


METHOD:

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    
    private void markParent(TreeNode root, Map<TreeNode, TreeNode> parent_track) {
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty()) {
            TreeNode current = q.poll();
            
            if(current.left != null) {
                parent_track.put(current.left, current);   // adding the parent of the current node
                q.offer(current.left);
            }
            
            if(current.right != null) {
                parent_track.put(current.right, current);
                q.offer(current.right);
            }
        }
    }
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        
        // in an binary tree we can move downward and can't move upward
        // so to solve this problem we are using map to keep track of the parent of the current nodes
        // s1: Mark each node to its parent to traverse upwards
        Map<TreeNode, TreeNode> parent_track = new HashMap<>();
        
        markParent(root, parent_track);
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(target);
        
        Set<TreeNode> visited = new HashSet<>();   // to keep track of the visited nodes
        visited.add(target);
        
        int current_level = 0;   
        
        // s2. Doing a BFS traversal
        while(!q.isEmpty()) {
            
            int size = q.size();
            
            // check whether we reached the kth level or not
            if(current_level == k) break;
            
            current_level++;         // other wise increase the current level
            
            for(int i = 0; i < size; i++) {
                
                TreeNode current = q.poll();
                
                // for left 
                if(current.left != null && !visited.contains(current.left)) {
                    q.offer(current.left);
                    visited.add(current.left);
                }
                
                // for right
                if(current.right != null && !visited.contains(current.right)) {
                    q.offer(current.right);
                    visited.add(current.right);
                }
                
                // for upward 
                TreeNode par = parent_track.get(current);
                
                if(par != null && !visited.contains(par)) {
                    q.offer(par);
                    visited.add(par);
                }
            } 
        }
        
        List<Integer> ans = new ArrayList<>();
        
        while(!q.isEmpty()) {
            ans.add(q.poll().val);
        }
        
        return ans;
    }
}