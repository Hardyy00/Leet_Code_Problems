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

        // TC : O(N + flights.length) + O(N+flights.length)
        // SC : O(N+ flights.length) + O(N) + O(N)
        List<List<List<Integer>>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] row : flights){

            List<Integer> temp = new ArrayList<>();

            temp.add(row[1]);
            temp.add(row[2]);
            adj.get(row[0]).add(temp);
        }
        
        int[] dis = new int[n];

        // we do not need pq, because :
        // suppose we take a shortest path acc to price, but that took more than k stops
        // so we won't we able to reach it, but on the other hand, if we take a costly path
        // with less number of steps, we will be able to reach it

        // so we have to sort it acc. to stops, but on every stop , number of stops are
        // increasing by 1, so it will always be sorted in , queue, therefore we do not
        // have to use a pq, queue will be suffice
        Queue<Pair> q = new LinkedList<>();

        Arrays.fill(dis,(int)1e8);

        dis[src] = 0;

        q.offer(new Pair(src,0,0));     // node + dis + stops

        // we are also counting the destination taking step , hence k+1
        while(!q.isEmpty()){

            int node = q.peek().node;
            int cost = q.peek().cost;
            int num = q.peek().num;

            q.poll();

            if(node==dst && num<=k+1) continue;
            else if(num>=k+1) break; // since steps will be in sorted order, if steps become
            // > k+1, no one else will have steps <=k+1 , so just break 

            for(List<Integer> adjList : adj.get(node)){

                int adjNode = adjList.get(0);
                int adjCost = adjList.get(1);
                
                // we find a better cost , replace it
                if(cost + adjCost < dis[adjNode] && num<=k){

                    dis[adjNode] = cost + adjCost;

                    q.offer(new Pair(adjNode, dis[adjNode], num+1));
                }
            }
        }

        return dis[dst] == (int)1e8 ? -1 : dis[dst];    // if we weren't able to reach it, return -1


    }
}