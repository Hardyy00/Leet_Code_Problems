class Solution {
    public long countPairs(int n, int[][] edges) {

        // if(edges.length == 0) return ;

        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] row : edges){

            adj.get(row[0]).add(row[1]);
            adj.get(row[1]).add(row[0]);
        }

        List<Integer> sizes = new ArrayList<>();
        boolean visit[] = new boolean[n];

        for(int i =0;i<n;i++){

            if(visit[i] == false){

                int[] counter = {0};
                dfs(i, visit, adj, counter);

                sizes.add(counter[0]);
            }
        }        

        if( sizes.size() ==1 ) return 0;
        
        int len = sizes.size()-1;
        int[] suffix = new int[len];
    
        suffix[len-1] = sizes.get(sizes.size()-1);
        int index = sizes.size()-2;

        for(int i=len-2;i>=0;i--){

            suffix[i] = suffix[i+1] + sizes.get(index--);
        }

        long pairs = 0;

        for(int i=0;i<suffix.length;i++){

            pairs += (long)sizes.get(i) * (long)suffix[i];
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

