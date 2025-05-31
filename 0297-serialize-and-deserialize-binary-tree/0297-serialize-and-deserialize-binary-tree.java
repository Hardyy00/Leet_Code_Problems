/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        StringBuilder build = new StringBuilder();

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while(!queue.isEmpty()){

            for(int i=queue.size();i>0;i--){

                TreeNode node = queue.poll();

                if(node == null){
                    build.append("N ");
                    continue;
                }

                build.append(node.val + " ");

                queue.offer(node.left);
                
                queue.offer(node.right);

            }
        } 

        build.deleteCharAt(build.length()-1);

        return build.toString();
        
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if(data.equals("N")){
            return null;
        }
        
        String[] split = data.split(" ");


        TreeNode root = new TreeNode(Integer.parseInt(split[0]));
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        for(int i=1;i<split.length;i++){
            TreeNode parentNode = queue.poll();

            if(!split[i].equals("N")){
                
                TreeNode childNode = new TreeNode(Integer.parseInt(split[i]));
                parentNode.left = childNode;

                queue.offer(childNode);
            }

            i++;

            if(!split[i].equals("N")){

                TreeNode childNode = new TreeNode(Integer.parseInt(split[i]));
                parentNode.right = childNode;
                queue.offer(childNode);
            }

        }

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));