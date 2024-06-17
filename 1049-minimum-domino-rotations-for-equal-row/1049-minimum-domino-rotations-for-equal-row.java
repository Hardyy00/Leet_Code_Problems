class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {

        // return solve1(tops, bottoms);

        return solve2(tops, bottoms);
       
        
    }

    private int solve2(int[] tops, int[] bottoms){

        int minSwaps = Integer.MAX_VALUE;
        int n= tops.length;

        for(int num=1;num<=6;num++){

            int countTop= 0, countBottom =0, countSame = 0;
            
            for(int i=0;i<n;i++){

                if(tops[i]==bottoms[i] && tops[i]==num){
                    countSame++;
                    
                }else if(tops[i]==num){
                    countTop++;
                }else if(bottoms[i]==num){
                    countBottom++;
                }

            }

            if(countTop + countBottom + countSame >=n){
                minSwaps = Math.min( minSwaps, Math.min(countTop, countBottom) );
            }
        }

        return minSwaps == Integer.MAX_VALUE ? -1 : minSwaps;


    }

    private int solve1(int[] tops, int[] bottoms){
        // TC : O(N)
        // SC : O(N)
        Map<Integer,Map<Integer,Integer>> map =new HashMap<>();

        int n = tops.length;
        for(int i =0;i<n;i++){

            map.putIfAbsent(tops[i], new HashMap<>());
            map.putIfAbsent(bottoms[i] , new HashMap<>());

           
            map.get(tops[i]).put(i,1);
            

            if(map.get(bottoms[i]).containsKey(i)){
                map.get(bottoms[i]).put(i,3);
            }else{
                map.get(bottoms[i]).put(i,2);
            }
        }

        int minSwaps = Integer.MAX_VALUE;

        for(Map<Integer,Integer> temp : map.values()){

            if(temp.size() < n){
                continue;
            }

            int topCount = 0, bottomCount = 0;

            for(int val : temp.values()){

                if(val==1){
                    topCount++;
                }else if( val==2){
                    bottomCount++;
                }
            }

            minSwaps = Math.min(  minSwaps, Math.min(topCount, bottomCount) );
        }

        return minSwaps == Integer.MAX_VALUE ? -1 : minSwaps;
    }
}