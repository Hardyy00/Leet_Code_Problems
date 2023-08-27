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
    public int minCostConnectPoints(int[][] points) {
        
        int n = points.length;
        int size = n-1;
        int tot =  (size * (size + 1))/2;

        Integer[][] adj = new Integer[tot][3];

        int index =0;
        for(int i=0;i<n;i++){

            for(int j=i+1; j<n;j++){

                int dis = Math.abs(points[j][0] - points[i][0]) + Math.abs(points[j][1] - points[i][1]);
                
                adj[index][0] = i;
                adj[index][1] = j;
                adj[index++][2] = dis;
            }
        }

        


        Arrays.sort(adj, new Comparator<Integer[]>(){

            @Override
            public int compare(Integer[] l1 , Integer[] l2){

                return l1[2] - l2[2];
            }
        });


        int sum = 0;
        DisjointSet ds = new DisjointSet(n);

        for(Integer[] row : adj){

            int u = row[0];
            int v = row[1];
            int w = row[2];

            if(ds.findParent(u) != ds.findParent(v)){

                ds.unionBySize(u,v);
                sum += w;
            }
        }

        return sum;

        
    }
}