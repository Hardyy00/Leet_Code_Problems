class Solution {
    public int[] gardenNoAdj(int n, int[][] paths) {

        if(paths.length==0){

            int[] ans = new int[n];

            Arrays.fill(ans,1);
        }
        
        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] row : paths){

            adj.get(row[0]-1).add(row[1]-1);

            adj.get(row[1]-1).add(row[0]-1);
        }

        int[] ans = new int[n];

        for(int i=0;i<n;i++){

            if(ans[i]==0) dfs(i, ans,adj);
        }

        return ans;
    }

    private void dfs(int node, int[] ans, List<List<Integer>> adj){

        boolean[] planted = new boolean[4];

        for(int i : adj.get(node)){

            if(ans[i]!=0){

                planted[ans[i]-1] = true;
            }
        }

        int notPlanted = -1;

        for(int i=0;i<4;i++){

            if(!planted[i]) {

                notPlanted = i+1;
                break;
            }
        }

        ans[node] = notPlanted;

        for(int i : adj.get(node)){

            if(ans[i]==0){
                dfs(i, ans, adj);
            }
        }
    }
}