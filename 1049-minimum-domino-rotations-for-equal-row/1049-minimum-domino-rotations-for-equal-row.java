class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {

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