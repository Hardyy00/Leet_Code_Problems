//{ Driver Code Starts
#include <bits/stdc++.h>
using namespace std;

// } Driver Code Ends
class Solution {
    
    
  public:
    // Function to return a list containing the DFS traversal of the graph.
    vector<int> dfsOfGraph(int v, vector<int> adj[]) {
        
        bool visit[v]{false};
        
        vector<int> ans;
        
        dfs(0,visit,adj,ans);
        
        return ans;
    }
    
    void dfs(int node,bool visit[],vector<int> adj[], vector<int> &ans){
        
        // TC : O(V + 2E) 
        // SC : O(V)
        
        visit[node] = true;     // we have visited this node so it is true
        
        ans.push_back(node);
        
        // traverse through all the neighbors, if they are not visited
        for(auto &i : adj[node]){
            
            if(!visit[i]){
                
                dfs(i,visit,adj,ans);
            }
        }
    }
};

//{ Driver Code Starts.
int main() {
    int tc;
    cin >> tc;
    while (tc--) {
        int V, E;
        cin >> V >> E;

        vector<int> adj[V];

        for (int i = 0; i < E; i++) {
            int u, v;
            cin >> u >> v;
            adj[u].push_back(v);
            adj[v].push_back(u);
        }
        // string s1;
        // cin>>s1;
        Solution obj;
        vector<int> ans = obj.dfsOfGraph(V, adj);
        for (int i = 0; i < ans.size(); i++) {
            cout << ans[i] << " ";
        }
        cout << endl;
    }
    return 0;
}
// } Driver Code Ends