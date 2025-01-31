class DisjointSet {

    // path compression refer to connecting the node directly to the ultimate node, while returning from the recursion
    public int[] parent;

    public int[] size;     // using only one, (rank or size)

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

class Solution {
    public int largestIsland(int[][] grid) {

        // Using disjoint set to connect all the island, at each zero  , finding 
        // the sizes of adjacent island , if we switch this 0 to 1, then all those
        // adjacent islands would be connected,so just take the sum of their sizes
        // no need to connect them 

        // TC : O(N*N*4)
        // SC : O(N*N) (hashset will only contain at most 4 elements , so it's a constant space)
        
        int n = grid.length;
        DisjointSet ds = new DisjointSet(n*n);

        int[] ra = {-1,0,1,0};
        int[] ca = {0,1,0,-1};

        int maxi = 0;

        // connect all the islands (1's)
        for(int i=0;i<n;i++){

            for(int j=0;j<n;j++){

                if(grid[i][j]==0) continue;

                int node = i*n+j; // get node number

                // find in all four directions
                for(int k=0;k<4;k++){

                    int nr = i + ra[k];
                    int nc = j + ca[k];

                    // connect only if other cell is also 1
                    if(nr>=0 && nr<n && nc>=0 && nc<n && grid[nr][nc]==1){
                        
                        int adjNode = nr*n + nc;  // get adjacent node number
                        ds.unionBySize(node,adjNode);  // join the islands
                    }
                }   
            }
        }

        // if matrix contains all 1's, then we won't be able to switch 0 to 1,
        // hence answer won't  be calculated, so just find the size of 0's parent
        //(any node's parent would do , as all the nodes are connected)
        maxi = Math.max(maxi, ds.size[ds.findParent(0)]);
        
        for(int i=0;i<n;i++){

            for(int j=0;j<n;j++){

                if(grid[i][j]==1) continue;

                // flip the zero
                int size = 1;
                Set<Integer> set = new HashSet<>();  // as we can find two island , such that
                // both of them are connected, so using set, to make sure we only use one
                // (example 2)

                // find if we switch to 1, what is the size of islands on all 4 sides
                for(int k=0;k<4;k++){

                    int nr = i + ra[k];
                    int nc = j + ca[k];

                    if(nr>=0 && nr<n && nc>=0 && nc<n && grid[nr][nc]==1){
                        
                        int adjNode = nr*n + nc;    // get adjacent node number

                        // to get the size of the adjacent island , we get the ultimate parent
                        // node of adjacent , as it will have the size of the whole island
                        int uParent = ds.findParent(adjNode); 

                        set.add(uParent);  // add it in the set , to avoid duplicacy
                    }
                }

                // add the sizes of the adjacent islands
                for(int uparent : set){

                    size += ds.size[uparent];
                }

                maxi = Math.max(maxi, size);
            }
        }

        return maxi;
        
    }
}