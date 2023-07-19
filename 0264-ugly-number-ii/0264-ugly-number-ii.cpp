class Solution {
public:
    int nthUglyNumber(int n) {
        return solve(n);
    }
    int solve(int n){
        
        // Tab
        // TC : O(N)
        // SC : O(N)

        vector<int> dp(n,0);

        dp[0] =1;
        int x=0;    // points to the number where is should multiply 2
        int y=0;        // points to the number where is should multiply 3
        int z = 0;      // points to the number where is should multiply 5

        for(int i=1;i<n;i++){

            // assign minimum to the current
            dp[i] = min(2*dp[x] , min(3*dp[y] , 5*dp[z]));

            if(dp[i]==2*dp[x]) x++; // if you have multiplied the current number by 2 , you cannot 
            // multiply it by 2 again so move forward to multiply it by 2

            // for the cases , if we get duplicated  wea re not using else if
            // like 2*3 and 3*2 ,we are moving voth forward
            if(dp[i]==3*dp[y]) y++;
            if(dp[i]==5*dp[z])z++;

        }

        return dp[n-1];
    }

    
};