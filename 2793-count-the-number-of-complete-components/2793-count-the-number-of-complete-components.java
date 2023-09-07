class Solution {
    public int countCompleteComponents(int n, int[][] edges) {

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

                for(int j=0;j<temp.size();j++){

                    int v1 = temp.get(j);
                    for(int k=j+1;k<temp.size();k++){
                        int v2 = temp.get(k);

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