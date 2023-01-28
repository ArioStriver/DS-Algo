/*
Given below is a binary tree. The task is to print the top view of binary tree. Top view of a binary tree is the set of nodes visible when the tree is viewed from the top. For the given 
below tree

       1
    /     \
   2       3
  /  \    /   \
4    5  6   7

Top view will be: 4 2 1 3 7
Note: Return nodes from leftmost node to rightmost node.

Example 1:

Input:
      1
   /    \
  2      3

Output: 2 1 3

Example 2:

Input:
       10
    /      \
  20        30
 /   \    /    \
40   60  90    100

Output: 40 20 10 30 100

Your Task:
Since this is a function problem. You don't have to take input. Just complete the function topView() that takes root node as parameter and returns a list of nodes visible from the top view 
from left to right.


METHOD: (SAME AS VERTICAL ORDER TRAVERSAL WITH SLIGHT CHANGE)
	APPROACH:
		We can mark vertical straight lines +ve and -ve indexes. The first node of every line will be my top view.

TIME: O(N).

SPACE: O(N).
*/

class Pair {
    Node node;
    int line;
    
    Pair(Node _node, int _line) {
        node = _node;
        line = _line;
    }
}

class Solution
{
    static ArrayList<Integer> topView(Node root)
    {
        // the queue will store the (node, vertical line no.)
        Queue<Pair> que = new LinkedList<>();
        que.offer(new Pair(root, 0));
        
        // Here we are using treemap so that we can get the values in sorted format
        // from left to right line
        Map<Integer, Integer> map = new TreeMap<>();
        
        // level-order traversal
        while(!que.isEmpty()) {
            
            Pair it = que.poll();
            Node node = it.node;
            int line = it.line;
            
            /* if the current line is not present then put it into the map
               otherwise skip it, why we are skipping it bcz we don't the internal nodes
               we only need the top nodes on that line */
            if(!map.containsKey(line)) {
                map.put(line, node.data);
            }
            
            if(node.left != null) {
                que.offer(new Pair(node.left, line - 1));
            }
            
            if(node.right != null) {
                que.offer(new Pair(node.right, line + 1));
            }
        }
        
        ArrayList<Integer> res = new ArrayList<>();
        
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res.add(entry.getValue());
        }
        
        return res;
    }
}