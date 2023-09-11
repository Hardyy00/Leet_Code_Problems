class Solution {
    public int hIndex(int[] citations) {

        int n = citations.length;
        int low = 0;
        int high = n;
        int ans =0;

        while(low <= high){

            int  mid = high + (low - high)/2;

            if(bs(mid, citations)){
                ans = mid;
                low = mid+1;
            }else {

                high = mid-1;
            }

        }

        return ans;
    }

    private boolean bs(int h, int[] citations){

        int n = citations.length;
        int low = 0;
        int high = n-1;
        int pos = -1;

        while(low <= high){

            int mid = high + (low - high )/2;

            if(citations[mid] >= h){
                pos = mid;
                high = mid-1;
            }else{

                low = mid+1;
            }
        }

        if(pos ==-1) return false;

        return n-pos >=h;
    }
}