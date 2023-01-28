/*
Consider Red lines of slope -1 passing between nodes (in following diagram). The diagonal sum in a binary tree is the sum of all node’s data lying between these lines. Given a Binary Tree 
of size N, print all diagonal sums.

For the following input tree, output should be 9, 19, 42.
9 is sum of 1, 3 and 5.
19 is sum of 2, 6, 4 and 7.
42 is sum of 9, 10, 11 and 12.

DiagonalSum

Example 1:

Input:
         4
       /   \
      1     3
           /
          3

Output: 7 4 

Example 2:

Input:
           10
         /    \
        8      2
       / \    /
      3   5  2

Output: 12 15 3 

Your Task:
You don't need to take input. Just complete the function diagonalSum() that takes root node of the tree as parameter and returns an array containing the diagonal sums for every diagonal 
present in the tree with slope -1.


METHOD:(USING LEVEL-ORDER TREVERSAL)

TIME: O(N).

SPACE: O(N), at worst case.
*/

class Tree {
    public static ArrayList <Integer> diagonalSum(Node root) 
    {
        ArrayList<Integer> diagonalSums = new ArrayList<>();
        
        // here we gonna do the level order traversal
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty()) {
            
            int size = q.size();
            int sum = 0;
            
            for(int i = 0; i < size; i++) {
                
                Node node = q.poll();
                
                // keep on adding the right child values untill null and if the current node has left child then
                // push them into the stack for next diagonal sum calculation
                while(node != null) {
                    sum += node.data;
                    
                    if(node.left != null) {
                        q.offer(node.left);
                    }
                    
                    node = node.right;
                }
            }
            
            diagonalSums.add(sum);
        }
        
        return diagonalSums;
    }
}