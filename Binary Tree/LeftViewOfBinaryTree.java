/*
Given a Binary Tree, print Left view of it. Left view of a Binary Tree is set of nodes visible when tree is visited from Left side. The task is to complete the function leftView(), which 
accepts root of the tree as argument.

Left view of following tree is 1 2 4 8.

          1
       /     \
     2        3
   /     \    /    \
  4     5   6    7
   \
     8   

Example 1:

Input:
   1
 /  \
3    2
Output: 1 3

Example 2:

Input:

Output: 10 20 40

Your Task:
You just have to complete the function leftView() that returns an array containing the nodes that are in the left view. The newline is automatically appended by the driver code.


METHOD: (PREORDER TRAVERSAL)

TIME: O(N).

SPACE: O(H), at worst O(N) if the given tree is skewed.
*/

class Tree
{
    //Function to return list containing elements of left view of binary tree.
    ArrayList<Integer> leftView(Node root)
    {
        ArrayList<Integer> res = new ArrayList<>();
        
        leftView(root, res, 0);
        
        return res;
    }
    
    private void leftView(Node node, ArrayList<Integer> res, int level) {
        
        if(node == null) return;
        
        if(level == res.size()) {
            res.add(node.data);
        }
        
        leftView(node.left, res, level + 1);
        leftView(node.right, res, level + 1);
    }
}