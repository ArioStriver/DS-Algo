/*
Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.

Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 109 + 7.

Note that you need to maximize the answer before taking the mod and not after taking it.

Example 1:

Input: root = [1,2,3,4,5,6]
Output: 110

Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)

Example 2:

Input: root = [1,null,2,3,4,null,null,5,6]
Output: 90

Explanation: Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)


METHOD:
	Approach:
		First pass, get the total sum.
		Now we have the right total sum of the whole tree.
		Second pass, find the biggest product.

TIME: O(N).

SPACE: O(H), where H is the height of the tree.
*/

class Solution {

    long maxProd = 0, MOD = 1000000007;

    public int maxProduct(TreeNode root) {
        
        if(root == null) return 0;

        // first calculating the total sum of the tree
        long totalSum = findTotalSum(root);
        findMaxProd(root, totalSum);        // then finding the maximum product

        return (int)(maxProd % MOD);
    }

    private long findTotalSum(TreeNode node) {
        if(node == null) return 0;

        return node.val + findTotalSum(node.left) + findTotalSum(node.right);
    }

    private long findMaxProd(TreeNode node, long totalSum) {

        if(node == null) return 0;

        // getting the left and right subtree sum for the current node
        long leftSum = findMaxProd(node.left, totalSum);
        long rightSum = findMaxProd(node.right, totalSum);

        long currSubtreeSum = (leftSum + rightSum + node.val);

        // calculating the maximum product after splliting
        maxProd = Math.max(maxProd, ((totalSum - currSubtreeSum) * currSubtreeSum));

        return currSubtreeSum;
    }
}