class Solution {
public:
    bool isInterleave(string s1, string s2, string s3) {

        // since s3, is made of s1 and s2, we always find something equal, if we traverse
        // if if we do not find any character equal, that means we are traversing in a wrong way
        // so traverse in an aother way

        int p = s1.size(), q = s2.size() , r = s3.size();

        // since , only i1, and i2 changes , we make a dp with only them
        // i3 only changes in forward direction
        // vector<vector<int>> dp(p+1 , vector<int>(q+1, -1));

        // return solve2(p-1,q-1,r-1,s1,s2,s3,dp);

        // return solve3(p,q,r,s1,s2,s3);

        // return solve4(p,q,r,s1,s2,s3);

        return solve5(p,q,r,s1,s2,s3);
        
    }

    bool solve5(int p, int q, int r, string &s1, string &s2, string &s3){

        // Space Optimised
        // TC : O(P*Q) (only one array)
        // SC : O(Q)

        if(p==0 && q==0 && r==0) return true;

        if((p+q)!=r) return false;

        vector<bool> pre(q+1, false);

        pre[0] = true;

        // when s1 is finished , finding till where s2 and s3 are equal (from starting)
        for(int i=1;i<=q;i++){

            bool eq = s2[i-1]==s3[i-1];

            if(eq) pre[i] = eq;
            else break;
        }
       

        // i+j-1 , gives the index of the corresponing character in s3
        for(int i=1;i<=p;i++){

            pre[0] = pre[0] && s1[i-1]==s3[i-1];       // 2nd base case
            for(int j=1;j<=q;j++){
                
                bool a= false;
                bool b =  false;
                if(s1[i-1]==s3[i+j-1]) a = pre[j];

                if(s2[j-1]==s3[i+j-1]) b = pre[j-1];

                pre[j] = a || b;

            }
        }

        return pre[q]; 
    }

    bool solve4(int p, int q, int r, string &s1, string &s2, string &s3){

        // Space Optimised
        // TC : O(P*Q)
        // SC : O(Q)

        if(p==0 && q==0 && r==0) return true;

        if((p+q)!=r) return false;

        vector<bool> pre(q+1, false);
        vector<bool> curr(q+1, false);

        pre[0] = true;

        // when s1 is finished , finding till where s2 and s3 are equal (from starting)
        for(int i=1;i<=q;i++){

            bool eq = s2[i-1]==s3[i-1];

            if(eq) pre[i] = eq;
            else break;
        }
       

        // i+j-1 , gives the index of the corresponing character in s3
        for(int i=1;i<=p;i++){

            curr[0] = pre[0] && s1[i-1]==s3[i-1];       // 2nd base case
            for(int j=1;j<=q;j++){
                
                bool a= false;
                bool b =  false;
                if(s1[i-1]==s3[i+j-1]) a = pre[j];

                if(s2[j-1]==s3[i+j-1]) b = curr[j-1];

                curr[j] = a || b;

            }

            pre = curr;
        }

        return pre[q]; 
    }

    bool solve3(int p, int q, int r, string &s1, string &s2, string &s3){

        // Tabulation
        // TC : O(P*Q)
        // SC : O(P*Q)

        if(p==0 && q==0 && r==0) return true;

        if((p+q)!=r) return false;

        vector<vector<bool>> dp(p+1, vector<bool>(q+1, false));

        dp[0][0] = true;

        // when s1 is finished , finding till where s2 and s3 are equal (from starting)
        for(int i=1;i<=q;i++){

            bool eq = s2[i-1]==s3[i-1];

            if(eq) dp[0][i] = eq;
            else break;
        }

        // same with s2
        for(int i=1;i<=p;i++){

            bool eq = s1[i-1]==s3[i-1];

            if(eq) dp[i][0] = eq;
            else break;
        }

        // i+j-1 , gives the index of the corresponing character in s3
        for(int i=1;i<=p;i++){

            for(int j=1;j<=q;j++){
                
                bool a= false;
                bool b =  false;
                if(s1[i-1]==s3[i+j-1]) a = dp[i-1][j];

                if(s2[j-1]==s3[i+j-1]) b = dp[i][j-1];

                dp[i][j] = a || b;

            }
        }

        return dp[p][q]; 
    }

    bool solve2(int i1, int i2, int i3, string &s1, string &s2, string &s3, vector<vector<int>>  &dp){

        // Intution : if we do something like , match 1st string , and then match 2nd string
        // even if the character matches or not, cuz if we do this, we will have equal number of partitions

        // Memoization
        // TC : O(P*Q)
        // SC : O(P*Q) + O(P+Q)

        // all the three strings have been finished
        if( i1<0 && i2<0 && i3<0) return true;

        if(i3<0) return false;

        // since i1 and i2 and be less than 0 i.e -1 indicating that a string is finished
        // thereofore shifting the indexex by 1
        if(dp[i1+1][i2+1]!=-1) return dp[i1+1][i2+1];

        bool a = false;
        bool b = false;

        // if s1 is not finised, and try checking is the corresponding chars. are equal, if yes , then decrease
        // the indexes 
        if(i1>=0 && s1[i1]==s3[i3]) a = solve2(i1-1,i2,i3-1,s1,s2,s3, dp);

        if(i2>=0 && s2[i2]==s3[i3]) b = solve2(i1,i2-1,i3-1,s1,s2,s3, dp);

        return  dp[i1+1][i2+1] = a || b;

    }

    bool solve(int i1, int i2, int i3, string &s1, string &s2, string &s3){

        // Recursion
        // TC : O(2^R) approx
        // SC : O(P+Q)

        if( i1<0 && i2<0 && i3<0) return true;

        if(i3<0) return false;

        bool a = false;
        bool b = false;

        // first string matchs
        if(i1>=0 && s1[i1]==s3[i3]) a = solve(i1-1,i2,i3-1,s1,s2,s3);

        // second string matches
        if(i2>=0 && s2[i2]==s3[i3]) b = solve(i1,i2-1,i3-1,s1,s2,s3);

        return a || b;

    }
};