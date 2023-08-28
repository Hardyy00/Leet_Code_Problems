class Solution {
    public int findCenter(int[][] edges) {
        
        int n = edges.length+1;

        int[] indegree = new int[n+1];

        for(int[] row : edges){

            indegree[row[0]]++;
            indegree[row[1]]++;

        }

        for(int i=1;i<=n;i++){

            if(indegree[i]==n-1) return i;
        }

        return -1;
    }
}