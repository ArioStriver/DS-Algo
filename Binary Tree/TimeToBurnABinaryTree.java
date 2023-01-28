/*
You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection starts from the node with value start.

Each minute, a node becomes infected if:

The node is currently uninfected.
The node is adjacent to an infected node.
Return the number of minutes needed for the entire tree to be infected.

Example 1:

Input: root = [1,5,3,null,4,10,6,9,2], start = 3
Output: 4

Explanation: The following nodes are infected during:
- Minute 0: Node 3
- Minute 1: Nodes 1, 10 and 6
- Minute 2: Node 5
- Minute 3: Node 4
- Minute 4: Nodes 9 and 2
It takes 4 minutes for the whole tree to be infected so we return 4.

Example 2:

Input: root = [1], start = 1
Output: 0

Explanation: At minute 0, the only node in the tree is infected so we return 0.


METHOD: (SAME AS All Nodes Distance K in Binary Tree)

TIME: O(N).

SPACE: O(N).
*/

class Solution {
    
    TreeNode infected;
    
    private void markParent(TreeNode root, Map<TreeNode, TreeNode> parent_track, int start) {
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty()) {
            TreeNode current = q.poll();
            
            if(current.left != null) {
                parent_track.put(current.left, current);
                q.offer(current.left);
            }
            
            if(current.right != null) {
                parent_track.put(current.right, current);
                q.offer(current.right);
            }
            
            // finding the address of the start node
            if(current.val == start) {
                infected = current;
            }
        }
    }
    
    public int amountOfTime(TreeNode root, int start) {
        
        // s1: mark the parents so that we can move in the upward direction
        Map<TreeNode, TreeNode> parent_track = new HashMap<>();
        
        markParent(root, parent_track, start);
        
        // s2: now just do a bfs traversal
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(infected);
        
        Set<TreeNode> visited = new HashSet<>();
        visited.add(infected);
        
        int minutes = 0;
        
        while(!q.isEmpty()) {
            
            int size = q.size();
            
            minutes++;            // denoting the distance
            
            for(int i = 0; i < size; i++) {
                
                TreeNode current = q.poll();
                
                // for left direction
                if(current.left != null && !visited.contains(current.left)) {
                    q.offer(current.left);
                    visited.add(current.left);
                }
                
                // for right direction
                if(current.right != null && !visited.contains(current.right)) {
                    q.offer(current.right);
                    visited.add(current.right);
                }
                
                // for upward direction
                TreeNode par = parent_track.get(current);
                if(par != null && !visited.contains(par)) {
                    q.offer(par);
                    visited.add(par);
                }
            }
        }
        
        return (minutes - 1);
    }
}