/*
You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.

Example 1:

Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]
Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.

Example 2:

Input: root = [3,1,4,null,null,2]
Output: [2,1,4,null,null,3]
Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.

METHOD 1:(SORTING + INORDER)

TIME: O(N) + O(NlogN).

SPACE: O(N).
*/

class Solution {

    private void inorder(TreeNode root, List<Integer> arr) {
        if(root == null) return;
        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
    }

    private void updateTreeValues(TreeNode root, int v1, int v2) {
        if(root == null) return;
        updateTreeValues(root.left, v1, v2);
        
        if(root.val == v1) {
            root.val = v2;
        }
        else if(root.val == v2){
            root.val = v1;
        }
        updateTreeValues(root.right, v1, v2);
    }

    public void recoverTree(TreeNode root) {
        
        if(root == null) return;
        List<Integer> arr = new ArrayList<>();

        inorder(root, arr);
        List<Integer> temp = new ArrayList<>(arr);
        Collections.sort(arr);

        for(int i = 0; i < arr.size(); i++) {
            if(arr.get(i) != temp.get(i)) {
                updateTreeValues(root, arr.get(i), temp.get(i));
                break;
            }
        }
    }
}

/*
METHOD 2:(SPACE OPTIMIZED)

TIME: O(N).

SPACE: O(1), excluding the recursive stack space.
*/

class Solution {
    TreeNode prev;
    TreeNode first;
    TreeNode middle;
    TreeNode last;

    private void inorder(TreeNode root) {

        if(root == null) return;
        inorder(root.left);

        // bcz inorder the cuurent elemnt should be > the previous element
        // if not then it violates the rule
        if(prev != null && (root.val < prev.val)) {

            // check if this is the first violation, then mark this two nodes 
            // as first and middle
            if(first == null) {
                first = prev;
                middle = root;
            }
            // else it is the second violation, mark this node as last
            else {
                last = root;
            }
        }
        // mark the current node as previous
        prev = root; 
        inorder(root.right);
    }

    public void recoverTree(TreeNode root) {
        
        if(root == null) return;

        first = middle = last = null;
        prev = new TreeNode(Integer.MIN_VALUE);
        inorder(root);

        // here we need to check two cases
        // case 1: swapped nodes are not adjacent
        if(first != null && last != null) {
            int temp = first.val;
            first.val = last.val;
            last.val = temp;
        }
        // case 2: swapped nodes are adjacent
        else if(first != null && middle != null) {
            int temp = first.val;
            first.val = middle.val;
            middle.val = temp;
        }   
    }
}

/*
METHOD 3:(USING MORRIS TRAVERSAL)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    TreeNode prev;
    TreeNode first;
    TreeNode middle;
    TreeNode last;

    public void recoverTree(TreeNode root) {
        
        if(root == null) return;

        first = middle = last = null;
        prev = new TreeNode(Integer.MIN_VALUE);

        // Morris Traversal
        while(root != null) {
            if(root.left == null) {
                if(prev != null && (root.val < prev.val)) {

                    // check if this is the first violation, then mark this two nodes 
                    // as first and middle
                    if(first == null) {
                        first = prev;
                        middle = root;
                    }
                    // else it is the second violation, mark this node as last
                    else {
                        last = root;
                    }
                }
                prev = root; 
                root = root.right;
            }
            else {
                TreeNode temp = root.left;
                while(temp.right != null && temp.right != root) {
                    temp = temp.right;
                }

                if(temp.right == null) {
                    temp.right = root;
                    root = root.left;
                }
                else {
                    temp.right = null;
                    if(prev != null && (root.val < prev.val)) {
                        // check if this is the first violation, then mark this two nodes 
                        // as first and middle
                        if(first == null) {
                            first = prev;
                            middle = root;
                        }
                        // else it is the second violation, mark this node as last
                        else {
                            last = root;
                        }
                    }
                    prev = root; 
                    root = root.right;
                }
            }
        }

        // here we need to check two cases
        // case 1: swapped nodes are not adjacent
        if(first != null && last != null) {
            int temp = first.val;
            first.val = last.val;
            last.val = temp;
        }
        // case 2: swapped nodes are adjacent
        else if(first != null && middle != null) {
            int temp = first.val;
            first.val = middle.val;
            middle.val = temp;
        }   
    }
}