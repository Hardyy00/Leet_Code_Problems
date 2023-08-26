class Solution {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {

        List<List<Integer>> adj = new ArrayList<>();

        boolean[][] mat = new boolean[n][n];
        int[] indegree = new int[n];

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] row : edges){

            adj.get(row[0]).add(row[1]);
            mat[row[0]][row[1]] = true;

            indegree[row[1]]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i=0;i<n;i++){

            if(indegree[i]==0) q.offer(i);
        }

        while(!q.isEmpty()){

            int node = q.poll();

            for(int next : adj.get(node)){

                indegree[next]--;

                for(int i=0;i<n;i++) if( mat[i][node] ) mat[i][next] = true;

                if(indegree[next]==0) q.offer(next);
            }
        }


        List<List<Integer>> ans = new ArrayList<>();

        for(int i=0;i<n;i++) ans.add(new ArrayList<>());

        for(int i=0;i<n;i++){

            for(int j=0;j<n;j++){


                if(mat[i][j]){

                    ans.get(j).add(i);
                }
            }
        }


        return ans;


        
    }
}