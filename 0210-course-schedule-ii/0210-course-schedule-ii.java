class Solution {
    public int[] findOrder(int numCourses, int[][] pre) {
        
        // TC : O(V) + O(P) +( O(V) + O(V+E) ) + O(V) + O(V) + O(V+E) === O(V+E)
        // SC : O(V) + O(V) (BECAUSE after check cycle O(2V) for visit and pathVisit, is deallocated) 
        int v = numCourses;
        int p = pre.length;

        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<v;i++){

            adj.add(new ArrayList<>());
        }

        for(int i=0;i<p;i++){

            adj.get(pre[i][1]).add(pre[i][0]);
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

        if(i==v) return ans;

        return new int[]{};
    }
}