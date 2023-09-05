class Solution {
    public long countPairs(int n, int[][] edges) {

       // getting the sizes of the components and multiplying each component
       // size with every components , to get the number of pairs
       // the component can make with other components 

       // TC : O(E) + O(N+2E) + O(C) , where c = number of components
       // sc : O(N+2E) + O(N) + O(C)  

        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] row : edges){

            adj.get(row[0]).add(row[1]);
            adj.get(row[1]).add(row[0]);
        }

        boolean visit[] = new boolean[n];
        long pairs = 0;
        long rem  = n;
          
        for(int i =0;i<n;i++){

            if(visit[i] == false){

                int[] counter = {0};  // to count nodes in every components
                dfs(i, visit, adj, counter);

                pairs += (rem-counter[0])*(long)counter[0];

                rem -= counter[0];
            }
        }    

        return pairs;    

       
    }

    private void dfs(int node, boolean[] visit, List<List<Integer>> adj, int[] counter){

        visit[node] = true;
        counter[0]++;

        for(int it : adj.get(node)){

            if(visit[it] == false){

                dfs(it, visit, adj, counter);
            }
        }
    }
}

