class Solution {
    public int maximumDetonation(int[][] bombs) {

        if(bombs.length == 1) return 1;
        
        int n = bombs.length;
        
        boolean[][] areConnected = new boolean[n][n];

        for(int i=0;i<n;i++){

            areConnected[i][i] = true;

            for(int j=i+1;j<n;j++){

                double dis = calcDistance(bombs[i], bombs[j]);

                if(dis<=bombs[i][2]){
                    areConnected[i][j] = true;
                }

                if(dis<=bombs[j][2]){

                    areConnected[j][i]  = true;
                }
            }
        }

        for(int via = 0;via < n ; via++){

            for(int i=0;i<n;i++){

                for(int j=0;j<n;j++){

                    areConnected[i][j] = areConnected[i][j] || (areConnected[i][via] && areConnected[via][j]);
                }
            }
        }


        int maxi = 0;

        for(int i=0;i<n;i++){

            int count=0;

            for(int j=0;j<n;j++){

                if(areConnected[i][j]) count++;
            }

            maxi = Math.max(maxi, count);
        }

        return maxi;

            
    }

    private double calcDistance(int[] a1, int[] a2){
        int x1 = a1[0], x2 = a2[0] , y1 = a1[1], y2 = a2[1];

        return Math.sqrt( Math.pow(x1-x2, 2) + Math.pow(y1-y2 , 2));
    }
}