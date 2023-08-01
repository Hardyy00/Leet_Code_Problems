class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {

        // IF A NODE IS A PART OF A CYCLE OR IS CONNECTED TO A NODE THAT IS A PART OF A CYCLE , THEN IT 
        //  cannot be a safe node, therefore finding nodes , which are part of a cycle or is connected to 
        // a cycle 
        // when we find a cycle we do not unmark them , so that in that way we can know what nodes are not
        // safe node (are part of cycle)

        // using simple cycle detection in directed graph 

        //  TC : O(V) + O(V + E) + O(V) (loop + loop + overall dfs)
        // SC : O(2V) + O(V)

        int n = graph.length;
        boolean[] visit = new boolean[n];
        int[] pathVisit = new int[n];

        for(int i=0;i<n;i++){

            if(!visit[i]){

                dfs(i,visit,pathVisit,graph);
            }
        }

        List<Integer> list = new ArrayList<>();

        // if path visit is 0, that means we weren't able to find a cycle in that path
        // hence its a safe node
        for(int i=0;i<n;i++){

            if(pathVisit[i]==0) list.add(i);
        }

        return list;
    }

    private boolean dfs(int node, boolean[] visit, int[] pathVisit,int[][] graph){

        visit[node] = true;
        pathVisit[node] = 1;

        for(int adjacent : graph[node]){

            if(!visit[adjacent]){
                
                // if a cycle exist then return immediately, without making pathvisit = 0   
                if( dfs(adjacent, visit,pathVisit,graph) ) return true;

                // cycle exist
            } else if(pathVisit[adjacent]==1) return true;

        }

        pathVisit[node] = 0;    // this path has been visited
        return false;
    }
}