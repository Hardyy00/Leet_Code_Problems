class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {

        List<List<Integer>> adj = new ArrayList<>();

        int v = graph.length;

        for(int i=0;i<v;i++) adj.add(new ArrayList<>());

        int[] indegree = new int[v];
        Queue<Integer> queue = new LinkedList<>();

        for(int i=0;i<v;i++){

            for(int node : graph[i]){

                adj.get(node).add(i);
                indegree[i]++;
            }
        }

        for(int i=0;i<v;i++) {

            if(indegree[i]==0) queue.offer(i);
        }

        List<Integer> ans = new ArrayList<>();

        while(!queue.isEmpty()){

            int node = queue.poll();
            ans.add(node);

            for(int next : adj.get(node)){

                indegree[next]--;

                if(indegree[next]==0) queue.offer(next);
            }
        }

        Collections.sort(ans);

        return ans;
        
    }
}