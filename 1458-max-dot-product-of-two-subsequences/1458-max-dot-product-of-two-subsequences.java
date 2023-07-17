class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {

        // using the somethinng similar to longest common substring

        int n = nums1.length;
        int m = nums2.length;

        // int[][] dp = new int[n][m];

        // for(int[] row : dp) Arrays.fill(row, -1);

        // return solve(n-1,m-1,nums1,nums2,dp);

        // return solve2(n,m,nums1,nums2);

         return solve3(n,m,nums1,nums2);
        
    }

    int solve3(int n, int m, int[] nums1, int[] nums2){

        // Space Optimization
        // TC : O(N*M)
        // SC : O(M)

        int[] pre = new int[m+1];
        int[] curr = new int[m+1];

        for(int i=1;i<=n;i++){

            for(int j=1;j<=m;j++){

                int take = nums1[i-1] * nums2[j-1] + Math.max(0,pre[j-1]);

                int notTake1 = i-1 == 0 ? Integer.MIN_VALUE : pre[j];

                int notTake2 = j-1 == 0 ? Integer.MIN_VALUE : curr[j-1];

                curr[j] = Math.max(take , Math.max(notTake1, notTake2));
            }

            int[] temp = pre;
            pre = curr;
            curr = temp;
        }

        return pre[m];
    }

    int solve2(int n, int m, int[] nums1, int[] nums2){

        // Tabulation
        // TC : O(N*M)
        // SC : O(N*M)

        int[][] dp = new int[n+1][m+1];

        for(int i=1;i<=n;i++){

            for(int j=1;j<=m;j++){

                int take = nums1[i-1] * nums2[j-1] + Math.max(0,dp[i-1][j-1]);

                int notTake1 = i-1 == 0 ? Integer.MIN_VALUE : dp[i-1][j];

                int notTake2 = j-1 == 0 ? Integer.MIN_VALUE : dp[i][j-1];

                dp[i][j] = Math.max(take , Math.max(notTake1, notTake2));
            }
        }

        return dp[n][m];
    }

    int solve(int index1, int index2, int[] nums1, int[] nums2, int[][] dp){

        // Memoization
        // TC : O(N*M)
        // SC : O(N*M) + O(N+M)

        if(index1<0 || index2<0) return 0;

        if(dp[index1][index2]!=-1) return dp[index1][index2];

        // multiply the corresponding numbers
        // and making sure that we do not take -ve numbers ,by using max function
        int take = nums1[index1] * nums2[index2] + Math.max(0,solve(index1-1,index2-1,nums1,nums2, dp));

        // if index1-1<0, we get a empty subsequence which is not allowed 
        //so we save a negative value, so that it don't get selected

        // keep the index2 as it is and move the index1
        int notTake1 = index1-1 <0 ? Integer.MIN_VALUE : solve(index1-1,index2,nums1,nums2,dp);

        // keep the index1 as it is and move the index2
        int notTake2 = index2-1<0 ? Integer.MIN_VALUE : solve(index1,index2-1,nums1,nums2,dp);

        return dp[index1][index2] = Math.max(take, Math.max(notTake1,notTake2));
    }
}