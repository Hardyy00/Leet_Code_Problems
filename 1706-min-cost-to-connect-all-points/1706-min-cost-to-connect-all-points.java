class Pair{

    int node;
    int dis;

    public Pair(int node, int dis){

        this.node = node;
        this.dis = dis;
    }
}
class Solution {
    public int minCostConnectPoints(int[][] points) {

        int n = points.length;
        List<List<Pair>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int i=0;i<n;i++){

            for(int j=0;j<n;j++){

                int dis = Math.abs(points[j][0] - points[i][0]) + Math.abs(points[j][1] - points[i][1]);
                adj.get(i).add(new Pair(j, dis));
            }
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((x,y) -> x.dis - y.dis);

        boolean[] visit = new boolean[n];

        pq.offer(new Pair(0,0));
        int sum =0;
        while(!pq.isEmpty()){

            int node = pq.peek().node;
            int dis = pq.peek().dis;
            
            pq.poll();

            if(visit[node]) continue;

            visit[node] = true;
            sum += dis;

            // System.out.println(node + " " + dis);

            for(Pair pair : adj.get(node)){

                if(!visit[pair.node]){

                    pq.offer(pair);
                }
            }
        }

        return sum;
        
    }
}