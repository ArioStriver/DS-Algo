/*
Given a Binary Tree of size N , where each node can have positive or negative values. Convert this to a tree where each node contains the sum of the left and right sub trees of the 
original tree. The values of leaf nodes are changed to 0.

Example 1:

Input:
             10
          /      \
        -2        6
       /   \     /  \
      8    -4   7    5

Output:
            20
          /     \
        4        12
       /  \     /  \
     0     0   0    0

Explanation:
           (4-2+12+6)
          /           \
      (8-4)          (7+5)
       /   \         /  \
      0     0       0    0

Example 2:

Input:
            10
        /        \
      -2           6
     /   \        /  \
    8    -4      7     5
    / \   / \    / \   / \
  2  -2 3  -5  9  -8 2   8

Output:
            29
        /        \
       2          23
     /  \        /  \
    0   -2      1    10
   / \  / \    / \   / \
   0  0 0   0  0   0 0   0

Explanation:
                 (2+6+8-4+7+5+2-2+3-5+9-8+2+8)
               /                                \
          (8-4+2-2+3-5)                    (7+5+9-8+2+8)
          /      \                            /      \       
       (2-2)   (3-5)                        (9-8)    (2+8)
        /     \  /    \                      /     \   /     \
       0      0 0      0                   0        0 0       0

Your Task: 
You dont need to read input or print anything. Complete the function toSumTree() which takes root node as input parameter and modifies the given tree in-place.

METHOD:

TIME: O(N).

SPACE: O(H), where H is the height of the tree.
*/

class Solution{
    public void toSumTree(Node root){
         
        if(root == null) return;
        
        sumTree(root);
    }
    
    private int sumTree(Node root) {
        if(root == null) return 0;
         
         // storing the current node's data
         int prev_val = root.data;
         
         // Recursively call for left and right subtrees and store the sum
        // as new value of this node
         root.data = sumTree(root.left) + sumTree(root.right);
         
         // Return the sum of values of nodes in left and right subtrees
        // and previous_value of this node
         return prev_val + root.data;
    }
}