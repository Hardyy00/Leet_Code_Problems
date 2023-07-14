class Solution {
public:
    int minCost(int n, vector<int>& cuts) {
        
        cuts.insert(cuts.begin(), 0);
        cuts.push_back(n);

        sort(cuts.begin(), cuts.end());

        return solve(cuts);
    }

    int solve(vector<int> &cuts){

        //  
        // TC : O(cuts.length * cuts.length * cuts.length) +  O(N log N) (for sorting)
        // SC : O(cust.length * cuts.length)

        int n = cuts.size();
        vector<vector<int>> dp(n, vector<int>(n, 0));

        for(int i=n-2;i>=1;i--){

            for(int j=i;j<=n-2;j++){

                int mini = INT_MAX;
                for(int k=i;k<=j;k++){

                    int cost = cuts[j+1] -cuts[i-1] + dp[i][k-1] + dp[k+1][j];

                    mini = min(mini, cost);
                }

                dp[i][j] = mini;
            }
        }

        return dp[1][n-2];
    }
};