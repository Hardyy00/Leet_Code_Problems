class Solution {
    public int findLength(int[] nums1, int[] nums2) {

        // Using the concept of longest common substring

        return lcs2(nums1, nums2);
        
    }

    int lcs2(int[] nums1, int[] nums2){

        // Space Optimization
        // TC : O(N*M)
        // SC : O(M)

        int n = nums1.length;
        int m = nums2.length;

        int[] pre = new int[m+1];

        int maxi = 0;

        for(int i=1;i<=n;i++){
            int[] curr = new int[m+1];
            for(int j=1;j<=m;j++){

                if(nums1[i-1]==nums2[j-1]){
                    curr[j] = 1+pre[j-1];
                    maxi = Math.max(maxi, curr[j]);
                }
            }

            int[] temp =curr;
            curr = pre;
            pre = temp;
        }

        return maxi;
    }

    int lcs(int[] nums1, int[] nums2){

        // Tabulation
        // TC : O(N*M)
        // SC : O(N*M)

        int n = nums1.length;
        int m = nums2.length;

        int[][] dp = new int[n+1][m+1];

        int maxi = 0;

        for(int i=1;i<=n;i++){

            for(int j=1;j<=m;j++){

                if(nums1[i-1]==nums2[j-1]){
                    dp[i][j] = 1+dp[i-1][j-1];
                    maxi = Math.max(maxi, dp[i][j]);
                }
            }
        }

        return maxi;
    }
}