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

        int e = roads.length;
        List<List<Pair>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] it : roads){

            adj.get(it[0]).add(new Pair(it[1], it[2]));
            adj.get(it[1]).add(new Pair(it[0], it[2]));
        }

        long[] dis = new long[n];
        int[] ways = new int[n];
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>(){

            @Override
            public int compare(Pair p1, Pair p2){

                return p1.dis < p2.dis ? -1 : 1;
            }
        });

        Arrays.fill(dis, (long)1e12);

        dis[0] = 0;
        ways[0] = 1;

        pq.offer(new Pair(0,0));

        while(!pq.isEmpty()){

            int node = pq.peek().node;
            long time = pq.peek().dis;

            pq.poll();

            for(Pair pair : adj.get(node)){

                int adjNode = pair.node;
                long d = pair.dis;

                if(time + d< dis[adjNode]){

                    dis[adjNode] = (time+d);

                    ways[adjNode] = ways[node]%mod;

                    pq.offer(new Pair(adjNode,dis[adjNode]));

                }else if(time + d == dis[adjNode]){

                    ways[adjNode] += ways[node]%mod;

                    ways[adjNode] %= mod;

                }
            }
        }

        // System.out.println(Arrays.toString(dis));
        // System.out.println(Arrays.toString(ways));

        return ways[n-1]%mod;

    }
}