/*
Given a Binary Tree, print the diagonal traversal of the binary tree.

Consider lines of slope -1 passing between nodes. Given a Binary Tree, print all diagonal elements in a binary tree belonging to same line.
If the diagonal element are present in two different subtress then left subtree diagonal element should be taken first and then right subtree. 

Example 1:

Input :
            8
         /     \
        3      10
      /   \      \
     1     6     14
         /   \   /
        4     7 13

Output : 8 10 14 3 6 7 13 1 4

Explanation: Diagonal Traversal of binary tree : 8 10 14 3 6 7 13 1 4

Your Task:
You don't need to read input or print anything. The task is to complete the function diagonal() that takes the root node as input argumets and returns the diagonal traversal of the given 
tree.

METHOD:

TIME: O(N).

SPACE: O(N).
*/

class Tree
{
     public ArrayList<Integer> diagonal(Node root)
      {
           ArrayList<Integer> diagonalEles = new ArrayList<>();
           
           Queue<Node> q = new LinkedList<>();
           q.offer(root);
           
           while(!q.isEmpty()) {
               
               int size = q.size();
               
               for(int i = 0; i < size; i++) {
                   
                   Node node = q.poll();
                   
                   // move towards right untill they are null
                   while(node != null) {
                       
                       diagonalEles.add(node.data);

			     // adding the elements in the left
                       if(node.left != null) {
                           q.offer(node.left);
                       }
                       node = node.right;
                   }
               }
           }
           
           return diagonalEles;
      }
}