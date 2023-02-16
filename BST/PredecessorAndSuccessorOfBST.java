/*
There is BST given with root node with key part as an integer only. You need to find the in-order successor and predecessor of a given key. If either predecessor or successor is not found, 
then set it to NULL.

Example:

Input:
2
6
50 30 L 30 20 L 30 40 R 50 70 R 70 60 L 70 80 R
65
6
50 30 L 30 20 L 30 40 R 50 70 R 70 60 L 70 80 R
100

Output:
60 70
80 -1

Explanation: In each test case first node is the root. Here, 50 is the root. Here, 50 30 L denotes that node having data 50 has its left child having data 30. Similarly, 30 20 L denotes that 
node having data 30 has its left child having data 20. Same goes for 30 40 R but here R means node 40 is right child of node 30. So, we can easily draw a bst and in first case, we have find 
predecessor and successor of 65. 
Now, 65 need not to be present in the tree. Here, we can see 60 is its predecessor and 70 would be its successor. Please note that even if 65 were there in the tree, its predecessor and 
successor would have been the same because we don't count node itself as predecessor or successor.

METHOD:

TIME: O(H), where H is the height of the tree.

SPACE: O(1).
*/

class Solution
{
    private static void findPredecessor(Node root, Res p, int key) {
        
        while(root != null) {
            if(root.data < key) {
                p.pre = root;
                root = root.right;
            }
            else {
                root = root.left;
            }
        }
    }
    
    private static void findSuccessor(Node root, Res s, int key) {
        
        while(root != null) {
            if(root.data <= key) {
                root = root.right;
            }
            else {
                s.succ = root;
                root = root.left;
            }
        }
    }
    
    public static void findPreSuc(Node root, Res p, Res s, int key)
    {
        findPredecessor(root, p, key);
        findSuccessor(root, s, key);
    }
}