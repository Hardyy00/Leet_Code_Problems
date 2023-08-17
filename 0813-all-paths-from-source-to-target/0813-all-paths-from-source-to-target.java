class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        
        List<List<Integer>> ans = new ArrayList<>();


        dfs(0,graph, new ArrayList<>(), ans);

        return ans;
    }


    private void dfs(int node, int[][] graph, List<Integer> list, List<List<Integer>> ans){

        list.add(node);

        if(node==graph.length-1){
            ans.add(new ArrayList<>(list));
            list.remove(list.size()-1);
            return ;
        }

        for(int adj : graph[node]){

            dfs(adj, graph, list, ans);
        }

        list.remove(list.size()-1);
    }
}