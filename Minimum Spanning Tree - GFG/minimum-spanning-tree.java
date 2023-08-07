//{ Driver Code Starts


import java.util.*;
import java.io.*;
import java.lang.*;

public class Main{
	static BufferedReader br;
	static PrintWriter ot;
    public static void main(String args[]) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		ot = new PrintWriter(System.out);
		int t = Integer.parseInt(br.readLine().trim());
		while(t-- > 0){
			String s[] = br.readLine().trim().split(" ");
			int V = Integer.parseInt(s[0]);
			int E = Integer.parseInt(s[1]);
			int edges[][] = new int[E][3];
			for(int i = 0; i < E; i++){
				s = br.readLine().trim().split(" ");
				edges[i][0] = Integer.parseInt(s[0]);
				edges[i][1] = Integer.parseInt(s[1]);
				edges[i][2] = Integer.parseInt(s[2]);
			}
			ot.println(new Solution().spanningTree(V, E, edges));
		}
		ot.close();
	}
}
// } Driver Code Ends


class Pair{
    
    int node;
    int w;
    
    public Pair(int node, int w){
        this.node = node;
        this.w = w;
    }
}
class Solution{
	static int spanningTree(int v, int e, int edges[][]){
	     
	     List<List<Pair>> adj =new ArrayList<>();
	       
	     for(int i=0;i<v;i++) adj.add(new ArrayList<>());
	     
	     for(int[] it : edges){
	         
	         adj.get(it[0]).add(new Pair(it[1],it[2]));
	         adj.get(it[1]).add(new Pair(it[0],it[2]));
	     }
	     
	     boolean[] visit = new boolean[v];
	     
	     PriorityQueue<Pair> pq = new PriorityQueue<>((x,y)-> x.w - y.w);
	     
	     pq.offer(new Pair(0,0));
	     
	     int sum =  0;
	     
	     while(!pq.isEmpty()){
	         
	         int node = pq.peek().node;
	         int wei = pq.peek().w;
	         
	         pq.poll();
	         
	         if(visit[node]) continue;
	         
	         visit[node] = true;
	         sum += wei;
	         
	         for(Pair pair : adj.get(node)){
	             
	             if(!visit[pair.node]){
	                 
	                 pq.offer(pair);
	             }
	         }
	     }
	     
	     return sum;
	}
}