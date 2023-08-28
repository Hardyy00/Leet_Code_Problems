class DisjointSet {

    private int[] parent;

    private int[] size;     
    public DisjointSet(int n){

        this.parent = new int[n+1]; 
        this.size = new int[n+1];

        for(int i=0;i<=n;i++) parent[i] = i;

        Arrays.fill(size, 1);      
    }

    public int findParent(int node){

        if(parent[node]==node) return node;

        return parent[node] = findParent(parent[node]);
    }

    public void unionBySize(int u, int v){

        int ultimateParentOfU = findParent(u);
        int ultimateParentOfV = findParent(v);

        if(ultimateParentOfU==ultimateParentOfV) return;

        if(size[ultimateParentOfU] > size[ultimateParentOfV]){

            parent[ultimateParentOfV] = ultimateParentOfU;      
            size[ultimateParentOfU] += size[ultimateParentOfV];     

        }else{      
            parent[ultimateParentOfU] = ultimateParentOfV;      
            size[ultimateParentOfV] += size[ultimateParentOfU];
        }
    }

}

class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        
        // return solve(n, edges, source, destination);

        return solve2(n, edges, source, destination);
    }

    private boolean solve2(int n, int[][] edges, int source, int destination){

        DisjointSet ds = new DisjointSet(n);

        for(int[] row : edges){

            ds.unionBySize(row[0], row[1]);
        }

        return ds.findParent(source) == ds.findParent(destination);
    }

    private boolean solve(int n, int[][] edges, int source, int destination){

        // Using DFS
        // TC : O(E) + O(V+2E)   
        // SC : O(V+2E) + O(V) 

        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] row : edges){

            adj.get(row[0]).add(row[1]);
            adj.get(row[1]).add(row[0]);
        }

        boolean[] visit = new boolean[n];

        return dfs(source, destination, visit , adj);
    }

    private boolean dfs(int node, int dst, boolean[] visit, List<List<Integer>> adj){

        if(node == dst) return true;

        visit[node] = true;

        for(int next : adj.get(node)){


            if(!visit[next]) {

                if( dfs(next, dst, visit, adj) ) return true;
            }
        }

        return false;
    }
}