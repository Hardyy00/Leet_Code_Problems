class Solution {
    public int climbStairs(int n) {

        // return findPaths(n,new int[n+1]);

        // return findPaths2(n);

        return findPaths(n);
        
    }

    int findPaths(int n,int[] dp){

        // TC : O(N) && SC : O(N + N) (array and recursion stack)
        // Memoization

        // if i am able to reach 1 or 0 then the current traversed path is a valid way (counting it)
        if(n==0 || n==1) return 1;
        if(dp[n]!=0) return dp[n];      // if the subproblem is already solved then return it

        // saving the subprroblem 
        return dp[n] = findPaths(n-1,dp) + findPaths(n-2,dp);  
    }

    int findPaths2(int n){

        // Tabulation
        // TC : O(N) && SC : O(N) (array)

        if(n<=1) return 1;

        int[] dp = new int[n+1];
        dp[0]=dp[1] = 1;

        for(int i=2;i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    int findPaths(int n){

        // Space Optimization 
        // TC : O(N) && SC: O(1)

        if(n<=1) return 1;

        int pre1 = 1;
        int pre2 = 1;

        for(int i=2;i<=n;i++){

            int temp = pre1+ pre2;
            pre2 = pre1;
            pre1 = temp;
        }

        return pre1;
    }
}