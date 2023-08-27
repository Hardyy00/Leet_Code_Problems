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

class Solution {
    public int minCostConnectPoints(int[][] points) {
        
        int n = points.length;
        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<n;i++){

            for(int j=i+1;j<n;j++){

                List<Integer> temp = new ArrayList<>();
                int dis  = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                temp.add(i);
                temp.add(j);
                temp.add(dis);

                adj.add(temp);
            }
        }


        Collections.sort(adj, new Comparator<List<Integer>>(){

            @Override
            public int compare(List<Integer> l1 , List<Integer> l2){

                return l1.get(2) - l2.get(2);
            }
        });

        // System.out.println(adj);

        int sum = 0;
        DisjointSet ds = new DisjointSet(n);

        for(List<Integer> row : adj){

            int u = row.get(0);
            int v = row.get(1);
            int w = row.get(2);

            if(ds.findParent(u) != ds.findParent(v)){

                ds.unionBySize(u,v);
                sum += w;
            }
        }

        return sum;

        
    }
}