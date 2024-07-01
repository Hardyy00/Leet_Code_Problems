class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        

        for(int i=0;i<arr.length-2;i++){

            int a = arr[i], b = arr[i+1], c= arr[i+2];

            if((a & 1)==1 && (b & 1)==1 && (c & 1 )==1){
                return true;
            }
        }

        return false;
    }
}