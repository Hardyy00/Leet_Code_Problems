class Solution {
public:
    int longestSubsequence(vector<int>& arr, int difference) {
        
        // return las(0,-1,difference,arr);

        int n = arr.size();

        // vector<vector<int>> dp(n, vector<int>(n+1, -1));

        // return las2(0,-1,difference,arr,dp);

        // return las3(n, difference, arr);

        return las4(n, difference, arr);
    }

    int las4(int n, int diff, vector<int> &arr){

        // we calculate , at every index, what can be the length of the largest subsequence 
        // having arr[i] as its last element, since suppose you are what can be the length
        // of las with 5 as it last element , when the diff is 2, for a subsequence to ocuur
        // 3 must be in the array (5-2=3), only then we can make a subsequence, therefore
        // we need to make only one call of dp[arr[i]-k] , but arr[i] can be negative
        // we take tabulation

        // Tabulation
        // TC : O(N)
        // SC : O(N)

        unordered_map<int, int> dp;

        int maxi = 0;
        for(int i=0;i<n;i++){

            dp[arr[i]] = 1 + dp[arr[i]-diff];

            maxi = max(maxi, dp[arr[i]]);
        }

        return maxi;
    }

    int las3(int n,int diff,vector<int> &arr){


        // Tabulation
        // TC : O(N*N)
        // SC : O(N)

        vector<int> dp(n, 1);

        int maxi = 0;
        for(int i=0;i<n;i++){

            for(int j=0;j<i;j++){

                if(arr[i]-arr[j]==diff && 1+dp[j]>dp[i]){

                    dp[i] = 1+dp[j];
                }
            }

            maxi = max(maxi,dp[i]);
        }

        return maxi;
    }

    int las2(int index, int preIndex, int diff, vector<int> &arr, vector<vector<int>> &dp){

        // Memoization (TLE)
        // TC : O(N*N)
        // SC : O(N*N) + O(N)

        if(index==arr.size()) return 0;

        if(dp[index][preIndex+1]!=-1) return dp[index][preIndex+1];

        int take = 0, notTake = 0;

        if(preIndex==-1 || arr[index]-arr[preIndex]==diff){

            take = 1+ las2(index+1, index, diff, arr, dp);
        }

        notTake = las2(index+1, preIndex, diff, arr, dp);


        

        return dp[index][preIndex+1] = max(take, notTake);
    }

    int las(int index, int preIndex, int diff,vector<int>&arr){

        // Recursion
        // TC : O(2^N)
        // SC : O(N)

        if(index==arr.size()) return 0;
  

        int take = 0;
        if(preIndex==-1 || arr[index]-arr[preIndex]==diff){
            take = 1 + las(index+1, index, diff, arr);

        }


        int notTake = las(index+1, preIndex, diff, arr);

        return max(take, notTake);
    }


};