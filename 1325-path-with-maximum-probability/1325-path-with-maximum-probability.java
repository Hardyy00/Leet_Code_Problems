class Pair{

    int node;
    double prob;

    public Pair(int node, double prob){

        this.node = node;
        this.prob = prob;
    }
}

class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        
        List<List<Pair>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int i=0;i<edges.length;i++){

            adj.get(edges[i][0]).add(new Pair(edges[i][1], succProb[i]));
            adj.get(edges[i][1]).add(new Pair(edges[i][0], succProb[i]));
        }

        double[] dis = new double[n];
        PriorityQueue<Pair> pq  = new PriorityQueue<>(new Comparator<Pair>(){

            @Override 
            public int compare(Pair p1, Pair p2){

                if(p1.prob <= p2.prob) return 1;

                return -1;
            }
        });

        dis[start_node] = 1;

        pq.offer(new Pair(start_node, 1));

        while(!pq.isEmpty()){

            int node = pq.peek().node;
            double prob = pq.peek().prob;

            if(node == end_node) return dis[node];


            pq.poll();

            for(Pair pair : adj.get(node)){

                int adjNode = pair.node;
                double reachProb = pair.prob;

                double totalProb = prob * reachProb;

                if(totalProb > dis[adjNode]){

                    dis[adjNode] = totalProb;

                    pq.offer(new Pair(adjNode, totalProb));     
                }

            }
        } 

        return 0;

    }
}