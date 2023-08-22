class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] pre, int[][] queries) {
        
        // TC : O(N^3) 
        // SC : O(N*N) + O(N)

        // if mat[a][b] = 1 , i.e a is pre of b, then visit,b and see it is connected to which node
        // when you complete the call return the array, so that the b can know for who
        // c was pre, b would also be a pre of them, and similarily return mat[b]
        // so that a can mark for whom b (mixed with c)  was a pre/

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

        // call for dfs
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

        visit[node] = true;  // visit the node

        boolean[] save = new boolean[n];  // to save answers for every call and , appling them in the end

        for(int i=0;i<n;i++){

            // if a is pre of b, then visit b
            if(mat[node][i]){

                boolean[] temp = null;
                if(visit[i]){  // is b is already visited , then get b , cuz we want to mark , for whom b was a pre

                    temp = mat[i];

                }else{
                    
                    // other wise call for dfs
                    temp = dfs(i, mat, visit, n);
                }

                // save the answerrs
                for(int j=0;j<n;j++) save[j] = save[j] || temp[j];
            }
        }

        // apply the answers
        for(int i=0;i<n;i++) mat[node][i] = mat[node][i] || save[i];

        return mat[node];  // return the info about the current node

    }

}