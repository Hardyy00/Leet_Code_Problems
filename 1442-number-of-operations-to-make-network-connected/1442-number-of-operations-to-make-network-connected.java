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

        // if in a disjoint set there are number of nodes , connected to ultimate parent node
        // then , if an edge comes in between some , non parent nodes, then there is no need
        // to use that cable , as the computers are already connected to the ultimate computer
        // so we can use that cable , to connected diconnected computers
        int usableCables = 0;   

        for(int[] row : connections){

            // if they are not connected , so connected them
            if(ds.findParent(row[0]) != ds.findParent(row[1])){
                ds.unionBySize(row[0], row[1]);

            }  else{    // if two computers are already connected, to a main computer
            // just use this cable to connect diconnected computers
                usableCables++;
            }
        }

        int disconnectedComputer = 0;      

        for(int i=0;i<n;i++){

            // for the connected computers , there shall be one ultimate node
            // and for all the disconnected computers, ultimate parent node must be themselves
            if(ds.parent[i]==i) disconnectedComputer++;
        }

        disconnectedComputer--; // remove the connected computers group

        // if we have enough cables to connect all the diconnected, then return number of disconnected computers
        if(disconnectedComputer <= usableCables ) return disconnectedComputer;

        return -1;      //we cannot connect all the computers
    }
}