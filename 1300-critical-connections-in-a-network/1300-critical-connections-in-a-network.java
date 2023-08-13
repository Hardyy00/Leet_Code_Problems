class Solution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(List<Integer> row : connections)
        {
            adj.get(row.get(0)).add(row.get(1));
            adj.get(row.get(1)).add(row.get(0));
        }

        int[]  visitTime = new int[n];
        int[]  low = new int[n];
        int[] inc = new int[1]; 

        List<List<Integer>> ans = new ArrayList<>();

        dfs(0,-1, adj, visitTime, low, inc, ans);

        return ans;
    }

    private void dfs(int node, int parent, List<List<Integer>> adj, int[] visitTime, int[] low,int[] inc,List<List<Integer>> ans){

        visitTime[node] = low[node] = ++inc[0];

        for(int it : adj.get(node)){

            if(it==parent) continue;

            // not visited
            if(visitTime[it]==0){

                dfs(it, node,adj, visitTime, low, inc, ans );

                low[node] = Math.min(low[node], low[it]);

                if(visitTime[node] < low[it]){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(node);
                    temp.add(it);
                    ans.add(temp);
                }
            } else if(visitTime[it]!=0){

                low[node] = Math.min(low[node], low[it]);
            }
        }
    }
}