class Pair{
    int node;
    int cost;
    int num;

    public Pair(int node, int cost, int num){

        this.node = node;
        this.cost = cost;
        this.num = num;
    }
}
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        List<List<List<Integer>>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] row : flights){

            List<Integer> temp = new ArrayList<>();

            temp.add(row[1]);
            temp.add(row[2]);
            adj.get(row[0]).add(temp);
        }
        
        int[] dis = new int[n];

        Queue<Pair> q = new LinkedList<>();

        Arrays.fill(dis,(int)1e8);

        dis[src] = 0;

        q.offer(new Pair(src,0,0));

        while(!q.isEmpty()){

            int node = q.peek().node;
            int cost = q.peek().cost;
            int num = q.peek().num;

            q.poll();

            if(node==dst && num<=k+1) continue;
            else if(num>k+1) break;

            for(List<Integer> adjList : adj.get(node)){

                int adjNode = adjList.get(0);
                int adjCost = adjList.get(1);

                if(cost + adjCost < dis[adjNode] && num<=k){

                    dis[adjNode] = cost + adjCost;

                    q.offer(new Pair(adjNode, dis[adjNode], num+1));
                }
            }
        }

        return dis[dst] == (int)1e8 ? -1 : dis[dst];


    }
}