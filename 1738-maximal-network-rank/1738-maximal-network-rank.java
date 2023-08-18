class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        

        int[] indegree = new int[n];
        boolean[][] adj  = new boolean[n][n];

        for(int[] row : roads){

            indegree[row[0]]++;
            indegree[row[1]]++;

            adj[row[0]][row[1]] = adj[row[1]][row[0]] = true;
        }

        int maxi = 0;

        for(int i=0;i<n;i++){

            for(int j=0;j<n;j++){

                if(j==i) continue;

                int rank = indegree[i] + indegree[j];

                if(adj[i][j]) rank--;

                maxi = Math.max(maxi, rank); 
            }
        }

        return maxi;
    }
}