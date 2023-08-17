class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        
        int[] colorVisit = new int[n+1];

        Arrays.fill(colorVisit,-1);

        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<=n;i++) adj.add(new ArrayList<>());

        for(int[] row : dislikes){

            adj.get(row[0]).add(row[1]);

            adj.get(row[1]).add(row[0]);
        }

        for(int i=1;i<=n;i++) {

            if(colorVisit[i]==-1){

                if( !dfs(i,-1,0, adj, colorVisit) ) return false;
            }
        }

        return true;
    }

    private boolean dfs(int node, int parent,int color, List<List<Integer>> adj,
                         int[] colorVisit){

        colorVisit[node]= color;

        for(int next : adj.get(node)){

            if(next==parent) continue;

            if(colorVisit[next]==-1){

                if( !dfs(next, node, color ^ 1, adj, colorVisit)) return false;

            }else if(colorVisit[next]== colorVisit[node]) return false;
        }

        return true;
    }
}