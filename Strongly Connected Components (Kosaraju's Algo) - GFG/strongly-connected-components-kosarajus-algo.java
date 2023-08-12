//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.io.*;
import java.lang.*;

class Gfg
{
    public static void main (String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while(t-- > 0)
        {
            // arraylist of arraylist to represent graph
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            
            int V = Integer.parseInt(sc.next());
            int E = Integer.parseInt(sc.next());
            
            for(int i =0; i < V; i++)
                adj.add(i, new ArrayList<Integer>());
                
            for(int i = 1; i <= E; i++)
            {    int u = Integer.parseInt(sc.next());
                 int v = Integer.parseInt(sc.next());
                 
                 // adding directed edgese between 
                 // vertex 'u' and 'v'
                 adj.get(u).add(v);
            }
            
            Solution ob = new Solution();
            System.out.println(ob.kosaraju(V, adj));
		}
    }
}

// } Driver Code Ends


//User function Template for Java


class Solution
{
    //Function to find number of strongly connected components in the graph.
    public int kosaraju(int v, ArrayList<ArrayList<Integer>> adj)
    {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] visit = new int[v];
        
        for(int i=0;i<v;i++){
            
            if(visit[i]==0){
                
                dfs(i,adj,visit,stack);
            }
            
        }
        
        ArrayList<ArrayList<Integer>> reverse = new ArrayList<>();
        
        for(int i=0;i<v;i++) reverse.add(new ArrayList<>());
        
        for(int i=0;i<v;i++){
            
            visit[i] =0;  // reset the visit array
            for(int next : adj.get(i)){
                // i -> next => next -> i;
                reverse.get(next).add(i);
            }
        }
            
        int scc = 0;
        
        while(!stack.isEmpty()){
            
            int node = stack.pop();
            
            if(visit[node]==0){
                
                scc++;
                dfs2(node,reverse,visit);
            }
            
        }
        
        return scc;
    }
    
    private void dfs2(int node, ArrayList<ArrayList<Integer>> adj, int[] visit){
        
        visit[node] = 1;
        
        for(int next : adj.get(node)){
            
            if(visit[next]==0){
                
                dfs2(next,adj,visit);
            }
        }
    }
    
    private void dfs(int node, ArrayList<ArrayList<Integer>> adj, int[] visit, Deque<Integer> stack){
        
        visit[node] = 1;
        
        for(int next : adj.get(node)){
            
            if(visit[next]==0){
                
                dfs(next,adj,visit,stack);
            }
        }
        
        stack.push(node);
    }
}
