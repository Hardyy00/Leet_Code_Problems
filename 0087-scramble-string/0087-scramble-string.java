class Solution {
    int[][][][] dp;
    public boolean isScramble(String s1, String s2) {

        if(s1.equals(s2)){
            return true;
        }

        int n = s1.length();

        dp= new int[n][n][n][n];

        for(int[][][] a : dp){

            for(int[][] mat : a){

                for(int[] row : mat){

                    Arrays.fill(row, -1);
                }
            }
        }

        boolean ans = memo(0,n-1, 0,n-1, s1, s2) == 1; 
        return ans;
    }

    private int memo(int x,int y, int p, int q,String s1, String s2){

        // System.out.println(x + " " + y + " " + p + " " + q);

        if((y-x) != (q -p)){
            return 0;
        }

        if( y== x ){

            return s1.charAt(x) == s2.charAt(p) ? 1 : 0;
        }

        if(dp[x][y][p][q] !=-1){
            return dp[x][y][p][q];
        }

        int res = 0;
        int len = y-x;

        // if(x==1 && y==2 && p==1 && q==2){
        //     System.out.println("here " + len);
        // }

        for(int k=0;k<len;k++){

            int mid1 = x + k, mid2 = p + k;

            int notSwap = memo(x,mid1,p, mid2, s1, s2) + memo(mid1+1, y, mid2+1, q,s1,s2);

            int backIndex = q -k;



            int swap = memo(x, mid1, backIndex, q, s1, s2) + memo(mid1+1, y, p,backIndex-1, s1,s2);

            if(notSwap==2 || swap==2){
                res = 1;
            }
            
        }

        return dp[x][y][p][q] = res;
    }
}