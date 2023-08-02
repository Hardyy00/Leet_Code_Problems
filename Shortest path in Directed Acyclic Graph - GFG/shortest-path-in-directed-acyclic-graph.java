//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		while (T-- > 0) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			int[][] edge = new int[m][3];
			for (int i = 0; i < m; i++) {
				edge[i][0] = sc.nextInt();
				edge[i][1] = sc.nextInt();
				edge[i][2] = sc.nextInt();
			}
			Solution obj = new Solution();
			int res[] = obj.shortestPath(n, m,edge);
			for (int i = 0; i < n; i++) {
				System.out.print(res[i] + " ");
			}
			System.out.println();
		}
	}
}
// } Driver Code Ends


class Pair{
    int node;
    int weight;
    
    public Pair(int node,  int weight){
        
        this.node = node;
        this.weight = weight;
    }
    
}

class Solution {

	public int[] shortestPath(int n,int m, int[][] edges) {
		
	    List<List<Pair>> adj = new ArrayList<>();
	    
	    for(int i=0;i<n;i++) adj.add(new ArrayList<>());
	    
	    for(int i=0;i<m;i++){
	        
	        int u = edges[i][0];
	        int v = edges[i][1];
	        int w = edges[i][2];
	        
	        adj.get(u).add(new Pair(v,w));
	    }
	    
	    Deque<Integer> stack = new ArrayDeque<>();
	    
	    boolean[] visit = new boolean[n];
	    
	    for(int i=0;i<n;i++){
	        if(!visit[i]){
	            
	            topoSort(i,visit,adj,stack);
	        }
	    }
	    
	    int[] dis = new int[n];
	    
	    Arrays.fill(dis,(int)1e8);
	    
	    dis[0] = 0;
	    
	    while(!stack.isEmpty()){
	        
	        int node = stack.pop();
	        
	        for(Pair neigh : adj.get(node)){
	            
	            int w = neigh.weight;
	            int next = neigh.node;
	            
	            if(w + dis[node] < dis[next]){
	                dis[next] = w + dis[node];
	            }
	        }
	    }
	    
	    for(int i=0;i<n;i++){
	        
	        if(dis[i]==(int)1e8) dis[i] = -1;
	    }
	    
	    return dis;
	    
	    
	    
	    
	}
	
	private void topoSort(int node, boolean[] visit, List<List<Pair>> adj, Deque<Integer> stack){
	    
	    visit[node] = true;
	    
	    for(Pair next : adj.get(node)){
	        
	        if( !visit[next.node] ){
	            
	            topoSort(next.node,visit,adj,stack);
	        }
	    }
	    
	    stack.push(node);
	}
}                                                               