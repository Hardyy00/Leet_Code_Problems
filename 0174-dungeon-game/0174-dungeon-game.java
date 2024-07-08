class Solution {

    int[][] dp = new int[200][200];
    public int calculateMinimumHP(int[][] dungeon) {


        // for(int[] row : dp){
        //     Arrays.fill(row, -1);
        // }
        
        int n = dungeon.length , m = dungeon[0].length;

        // return memo(0,0,n,m, dungeon);

        return iterative(dungeon, n,m);
    }

    private int iterative(int[][] mat, int n, int m){

        int[][] dp = new int[n][m];

        dp[n-1][m-1] = mat[n-1][m-1] < 0 ? 1 + Math.abs(mat[n-1][m-1]) : 1;

        for(int row=n-1;row>=0;row--){

            for(int col =m-1;col>=0;col--){

                if(row==n-1 && col==m-1){
                    continue;
                }

                int right = (int)1e9;

                if(col + 1 < m){
                    right = dp[row][col+1];
                }

                int down = (int)1e9;

                if(row +1 < n){
                    down = dp[row+1][col];
                }

                int heightFromHere = Math.min(right, down) - mat[row][col];

                dp[row][col] = heightFromHere <=0 ? 1 : heightFromHere;
            }
        }

        return dp[0][0];
    }


    private int memo(int row, int col, int n, int m, int[][] mat){

        if(row == n || col==m){

            return (int)1e8;
        }

        if(row==n-1 && col==m-1){
            return mat[row][col] < 0 ? 1 + Math.abs(mat[row][col]) : 1;

        }

        if(dp[row][col] !=-1){
            return dp[row][col];
        }


        int right = memo(row, col+1, n, m , mat);

        int down = memo(row+1, col, n, m , mat);


        int healthFromHere = Math.min(right, down) - mat[row][col];

        return dp[row][col] = healthFromHere <=0 ? 1 : healthFromHere;
    }
}