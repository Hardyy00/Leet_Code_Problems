class DisjointSet{

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
    public int makeConnected(int n, int[][] connections) {
        
        DisjointSet ds = new DisjointSet(n);

        int usableCables = 0;

        for(int[] row : connections){

            if(ds.findParent(row[0]) != ds.findParent(row[1])){
                ds.unionBySize(row[0], row[1]);

            }  else{
                usableCables++;
            }
        }

        int disconnectedComputer = 0;

        for(int i=0;i<n;i++){

            if(ds.parent[i]==i) disconnectedComputer++;
        }

        disconnectedComputer--;

        if(disconnectedComputer <= usableCables ) return disconnectedComputer;

        return -1;
    }
}