class Solution {
    public boolean isMatch(String s, String p) {

        int n = s.length();
        int m = p.length();
        
        // return match1(n-1, m-1, s, p);

        // int[][] dp = new int[n][m];

        // for(int[] row : dp) Arrays.fill(row , -1);

        // return match2(n-1, m-1, s, p, dp)==1;

        // return match3(n, m, s, p);

         return match4(n, m, s, p);
    }     

    boolean match4(int n, int m , String s1, String s2){

        //  Space Optimised
        // TC : O(N*M)
        // SC : O(M)

        boolean[] pre = new boolean[m+1];
        

        // base cases (setting of trues)

        pre[0] = true;


        int in = 0;

        while(in<s2.length() && s2.charAt(in)=='*'){
            pre[in+1] = true;
            in++;
        }

        for(int i=1;i<=n;i++){
            boolean[] curr = new boolean[m+1];
            for(int j=1;j<=m;j++){

                if(s1.charAt(i-1)==s2.charAt(j-1) || s2.charAt(j-1)=='?') curr[j] = pre[j-1];

                else if(s2.charAt(j-1)=='*'){

                    boolean find1 = curr[j-1];

                    boolean find2 = pre[j];

                    curr[j] = find1 || find2;
                }else {

                    curr[j] = false;
                }
            }

            pre = curr;
        }

        return pre[m];
        
    }

    boolean match3(int n, int m , String s1, String s2){

        //  Tabulation
        // TC : O(N*M)
        // SC : O(N*M)

        boolean[][] dp = new boolean[n+1][m+1];

        // base cases (setting of trues)

        // both strings are finished
        dp[0][0] = true;


        int in = 0;
        
        // when the s1 is finished , and s2 is not finished. if the remaining characters are "*" then
        // return true, for each index , if the "*" occur in continuation , then marking it
        // as true (because call can be anyting (-1, 0) (-1, 2) if only "*" occur till there then it is true) 
        while(in<s2.length() && s2.charAt(in)=='*'){
            dp[0][in+1] = true;
            in++;
        }

        // for(int i=1;i<=n;i++) dp[i][0] = false;

        for(int i=1;i<=n;i++){

            for(int j=1;j<=m;j++){

                if(s1.charAt(i-1)==s2.charAt(j-1) || s2.charAt(j-1)=='?') dp[i][j] = dp[i-1][j-1];

                else if(s2.charAt(j-1)=='*'){

                    boolean find1 = dp[i][j-1];

                    boolean find2 = dp[i-1][j];

                    dp[i][j] = find1 || find2;
                }else {

                    dp[i][j] = false;
                }
            }
        }

        return dp[n][m];
        
    }

    int match2(int index1, int index2, String s1, String s2, int[][] dp){

        // Memoization
        // TC : O(N*M)
        //SC : O(N*M) + O(N+M)

        if(index1<0 && index2<0) return 1;

        if(index1<0 && index2>=0){

            for(int i=0;i<=index2;i++) if(s2.charAt(i)!='*') return 0;

            return 1;
        }

        if(index2<0 && index1>=0) return 0;

        if(dp[index1][index2]!=-1) return dp[index1][index2];

        if(s1.charAt(index1)==s2.charAt(index2) || s2.charAt(index2)=='?') 
            return dp[index1][index2] = match2(index1-1, index2-1,s1,s2 ,dp);

        else if(s2.charAt(index2)=='*'){
            
            // do not compare * to anything
            int find1 = match2(index1, index2-1, s1,s2 ,dp);

            // compare * to current character
            int find2 = match2(index1-1, index2, s1,s2,dp);

            return dp[index1][index2] = find1==1 || find2==1 ? 1 : 0;
        }

        return dp[index1][index2] = 0;
    }

    boolean match1(int index1, int index2, String s1, String s2){

        // Recursion
        // TC : exponential
        // SC : O(N+M)

        // both the string have been been finished
        if(index1<0 && index2<0) return true;

        // if my string 1 has been finished, but s2 not , so if s2 contains "*" as remaining characters
        // then it will work for us (return true) , full if it conatains some another character
        // (other than "*") then it is impossible for them to be equal , so return false;
        if(index1<0 && index2>=0){
            for(int i=0;i<=index2;i++) if(s2.charAt(i)!='*') return false;

            // all tthe remaining characters were *
            return true;
        }

        // string 2 was completed but string 1 has some characters left, so return false;
        if(index2<0 && index1>=0) return false;


        // characters are equal
        if(s1.charAt(index1)==s2.charAt(index2) || s2.charAt(index2)=='?') 
            return match1(index1-1, index2-1, s1 ,s2);

        else if(s2.charAt(index2)=='*'){
            
            // we can  make it equal to an empty subsequence i.e. not compare it with anybody
            // so shrink the s2
            boolean find1 = match1(index1,index2-1,s1,s2);

            // when we find a "*", we can make it equal to current character , so shrink the s1
            // (we can make it equal to the whole subsequence in that case index2 remains at single place)
            boolean find2 = match1(index1-1, index2, s1,s2);

            return find1 || find2;
        }

        // the characters were not equal so return false
        return false;
    }
}