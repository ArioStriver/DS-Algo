/*
Given a binary tree and two node values your task is to find the minimum distance between them.
The given two nodes are guaranteed to be in the binary tree and nodes are numbered from 1 to N.
Please Note that a and b are not always leaf node.

Example 1:

Input:
        1
      /  \
     2    3

a = 2, b = 3

Output: 2

Explanation: The tree formed is:
       1
     /   \ 
    2     3
We need the distance between 2 and 3.
Being at node 2, we need to take two
steps ahead in order to reach node 3.
The path followed will be: 2 -> 1 -> 3. Hence, the result is 2. 

Your Task:
You don't need to read input or print anything. Your task is to complete the function findDist() which takes the root node of the Tree and the two node values a and b as input parameters 
and returns the minimum distance between the nodes represented by the two given node values.


METHOD:

TIME: O(N) + O(N).

SPACE: O(N).
*/


class Solution {
    int findDist(Node root, int a, int b) {
        
        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();
        
	  // 1. finding the path from the given nodes to the root node
        findNodetoRootPath(root, a, path1);
        findNodetoRootPath(root, b, path2);
        
        int i = path1.size()-1, j = path2.size()-1;
        
	  // 2. Continue until they are equal
        while(i >= 0 && j >= 0 && path1.get(i).data == path2.get(j).data) {
            i--;
            j--;
        }
        
        i++; j++;
        return (i + j);
    }
    
    private boolean findNodetoRootPath(Node node, int val, ArrayList<Node> arr) {
        
        if(node == null) return false;
        
        if(node.data == val) {
            arr.add(node);
            return true;
        }
        
        if(findNodetoRootPath(node.left, val, arr) || findNodetoRootPath(node.right, val, arr)) {
            arr.add(node);
            return true;
        }
        
        // arr.remove(arr.size()-1);
        return false;
    }
}


/*
METHOD: (USING LOWEST COMMON ANCESTOR)

TIME: O(N).

SPACE: O(N), at worst case if it is a skewed BT.
*/



class Solution {
    int findDist(Node root, int a, int b) {
        
        if(root == null) return 0;
        
        // 1. first find the LCA of both the nodes
        Node lcaN = findLCA(root, a, b);
        
        // 2. Now find the distance from the ancestor to the nodes a and b
        int d1 = dist(lcaN, a, 0);
        int d2 = dist(lcaN, b, 0);
        
        return (d1 + d2);
    }
    
    private int dist(Node node, int a, int level) {
        
        if(node == null) return -1;
        
        if(node.data == a) {
            return level;
        }
        
        int leftL = dist(node.left, a, level+1);
        
        if(leftL != -1) {
            return leftL;
        }
        
        return dist(node.right, a, level+1);
    }
    
    private Node findLCA(Node node, int a, int b) {
        
        if(node == null) {
            return null;
        }
        
        if(node.data == a || node.data == b) {
            return node;
        }
        
        Node left = findLCA(node.left, a, b);
        Node right = findLCA(node.right, a, b);
        
        if(left == null) {
            return right;
        }
        else if(right == null) {
            return left;
        }
        else {
            return node;
        }
    }
}