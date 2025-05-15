class Solution {
    public int reversePairs(int[] nums) {

        if(nums.length==1) return 0;

        // return reversePairsBruteForce(nums);

        int[] count = {0};
        mergeSort(0,nums.length-1,nums, count);

        return count[0];
    }

    private int[] mergeSort(int start, int end, int[] nums, int[] count){

        // Optimal Approach : Use merge sort, (count inversion logic) to find reverse pairs, using the count inversion logic
        // to find the reverse pair, count it separately, instead of finding them in the same loop
        // buz of the -ve element like -5,-5, and also buz and element left[i] > right[j] may or may not be
        // left[i] > 2*right[j], hence count them separately

        // TC : O(NlogN)
        // SC : O(N)

        if(start==end) return new int[]{nums[start]};

        int mid = (start + end)/2;

        int[] left = mergeSort(start, mid, nums, count);
        int[] right = mergeSort(mid+1, end, nums, count);

        return merge(left, right, count);


    }

    private int[] merge(int[] left, int[] right, int[] count){


        int i=0;
        int j=0;
        int k=0;

        // count reverse pairs
        while(i<left.length && j<right.length){

            // 1L * cuz of overflow, if smaller then increment the index
            if(1L*left[i]<=2*1L*right[j]) i++;
            else {

                count[0] += left.length - i;  // alll the element including curren left index, are > 2*right[j] 
                // so count them
                j++;
            }
        }

        i=0;
        j=0;

        int[] sortedArray = new int[left.length + right.length];

        while(i<left.length && j<right.length){

            if(left[i] <=right[j]){
                sortedArray[k++] = left[i++];
            }else{
                sortedArray[k++] = right[j++];
            }
        }

        while(i<left.length) sortedArray[k++] = left[i++];

        while(j < right.length) {
            sortedArray[k++] = right[j++];
        }

        return sortedArray;
    }

    private int reversePairsBruteForce(int[] nums){

        // Brute Force : Use nested loops to detech the reverse pairs
    
        // TC : O(N * N)
        // SC : O(1)

        int cn = 0;

        for(int i=0;i<nums.length;i++){

            for(int j=i+1;j<nums.length;j++){


                if(nums[i] > 2*nums[j]) cn++;
            }
        }

        return cn;
    }
}