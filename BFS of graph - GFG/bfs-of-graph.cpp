//{ Driver Code Starts
#include <bits/stdc++.h>
using namespace std;

// } Driver Code Ends
class Solution {
  public:
    // Function to return Breadth First Traversal of given graph.
    vector<int> bfsOfGraph(int v, vector<int> adj[]) {
        
        // TC : O(V+2E)     (v is for outer loop, because we are visiting all the node and inner loop is 2E  
        // because for every node we are looping to its degree and the total degree of a graph is 2E)
        // SC : O(V) + O(V) (queue and visit array)
        
        bool visit[v]{false};
        
        queue<int> queue;
        
        vector<int> ans;
        
        queue.push(0);
        visit[0] = true;
        
        while(!queue.empty()){
            
            int node = queue.front();
            queue.pop();
            vector<int> neighbors = adj[node];
            
            ans.push_back(node);
            
            for(int i : neighbors){
                
                if(!visit[i]){
                    
                    visit[i] = true;
                    queue.push(i);
                }
            }
            
            
        }
        
        return ans;
    }
};

//{ Driver Code Starts.
int main() {
    int tc;
    cin >> tc;
    while (tc--) {
        int V, E;
        cin >> V >>

            E;

        vector<int> adj[V];

        for (int i = 0; i < E; i++) {
            int u, v;
            cin >> u >> v;
            adj[u].push_back(v);
            // 		adj[v].push_back(u);
        }
        // string s1;
        // cin>>s1;
        Solution obj;
        vector<int> ans = obj.bfsOfGraph(V, adj);
        for (int i = 0; i < ans.size(); i++) {
            cout << ans[i] << " ";
        }
        cout << endl;
    }
    return 0;
}
// } Driver Code Ends