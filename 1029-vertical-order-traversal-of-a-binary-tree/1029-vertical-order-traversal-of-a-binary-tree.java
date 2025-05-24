/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class SuperNode{
    TreeNode node;
    int axis;
    int level;

    public SuperNode(TreeNode node, int axis, int level){
        this.node = node;
        this.axis = axis;
        this.level = level;
    }
} 
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        
        TreeMap<Integer,TreeMap<Integer,PriorityQueue<Integer>>> map = new TreeMap<>();

        Queue<SuperNode> queue = new LinkedList<>();

        queue.offer(new SuperNode(root,0,0));

        while(!queue.isEmpty()){

            for(int i=queue.size();i>0;i--){

                SuperNode supNode = queue.poll();

                if(map.get(supNode.axis)==null) {

                    map.put(supNode.axis, new TreeMap<>());
                }

                if(map.get(supNode.axis).get(supNode.level)==null){

                     map.get(supNode.axis).put(supNode.level,new PriorityQueue<>());

                }

                map.get(supNode.axis).get(supNode.level).offer(supNode.node.val);


                if(supNode.node.left !=null) {

                    queue.offer(new SuperNode(supNode.node.left,supNode.axis-1,supNode.level+1));
                }


                if(supNode.node.right !=null) {

                    queue.offer(new SuperNode(supNode.node.right,supNode.axis+1,supNode.level+1));
                }
            }
        }

        List<List<Integer>> answer = new ArrayList<>();

        for(TreeMap<Integer,PriorityQueue<Integer>> vertical : map.values()){

            List<Integer> list = new ArrayList<>();

            for(PriorityQueue<Integer> level : vertical.values()){

                while(!level.isEmpty()) {

                    list.add(level.poll());
                }
            }

            answer.add(list);
        }

        return answer;
    }
}