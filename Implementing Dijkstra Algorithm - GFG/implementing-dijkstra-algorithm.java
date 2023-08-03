//{ Driver Code Starts
import java.util.*;
import java.io.*;
import java.lang.*;

class DriverClass
{
    public static void main(String args[]) throws IOException {

        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String str[] = read.readLine().trim().split(" ");
            int V = Integer.parseInt(str[0]);
            int E = Integer.parseInt(str[1]);
    
            ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();
            for(int i=0;i<V;i++)
            {
                adj.add(new ArrayList<ArrayList<Integer>>());
            }
            
            int i=0;
            while (i++<E) {
                String S[] = read.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                int w = Integer.parseInt(S[2]);
                ArrayList<Integer> t1 = new ArrayList<Integer>();
                ArrayList<Integer> t2 = new ArrayList<Integer>();
                t1.add(v);
                t1.add(w);
                t2.add(u);
                t2.add(w);
                adj.get(u).add(t1);
                adj.get(v).add(t2);
            }
            
            int S = Integer.parseInt(read.readLine());
            
            Solution ob = new Solution();
            
            int[] ptr = ob.dijkstra(V, adj, S);
            
            for(i=0; i<V; i++)
                System.out.print(ptr[i] + " ");
            System.out.println();
        }
    }
}
// } Driver Code Ends


class Pair{
    int dis;
    int node;
    
    public Pair(int dis,  int node){
        
        this.dis = dis;
        this.node = node;
    }
}


class Solution
{
    //Function to find the shortest distance of all the vertices
    //from the source vertex S.
    static int[] dijkstra(int v, ArrayList<ArrayList<ArrayList<Integer>>> adj, int s)
    {
        
       PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>(){
           
           @Override
           public int compare(Pair p1, Pair p2){
               
               if(p1.dis == p2.dis) return p1.node - p2.node;
               
               return p1.dis - p2.dis;
           }
       });    
       
       int[] dis = new int[v];
       
       Arrays.fill(dis,(int)1e8);       // initially mark all distance as infinity
       
       dis[s] = 0; // from src to src distance is 0
       
       pq.offer(new Pair(0,s));
       
       while(!pq.isEmpty()){
           
           int pathLen = pq.peek().dis;
           int node = pq.peek().node;
           
           pq.poll();
           
           for(ArrayList<Integer> adjPair : adj.get(node)){
               
               int adjNode = adjPair.get(0);
               int reachLen = adjPair.get(1);
               
               if( pathLen + reachLen < dis[adjNode] ){
                   
                   dis[adjNode] = pathLen + reachLen;
                   
                   pq.offer(new Pair(dis[adjNode], adjNode));
               }
           }
           
       }
       
       return dis;
    }
}

