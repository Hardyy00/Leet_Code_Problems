class Solution {
public:
    int findCheapestPrice(int n, vector<vector<int>>& flights, int src, int dst, int k) {
        
        vector<pair<int, int>> adj[n];

        for(auto it : flights){

            adj[it[0]].push_back({it[1], it[2]});

        }

        vector<int> dis(n, 1e8);

        // {{node, dis}, stops}
        queue<pair<pair<int, int>, int>> q;

        dis[src] = 0;
        q.push( {{src,0}, 0} );

        while(!q.empty()){

            int node = q.front().first.first;
            int cost = q.front().first.second;
            int stops = q.front().second;

            q.pop();

            if(node == dst && stops <= k+1) continue;
            else if(stops >= k+1) break;

            for(auto it : adj[node]){

                int adjNode = it.first;
                int currCost = it.second;

                if(cost + currCost < dis[adjNode]){

                    dis[adjNode] = cost + currCost;

                    q.push({{adjNode, dis[adjNode]} , stops+1 }); 
                }  
            }
        }

        return dis[dst] == 1e8 ? -1 : dis[dst];
    }
};