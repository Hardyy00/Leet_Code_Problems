class Solution {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        
        Set<Integer> set = new HashSet<>();

        for(int i : restricted) set.add(i);

        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] row : edges){

            adj.get(row[0]).add(row[1]);
            adj.get(row[1]).add(row[0]);
        }

        boolean[] visit = new boolean[n];

        int[] counter = new int[1];

        dfs(0,visit,adj, counter,set);

        return counter[0];
    }

    private void dfs(int node,boolean[] visit, List<List<Integer>> adj, int[] counter, Set<Integer> set){

        visit[node] = true;

        counter[0]++;

        for(int it : adj.get(node)){

            if(!visit[it] && !set.contains(it)){

                dfs(it, visit,adj, counter, set);
            }
        }
    }
}