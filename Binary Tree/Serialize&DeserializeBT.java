/*

Serialize and Deserialize Binary Tree

METHOD 1: ( USING PREORDER TRAVERSAL)
	APPROACH:
		We can use any traversal to encode a tree to a single string.

TIME: O(N).

SPACE: O(N).
*/

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        
        if(root == null) return "N,";
        
        String leftSerialized = serialize(root.left);
        String rightSerialized = serialize(root.right);
        
        // preorder traversal
        return root.val + "," + leftSerialized + rightSerialized;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
       
        return deserializeHelper(new LinkedList<>(Arrays.asList(data.split(","))));
    }
    
    private TreeNode deserializeHelper(Queue<String> q) {
        
        String val = q.poll();
        
        if(val.equals("N")) {
            return null;
        }
        
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = deserializeHelper(q);
        root.right = deserializeHelper(q);
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));


// METHOD 2: (USING LEVEL-ORDER TRAVERSAL)

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        
        if(root == null) return "";
        
        StringBuilder res = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        
        q.offer(root);
        
        // level-order traversal
        while(!q.isEmpty()) {
            
            TreeNode node = q.poll();
            
            if(node == null) {
                res.append("N,");
                continue;
            }
            
            // if not null append the current value and move to its left and right
            res.append(node.val+",");
            q.offer(node.left);
            q.offer(node.right);
        }
        
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        
        if(data == "") return null;
        
        String[] values = data.split(",");
        
        // creating the root node
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        for(int i = 1; i < values.length; i++) {
            
            TreeNode parent = q.poll();
            
            // creating the left child 
            if(!values[i].equals("N")) {
               TreeNode leftNode = new TreeNode(Integer.parseInt(values[i])); 
                parent.left = leftNode;
                q.offer(leftNode);
            }
            
            // creating the right child
            // why ++i, bcz a parent have two children, left and right
            if(!values[++i].equals("N")) {
               TreeNode rightNode = new TreeNode(Integer.parseInt(values[i])); 
                parent.right = rightNode;
                q.offer(rightNode);
            }
        }
        
        return root;
    }
}