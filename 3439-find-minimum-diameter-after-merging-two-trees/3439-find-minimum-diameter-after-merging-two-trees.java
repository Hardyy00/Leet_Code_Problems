class Solution {
    int dia =0;
    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {

        int n = edges1.length+1, m = edges2.length+1;

        List<List<Integer>> adj1 =new ArrayList<>() , adj2 = new ArrayList<>();

        for(int i=0;i<n;i++){
            adj1.add(new ArrayList<>());
        }

        for(int j=0;j<m;j++){
            adj2.add(new ArrayList<>());
        }

        for(int[] row : edges1){

            adj1.get(row[0]).add(row[1]);
            adj1.get(row[1]).add(row[0]);
        }

        for(int[] row : edges2){

            adj2.get(row[0]).add(row[1]);
            adj2.get(row[1]).add(row[0]);
        }

        dfs(0,adj1,-1);

        int dia1 = dia;

        dia = 0;

        dfs(0, adj2, -1);
        int dia2 = dia;

        int connectedDia = (dia1 + 1)/2 + (dia2 + 1) / 2 + 1;

        return Math.max(dia1, Math.max(dia2, connectedDia));

        
    }

    private int dfs(int node, List<List<Integer>> adj, int parent){

        int maxLen = 0;

        for(int next : adj.get(node)){

            if(next == parent){
                continue;
            }

            int val = dfs(next, adj, node);

            dia = Math.max(dia, maxLen + val);
            maxLen = Math.max(maxLen, val);

        }

        return maxLen + 1;
    }
}