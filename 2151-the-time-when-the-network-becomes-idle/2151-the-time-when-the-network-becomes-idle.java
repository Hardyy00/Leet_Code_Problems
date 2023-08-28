class Solution {
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        
        int n = patience.length;
        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] row : edges){

            adj.get(row[0]).add(row[1]);

            adj.get(row[1]).add(row[0]);
        }

        int[] dis = new int[n];

        Arrays.fill(dis,(int)1e8);

        Queue<Integer> q = new LinkedList<>();

        dis[0] = 0;

        q.offer(0);

        while(!q.isEmpty()){

            int node = q.poll();
            int pathLen = dis[node];

            for(int next : adj.get(node)){

                if(pathLen + 1 < dis[next]){

                    dis[next] = pathLen + 1;
                    q.offer(next);
                }
            }
        }


        int maxTime = 0;

        for(int i=1;i<n;i++){

            int totDis = 2*dis[i];

            if(totDis <= patience[i]) maxTime = Math.max(maxTime, totDis);
            else if(totDis % patience[i] == 0){ 
            
                int lastMessage = totDis - patience[i];
                int timeTaken = totDis + lastMessage;

                maxTime = Math.max(maxTime, timeTaken);
                
            }else if( totDis > patience[i]){

                int lastMessage = (totDis / patience[i])*patience[i];
                int timeTaken = totDis + lastMessage;

                maxTime = Math.max(maxTime, timeTaken);
            }
        }

        return maxTime+1;
    }
}