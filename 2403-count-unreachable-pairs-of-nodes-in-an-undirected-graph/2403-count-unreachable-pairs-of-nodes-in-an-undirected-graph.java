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
    public long countPairs(int n, int[][] edges) { 

        // return solve1(n, edges);

        return solve2(n, edges);
       
    }

    private long solve2(int n, int[][] edges){

        DisjointSet ds = new DisjointSet(n);

        for(int row[] : edges){

            int u = row[0];
            int v = row[1];

            ds.unionBySize(u,v);
        }

        long pairs =0 , rem = n;

        for(int i=0;i<n;i++){

            if(ds.findParent(i) == i){

                long size = ds.size[i];
                pairs += (rem - size) * size;

                rem -= size;
            }
        }

        return pairs;
        
    }

    private long solve1(int n, int[][] edges){
        // getting the sizes of the components and multiplying each component
       // size with every components , to get the number of pairs
       // the component can make with other components 

       // TC : O(E) + O(N+2E)
       // sc : O(N+2E) + O(N)

        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] row : edges){

            adj.get(row[0]).add(row[1]);
            adj.get(row[1]).add(row[0]);
        }

        boolean visit[] = new boolean[n];
        long pairs = 0;
        long rem  = n;
          
        for(int i =0;i<n;i++){

            if(visit[i] == false){

                int[] counter = {0};  // to count nodes in every components
                dfs(i, visit, adj, counter);

                pairs += (rem-counter[0])*(long)counter[0];
                // the current components has x nodes, and the remaining node are n-x
                // hence x nodes will make pair with n-x nodes

                rem -= counter[0];  // deduct the current component nodes
            }
        }    

        return pairs;   
    }

    private void dfs(int node, boolean[] visit, List<List<Integer>> adj, int[] counter){

        visit[node] = true;
        counter[0]++;

        for(int it : adj.get(node)){

            if(visit[it] == false){

                dfs(it, visit, adj, counter);
            }
        }
    }
}


