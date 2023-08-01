class Solution {
    public boolean canFinish(int numCourses, int[][] pre) {
        
        List<List<Integer>> adj = new ArrayList<>();
        int n = numCourses;

        // we need to know , if we can complete all the courses, if a cycle exists, the  we will never be 
        // able to complete the courses,
        // hence finding a cycle in a directed graph

        // clearly, pre[i][0] , is dependent on pre[i][1], hence making edge b/w them
        for(int i=0;i<n;i++){

            adj.add(new ArrayList<>());
        }

        for(int i=0;i<pre.length;i++){
            
            // an edge exists b/w pre[i][1] and pre[i][0]
            adj.get(pre[i][1]).add(pre[i][0]);
        }

        // return toposort(n, pre,adj);

        int[] pathVisit = new int[n];
        boolean[] visit = new boolean[n];

        for(int i=0;i<n;i++){

            if(!visit[i]){
                
                // if cycle exists, then return false
                if( dfs(i,visit,pathVisit,adj) ) return false;
            }
        }

        return true;

    }

    private boolean dfs(int node, boolean[] visit, int[] pathVisit, List<List<Integer>> adj){

        visit[node] = true;
        pathVisit[node] = 1;

        for(int next : adj.get(node)){

            if(!visit[next]){

                if( dfs(next,visit,pathVisit, adj) ) return true;

            } else if(pathVisit[next]==1) return true;

        }


        pathVisit[node] = 0;
        return false;
    }

    private boolean toposort(int n, int[][] pre, List<List<Integer>> adj){

        // Find a cycle with toposort with bfs, or khan's algo

        // TC : O(N) + O(pre.length) + O(N+E) + O(N) + O(N+E)
        // SC : O(V+E) + O(V) + O(V)

        Queue<Integer> queue = new LinkedList<>();

        int[] indegree = new int[n];

        for(int i=0;i<n;i++){

            for(int node : adj.get(i)){

                indegree[node]++;
            }
        }

        int notZero = 0;

        for(int i=0;i<n;i++){

            if(indegree[i]==0) queue.offer(i);

            else notZero++;
        }

        if(queue.isEmpty()) return false;   // cycle exist so we will not be able to complete

        while(!queue.isEmpty()){

            int node = queue.poll();

            for(int next : adj.get(node)){

                indegree[next]--;

                if(indegree[next]==0){

                    queue.offer(next);
                    notZero--;
                }
            }
        }

        System.out.println(Arrays.toString(indegree));

        if(notZero==0) return true;     // cycle doesn't exists, every node's degree has become zero

        return false;
    }
}