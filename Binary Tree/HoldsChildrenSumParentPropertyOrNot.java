/*
Given a Binary Tree. Check whether all of its nodes have the value equal to the sum of their child nodes.


Example 1:

Input:
     10
    /
  10 

Output: 1

Explanation: Here, every node is sum of its left and right child.

Example 2:

Input:
       1
     /   \
    4     3
   /  \
  5    N

Output: 0

Explanation: Here, 1 is the root node and 4, 3 are its child nodes. 4 + 3 = 7 which is not equal to the value of root node. Hence, this tree does not satisfy the given conditions.

Your Task:
You don't need to read input or print anything. Your task is to complete the function isSumProperty() that takes the root Node of the Binary Tree as input and returns 1 if all the nodes in 
the tree satisfy the following properties. Else, it returns 0. For every node, data value must be equal to the sum of data values in left and right children. Consider data value as 0 for 
NULL child.  Also, leaves are considered to follow the property.


METHOD:

TIME: O(N).

SPACE: O(H).
*/

class Tree
{
    //Function to check whether all nodes of a tree have the value 
    //equal to the sum of their child nodes.
    public static int isSumProperty(Node root)
    {
        int left_data = 0, right_data = 0;
        
        // base case
        // if the current node is null or it is  leaf node then no need to check further just return true(1)
        if(root == null || root.left == null && root.right == null) {
            return 1;
        }
        // it is not a leaf node
        else {
            
            if(root.left != null) {
                left_data = root.left.data;
            }
            
            if(root.right != null) {
                right_data = root.right.data;
            }
            
            // if the summatio of left amd right is equal to root value and again check for the 
            // left and right subtree for that root
            if((root.data == left_data + right_data) && (isSumProperty(root.left) == 1) && (isSumProperty(root.right) == 1)) {
                return 1;
            }
            else {
                return 0;
            }
        }
        
    }
}