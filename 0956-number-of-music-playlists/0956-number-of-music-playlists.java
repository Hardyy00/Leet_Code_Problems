class Solution {
    int mod = (int)1e9 + 7;
    public int numMusicPlaylists(int n, int goal, int k) {
        
        int[][] dp = new int[n+1][goal+1];

        for(int[] row : dp) Arrays.fill(row, -1);

        return (int)solve(0,goal,k,n,dp)%mod;
    }

    public long solve(int oldSongs, int goal, int k,int n, int[][] dp){

        if(oldSongs == n  && goal==0 ) return 1l;

        if(goal == 0 || oldSongs > n ) return 0;

        // if() return 
        
        if(dp[oldSongs][goal]!=-1) return dp[oldSongs][goal];

        long addNewSong = (n-oldSongs) * solve(oldSongs+1, goal-1, k,n, dp) %mod;
        
        long repeatOldSong = 0;
        if(oldSongs > k)
            repeatOldSong = (oldSongs-k) * solve(oldSongs, goal-1,k,n, dp)%mod;

        return dp[oldSongs][goal] = (int)((addNewSong + repeatOldSong)%mod);

    }
}