class Solution {
    public int findTheCity(int n, int[][] edges, int dt) {
        
        // applying floyd warshall algo to find distance of every node to every node
        // and then finding which city has minimum number of city which we can be reach in
        // threshold distance , if multiple cities exist , then just storing them 
        // in a iterative way

        // TC : O(N*N*N)
        // SC : O(N^2)
        int[][] dis = new int[n][n];

        for(int[] row : dis) Arrays.fill(row , (int)1e8);

        // make a adjacency matrix , edges are bi-directional
        for(int[] it : edges){

            dis[it[0]][it[1]] = it[2];
            dis[it[1]][it[0]] = it[2];
        }

        for(int i=0;i<n;i++) dis[i][i] = 0;

        // floyd warshall
        for(int via = 0; via < n; via++){

            for(int i=0;i<n;i++){

                for(int j=0;j<n;j++){

                    int minDistance = Math.min( dis[i][via] + dis[via][j] , dis[i][j]);

                    dis[i][j] = minDistance;
                }
            }
        }

        int minCount = Integer.MAX_VALUE;   // to track , minimum citites
        int city = 0;   // initiall city can be anything

        for(int i=0;i<n;i++){

            int count = 0;  // count cities, with threshold distance
            for(int j=0;j<n;j++){

                if(dis[i][j] <= dt){

                    count++;
                }
            }

            // minimize the cities count , and for the maximum condition, assign city 
            // on every equal count, so the city value get increased
            if(count <= minCount){
                minCount = count;
                city = i;
            }

        }

        return city;
        
    }
}