class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {

        List<List<Integer>> adj = new ArrayList<>();

        int n = quiet.length;

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        int[] indegree = new int[n];
        Queue<Integer> q = new LinkedList<>();
        int ans[] = new int[n];

        for(int[] row : richer){

            adj.get(row[0]).add(row[1]);
            indegree[row[1]]++;
            
        }

        for(int i=0;i<n;i++){

            ans[i] = i;

            if(indegree[i]==0) q.offer(i);
        }

        while(!q.isEmpty()){

            int node = q.poll();

            for(int next : adj.get(node)){

                if(quiet[ans[node]] < quiet[ans[next]]){

                    ans[next] = ans[node];
                }

                indegree[next]--;

                if(indegree[next]==0) q.offer(next);
            }
        }

        return ans; 
        
    }
}