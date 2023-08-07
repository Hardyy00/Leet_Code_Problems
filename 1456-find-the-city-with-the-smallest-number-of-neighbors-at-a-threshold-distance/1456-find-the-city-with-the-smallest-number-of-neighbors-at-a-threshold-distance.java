class Solution {
    public int findTheCity(int n, int[][] edges, int dt) {

        int[][] dis = new int[n][n];

        for(int[] row : dis) Arrays.fill(row , (int)1e8);

        for(int[] it : edges){

            dis[it[0]][it[1]] = it[2];
            dis[it[1]][it[0]] = it[2];
        }

        for(int i=0;i<n;i++) dis[i][i] = 0;

        for(int via = 0; via < n; via++){

            for(int i=0;i<n;i++){

                for(int j=0;j<n;j++){

                    int minDistance = Math.min( dis[i][via] + dis[via][j] , dis[i][j]);

                    dis[i][j] = minDistance;
                }
            }
        }

        int minCount = Integer.MAX_VALUE;
        int city = 0;

        for(int i=0;i<n;i++){

            int count = 0;
            for(int j=0;j<n;j++){

                if(dis[i][j] <= dt){

                    count++;
                }
            }

            if(count <= minCount){
                minCount = count;
                city = i;
            }

        }

        return city;
        
    }
}