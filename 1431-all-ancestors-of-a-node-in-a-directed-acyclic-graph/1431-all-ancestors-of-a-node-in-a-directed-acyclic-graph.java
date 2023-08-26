class Solution {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {

        // Using topo sort , to make connection from ancestors to descendents
        // TC : O(N) + O(E) + O(N) + (N + E*N) + O(N^2)
        // SC : O(N + E) + O(N) + O(N^2)

        
        List<List<Integer>> adj = new ArrayList<>();

        boolean[][] mat = new boolean[n][n];  // adjacency matrix, to mark which node is an ancestor of which node
        int[] indegree = new int[n];

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] row : edges){

            adj.get(row[0]).add(row[1]);
            mat[row[0]][row[1]] = true; // row[0] is connected to 1

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

                // if a connected to b , then all the  nodes connected to a  must
                // also be connected to b

                // if mat[i][node] , i.e i is connected to node ,then i is also connected to next
                for(int i=0;i<n;i++) if( mat[i][node] ) mat[i][next] = true;

                if(indegree[next]==0) q.offer(next);
            }
        }

        // we have made all the connections
        List<List<Integer>> ans = new ArrayList<>();

        for(int i=0;i<n;i++) ans.add(new ArrayList<>());

        for(int i=0;i<n;i++){

            for(int j=0;j<n;j++){

                // if i is connected to j , then i is an ancestor of j
                // since we are moving from 0 to n-1,we will always add a smaller ancestor 
                // first (as we move from 0 to n-1, then order will be 0,1 2 ,3 ...)
                if(mat[i][j]){

                    ans.get(j).add(i);
                }
            }
        }


        return ans;
    }
}