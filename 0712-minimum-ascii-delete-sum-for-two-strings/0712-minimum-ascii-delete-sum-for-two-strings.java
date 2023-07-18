class Solution {
    public int minimumDeleteSum(String s1, String s2) {

        // at a corresponding index, if characters are equal just move index-1's , otherwise do all possible
        // ways, i.e delete s1's character and in next way delete s2's character

        int n = s1.length();
        int m = s2.length();

        // int[][] dp = new int[n][m];

        // for(int[] arr : dp) Arrays.fill(arr,-1);

        // return solve(n-1,m-1,s1,s2,dp);

        // return solve2(n,m, s1,s2);

        return solve3(n,m, s1,s2);        
    }

    int solve3(int n, int m, String s1, String s2){

        // Space Optimization
        // TC : O(N*M)
        // SC : O(M)

        int[] pre = new int[m+1];
        int[] curr = new int[m+1];

        int sum = 0;
        for(int i=1;i<=m;i++){

            sum += s2.charAt(i-1);
            
            pre[i] = sum; 
        }

        sum = 0;

        for(int i=1;i<=n;i++){
            sum += s1.charAt(i-1);
            curr[0] = sum;

            for(int j=1;j<=m;j++){

                if(s1.charAt(i-1)==s2.charAt(j-1)) curr[j] = pre[j-1];
                else{

                    int a = s1.charAt(i-1) + pre[j];

                    int b = s2.charAt(j-1) + curr[j-1];

                    curr[j] = Math.min(a,b);
                }
            }

            int[] temp = curr;
            curr = pre;
            pre = temp;
        }

        return pre[m];
    }

    int solve2(int n, int m, String s1, String s2){

        // Tabulation
        // TC : O(N*M)
        // SC : O(N*M)

        int[][] dp = new int[n+1][m+1];

        int sum = 0;
        for(int i=1;i<=m;i++){

            sum += s2.charAt(i-1);
            
            dp[0][i] = sum; 
        }

        sum = 0;

        for(int i=1;i<=n;i++){

            sum += s1.charAt(i-1);
            dp[i][0] = sum;
        }


        for(int i=1;i<=n;i++){

            for(int j=1;j<=m;j++){

                if(s1.charAt(i-1)==s2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else{

                    int a = s1.charAt(i-1) + dp[i-1][j];

                    int b = s2.charAt(j-1) + dp[i][j-1];

                    dp[i][j] = Math.min(a,b);
                }
            }
        }

        return dp[n][m];
    }

    int solve(int index1, int index2, String s1, String s2, int[][] dp){

        // Memoization
        // TC : O(N*M)
        // SC : O(N*M) + O(N+M)

        // so the string's are made equal, so we cannot delete anything so return 0
        if(index1<0 && index2<0) return 0;

        if(index1<0 && index2>=0){

            int sum = 0;

            // delete all the s2's remaining character, and add their ascii
            for(int i=0;i<=index2;i++){
                sum += s2.charAt(i);
            }

            return sum;
        }

        if(index2<0 && index1>=0){

            int sum = 0;

            // delete all the s1's remaining character, and add their ascii
            for(int i=0;i<=index1;i++){
                sum += s1.charAt(i);
            }

            return sum;
        }

        if(dp[index1][index2]!=-1) return dp[index1][index2];

        // characters are equal so move backward
        if(s1.charAt(index1)==s2.charAt(index2)) 
                    return dp[index1][index2] = solve(index1-1,index2-1,s1,s2,dp);

        // delete s1's character and add it's ascii, and move index1 backward by 1
        int a = s1.charAt(index1) + solve(index1-1,index2,s1,s2,dp);    // delete from s1

        // delete s2's character
        int b = s2.charAt(index2) + solve(index1,index2-1,s1,s2,dp);    // delete from s2

        return dp[index1][index2] = Math.min(a,b);


    }
}