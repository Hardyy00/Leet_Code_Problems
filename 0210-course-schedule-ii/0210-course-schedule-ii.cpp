class Solution {
public:
    vector<int> findOrder(int v, vector<vector<int>>& pre) {
        
        vector<int> adj[v];

        int p = pre.size();

        for(int i=0; i<p;i++){

            adj[pre[i][1]].push_back(pre[i][0]);
        }


        vector<int> indegree(v,0);
        queue<int> queue;

        for(int i=0;i<v;i++){

            for(auto &node : adj[i]){

                indegree[node]++;
            }
        }

        for(int i=0;i<v;i++){

            if(indegree[i]==0) queue.push(i);
        }

        vector<int> ans;

        while(!queue.empty()){

            int node = queue.front();
            queue.pop();

            ans.push_back(node);

            for(auto &next : adj[node]){

                indegree[next]--;

                if(indegree[next]==0) queue.push(next);
            }
        }

        if(ans.size()==v) return ans;

        return {};
    }
};