class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {

        // Memoization
        int[][] dp = new int[triangle.size()][triangle.size()];

        for(int[] row : dp) Arrays.fill(row,Integer.MAX_VALUE);

        return triangle1(0,0, triangle,dp);

        // Tabulation
        // // return triangle2(triangle);

        // Space Optimized
        // // return triangle3(triangle);
        
    }

    int triangle3(List<List<Integer>> list){

        // Space optimised
        // TC : O(M^2) && SC : O(M)  

        int[] pre = new int[list.size()+1];
        int[] help = new int[list.size()+1];

        for(int i=list.size()-1;i>=0;i--){

            for(int j=0;j<list.get(i).size();j++){

                int bottom = pre[j] + list.get(i).get(j);
                int bottomPlusOne = pre[j+1] + list.get(i).get(j);

                help[j] = Math.min(bottom,bottomPlusOne);
            }

            int[]  swap = help;
            help = pre;
            pre = swap;
        }

        return pre[0];
    }

    int triangle2(List<List<Integer>> list){

        // Tabulation
        // TC : O(M^2) && SC : O(M^2)       // m is no of rows

        int[][] dp = new int[list.size()][list.size()];

        for(int i=list.size()-1;i>=0 ;i--){

            for(int j=0;j<list.get(i).size();j++){

                if(i==list.size()-1){
                    dp[i][j] = list.get(i).get(j);
                }else{

                    int bottom = dp[i+1][j] + list.get(i).get(j);
                    int bottomPlusOne = dp[i+1][j+1] + list.get(i).get(j);

                    dp[i][j] = Math.min(bottom,bottomPlusOne);
                }
            }
        }

        return dp[0][0];

    }

    int triangle1(int row, int col, List<List<Integer>> list,int[][] dp){

        // Memoization
        // TC : O(M*(M+1)/2) (1+2+3+..) == O(M^2) && SC : O(M)

        if(row==list.size()-1) return list.get(row).get(col);

        if(dp[row][col]!=Integer.MAX_VALUE) return dp[row][col];

        int bottom = triangle1(row+1,col,list,dp) + list.get(row).get(col);

        int bottomPlusOne = triangle1(row+1,col+1,list,dp) + list.get(row).get(col);

        return dp[row][col] = Math.min(bottom,bottomPlusOne);
    }
}