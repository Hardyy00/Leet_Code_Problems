class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] pre, int[][] queries) {
        
        if(pre.length == 0){

            List<Boolean> ans = new ArrayList<>();

            for(int i=0;i< numCourses ;i++) ans.add(false);

            return ans;
        }

        // return solve(numCourses, pre, queries);

        // return solve2(numCourses, pre, queries);

        return solve3(numCourses, pre, queries);
       
    }

    private List<Boolean> solve3(int n, int[][] pre, int[][] queries){

        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        int[] indegree =new int[n];

        for(int[] row : pre){

            adj.get(row[0]).add(row[1]);

            indegree[row[1]]++;
        }


        boolean[][] mat =new  boolean[n][n];

        Queue<Integer> q = new LinkedList<>();

        for(int i=0;i<n;i++) if(indegree[i] == 0) q.offer(i);

        while(!q.isEmpty()){

            int node = q.poll();

            for(int next : adj.get(node)){

                mat[node][next] = true;

                for(int i=0;i<n;i++) if(mat[i][node]) mat[i][next] = true;

                indegree[next]--;

                if(indegree[next]== 0) q.offer(next);
            }

        }

        List<Boolean> ans = new ArrayList<>();

        for(int[] row : queries) ans.add(mat[row[0]][row[1]]);

        return ans;
    }

    private List<Boolean> solve2(int n, int[][] pre, int[][] queries){

        // applying floyd warshall, so find if from a node can i reach a node
        // TC : O(N*N*N)
        // SC : O(N^2)

        boolean[][] mat = new boolean[n][n];

        for(int[] row : pre) mat[row[0]][row[1]] = true;

        for(int via = 0;via<n;via++){

            for(int i = 0;i< n;i++){

                for(int j=0;j< n;j++){

                    mat[i][j] = mat[i][j] || (mat[i][via] && mat[via][j]);

                }
            }
        }

        List<Boolean> ans = new ArrayList<>();

        for(int[] row : queries) ans.add(mat[row[0]][row[1]]);


        return ans;

    }

    private List<Boolean> solve(int numCourses, int[][] pre , int[][] queries){

        // TC : O(N^3) 
        // SC : O(N*N) + O(N)

        // if mat[a][b] = 1 , i.e a is pre of b, then visit,b and see it is connected to which node
        // when you complete the call return the array, so that the b can know for who
        // c was pre, b would also be a pre of them, and similarily return mat[b]
        // so that a can mark for whom b (mixed with c)  was a pre/

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