class Pair{

    int r;
    int c;
    int diff;

    public Pair(int r, int c, int diff){

        this.r = r;
        this.c = c;
        this.diff = diff;
    }
}
class Solution {

    public int minimumEffortPath(int[][] heights) {

        // TC : O(N*M * Log(N*m)) or O(N*M *4 * Log(N*M)) as- For dijkstra's algo
        // tc is E log V, number of edges = n*m*4 and  v = n*m

        // also , we can derive it

        int n = heights.length;
        int m = heights[0].length;

        int[][] dis = new int[n][m];

        for(int[] row : dis) Arrays.fill(row, (int)1e9);

        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>(){

            @Override
            public int compare(Pair p1, Pair p2){

                return p1.diff - p2.diff;       // sort w.r.t minimum diff
            }

        });
        dis[0][0] = 0;  // at first there is no effort

        pq.offer(new Pair(0,0,0));

        int[] ra = {-1,0,1,0};
        int[] ca = {0,1,0,-1};

        while(!pq.isEmpty()){

            int r = pq.peek().r;
            int c = pq.peek().c;
            int diff = pq.peek().diff;

            pq.poll();

            // if we reach the end, that means all the above differences, will be greater than
            // the current diff (as we are using a min heap), so just return it
            if(r==n-1 && c==m-1) return diff;

            for(int i=0;i<4;i++){

                int nr = r + ra[i];
                int nc = c + ca[i];

                if(nr>=0 && nr<n && nc>=0 && nc<m){
                    
                    // get the current effort
                    int currDiff = Math.abs(heights[nr][nc] - heights[r][c]);

                    // find  the maximum absoulte effort
                    int maxDiff = Math.max(currDiff, diff); 

                    // if we find a better effort
                    if(maxDiff < dis[nr][nc]){

                        dis[nr][nc] = maxDiff;

                        pq.offer(new Pair(nr,nc,maxDiff));
                    }
                }
            }

        }

        return dis[n-1][m-1];       // we won't be reaching here , but still just return it
    }
}