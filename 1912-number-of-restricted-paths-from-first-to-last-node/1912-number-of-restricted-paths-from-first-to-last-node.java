class Pair{

    int node;
    long weig;

    public Pair(int node, long weig){
        
        this.node = node;
        this.weig = weig;
    }
}
class Solution {
    int mod = (int)1e9 +7;
    public int countRestrictedPaths(int n, int[][] edges) {
        
        List<List<Pair>> adj = new ArrayList<>();

        for(int i=0;i<=n;i++) adj.add(new ArrayList<>());

        for(int[] row : edges){

            adj.get(row[0]).add(new Pair(row[1],row[2]));
            adj.get(row[1]).add(new Pair(row[0], row[2]));
        }

        long[] dis = new long[n+1];
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<>(){

            @Override
            public int compare(Pair p1, Pair p2){


                if(p1.weig < p2.weig) return -1;

                else if(p1.weig == p2.weig) return 0;

                return 1;
            }
        });

        Arrays.fill(dis, (long)1e12);

        dis[n] = 0;
        pq.offer(new Pair(n,0));
        

        while(!pq.isEmpty()){

            int node  = pq.peek().node;
            long weight = pq.peek().weig;

            pq.poll();

            for(Pair pair : adj.get(node)){

                int adjNode = pair.node;
                long adjWeight = pair.weig;

                if(weight + adjWeight < dis[adjNode]){

                    dis[adjNode] = (long)weight + (long)adjWeight;

                    pq.offer(new Pair(adjNode,dis[adjNode]));
                }
            }
        }


        long[] dp = new long[n+1];

        Arrays.fill(dp,-1);

        return (int)solve(1,n, dp, dis, adj)%mod;
    }

    private long solve(int node, int n, long[] dp, long[] dis, List<List<Pair>> adj){

        if(n==node) return 1l;

        if(dp[node]!=-1) return dp[node];

        long ways = 0;
        for(Pair pair : adj.get(node)){

            if(dis[node] > dis[pair.node]){

                ways += solve(pair.node, n, dp, dis, adj)%mod;
            }
        }

        return dp[node] = ways%mod;
    }
}