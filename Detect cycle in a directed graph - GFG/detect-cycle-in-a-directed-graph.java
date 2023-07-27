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
        
        
        boolean[] visit = new boolean[v];
        int[] pathVisit = new int[v];
        
        for(int i=0;i<v;i++){
            
            if(!visit[i]){
                if( dfs(i,visit,pathVisit,adj) ) return true;
            }
        }
        
        return false;
        
    }
    
    private boolean dfs(int node, boolean[] visit, int[] pathVisit,ArrayList<ArrayList<Integer>> adj){
        
        visit[node] = true;
        pathVisit[node] = 1;
        
        for(int adjacent : adj.get(node)){
            
            if(!visit[adjacent]){
                
                if( dfs(adjacent,visit,pathVisit,adj) ) return true;
                
            }else if(visit[adjacent] && pathVisit[adjacent]==1){
                
                return true;
            }
        }
        
        pathVisit[node] = 0;
        
        return false;
    }
    
}