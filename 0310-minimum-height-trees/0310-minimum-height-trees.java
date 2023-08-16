class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        if(n==1){ 

            List<Integer> temp = new ArrayList<>();
            temp.add(0);
            return temp;
        }
        
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[n];

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        for(int[] row : edges){

            adj.get(row[0]).add(row[1]);
            adj.get(row[1]).add(row[0]);

            indegree[row[0]]++;
            indegree[row[1]]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i=0;i<n;i++){
            if(indegree[i]==1) q.offer(i);
        }

        while(n>2){

            int size = q.size();

            while(size-->0){
                n--;
                int node = q.poll();

                for(int neigh : adj.get(node)){

                    indegree[neigh]--;

                    if(indegree[neigh]==1) q.offer(neigh);
                }
            }
        }

        List<Integer> list = new ArrayList<>();

        while(!q.isEmpty()) list.add(q.poll());

        return list;

        
    }
}