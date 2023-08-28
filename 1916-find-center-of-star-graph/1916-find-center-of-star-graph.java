class Solution {
    public int findCenter(int[][] edges) {
        
        int n = edges.length+1;

        int[] indegree = new int[n+1];

        for(int[] row : edges){

            indegree[row[0]]++;
            indegree[row[1]]++;

            if(indegree[row[0]]==n-1) return row[0];

            if(indegree[row[1]] == n-1) return row[1];

        }

        return -1;
    }
}