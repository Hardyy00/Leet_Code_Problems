class Solution {
    public int[] findOrder(int numCourses, int[][] pre) {
        
        int v = numCourses;
        int p = pre.length;

        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<v;i++){

            adj.add(new ArrayList<>());
        }

        for(int i=0;i<p;i++){

            adj.get(pre[i][1]).add(pre[i][0]);
        }

        if( checkCycle(v,adj) ){
            return new int[]{}; // empty array
        }

        int[] indegree = new int[v];
        Queue<Integer> queue = new LinkedList<>();
        
        for(int i=0;i<v;i++){

            for(int node : adj.get(i)){

                indegree[node]++;
            }
        }

        for(int i=0;i<v;i++){

            if(indegree[i]==0) queue.offer(i);
        }

        int[] ans = new int[v];
        int i = 0;

        while(!queue.isEmpty()){

            int node = queue.poll();

            ans[i++] = node;

            for(int next : adj.get(node)){

                indegree[next]--;

                if(indegree[next]==0) queue.offer(next);
            }
        }

        return ans;
    }

    private boolean checkCycle( int v, List<List<Integer>> adj ){

        boolean[] visit = new boolean[v];
        int[] pathVisit = new int[v];

        for(int i=0;i<v;i++){

            if(!visit[i]){

                if( dfs(i,visit,pathVisit,adj) ) return true;

            }
        }

        return false;
    }

    private boolean dfs( int node, boolean[] visit, int[] pathVisit, List<List<Integer>> adj ){

        visit[node] = true;
        pathVisit[node] = 1;

        for(int next : adj.get(node)){

            if(!visit[next]){

                if( dfs(next, visit, pathVisit,adj )) return true;

            } else if( pathVisit[next]==1 ) return true;
        }

        pathVisit[node] = 0;
        return false;
    }
}