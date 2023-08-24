class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        
        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        int root = -1;

        for(int i=0;i<n;i++){

            if(manager[i]==-1){

                root = i;
            }else {

                adj.get(manager[i]).add(i);
            }

        }

        int[] max = new int[1];

        dfs(root,0, adj, informTime,max);

        return max[0];

    }

    private void dfs(int root,int time ,List<List<Integer>> adj , int[] informTime, int[] max){

        if(adj.get(root).size()==0){

            max[0] = Math.max(max[0], time);
            return ;
        }

        for(int next : adj.get(root)){

            dfs(next, time+informTime[root] , adj, informTime, max);
        }

    }
}