/*
Problem Description

Given a Binary Tree A containing N nodes.

You need to find the path from Root to a given node B.

NOTE:

No two nodes in the tree have same data values.
You can assume that B is present in the tree A and a path always exists.


Example Input
Input 1:

 A =

           1
         /   \
        2     3
       / \   / \
      4   5 6   7 


B = 5

Input 2:

 A = 
            1
          /   \
         2     3
        / \ .   \
       4   5 .   6


B = 1

Example Output

Output 1: [1, 2, 5]

Output 2: [1]


Example Explanation

Explanation 1: We need to find the path from root node to node with data value 5. So the path is 1 -> 2 -> 5 so we will return [1, 2, 5]

Explanation 2: We need to find the path from root node to node with data value 1. As node with data value 1 is the root so there is only one node in the path. So we will return [1].


METHOD:

TIME: O(N).

SPACE: O(H), where H is the height of the tree.
*/


public class Solution {
    public ArrayList<Integer> solve(TreeNode A, int B) {
        
        ArrayList<Integer> res = new ArrayList<>();
        
        if(A == null) {
            return res;
        }
        
        findPath(A, B, res);
        
        return res;
    }
    
    private boolean findPath(TreeNode node, int target, ArrayList<Integer> res) {
        
        if(node == null) return false;
        
        // first add the current value to the array
        res.add(node.val);
        
        // check whether the currebt value is same as target ot not
        if(node.val == target) {
            return true;
        }
        
        // find the target node either in left or right subtree
        // if one of them return true, it means it is present 
        if(findPath(node.left, target, res) ||  findPath(node.right, target, res)) {
            return true;
        }
        
        // if both of them return false, it means it is not present either in left/right subtree
        // then check for other ones and remove the value we added at last to ecplore other paths
        res.remove(res.size()-1);
        
        return false;
    }
}