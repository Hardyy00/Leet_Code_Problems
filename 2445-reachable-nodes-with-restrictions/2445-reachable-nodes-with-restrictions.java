class Solution {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {

        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] row : edges){

            adj.get(row[0]).add(row[1]);
            adj.get(row[1]).add(row[0]);
        }

        boolean[] visit = new boolean[n];

        for(int i : restricted) visit[i] = true;

        int[] counter = new int[1];

        dfs(0,visit,adj, counter);

        return counter[0];
    }

    private void dfs(int node,boolean[] visit, List<List<Integer>> adj, int[] counter){

        visit[node] = true;

        counter[0]++;

        for(int it : adj.get(node)){

            if(!visit[it]){

                dfs(it, visit,adj, counter);
            }
        }
    }
}