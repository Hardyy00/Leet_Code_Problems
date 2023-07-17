class Solution {
    public int maximalSquare(char[][] mat) {

        // brute force : for every element , check if 1 size square can be made, 2 , 3, 4,
        // fby traversing (size*size) grid
        // TC : O(N*M * k * k*k) ) , where min(m,n) = k (maximum size square that can be made) 

        int m = mat.length;
        int n = mat[0].length;

        // return solve1(m, n, mat);

        return solve2(m, n, mat);
    }

    int solve2(int m, int n,char[][] mat){

        // Space Optimization
        // TC : O(M*N)
        // SC : O(N)

        // At every, (i,j) , calculating the maximum size of square with (i,j) as its right bottom
        // thereofore, getting i-1,j-1 and i,j-1 and i-1,j  , ca

        int[] pre = new int[n];
        
        int maxi = 0;

        // for the first row and first column, either one or zero can be the size of maximum square
        for(int i=0;i<n;i++){

            pre[i] = mat[0][i] - '0';

            maxi = Math.max(maxi, pre[i]);
        }

        for(int i=1;i<m;i++){
            int[] curr = new int[n];

            curr[0] = mat[i][0]-'0';

            maxi = Math.max(maxi, curr[0]);

            for(int j=1;j<n;j++){   

                if(mat[i][j]=='0') continue;

                // if the cell contains 1, then it is already of size 1 , hence adding 1
                curr[j] = 1 + Math.min(pre[j-1] , Math.min(curr[j-1] , pre[j]));

                maxi = Math.max(maxi, curr[j]);
            }

            pre = curr;
        }

        // maxi is the size of the maximum square , therefore maxi * maxi , is the area
        return maxi * maxi;

    }

    int solve1(int m, int n,char[][] mat){

        // Tabulation
        // TC : O(M*N)
        // SC : O(M*N)

        // At every, (i,j) , calculating the maximum size of square with (i,j) as its right bottom
        // thereofore, getting i-1,j-1 and i,j-1 and i-1,j  , ca

        int[][] dp = new int[m][n];

        int maxi = 0;

        // for the first row and first column, either one or zero can be the size of maximum square
        for(int i=0;i<n;i++){

            dp[0][i] = mat[0][i] -'0';

            maxi = Math.max(maxi, dp[0][i]);
        }

        for(int i=1;i<m;i++){

            dp[i][0] = mat[i][0] -'0';

            maxi = Math.max(maxi, dp[i][0]);
        }

        for(int i=1;i<m;i++){

            for(int j=1;j<n;j++){   

                if(mat[i][j]=='0') continue;

                // if the cell contains 1, then it is already of size 1 , hence adding 1
                dp[i][j] = 1 + Math.min(dp[i-1][j-1] , Math.min(dp[i][j-1] , dp[i-1][j]));

                maxi = Math.max(maxi, dp[i][j]);
            }
        }

        // maxi is the size of the maximum square , therefore maxi * maxi , is the area
        return maxi * maxi;

    }
}