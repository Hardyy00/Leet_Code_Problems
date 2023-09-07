class Solution {
    public int countCompleteComponents(int n, int[][] edges) {

        // return solve(n, edges);

        return solve2(n, edges);
        
        
    }

    private int solve2(int n, int[][] edges){

        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] row : edges){

            adj.get(row[0]).add(row[1]);
            adj.get(row[1]).add(row[0]);
            
        }

        boolean[] visit = new boolean[n];
        int count = 0;

        for(int i=0;i<n;i++){

            if( !visit[i] ){

                int[] nodes = {0};
                int[] edge = {0};

                dfs2(i,adj, visit, nodes, edge);

                int totEdges = nodes[0] * (nodes[0]-1);

                if(totEdges == edge[0]) count++;
            }
        }

        return count;


    }

    private void dfs2(int node, List<List<Integer>> adj,boolean[] visit, int[] nodes, int[] edges){

        visit[node] = true;

        nodes[0]++;

        for(int it : adj.get(node)){

            edges[0]++;

            if(!visit[it]){
                dfs2(it, adj, visit, nodes, edges);
            }
        }
    }

    

    private int solve(int n, int[][] edges){

        // getting every node  in a component , and checking is each node, is connected
        // to every other node with the help of adj Matrix

        // TC : O(n + E) + o(N + E) + o(N*N) 
        // sc : o(N + E) + O(N * N) + O(N) + O(N)

        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        boolean[][] adjMat = new boolean[n][n];

        for(int[] row : edges){

            adj.get(row[0]).add(row[1]);
            adj.get(row[1]).add(row[0]);
            
            adjMat[row[0]][row[1]] = true;
            adjMat[row[1]][row[0]] = true;
        }
        
        
        boolean[] visit = new boolean[n];
        int count = 0;  

        for(int i=0;i<n;i++){

            if(!visit[i]){
                List<Integer> temp = new ArrayList<>();

                dfs(i, adj, temp, visit);

                boolean isComplete = true;

                // CHECK for every pair , if they are connected
                for(int j=0;j<temp.size();j++){

                    int v1 = temp.get(j);
                    for(int k=j+1;k<temp.size();k++){
                        int v2 = temp.get(k);

                        // THERE IS atleast pair that is not connected
                        if(adjMat[v1][v2] == false){
                            isComplete = false;
                            break;
                        }
                    }
                }

                if(isComplete) count++;
            }

        }

        return count;
    }

    private void dfs(int node, List<List<Integer>> adj, List<Integer> temp,boolean[] visit){

        visit[node] = true;
        temp.add(node);

        for(int it : adj.get(node)){

            if(!visit[it]){

                dfs(it, adj, temp, visit);
            }
        }
    }
}