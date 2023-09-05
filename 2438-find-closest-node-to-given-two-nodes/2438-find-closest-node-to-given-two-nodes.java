class Solution {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        
        int n = edges.length;
        int[] dis1 = new int[n];
        int[] dis2 = new int[n];

        Arrays.fill(dis1, (int)1e7);
        Arrays.fill(dis2, (int)1e7);

        dis1[node1] = 0;

        dfs(node1, edges, dis1);

        dis2[node2] = 0;

        dfs(node2, edges, dis2);

        int minIndex = 0;
        int minDistance = Math.max(dis1[0], dis2[0]);

        for(int i=1;i<n;i++){

            int maxDis = Math.max(dis1[i], dis2[i]);


            if(maxDis < minDistance){

                minDistance = maxDis;
                minIndex = i;
            }
        }

        return minDistance == (int)1e7 ? -1 : minIndex;
        
    }

    private void dfs(int node, int edges[], int dis[]){

        int adjNode = edges[node];

        if( adjNode !=-1 && dis[node] + 1 < dis[adjNode]){

            dis[adjNode] = dis[node] + 1;
            dfs(adjNode,edges, dis);
        }
    }
}