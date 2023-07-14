class Solution {
    public int maxCoins(int[] nums) {
        
        // Instead of bursting each ballons one by one, we do a bottom up approach, suppose we have burst 
        // all balloons, we have left with portion (1 to n-2) , we have deleted any of them, now think i
        // backward way, suppose you have array 1 2 3 4 5 , all the ballons have been bursted 
        // now suppose in the end you burst 3, now array contains [3] and the coins would be 1 * 3 * 1 (nums[i-1])
        // and nums[j+1] of range i to j, means there are not ballon in the range., now you are left with 
        // subproblems 1 2 & 4 5 and you can solve them individually, suppose , after before 3 you delete either
        // 4 or 1 , in both the cases you calculate it with the help of 3, they are not depedfent on each other

        int n = nums.length;

        int[] arr = new int[n+2];

        // inserting 1 at index , 0 and n-1, so that we can get i-1 and j+1 , when the range is b/w 1 to n-2
        arr[0]=arr[arr.length-1]=1;

        // old array will be between 1 to n-1 indexes
        for(int i=1;i<arr.length-1;i++){
            arr[i]= nums[i-1];
        }

        // return solve(1,arr.length-2,arr);

        int size = n+2;
        // int[][] dp = new int[size][size];

        // for(int[] row : dp) Arrays.fill(row , -1);

        // return solve2(1, size-2,arr, dp);

        return solve3(arr);
    }

    int solve3(int[] nums){

        // Tabulation
        // TC  : O(N*N*N)
        // SC : O(N*N) + O(N)

        int n = nums.length;

        int[][] dp = new int[n][n];

        for(int i=n-2;i>=1;i--){

            for(int j=i;j<=n-2;j++){

                int maxi = 0;

                for(int k=i;k<=j;k++){

                    int coins = nums[i-1] * nums[k] * nums[j+1] + dp[i][k-1] + dp[k+1][j];

                    maxi = Math.max(maxi, coins);
                }

                dp[i][j] = maxi;
            }
        }

        return dp[1][n-2];
    }

    int solve2(int i, int j, int[] nums, int[][] dp){

        // Memoization
        // TC : O(N*N*N) (n= nums.length)
        // SC : O(N*N) + O(N) + O(N)   (one for recusion stack & one for new nums array)

        if(i>j) return 0;

        if(dp[i][j]!=-1) return dp[i][j];

        int maxi = 0;
        for(int k=i;k<=j;k++){

            int cost = nums[i-1] * nums[k] * nums[j+1] + solve2(i, k-1,nums, dp) + solve2(k+1, j, nums,dp);

            maxi = Math.max(maxi, cost);
        }


        return dp[i][j] = maxi;
    }

    int solve(int i, int j, int[] nums){

        // TC : Exponential
        // SC : O(nums.length) + O(nums.length) (one for recusion stack & one for new nums array)

        if(i>j) return 0;

        int maxi = 0;

        for(int k=i;k<=j;k++){

            int coins = nums[i-1]*nums[k]*nums[j+1] + solve(i,k-1, nums) + solve(k+1,j,nums);

            maxi = Math.max(maxi, coins); 
        }

        return maxi;
    }
}