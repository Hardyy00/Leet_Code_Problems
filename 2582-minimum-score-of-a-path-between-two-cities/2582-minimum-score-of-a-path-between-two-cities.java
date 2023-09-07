class DisjointSet {

    // path compression refer to connecting the node directly to the ultimate node, while returning from the recursion
    private int[] parent;

    private int[] size;     // using only one, (rank or size)

    public DisjointSet(int n){

        this.parent = new int[n+1]; // n+1 because, indexing might start from 1
        this.size = new int[n+1];

        for(int i=0;i<=n;i++) parent[i] = i;

        Arrays.fill(size, 1);       // initially nodes are alone , so their size 1
    }

    public int findParent(int node){

        // TC : O(4*a) == O(constant time) (a = alpha , a is very near to 1)

        // if the parent of the node is the node itself , then he is the ultimate parent
        if(parent[node]==node) return node;

        // on returning apply path-Compression, connect the node, to the ultimate node
        return parent[node] = findParent(parent[node]);
    }

    public void unionBySize(int u, int v){

        // TC : O(4*a) == O(constant time) (a = alpha , a is very near to 1)

        int ultimateParentOfU = findParent(u);
        int ultimateParentOfV = findParent(v);

        if(ultimateParentOfU==ultimateParentOfV) return;

        if(size[ultimateParentOfU] > size[ultimateParentOfV]){

            parent[ultimateParentOfV] = ultimateParentOfU;      // connect v to u
            size[ultimateParentOfU] += size[ultimateParentOfV];     // as nodes are now connected to u, increase its size

        }else{      // if the sizes are equal , then it doesn't , which nodes, we connect ,and we have to increase the size in both the cases

            parent[ultimateParentOfU] = ultimateParentOfV;      // connect u to v
            size[ultimateParentOfV] += size[ultimateParentOfU];
        }
    }

}

class Pair{
    int node;
    int wt;

    public Pair(int node, int wt){
        this.node = node;
        this.wt = wt;
    }
}

class Solution {
    public int minScore(int n, int[][] roads) {

        // we will always have a component containing 1 and n. Since we can visit any
        // node any number of times and the path can be any long, the smallest path
        // in the component is the answer . as in that component , we can also
        // visit that path, and still reach the nth node from that path

        // return solve1(n, roads);

        return solve2(n, roads);
       
    }

     private int solve1(int n, int[][] roads){

        // USING disjoint set , connecting all the nodes, and then checking 
        // which path , occurs in the component containing 1 and n ,and then taking
        // minimum of the path

        // we can do thing by checking if the node's parent and the 1's parent are equal
        //(they belong to same component or not)

        // TC : O(E) 
        // SC : O(N)
        DisjointSet ds = new DisjointSet(n+1);

       for(int[] row : roads){

           ds.unionBySize(row[0], row[1]);  // make all connections
       }

       int minPath = Integer.MAX_VALUE;

       for(int[] row : roads){

           if(ds.findParent(row[0]) == ds.findParent(1) || ds.findParent(row[1]) == ds.findParent(1)){

               minPath = Math.min(minPath, row[2]);  // if the path occurs in the main component then cal. min
           }
       }

       return minPath;
    }

    private int solve2(int n, int[][] roads){

        List<List<Pair>> adj = new ArrayList<>();

        for(int i=0;i<=n;i++) adj.add(new ArrayList<>());

        for(int[] row : roads){

            adj.get(row[0]).add(new Pair(row[1], row[2]));
            adj.get(row[1]).add(new Pair(row[0], row[2]));
        }

        int[] min = {Integer.MAX_VALUE};

        boolean[] visit = new boolean[n+1];

        dfs(1, adj, visit,min);

        return min[0];
    }

    private void dfs(int node,List<List<Pair>> adj, boolean[] visit,int[] min ){

        visit[node] = true;

        for(Pair it : adj.get(node)){

            min[0] = Math.min(min[0], it.wt);
            if( !visit[it.node]){
                dfs(it.node, adj, visit, min);
            }
        }
    }

   

}