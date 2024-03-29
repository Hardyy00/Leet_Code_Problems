//{ Driver Code Starts
//Initial Template for Java


import java.util.LinkedList;
import java.util.Queue;
import java.io.*;
import java.util.*;

class Node {
	int data;
	Node left;
	Node right;

	Node(int data) {
		this.data = data;
		left = null;
		right = null;
	}
}

class GfG {

	static Node buildTree(String str) {

		if (str.length() == 0 || str.charAt(0) == 'N') {
			return null;
		}

		String ip[] = str.split(" ");
		// Create the root of the tree
		Node root = new Node(Integer.parseInt(ip[0]));
		// Push the root to the queue

		Queue<Node> queue = new LinkedList<>();

		queue.add(root);
		// Starting from the second element

		int i = 1;
		while (queue.size() > 0 && i < ip.length) {

			// Get and remove the front of the queue
			Node currNode = queue.peek();
			queue.remove();

			// Get the current node's value from the string
			String currVal = ip[i];

			// If the left child is not null
			if (!currVal.equals("N")) {

				// Create the left child for the current node
				currNode.left = new Node(Integer.parseInt(currVal));
				// Push it to the queue
				queue.add(currNode.left);
			}

			// For the right child
			i++;
			if (i >= ip.length)
				break;

			currVal = ip[i];

			// If the right child is not null
			if (!currVal.equals("N")) {

				// Create the right child for the current node
				currNode.right = new Node(Integer.parseInt(currVal));

				// Push it to the queue
				queue.add(currNode.right);
			}
			i++;
		}

		return root;
	}

	static void printInorder(Node root) {
		if (root == null)
			return;

		printInorder(root.left);
		System.out.print(root.data + " ");

		printInorder(root.right);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		while (t > 0) {
			String s = br.readLine();
			int target = Integer.parseInt(br.readLine());
			Node root = buildTree(s);

			Solution g = new Solution();
			System.out.println(g.minTime(root, target));
			t--;

		}
	}
}



// } Driver Code Ends


//User function Template for Java

class Solution
{
    public static int minTime(Node root, int target) 
    {
        // TC : O(N) && SC : O(N)
        
        if(root.left==null && root.right==null) return 0; 
        
        // DFS Approach
        
        // Map<Node, Node> map = new HashMap<>();
        
        // int[] min = new int[1];
        
        // // to traverse in upward direction mapping each node to its parent
        // collectParents(root,map);
        
        // // so that we don't go the node that we have already visited
        // Map<Node,Boolean> visited = new HashMap<>();
        
        // Node targetNode = getTargetNode(root,target);
        
        // // System.out.println(targetNode.data);
        
        // burnTheTree(targetNode,target,0,map,visited,min);
        
        // return min[0];
        
        Map<Node,Node> map = new HashMap<>();
        collectParents(root,map);
        
        Node targetNode = getTargetNode(root,target);
        
        return burnTheTreeBFS(targetNode,map);
        
    }
    
    private static int burnTheTreeBFS(Node targetNode,Map<Node,Node> map){
        
        Queue<Node> queue = new LinkedList<>();
        Map<Node,Boolean> visit = new HashMap<>();
        queue.offer(targetNode);
        visit.put(targetNode,true);
        
        int time = 0;
        
        while(!queue.isEmpty()){
            
            boolean burnedSomething = false;
            // System.out.println(queue);
            
            for(int i=queue.size();i>0;i--){
                
                Node curr = queue.poll();
                // if(curr==null) break;
                // System.out.println(curr.data);
                
                if(curr.left!=null && visit.get(curr.left)==null){
                    burnedSomething = true;
                    queue.offer(curr.left);
                    visit.put(curr.left,true);
                }
                
                if(curr.right!=null && visit.get(curr.right)==null){
                    burnedSomething = true;
                    queue.offer(curr.right);
                    visit.put(curr.right,true);
                }
                
                Node parent = map.get(curr);
                if(parent!=null && visit.get(parent)==null){
                    burnedSomething = true;
                    queue.offer(parent);
                    visit.put(parent,true);
                }
                
            }
            
            if(burnedSomething) time++;
            
        }
        
        return time;
    }
    
    // private static void burnTheTree(Node root,int target,int dis,Map<Node,Node> map,Map<Node,Boolean> visited,int[] min){
        
    //     if(root==null) return;
        
    //     // the the leaf itself isn't the target node then returning
        
    //     if(root.data !=target &&  root.left==null && root.right==null){
    //         min[0] = Math.max(min[0],dis);
    //         return;
    //     }
        
    //     visited.put(root,true);
        
    //     // if left node hasn;t been visited then go to left
    //     if(root.left!=null && visited.get(root.left)==null){
    //         burnTheTree(root.left,target,dis+1,map,visited,min);
            
    //     }
        
    //      // if right node hasn;t been visited then go to right
    //     if(root.right!=null && visited.get(root.right)==null){
    //         burnTheTree(root.right,target,dis+1,map,visited,min);
            
    //     }
        
    //     Node parent = map.get(root);
    //      // if parent node hasn;t been visited then go to parent
    //     if(visited.get(parent)==null){
    //         burnTheTree(parent,target,dis+1,map,visited,min);
    //     }
        
    // }
    
    private static Node getTargetNode(Node root,int target){
        
        if(root==null) return root;
        
        if(root.data==target) return root;
        
        Node left = getTargetNode(root.left,target);
        if(left!=null) return left;
        Node right = getTargetNode(root.right,target);
        if(right!=null) return right;
        
        return null;
        
        
    }
    
    private static void collectParents(Node root,Map<Node,Node> map){
        
        Queue<Node> queue = new LinkedList<>();
        
        queue.offer(root);
        
        while(!queue.isEmpty()){
            
            for(int i=queue.size();i>0;i--){
                
                Node curr = queue.poll();
                
                if(curr.left!=null){
                    map.put(curr.left,curr);
                    queue.offer(curr.left);
                }
                
                if(curr.right!=null){
                    map.put(curr.right,curr);
                    queue.offer(curr.right);
                }
            }
        }
    }
}