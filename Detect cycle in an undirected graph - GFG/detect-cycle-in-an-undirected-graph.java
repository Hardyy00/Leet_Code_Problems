//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;
class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            String[] s = br.readLine().trim().split(" ");
            int V = Integer.parseInt(s[0]);
            int E = Integer.parseInt(s[1]);
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) adj.add(i, new ArrayList<Integer>());
            for (int i = 0; i < E; i++) {
                String[] S = br.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            Solution obj = new Solution();
            boolean ans = obj.isCycle(V, adj);
            if (ans)
                System.out.println("1");
            else
                System.out.println("0");
        }
    }
}
// } Driver Code Ends

class Pair{
    
    int node;
    int parent;
    
    public Pair(int node, int parent){
        
        this.node = node;
        this.parent = parent;
    }
}

class Solution {
    
    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int v, ArrayList<ArrayList<Integer>> adj) {
        
        // TC : O(V) + O(V + 2E)     (LOOP + BFS)
        // SC : O(V) + O(V)  (visit + queue)
       
       boolean visit[] = new boolean[v];
       
       // 
       for(int i=0;i<v;i++){
        
            // visit all the components
           if(!visit[i]){
            
                // if a cycle got found in a component then return true
            //   if(bfs(i,adj,visit)) return true;
            
            if(dfs(i,-1,adj,visit)) return true;
           }
       }
       
       return false;
       
    }
    
    private boolean dfs(int node, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] visit){
        
        visit[node] = true;
        
        for(int neighbor : adj.get(node)){
            
            if(!visit[neighbor]){
                
                if(dfs(neighbor,node,adj,visit)) return true;
                
            }else if(neighbor!=parent){
                
                return true;
            }
        }
        
        return false;
    }
    
    private boolean bfs(int start, ArrayList<ArrayList<Integer>> adj, boolean[] visit){
        
        Queue<Pair> queue = new LinkedList<>();
        
        // always make a pair of node and its parent, starting node's parent is no one hence -1
        queue.offer(new Pair(start,-1));
        visit[start] = true;    // mark as visited
        
        while(!queue.isEmpty()){
            
            int node = queue.peek().node;
            int parent = queue.peek().parent;
            
            queue.poll();
            
            // go through neighbors of the node
            for(int neighbor : adj.get(node)){
                
                /// is not visited then add in the queue
                if(!visit[neighbor]){
                    
                    queue.offer(new Pair(neighbor,node));
                    visit[neighbor] = true;
                
                // if it is already visited, then that means we are visiting it again, and if this is not the parent itself
                // then cycle does exist hence return true
                }else if(neighbor!=parent){
                    return true;
                }
            }
        }
        
        return false;
    }
}