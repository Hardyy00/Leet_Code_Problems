//{ Driver Code Starts
// Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;
@SuppressWarnings("unchecked") class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int edges[][] = new int[m][3];
            for (int i = 0; i < m; i++) {
                edges[i][0] = sc.nextInt();
                edges[i][1] = sc.nextInt();
                edges[i][2] = sc.nextInt();
            }
            Solution obj = new Solution();
            List<Integer> ans = obj.shortestPath(n, m, edges);
            for (int e : ans) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }
}
// } Driver Code Ends





class Solution {
    
    static class Pair{
    
    int node;
    int dist;
    
        public Pair(int node, int dist){
        
            this.dist = dist;
            this.node = node;
        }
        
    }
    public static List<Integer> shortestPath(int n, int m, int edges[][]) {
        
        List<List<Pair>> adj = new ArrayList<>();
        
        for(int i=0;i<n+1;i++) adj.add(new ArrayList<>());
        
        for(int i=0;i<m;i++){
            
            adj.get(edges[i][0]).add(new Pair(edges[i][1] , edges[i][2]));
            adj.get(edges[i][1]).add(new Pair(edges[i][0] , edges[i][2]));
            
            
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>( new Comparator<Pair>(){
            
            @Override
            public int compare(Pair p1, Pair p2){
                
                if(p1.dist== p2.dist) return p1.node - p2.node;
                
                return p1.dist - p2.dist;
            }
        });
        
        int[] dis = new int[n+1];
        int[] path = new int[n+1];
        
        
        Arrays.fill(dis, (int)1e8);
        
        for(int i=0;i<n+1;i++){
            path[i] = i;
        }
        
        dis[1] = 0;
        
        pq.offer(new Pair(1,0));
        
        while(!pq.isEmpty()){
            
            int node = pq.peek().node;
            int len = pq.peek().dist;
            
            pq.poll();
            
            for(Pair adjPair : adj.get(node)){
                
                int adjNode = adjPair.node;
                int weight = adjPair.dist;
                
                if( len + weight < dis[adjNode]){
                    
                    dis[adjNode] = len + weight;
                    
                    pq.offer(new Pair(adjNode, dis[adjNode]));
                    
                    path[adjNode] = node;
                }
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        
        int nodeJump = n;
        
        while(true){
            
            ans.add(nodeJump);
            
            if(path[nodeJump]==nodeJump) break;
            
            nodeJump = path[nodeJump];
        }
        
        Collections.reverse(ans);
        
        if(ans.get(0)!=1 || ans.get(ans.size()-1)!=n){
            List<Integer> temp = new ArrayList<>();
            
            temp.add(-1);
            return temp;
        }
        
        return ans;
        
    }
}