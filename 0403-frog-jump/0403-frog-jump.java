class Solution {
    public boolean canCross(int[] stones) {

        if(stones[1]!=1) return false;

        int n = stones.length;
        int[][] dp = new int[n+1][n+1];

        for(int[] row : dp) Arrays.fill(row, -1);

        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0;i<n;i++){

            map.put(stones[i], i);  
        }

        return solve(1,0,stones, dp ,map ) == 1;
        
    }

    private int solve(long pos, int preIndex, int[] stones, int[][] dp, Map<Integer, Integer> map){

        if(pos> Integer.MAX_VALUE) return 0;

        if(map.containsKey((int)pos) == false){
            return 0;
        }

        if(pos == stones[stones.length-1]) return 1;

        int index = map.get((int)pos);


        if(dp[index][preIndex]!=-1) return dp[index][preIndex];

        int steps = (int)pos - stones[preIndex];
        int a = 0, b=0 , c=0;
        
        
        if(pos + steps -1 > pos){
            a = solve(pos + steps -1,index, stones, dp, map );
        }
            

        b = solve(pos + steps , index, stones, dp, map);
            
        c = solve(pos + steps +1, index,stones, dp, map);


        return dp[index][preIndex] = (a == 1 || b==1 || c==1) ? 1 : 0;
    }
}