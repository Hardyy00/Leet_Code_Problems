class Solution {
    long fuel = 0;
    public long minimumFuelCost(int[][] roads, int seats) {
        
       int n = roads.length+1;
       List<List<Integer>> adj = new ArrayList<>();

       for(int i=0;i<n;i++) adj.add(new ArrayList<>());

       for(int[] row : roads) {

           adj.get(row[0]).add(row[1]);
           adj.get(row[1]).add(row[0]);
       }

        boolean[] visit =new boolean[n];

        dfs(0,seats,adj, visit);

        return fuel;

    }

    private long dfs(int node,int seats, List<List<Integer>> adj , boolean[] visit){

        visit[node] = true;
        int count = 1;

        for(int i : adj.get(node)){

            if(!visit[i]){
                count += dfs(i,seats, adj, visit);
            }
        }

        if(node!=0){

            fuel += (long) (count%seats == 0 ? count / seats : count/seats + 1); 
        }

        return count;


    }
}