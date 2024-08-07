class Solution {

    int[][] dp = new int[1000][51];

    public int splitArray(int[] nums, int k) {
        

        // for(int[] row : dp){
        //     Arrays.fill(row, -1);
        // }

        // return memo(0,k,nums);

        // return iterative(k, nums);

        return binarySearch(nums,k);
    }

    private int binarySearch(int[] nums, int k){
        
        int sum = 0;

        for(int i : nums){
            sum += i;
        }

        long low = 0, high = sum;
        long ans = 0;

        while(low<=high){

            long mid = (low + high) >> 1;

            if(canSplit(mid, k, nums)){
                ans = mid;
                high = mid-1;
            }else{
                low = mid+1;
            }
        }

        return  (int)ans;
    }

    private boolean canSplit(long maxAmount, int k, int[] arr){

        int sum = 0;
        int cn = 1;
        
        for(int i=0;i<arr.length;i++){

            if(sum + arr[i] > maxAmount){

                cn++;
                sum = arr[i];

                if(cn > k || sum > maxAmount){
                    return false;
                }
            }else{
                sum += arr[i];
            }
        }

        return true;
    }

    private int iterative(int k, int[] nums ){

        int n = nums.length;
        int[][] dp = new int[n+1][k+1];

        for(int i=1;i<=k;i++){
            dp[n][i] = (int)1e9;
        }

        for(int i=0;i<n;i++){
            dp[i][0] = (int)1e9;
        }

        for(int i=n-1;i>=0;i--){

            for(int j=1;j<=k;j++){

                int sum = 0;
                int mini = Integer.MAX_VALUE;

                for(int p =i;p<n;p++){

                    sum += nums[p];
                    int val = dp[p+1][j-1];

                    mini = Integer.min(mini, Math.max(sum , val));
                }

                dp[i][j] =mini;
            }
        }

        return dp[0][k];
    }


    private int memo(int idx, int k, int[] nums){

        // just minimize the maximum splits
        // if k<=0 before last index, or at last index k !=0
        // return a big value , so that of minimization it won't get considered

        //  TC : O(N * K * N)
        // SC : O(N * K)

        if(idx==nums.length){

            return k==0 ? 0 : (int)1e9 ;
        }

        if(k <= 0){
            return (int)1e9;
        }

        if(dp[idx][k] !=-1){
            return dp[idx][k];
        }

        int sum = 0;
        int mini = Integer.MAX_VALUE;

        for(int i=idx;i<nums.length;i++){

            sum += nums[i];

            int val = memo(i+1, k-1, nums);
            mini = Math.min(mini, Math.max(sum, val)); // minimize the maximum split
            
        }

        return dp[idx][k] = mini;
    }
}