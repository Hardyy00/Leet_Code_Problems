class Pair{

    int node;
    long dis;

    public Pair(int node, long dis){

        this.node = node;
        this.dis = dis;
    }
}


class Solution {
    int mod = (int)1e9 + 7;
    public int countPaths(int n, int[][] roads) {

        // using dijkstra's algo, to find the shortest path, and maintaining number of ways
        // to reach a node
        // TC : O(E*Log V) == O(N * Log E) (where e is roads.length)
        List<List<Pair>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] it : roads){

            // nodes are bi-directional
            adj.get(it[0]).add(new Pair(it[1], it[2]));
            adj.get(it[1]).add(new Pair(it[0], it[2]));
        }

        long[] dis = new long[n];   // time <= 10^9 , so take a long array
        int[] ways = new int[n];    // to count numbers of ways to each node
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>(){

            @Override
            public int compare(Pair p1, Pair p2){

                return p1.dis < p2.dis ? -1 : 1;    // sort acc. to dis
            }
        });

        Arrays.fill(dis, (long)1e12);       //10^9 * 200 == 10^11, hence take infinity == 10^12

        dis[0] = 0; // self distance is 0
        ways[0] = 1;    // ways to reach self is 0

        pq.offer(new Pair(0,0));    // node + dis

        while(!pq.isEmpty()){

            int node = pq.peek().node;
            long time = pq.peek().dis;

            pq.poll();

            for(Pair pair : adj.get(node)){

                int adjNode = pair.node;
                long d = pair.dis;

                // change on finding a better distance
                if(time + d< dis[adjNode]){

                    dis[adjNode] = (time+d);
                    
                    // on finding a better distance , discard old ways and replace them
                    // ways number of ways to reach previous ways,as in those ways we
                    // can this node
                    ways[adjNode] = ways[node]%mod; 

                    pq.offer(new Pair(adjNode,dis[adjNode]));

                }else if(time + d == dis[adjNode]){
                    
                    // if the distance are same , just add the ways of 'node' , to adjNode ways
                    ways[adjNode] += ways[node]%mod;

                    ways[adjNode] %= mod;   // take mod

                }
            }
        }

        return ways[n-1]%mod;

    }
}