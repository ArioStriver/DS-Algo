/*
Given a Binary Tree. Find the difference between the sum of node values at even levels and the sum of node values at the odd levels.

Example 1:

Input:
            1
          /   \
         2     3

Output: -4

Explanation: sum at odd levels - sum at even levels = (1)-(2+3) = 1-5 = -4

Example 2:

Input:
            10
          /    \
        20      30
       /  \         
     40    60      

Output: 60

Explanation: sum at odd levels - sum at even levels
		= (10+40+60) - (20+30)
		= 110 - 50
		= 60

Your Task:  
You dont need to read input or print anything. Complete the function getLevelDiff() which takes root node as input parameter and returns an integer.


METHOD:

TIME: O(N).

SPACE: O(N).
*/

class Solution
{
	int getLevelDiff(Node root)
	{
	    
        if(root == null) {
            return 0;
        }
        
        int d = 1;
        long[] evenSum = new long[1], oddSum = new long[1];
        
        helper(root, d, evenSum, oddSum);
       
        return (int)(oddSum[0] - evenSum[0]);
    }
    
    private void helper(Node node, int d, long[] evenSum, long[] oddSum) {
        
        if(node == null) return;
        
        if(d % 2 == 1) {
            oddSum[0] += (node.data);
        }
        else {
            evenSum[0] += (node.data);
        }
        
        helper(node.left, d+1, evenSum, oddSum);
        helper(node.right, d+1, evenSum, oddSum);
    }
}