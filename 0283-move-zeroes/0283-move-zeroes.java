class Solution {
    public void moveZeroes(int[] arr) {

        int d = 0;

        for(int i=0;i<arr.length;i++){
            if(arr[i]!=0){
                arr[d] = arr[i];
                d++;
            }
        }

        while(d<arr.length){
            arr[d]=0;
            d++;
        }
        
    }
}