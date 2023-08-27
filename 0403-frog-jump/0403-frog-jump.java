class Solution {
    public boolean canCross(int[] stones) {

        // USING A MAP TO KNOW IF A STONE EXIST AT THE CALLED POSITION 

        // USING LONG TO AVOID INTEGER OVERFLOW 
        if(stones[1]!=1) return false;

        int n = stones.length;
        int[][] dp = new int[n+1][n+1];

        for(int[] row : dp) Arrays.fill(row, -1);

        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0;i<n;i++){

            map.put(stones[i], i);  
        }

        // USING A PREINDEX STATE, BECAUSE WE CAN COME AT AN INDEX, FROM VARIOuS INDEXes,
        // using varoius steps,so the answer at particular index may differ acc. to the
        // previous index, from where it jumped, hence also passing the preindex

        // from the postion 1 ( index is also 1 ) and preindex is 0
        return solve(1,0,stones, dp ,map ) == 1;
        
    }

    private int solve(long pos, int preIndex, int[] stones, int[][] dp, Map<Integer, Integer> map){

        // Memoization
        // TC : O(N*N)
        // SC : O(N*N) + O(N) + O(N)

        if(pos> Integer.MAX_VALUE) return 0;  // INTEGER OVERFLOW

        /// IF THE STONE DOESN'T EXIST AT THE GIVEN POSITON RETURN FALSE
        if(map.containsKey((int)pos) == false){
            return 0;
        }

        if(pos == stones[stones.length-1]) return 1;  //IF WE REACH THE LAST STONE RETURN TRUE

        int index = map.get((int)pos);  // GET THE INDEX OF THE CURRENT POSITION


        if(dp[index][preIndex]!=-1) return dp[index][preIndex];


        int steps = (int)pos - stones[preIndex];  // calc. the number of steps taken
        int a = 0, b=0 , c=0;
        
        // only if we can go to fowaard direction
        if(pos + steps -1 > pos){
            // move k-1 steps, and give the index as preindex
            a = solve(pos + steps -1,index, stones, dp, map );
        }
            
        // move k steps
        b = solve(pos + steps , index, stones, dp, map);
        
        // move k+1 steps
        c = solve(pos + steps +1, index,stones, dp, map);


        return dp[index][preIndex] = (a == 1 || b==1 || c==1) ? 1 : 0;
    }
}