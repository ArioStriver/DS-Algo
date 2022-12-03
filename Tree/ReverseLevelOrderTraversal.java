/*
Given a binary tree of size N, find its reverse level order traversal. ie- the traversal must begin from the last level.

Example 1:

Input :
        1
      /   \
     3     2

Output: 3 2 1

Explanation:
Traversing level 1 : 3 2
Traversing level 0 : 1

Example 2:

Input :
       10
      /  \
     20   30
    / \ 
   40  60

Output: 40 60 20 30 10
Explanation:
Traversing level 2 : 40 60
Traversing level 1 : 20 30
Traversing level 0 : 10

Your Task: 
You dont need to read input or print anything. Complete the function reverseLevelOrder() which takes the root of the tree as input parameter and returns a list containing the reverse level 
order traversal of the given tree.


METHOD 1:

TIME: O(N).

SPACE: O(2N).
*/

public class Solution {
	public static ArrayList<Integer> reverseLevelOrder(TreeNode root) {
		
        ArrayList<Integer> res = new ArrayList<>();
        
        if(root == null) return res;
        
        Stack<TreeNode> st = new Stack<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty()) {
            
            TreeNode cur = q.poll();

            if(cur.left != null) {
                q.offer(cur.left);
            }

            if(cur.right != null) {
                q.offer(cur.right);
            }

            st.add(cur);
        }
        
        while(!st.isEmpty()) {
            res.add(st.pop().data);
        }
        
        return res;
	}
}


/*
METHOD 2:

TIME: O(N).

SPACE: O(N).
*/

public class Solution {
	public static ArrayList<Integer> reverseLevelOrder(TreeNode root) {
		
        ArrayList<Integer> res = new ArrayList<>();
        
        if(root == null) return res;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty()) {
            
            TreeNode cur = q.poll();

            if(cur.left != null) {
                q.offer(cur.left);
            }

            if(cur.right != null) {
                q.offer(cur.right);
            }

            res.add(cur.data);
        }
        
        Collections.reverse(res);
        
        return res;
	}
}