class Pair{
    int node;
    int time;

    public Pair(int node, int time){

        this.node = node;
        this.time =time;
    }
}
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        
        List<List<Pair>> adj = new ArrayList<>();
        
        for(int i=0;i<=n;i++) adj.add(new ArrayList<>());

        for(int[] row : times){

            adj.get(row[0]).add(new Pair(row[1], row[2]));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((x,y)-> x.time - y.time);

        int[] dis = new int[n+1];

        Arrays.fill(dis, (int)1e5);

        dis[k] = 0;
        pq.offer(new Pair(k,0));

        while(!pq.isEmpty()){

            int node = pq.peek().node;
            int time = pq.peek().time;

            pq.poll();

            for(Pair pair : adj.get(node)){

                int adjNode = pair.node;
                int adjWt = pair.time;

                if(time + adjWt < dis[adjNode]){

                    dis[adjNode] = time + adjWt;

                    pq.offer(new Pair(adjNode, dis[adjNode]));
                }
            }
        }

        int maxi = 0;

        for(int i=1;i<=n;i++){

            if(dis[i]==(int)1e5) return -1;

            maxi = Math.max(maxi, dis[i]);
        }

        return maxi;
    }
}