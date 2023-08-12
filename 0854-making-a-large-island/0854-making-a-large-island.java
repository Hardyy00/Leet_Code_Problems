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
        
        int n = grid.length;
        DisjointSet ds = new DisjointSet(n*n);

        int[] ra = {-1,0,1,0};
        int[] ca = {0,1,0,-1};

        int maxi = 0;

        for(int i=0;i<n;i++){

            for(int j=0;j<n;j++){

                if(grid[i][j]==0) continue;

                int node = i*n+j;

                for(int k=0;k<4;k++){

                    int nr = i + ra[k];
                    int nc = j + ca[k];

                    if(nr>=0 && nr<n && nc>=0 && nc<n && grid[nr][nc]==1){
                        
                        int adjNode = nr*n + nc;
                        ds.unionBySize(node,adjNode);
                    }
                }

                int parent = ds.findParent(node);
                maxi = Math.max(maxi,ds.size[parent]);

            }
        }

        System.out.println(Arrays.toString(ds.size));
        System.out.println(Arrays.toString(ds.parent));

        
        for(int i=0;i<n;i++){

            for(int j=0;j<n;j++){

                if(grid[i][j]==1) continue;

                int size = 1;
                Set<Integer> set = new HashSet<>();

                for(int k=0;k<4;k++){

                    int nr = i + ra[k];
                    int nc = j + ca[k];

                    if(nr>=0 && nr<n && nc>=0 && nc<n && grid[nr][nc]==1){
                        
                        int adjNode = nr*n + nc;
                        int uParent = ds.findParent(adjNode);

                        set.add(uParent);
                    }
                }

                for(int uparent : set){

                    size += ds.size[uparent];
                }

                maxi = Math.max(maxi, size);
            }
        }

        return maxi;
        
    }
}