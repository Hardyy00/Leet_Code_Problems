class Solution {
    public int minOperations(int[] arr) {

        int z =0,o = 0;

        for(int i=0;i<3;i++){

            if(arr[i]==0){
                z++;
            }else{
                o++;
            }
        }

        int op = 0;

        if(arr[0]==0){

            int temp = z;
            z = o;
            o = temp;

            arr[0]=1;
            arr[1] = 1 - arr[1];
            arr[2] = 1 - arr[2];
            op++;
        }

        for(int i=3;i<arr.length;i++){

            if(arr[i-3]==0){
                z--;
            }else{
                o--;
            }

            if(arr[i]==0){
                z++;
            }else{
                o++;
            }

            if(arr[i-2]==0){

                int swap = z;
                z = o;
                o = swap;

                arr[i-2] = 1;
                arr[i-1] = 1 - arr[i-1];
                arr[i] = 1 - arr[i];
                op++;
            }
        }


        return o==3 ? op : -1;


        
    }
}