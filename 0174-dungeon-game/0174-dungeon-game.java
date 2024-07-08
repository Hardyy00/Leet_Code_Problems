class Solution {

    int[][] dp = new int[200][200];
    public int calculateMinimumHP(int[][] dungeon) {


        for(int[] row : dp){
            Arrays.fill(row, -1);
        }
        
        int n = dungeon.length , m = dungeon[0].length;

        return memo(0,0,n,m, dungeon);
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