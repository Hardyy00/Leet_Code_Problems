//{ Driver Code Starts
import java.util.*;
import java.io.*;
import java.lang.*;

class DriverClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            int V = sc.nextInt();
            int E = sc.nextInt();
            for (int i = 0; i < V; i++)
                list.add(i, new ArrayList<Integer>());
            for (int i = 0; i < E; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                list.get(u).add(v);
            }
            if (new Solution().isCyclic(V, list) == true)
                System.out.println("1");
            else
                System.out.println("0");
        }
    }
}
// } Driver Code Ends


/*Complete the function below*/

class Solution {
    // Function to detect cycle in a directed graph.
    public boolean isCyclic(int v, ArrayList<ArrayList<Integer>> adj) {
        
        // instead of carrying tow separate arrays, use only one array such that
        // 0 - not visited
        // 1 - VISITED and not included in the current path
        // 2 - visited and included in the current path
        
        int[] visit = new int[v];
        
        for(int i=0;i<v;i++){
            
           if(visit[i]==0){
                if( dfs(i, visit,adj) ) return true;
           }
            
        }
        
        return false;
    }
    
    private boolean dfs(int node, int[] visit, ArrayList<ArrayList<Integer>> adj){
        
        visit[node] = 2;    // visited and included in our current path
        
        for(int adjacent : adj.get(node)){
            
            if(visit[adjacent]==0){
                
                if( dfs(adjacent,visit,adj) ) return true;
                
                // if node is visited and node is in out current path, then yes sir cycle exist
            }else if(visit[adjacent]==2){       
                
                return true;
            }
        }
        
        visit[node] = 1;    // node is no more in our current path, but is visited already
        
        return false;
    }
}