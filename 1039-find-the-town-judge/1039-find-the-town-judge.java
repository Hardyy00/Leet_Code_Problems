class Solution {
    public int findJudge(int n, int[][] trust) {
        
        int[] indegree = new int[n+1];
        int[] outdegree = new int[n+1];

        for(int[] row : trust){

            outdegree[row[0]]++;
            indegree[row[1]]++;
        }

        int judge = -1;

        for(int i=1;i<=n;i++){

            if(outdegree[i]==0 && indegree[i]==n-1){

                if(judge!=-1) return -1;
                else judge = i;
            }
        }

        return judge;
    }
}