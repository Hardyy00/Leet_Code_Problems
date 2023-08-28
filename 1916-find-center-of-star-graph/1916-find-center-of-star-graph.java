class Solution {
    public int findCenter(int[][] edges) {
        
        int n = 0;

        for(int[] row : edges){

            n = Math.max(n, Math.max(row[0], row[1]));
        }
        
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