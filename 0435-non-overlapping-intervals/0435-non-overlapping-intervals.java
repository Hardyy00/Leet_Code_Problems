class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {

        Arrays.sort(intervals, new Comparator<int[]>(){

            @Override
            public int compare(int[] a1, int[] a2){

                return a1[0]-a2[0];
            }
        });

        int n = intervals.length;

        // int[] dp = new int[n];

        // Arrays.fill(dp,-1);

        // return solve(0,intervals, dp, n);

        return solve2(intervals, n);
        
    }

    int solve2(int[][] intervals, int n){

        // Tabulation
        // TC : O(N*LogN)
        // SC : O(N)

        int[] dp = new int[n+1];

        for(int i=n-1;i>=0;i--){

            int nextIndex = lowerBound(intervals, intervals[i][1]);

            int consider = 0;

            if(nextIndex==-1){
                consider = n-1-i + dp[n];
            }else{
                consider = nextIndex - i -1 + dp[nextIndex];
            }

            int notConsider = 1+ dp[i+1];

            dp[i] = Math.min(consider, notConsider);
        }

        return dp[0];
    }

    int solve(int index, int[][] intervals, int[] dp, int n){

        // Memoization
        // TC : O(N*Log N)
        // SC : O(N)

        if(index==n) return 0;

        if(dp[index]!=-1) return dp[index];

        // find the next index such that intervals[index][1] <=intervals[nextIndex][1]
        int nextIndex = lowerBound(intervals, intervals[index][1]);

        int consider = 0;

        // no such interval was found, so delete all the remaining intervals
        if(nextIndex==-1){
            consider = (n-1-index) + solve(n,intervals, dp,n);
        }else{

            // delete all the between intervals
            consider = (nextIndex-index-1) + solve(nextIndex,intervals,dp,n);
        }

        // delete this interval, and move forward
        int notConsider = 1 + solve(index+1, intervals, dp, n);

        return dp[index]= Math.min(consider, notConsider);
    }

    int lowerBound(int[][] intervals,int val){
        
        
        int low = 0;
        int high = intervals.length-1;

        int ans = -1;

        while(low<=high){

            int mid = high + (low-high)/2;

            if(intervals[mid][0]>=val){
                ans = mid;
                high = mid-1;
            }else{

                low = mid+1;
            }
        }

        return ans;
    }


}