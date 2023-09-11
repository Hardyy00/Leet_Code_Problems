class Solution {
    public int hIndex(int[] citations) {

        int n = citations.length;
        int low = 0;
        int high = n;

        int ans = 0;

        while(low <= high){

            int mid = high + (low - high)/2;

            if( bs(mid, citations)){
                ans = mid;
                low = mid + 1;
            }else{

                high = mid-1;
            }
        }

        return ans;
        
    }

    private boolean bs(int h, int[] citations){

        int times = 0;

        for(int i : citations){

            if(i>=h) times++;

            if(times>=h) return true;
        }

        return false;
    }
}