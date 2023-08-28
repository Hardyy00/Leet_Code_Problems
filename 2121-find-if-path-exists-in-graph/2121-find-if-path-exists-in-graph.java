class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        
        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] row : edges){

            adj.get(row[0]).add(row[1]);
            adj.get(row[1]).add(row[0]);
        }

        boolean[] visit = new boolean[n];

        return dfs(source, destination, visit , adj);


    }

    private boolean dfs(int node, int dst, boolean[] visit, List<List<Integer>> adj){

        if(node == dst) return true;

        visit[node] = true;

        for(int next : adj.get(node)){


            if(!visit[next]) {

                if( dfs(next, dst, visit, adj) ) return true;
            }
        }

        return false;
    }
}