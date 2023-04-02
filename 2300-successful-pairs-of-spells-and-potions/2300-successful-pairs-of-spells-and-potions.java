class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {

        int[] ans = new int[spells.length];
        Arrays.sort(potions);

        for(int i=0;i<ans.length;i++){

            int val = findCombinations(spells[i],potions,success);
            ans[i] = val==-1 ? 0 : potions.length-val;
        }

        return ans;
        
    }

    private static int findCombinations(long spell,int[] potions,long success){

        if((long)potions[potions.length-1]*spell < success) return -1;

        int lower = 0;
        int higher = potions.length-1;

        while(lower<higher){

            int mid = (higher+lower)/2;

            if(spell*(long)potions[mid]>=success){
                higher = mid;   // maybe the first index for the success
            }else{
                lower = mid+1;
            }
        }

        // System.out.println(lower);

        return lower;


    }
}