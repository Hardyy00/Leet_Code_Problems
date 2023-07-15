class Solution {

    static bool comp(vector<int> &v1, vector<int> &v2){

        if(v1[0]==v2[0]) return v1[1]<=v2[1];

        return v1[0] < v2[0];
    }
    
public:
    int maxValue(vector<vector<int>>& events, int k) {

        // Intution, if you attend an event, you can attend , another event only if
        // the previous endDate is less than next event start date 
        // therefore since we have to find something greater, just sort the 
        // events, according to the start date
        // but if startdates becomes equal , the ending date must be less 
        int n = events.size();

        vector<vector<int>> dp(n, vector<int>(k+1, -1));

        sort(events.begin(), events.end(), comp);


        // return solve(0, k,dp,events);

        return solve2(n, k, events);
        
    }

    int solve2(int n, int k, vector<vector<int>> &events){

        // Tabulation
        // TC : O(N*K*Log N)
        // SC : O(N*K)

        vector<vector<int>> dp(n+1, vector<int>(k+1, 0));

        for(int i=n-1;i>=0;i--){

            for(int j=1;j<=k;j++){

                int notTake = solve(i+1, j, dp, events);

                int nextIndex = binarySearch(events[i][1], i+1, events);

                int take = events[i][2] +(nextIndex==-1 ? dp[n][0] : dp[nextIndex][j-1]);

                dp[i][j] = max(notTake, take);
            }
        }

        return dp[0][k];
    }

    int solve(int index, int k, vector<vector<int>> &dp, vector<vector<int>> &events){

        // Memoization
        // TC : O(N*K*Log N)
        // SC : O(N*k) + O(N)

        // k==0 means we cannot attend any other event
        if(index==events.size() || k==0) return 0;

        if(dp[index][k]!=-1) return dp[index][k];

        // when you are not attending any event,just move forward
        int notTake = solve(index+1, k, dp, events);

        // find the index of next event whose start date is greater than current event's end date
        // if no such event exists then return -1
        int nextIndex = binarySearch(events[index][1], index+1,events);

        // if no such index exists , then make k=0 , that we cannot attend an event anymore, and pass any index
        // if you can attend , then just pass that next event index, & decrease k
        int take = events[index][2] + (nextIndex!=-1 ? solve(nextIndex,k-1,dp,events) : solve(index+1,0,dp,events));

        return dp[index][k]  = max(take, notTake);
    }

    int binarySearch(int eventEnd, int low, vector<vector<int>> &events){

        // TC : O(LogN)
        // SC : O(1)

        int high = events.size()-1;

        int ans = -1;

        while(low<=high){

            int mid = high + (low - high)/2;

            if(events[mid][0] > eventEnd){
                ans = mid;
                high = mid-1;
            }else{
                low = mid+1;
            }
        }

        return ans;

    }

};