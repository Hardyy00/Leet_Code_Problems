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
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        
       Integer[][] mat = new Integer[edges.length][3];

       for(int i=0;i<edges.length;i++){

           for(int j=0;j<3;j++){

               mat[i][j] = edges[i][j];
           }
       }

       Arrays.sort(mat, new Comparator<Integer[]>(){

           @Override 
           public int compare(Integer[] a1 , Integer[] a2){

               int w1 = a1[2];
               int w2 = a2[2];

               return w1-w2;
           }
       });

       int mst = mst(n,mat,new int[]{-1,-1,-1}, false);

       List<List<Integer>> ans = new ArrayList<>();

       ans.add(new ArrayList<>());
       ans.add(new ArrayList<>());

       for(int i=0; i <edges.length;i++){

           int currMst = mst(n, mat,edges[i], false);

           if(currMst == mst){
                if(mst != mst(n,mat,edges[i],true)) continue;
                else ans.get(1).add(i);
           }
           else ans.get(0).add(i);
       }

       return ans;
        
    }

    private int mst(int n, Integer[][] edges, int[] notInclude,boolean flag){

        DisjointSet ds = new DisjointSet(n);

        int sum = 0;

        if(flag) {
            ds.unionBySize(notInclude[0], notInclude[1]);
            sum += notInclude[2];
        }

        for(Integer[] row : edges){

            int u = row[0];
            int v = row[1];
            int wt = row[2];

            if(u == notInclude[0] && v == notInclude[1] && wt == notInclude[2]) continue;

            if(ds.findParent(u) != ds.findParent(v)){

                ds.unionBySize(u,v);
                sum += wt;
            }
        }

        return sum;


    }
}