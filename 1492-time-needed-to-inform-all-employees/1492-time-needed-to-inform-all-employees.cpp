class Solution {

private:
    void dfs(int root, int time, vector<int> adj[] , vector<int> &informTime, int maxi[]){

        if(adj[root].size()==0) {

            maxi[0] = max(maxi[0], time);
            return ;
        }

        for(auto &next : adj[root]){

            dfs(next, time + informTime[root], adj, informTime, maxi);
        }

    }
public:
    int numOfMinutes(int n, int headID, vector<int>& manager, vector<int>& informTime) {
        
        vector<int> adj[n];

        for(int i=0;i< n;i++){

            if(manager[i]!=-1){

                adj[manager[i]].push_back(i);
            }
        }


        int maxi[1]{0};

        dfs(headID, 0, adj, informTime, maxi);

        return maxi[0];
    }

    
    
};