class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {

        HashSet<String> set = new HashSet<>();

        for(String str : wordDict){
            set.add(str);
        }

        int[] dp = new int[s.length()];

        Arrays.fill(dp,-1);

        return isPartable(0,s,set, dp)==1;        
        
    }

    private static int isPartable(int index,String s,HashSet<String> set, int[] dp){

        // Memoization
        // TC : O(N*N) // for dp + O(N*N*N) for string builder
        // SC : O(N) + O(M) 

        if(index==s.length()){
            return 1;
        }

        if(dp[index]!=-1) return dp[index];

        StringBuilder build = new StringBuilder();

        int ans = 0;

        for(int i=index;i<s.length();i++){

            build.append(s.charAt(i));

            if(set.contains(build.toString())){

                int val = isPartable(i+1,s,set,dp);
                
                if(val==1) ans = 1;
            }

        }

        return dp[index]=ans;
    }
}