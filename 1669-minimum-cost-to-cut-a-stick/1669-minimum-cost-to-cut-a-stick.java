class Solution {
    public int minCost(int n, int[] cuts) {

        int len = cuts.length;
        // to get the length initially, insert 0 and length of the stick at the end
        int[] copy = new int[len+2];

        for(int i=0;i<len;i++){
            copy[i+1] = cuts[i];
        }
        copy[copy.length-1] = n;

        // to solve the subproblems individually , you have to sort the array , suppose
        // you have at cutting order,  3 2 4 1 5, (at stick of length 7), if you make a cut at 4,
        // the you are left with 3 2   and 1 5 , can you solve them individually .. no
        // because, after cutting at 4 i left with 0 to 4 and 4 to 7, and the left portions  , shows i have to make a 
        // cut at 3 2 (okay with it), but right portions show i have to  make a cut at 1 (but currently i am woking)
        // with portion 4 to 7, so i cannot solve them individually
        // if i sort the array, i have 1 2 3 4 5, now if i make a cut at 4, i left with sticks, 0 to 4 and 4 to 7,
        // if see array , i have to make cuts at 1 2 3 in left portion (0 to 4) which is alright
        // and  right is same too
        Arrays.sort(copy);


        // return solve(1,copy.length-2,copy);
        
        int size = len+2;
        // int[][] dp = new int[len+2][len+2];

        // for(int[] row : dp) Arrays.fill(row, -1);

        // return solve2(1, size-2, copy, dp);

        return solve3(copy, size);
        
    }

    int solve3(int[] copy, int len){

        // Tabulation
        // TC : O(len * len * len) + O(N log N) (for sorting)
        // SC : O(len  * len * len) + O(len)

        int[][] dp = new int[len][len];

        for(int i=len-2;i>=1;i--){

            // j=i , because j cannot be less than i, but can be equal or greater then i
            for(int j=i;j<=len-2;j++){

                int mini = Integer.MAX_VALUE;

                for(int k=i;k<=j;k++){

                    int cost = copy[j+1] - copy[i-1] + dp[i][k-1] + dp[k+1][j];
                    mini = Math.min(mini, cost);
                }

                dp[i][j] = mini;
            }
        }

        return dp[1][len-2];
    }

    int solve2(int i, int j, int[] copy, int[][] dp){

        // Memoization (extra *copy.length for loop) 
        // TC : O(copy.length * copy.length * copy.length) + O(N log N) (for sorting)
        // SC : O(copy.length * copy.length)+ O(copy.length) + O(copy.length);

        if(i>j) return 0;

        if(dp[i][j] !=-1) return dp[i][j];

        int mini = Integer.MAX_VALUE;

        for(int k=i;k<=j;k++){

            int cost = copy[j+1] - copy[i-1] + solve2(i, k-1,copy, dp) + solve2(k+1, j, copy, dp);

            mini = Math.min(mini, cost);
        }

        return dp[i][j] = mini;
    }

    int solve(int i, int j, int[] copy){

        // Recursion
        // TC : O(Exponential) + O(N log N) (for sorting)
        // SC : O(Copy.length) +    O(Copy.length)

        // when there are no cutting partition left, just return
        if(i>j) return 0;

        int mini = Integer.MAX_VALUE;

        // you can make a partition, at every element of cuts (stored at 1 to n-2 int copy array)
        for(int k=i;k<=j;k++){

            // when you cut, you can get the length  from copy[i-1] && copy[j+1]
            // because between these range you are making a cut , suppose you make a cut 
            // in the array 1 3 5 , you made a cut at 1 and 5 , you left with portion (1 2 3 4 5) (you have already 
            // cut (0 1) && (5 6 7 8), these are other portions)
            // if you make a cut at 3 , what can be length , it will the length of the portion, you are dealing 
            // with , that is  (i-1 and j+1 (at 3 both are at 2,2)) 
            int cost = copy[j+1]-copy[i-1] + solve(i,k-1,copy) + solve(k+1, j, copy);
            // solving the remaining portitions
            mini = Math.min(mini, cost); 
        }

        return mini;
    }
}                    