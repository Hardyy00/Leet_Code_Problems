class Solution {
    public int findLongestChain(int[][] pairs) {

        // since you can take pairs from anywhere, sort them so they lineup in increasing fashion
        int n = pairs.length;
        Arrays.sort(pairs, new Comparator<int[]>(){

            @Override
            public int compare(int[] arr1, int[] arr2){

                return arr1[1] - arr2[1];
            }
        });

        // int[][] dp = new int[n][n+1];

        // for(int[] row : dp) Arrays.fill(row, -1);

        // return lis(0,-1,pairs, dp);

        // return lis2(n, pairs);

        return lis4(n, pairs);
    }

    int lis4(int n, int[][] pairs){

        // Greedy
        // TC : O(N)
        // SC : O(1)

        // always exposing a smaller tail, so that it has maximum chances of making a pair
        // therefore, sorting acc. to pair[1]

        int[] tail = pairs[0];
        int count = 1;
        for(int i=1;i<n;i++){
            
            // when finding a pair , attach it
            if(tail[1] < pairs[i][0]){
                tail = pairs[i];
                count++;
            }
        }


        return count;


    }

    int lis3(int n, int[][] pairs){

        // TC : O(N*N)
        // SC : O(N)

        int[] dp = new int[n];

        int maxLen = 0;

        for(int i=0;i<n;i++){

            dp[i] = 1;

            for(int j=0;j<i;j++){

                if(pairs[j][1] < pairs[i][0] && 1+dp[j] > dp[i]){
                    
                    dp[i] = 1+dp[j];
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    int lis2(int n, int[][] pairs){


        // Tabulation
        // TC : O(N*N)
        // SC : O(N*N)

        int[][] dp = new int[n+1][n+1];

        for(int i=n-1;i>=0;i--){

            for(int preIndex=i-1; preIndex >= -1; preIndex--){

                int notTake = dp[i+1][preIndex+1];

                int take = 0;

                if(preIndex==-1 || pairs[preIndex][1] < pairs[i][0]){

                    take = 1 + dp[i+1][i+1];
                }

                dp[i][preIndex+1] = Math.max(take ,notTake);
            }
        }

        return dp[0][0];
    }

    int lis(int index, int preIndex, int[][] pairs, int[][] dp){

        // Memoization
        // TC : O(N*N)
        // SC : O(N*N) + O(N)

        if(index==pairs.length) return 0;

        // Shifting index by 1
        if(dp[index][preIndex+1]!=-1) return dp[index][preIndex+1];

        int take = 0;
        if(preIndex==-1 || pairs[preIndex][1] < pairs[index][0]){

            take = 1+ lis(index+1, index, pairs, dp);
        }

        int notTake = lis(index+1, preIndex, pairs, dp);

        return dp[index][preIndex+1] = Math.max(take , notTake);
    }
}