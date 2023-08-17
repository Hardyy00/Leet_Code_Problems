class Solution {
public:
    vector<int> loudAndRich(vector<vector<int>>& richer, vector<int>& quiet) {
        
        int  n = quiet.size();
        vector<int> adj[n];

        vector<int> indegree(n,0);

        vector<int> ans(n,0);

        queue<int> q;

        for(auto row : richer){

            adj[row[0]].push_back(row[1]);

            indegree[row[1]]++;
        }

        for(int i=0;i<n;i++){

            ans[i]= i;

            if(indegree[i]==0) q.push(i);
        }

        while(!q.empty()){

            int node = q.front();

            q.pop();

            for(auto next : adj[node]){

                if(quiet[ans[node]] < quiet[ans[next]] ){

                    ans[next] = ans[node];
                }

                indegree[next]--;

                if(indegree[next]==0) q.push(next);
            }
        }

        return ans;
    }
};