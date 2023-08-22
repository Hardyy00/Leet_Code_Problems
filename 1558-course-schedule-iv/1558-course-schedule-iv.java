class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] pre, int[][] queries) {
        

        if(pre.length == 0){

            List<Boolean> ans = new ArrayList<>();

            for(int i=0;i< numCourses ;i++) ans.add(false);

            return ans;
        }

        boolean[][] mat = new boolean[numCourses][numCourses];

        for(int[] row : pre){

            mat[row[0]][row[1]] = true; 
        }

        boolean[] visit = new boolean[numCourses];

        for(int i=0;i<numCourses;i++){

            if(!visit[i]) dfs(i, mat,visit, numCourses);
        }

        List<Boolean> ans = new ArrayList<>();

        for(int[] qu : queries){

            ans.add( mat[qu[0]][qu[1]] );
        }

        return ans;
        
    }

    private boolean[]  dfs(int node, boolean[][] mat,boolean[] visit,int n ){

        visit[node] = true;

        boolean[] save = new boolean[n];

        for(int i=0;i<n;i++){

            if(mat[node][i]){

                boolean[] temp = null;
                if(visit[i]){

                    temp = mat[i];

                }else{

                    temp = dfs(i, mat, visit, n);
                }

                for(int j=0;j<n;j++) save[j] = save[j] || temp[j];
            }
        }

        for(int i=0;i<n;i++) mat[node][i] = mat[node][i] || save[i];

        return mat[node];

    }

}