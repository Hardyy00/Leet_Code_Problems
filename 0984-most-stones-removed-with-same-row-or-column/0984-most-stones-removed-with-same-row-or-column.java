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
    public int removeStones(int[][] stones) {

        // TC : O(N) + O(N) +(N+M)
        // SC : O(max(stones[i][0]) + max(stones[i][1])) (dsu)

        // clearly , number of stones can be removed from a component of size x is x-1
        // and x1 + x2... + xn = n (size of all the components the number of 1's)
        // and ans = x1-1 + x2-1 +... + xn-1 => ans = (x1+x2+x3+x3..+xn) - (1+1+1..number of components)
        // hence and = n - number of components


        // considering each row as a node, and each column as the another node
        // such that row 0,1,2 = node 0,1,2 and col 0,1,2 = node 3,4,5
        // hence whenever we find mat[r][c] = 1 , connecting r and c+maxrow+1 (in node form) 
        
        int n = stones.length;
        // calculating the last row and the last col
        int lastRow = 0;
        int lastCol = 0;

        for(int[] row: stones){

            lastRow = Math.max(lastRow, row[0]);
            lastCol = Math.max(lastCol, row[1]);
        }

        DisjointSet ds = new DisjointSet(lastRow + lastCol+1); // having row and cols as nodes

        for(int[] row: stones){

            int node1 = row[0];
            int node2 = row[1] + lastRow + 1;  // converting col to node

            ds.unionBySize(node1, node2); // since stones is there at row[0],row[1], connect them

            // set.add(node1);
            // set.add(node2);
        }

        int count = 0;

        // if a node's parent is itself, and its size is greater than 1, then its a component

        // as node 1 and node 2 , means a parent , and exactly one of them wil; have a size==2
        for(int i=0;i<ds.parent.length;i++){

            if(ds.parent[i]==i && ds.size[i]>1) count++;
        }

        return n- count;
        
    }
}