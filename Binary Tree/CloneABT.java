/*
Given a special binary tree having random pointers along with the usual left and right pointers. Clone the given tree.

The query on tree are of three types
	a) a b L  (Represents that b is the left child of a)
	b) a b R (Represents that b is the right child of a)
	c) a b X (Represents a random pointer from node a to node b)  

Example 1:

Input: 6 3 L 6 8 R 3 1 L 3 5 R 1 3 X 5 6 X

Output: 1

Explanation: The tree was cloned successfully.

Your Task:
No need to read input or print anything. Complete the function cloneTree() which takes root of the given tree as input parameter and returns the root of the cloned tree. 

Note: The output is 1 if the tree is cloned successfully. Otherwise output is 0.


METHOD:

TIME: O(N).

SPACE: O(N), for storing the N nodes in the map.
*/

class Solution{
    public Tree cloneTree(Tree tree){
       
       if(tree == null) return null;
       
       // this map will store the mapping b/w the original and the duplicate node
       Map<Tree, Tree> map = new HashMap<>();
       
       // s1: first we will create the BT without random nodes
       Tree newTree = cloneLeftRightPointer(tree, map);
       
       // s2: then we will set the random pointer for the duplicate node's
       copyRandomPointer(tree, newTree, map);
       
       return newTree;
     }
     
     private Tree cloneLeftRightPointer(Tree node, Map<Tree, Tree> map) {
         
         if(node == null) return null;
         
         Tree duplicateNode = new Tree(node.data);
         map.put(node, duplicateNode);
         
         // setting the left and right pointer for the current node
         duplicateNode.left = cloneLeftRightPointer(node.left, map);
         duplicateNode.right = cloneLeftRightPointer(node.right, map);
         
         return duplicateNode;
     }
     
     private void copyRandomPointer(Tree node, Tree duplicateNode, Map<Tree, Tree> map) {
         
         if(duplicateNode == null) return;
         
         // update the random pointer of the duplicate node
         duplicateNode.random = map.get(node.random);
         
         copyRandomPointer(node.left, duplicateNode.left, map);
         copyRandomPointer(node.right, duplicateNode.right, map);
     }
}