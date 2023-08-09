/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {

        if(node == null) return null;

        Map<Integer,Node> map = new HashMap<>();

        
        return makeGraph(node, map);
    }

    private Node makeGraph(Node node, Map<Integer,Node> map){

        System.out.println(node.val);

        Node temp = new Node(node.val);
        
        map.put(temp.val, temp);

        for(Node adj : node.neighbors){

            if(map.containsKey(adj.val)){
                temp.neighbors.add(map.get(adj.val));

            }else{

                Node neighNode = makeGraph(adj,map);

                temp.neighbors.add(neighNode);

            }
        }

        return temp;
    }
}