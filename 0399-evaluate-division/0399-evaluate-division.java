class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        
        int it = 0;
        Map<String, Integer> map = new HashMap<>();

        for(List<String> eq : equations){

            String a = eq.get(0);
            String b = eq.get(1);

            if(!map.containsKey(a)){
                map.put(a,it++);
            }

            if(!map.containsKey(b)){

                map.put(b,it++);
            }
        }

        int n = map.size();

        double[][] mat = new double[n][n];

        for(double[] row : mat) Arrays.fill(row, -1);

        for(int i=0;i<n;i++) mat[i][i] = 1;

        it = 0;
        for(List<String> eq : equations){

            int a1 = map.get(eq.get(0));
            int a2 = map.get(eq.get(1));

            double val = values[it++];

            mat[a1][a2] = val;
            mat[a2][a1] = 1/val;
        }

        for(int via = 0;via<n;via++){

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++ ){
                    if(mat[i][via] !=-1 && mat[via][j] !=-1){
                        mat[i][j] = Math.max(mat[i][via] * mat[via][j], mat[i][j]);
                    }
                }
            }
        }

        double[] ans = new double[queries.size()];
        it = 0;
        for(List<String> q : queries){

            String s1= q.get(0);
            String s2 = q.get(1);

            
            if(!map.containsKey(s1) || !map.containsKey(s2)){

                ans[it++] = -1;
            }else{

                int i1 = map.get(s1);
                int i2 = map.get(s2);

                
                ans[it++] = mat[i1][i2];
                
            }
        }

        return ans;
        
    }
}