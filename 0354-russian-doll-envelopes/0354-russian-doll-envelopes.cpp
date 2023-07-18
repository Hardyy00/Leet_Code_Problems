class Solution {
    static int comp(vector<int> &v1, vector<int> &v2){

       if(v1[0]==v2[0]) return v2[1] < v1[1];

       return v1[0] < v2[0];
    }
public:
    int maxEnvelopes(vector<vector<int>>& env) {

        // TC : O(N*Log N)
        // SC : O(max cards)

        sort(env.begin(), env.end(), comp);
        
        int n = env.size();

        return solve(n, env);
    }

    int solve(int n,vector<vector<int>> &env){

        vector<int> ans;

        ans.push_back(env[0][1]);

        for(int i=1;i<n;i++){

            if(env[i][1] > ans.back()) ans.push_back(env[i][1]);
            else{

                int index = lower_bound(ans.begin(), ans.end(),env[i][1]) - ans.begin();

                ans[index] = env[i][1];
            }
        }

        return ans.size();
    }

    
};