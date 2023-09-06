class Solution {
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        
        int n = amount.length;

        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] row : edges){

            adj.get(row[0]).add(row[1]);
            adj.get(row[1]).add(row[0]);
        }

        int[] dis = new int[n];
        int[] parent = new int[n];

        Arrays.fill(dis, (int)1e8);
        dis[bob] = 0;
        parent[bob] = -1;

        dfs(bob, dis, parent, adj); 

        int reach = 0;

        boolean[] visit = new boolean[n];

        int node = 0;
        while(node!=-1){
            visit[node] = true;
            node = parent[node];
        }

        for(int i=0;i<n;i++) if(visit[i] == false) dis[i] = (int) 1e8;


        // System.out.println(Arrays.toString(parent));

        // System.out.println(Arrays.toString(dis));


        int[] maxIncome = {Integer.MIN_VALUE};

        aliceTravel(0,-1,0,0,dis,adj, amount, maxIncome);

        return maxIncome[0];

    }

    private void aliceTravel(int node, int parent ,int steps, int collection, int[] dis, List<List<Integer>> adj, int[] amount,int[] maxIncome){

        if(steps < dis[node]){
            collection += amount[node];
        }else if(steps == dis[node]){

            collection += amount[node]/2;
        }

        boolean isLeaf = true;

        for(int it : adj.get(node)){

            if(it!=parent){

                isLeaf = false;
                aliceTravel(it,node,steps+1, collection, dis, adj, amount, maxIncome );
            }
        }

        // System.out.println(collection);

        if( isLeaf ) maxIncome[0] = Math.max(maxIncome[0], collection);

    }

    private void dfs(int node, int[] dis, int[] parent , List<List<Integer>> adj){

        for(int it : adj.get(node)){

            if(dis[node] + 1 < dis[it]){

                dis[it] = dis[node] + 1;
                parent[it] = node;

                dfs(it, dis, parent , adj);
            }
        }
    }
}