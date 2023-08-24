class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {

        // Using DFS to get to the leaf nodes  (consider time taken to reach a employee be 
        // the edge weight)
        // TC : O(2N)
        // SC : O(N + N-1) (atmost there can be n-1 edges in case of proper tree) + O(H)
        
        // maximum of all the times to reach the leaf node is the answer

        // create a adjacency list to know who is the manager of who
        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());


        for(int i=0;i<n;i++){
            
            // if there exist no manager , then i is the root node
            if(manager[i] !=-1){

               // manager[i] is the manager of i 
                adj.get(manager[i]).add(i);
            }
        }

        int[] max = new int[1];
        
        // time taken to reach the root is 0 
        dfs(headID,0, adj, informTime,max);

        return max[0];

    }

    private void dfs(int root,int time ,List<List<Integer>> adj , int[] informTime, int[] max){

        // when we get to the leaf node take maximum of the distance
        if(adj.get(root).size()==0){

            max[0] = Math.max(max[0], time);
            return ;
        }

        for(int next : adj.get(root)){

            // time taken to reach the next node from current root is informTime[root]
            dfs(next, time+informTime[root] , adj, informTime, max);
        }

    }
}