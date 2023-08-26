class Pair{

    int node;
    boolean isFake;

    public Pair(int node, boolean isFake){

        this.node =node;
        this.isFake = isFake;
    }
}

class Solution {
    public int minReorder(int n, int[][] connections) {
        
        List<List<Pair>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] row : connections){


            adj.get(row[0]).add(new Pair(row[1], false));
            adj.get(row[1]).add(new Pair(row[0], true));
        }

        int[] counter  = {0};

        boolean[] visit = new boolean[n];

        dfs(0, visit, adj, counter);

        return counter[0];
        
    }

    private void dfs(int node, boolean[] visit, List<List<Pair>> adj , int[] counter){

        visit[node] = true;

        for(Pair pair : adj.get(node)){

            if(!visit[pair.node]){

                if(pair.isFake == false) counter[0]++;

                dfs(pair.node, visit, adj, counter);
            }
        }
    }
}