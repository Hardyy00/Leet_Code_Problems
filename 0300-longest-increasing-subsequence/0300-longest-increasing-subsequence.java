class Solution {
    public int lengthOfLIS(int[] nums) {

        int n = nums.length;

        // return lis(0, -1, nums);
        
        // int[][] dp = new int[n][n+1];

        // for(int[] row : dp) Arrays.fill(row , -1);

        // return lis2(0, -1 , nums, dp);

        // return lis3(n, nums);

        // return lis4(n, nums);

        // return lis5(n, nums);

        return binarySearch(n, nums);
    } 

    int binarySearch(int n, int[] nums){

        // suppose in 0 1 0 3 2 3
        // you can generate 0 1 3
        // 0 3
        // 2 3
        // instead on maintaining diiferent arrays, but maintain one and everytime replace

        // TC : O(N * LogN)
        // SC : O(N)

        // maintaining a list for containing the increaing sequence max Size
        List<Integer> list = new ArrayList<>();

        list.add(nums[0]);

        for(int i=1;i<nums.length;i++){

            // if current element in greater , than previous element just add it
            if(nums[i] > list.get(list.size()-1)){

                list.add(nums[i]);
            }else{
                
                // if current element is not greater than previous element , then you have to
                // find a index such that the value at that index is equal or just greater than current 
                // value so that you can replace it for a new sequence
                int lowerBound = lowerBound(list, nums[i]);

                // replace that value
                list.set(lowerBound , nums[i]);
            }

        }

        // list size is maximum possible increasing subsequence
        return list.size();
    }



    int lowerBound(List<Integer> list, int val){

        int low = 0;
        int high = list.size()-1;

        int lowerBound = 0;
        while(low<=high){
            
            int mid = high + (low - high)/2;

            if(list.get(mid)>=val){
                lowerBound = mid;
                high = mid-1;
            }else{
                low = mid+1;
            }
            
        }

        return lowerBound;

    }



    int lis5(int n, int[] nums){

        // Tabulation
        // TC : O(N^2)
        // SC : O(N)

        // for every i means, what can be length of largest lis with element at index i as its largest element
        //  

        int[] dp = new int[n];

        Arrays.fill(dp, 1);

        int lis = 1;

        for(int i=0;i<n;i++){

            for(int j=0;j<i;j++){
                
                // when we have found a element smaller than the current current,
                // then calculating the length of sequence, always storing the length of largest
                // subsequence at a particular index
                if(nums[j] < nums[i]){
                    
                    // 1 + dp[j] , because length od before sequence + current element
                    dp[i] = Math.max(dp[i] , 1 + dp[j]);
                }
            }
            // every element contains length of increasing subsequence, so finding maximum for that
            lis = Math.max(lis, dp[i]);
        }

        return lis;

    }

    int lis4(int n, int[] nums){

        // Space Optimised
        // TC : O(N*N)
        // SC : O(N)

        int[] pre = new int[n+1];
        int[] curr = new int[n+1];

        for(int i=n-1;i>=0;i--){

            // i-1 , since previous index is always less than current index
            for(int preIndex=i-1;preIndex>=-1;preIndex--){

                int take = 0;

                if(preIndex==-1 || nums[i]>nums[preIndex]){
                    // since you have sifter the index by have , you have store and get at +1 index (i+1)
                    take = 1 + pre[i+1];
                }

                // indexs are shifted by one , therefore preIndex + 1
                int notTake = pre[preIndex+1];

                curr[preIndex+1] = Math.max(take, notTake);
            }

            int[] temp = curr;
            curr = pre;
            pre = temp;
        }

        // call was 0 , -1 , but due to index shifting -> 0,-1+1 -> 0 , 0
        return pre[0];
    }

    int lis3(int n, int[] nums){

        // Tabulation
        // TC : O(N*N)
        // SC : O(N*N)

        int[][] dp = new int[n+1][n+1];

        for(int i=n-1;i>=0;i--){

            // i-1 , since previous index is always less than current index
            for(int preIndex=i-1;preIndex>=-1;preIndex--){

                int take = 0;

                if(preIndex==-1 || nums[i]>nums[preIndex]){
                    // since you have sifter the index by have , you have store and get at +1 index (i+1)
                    take = 1 + dp[i+1][i+1];
                }

                // indexs are shifted by one , therefore preIndex + 1
                int notTake = dp[i+1][preIndex+1];

                dp[i][preIndex+1] = Math.max(take, notTake);
            }
        }

        // call was 0 , -1 , but due to index shifting -> 0,-1+1 -> 0 , 0
        return dp[0][0];
    }

    int lis2(int index,int preElementIndex, int[] nums, int[][] dp){
        
        // since preIndex can take value , -1 to n-1, shift them by one, i.e -1 to 0 , 0 to 1, and n-1 to n
        // therefore store at dp[index][preIndex+1]

        // Memoization
        // TC : O(N*N)
        // SC : O(N*N) + O(N)

        if(index==nums.length) return 0;

        if(dp[index][preElementIndex + 1] !=-1) return dp[index][preElementIndex+1];


        int takeTheElement = 0;

        if(preElementIndex==-1 || nums[index]> nums[preElementIndex]){

            takeTheElement = 1 + lis2(index+1 , index, nums, dp);
        }

        int notTake = lis2(index+1 , preElementIndex, nums, dp);

        return dp[index][preElementIndex+1] = Math.max(takeTheElement, notTake);
    }

    int lis(int index, int preElementIndex, int[] nums){
        
        // if you are taking a element , then you must record it index, so when you find another element 
        // you can always compare to check if it is greater than the previous element

        // -1 means there are no previous element , so you can take anything

        // Recursion
        // TC : O(2^N)
        // SC : O(N)

        // if you have finished you array , then you can no longer take anything
        if(index==nums.length) return 0;

        int takeTheElement = 0;
        // if you haven't taken anything or if current element is greater than the previous element
        // then only you can take it
        if(preElementIndex==-1 || nums[index]>nums[preElementIndex]){

            takeTheElement = 1 + lis(index+1, index, nums);
        }

        // if you are not taking then preIndex remains same
        int notTake = lis(index+1 , preElementIndex, nums);

        return Math.max(takeTheElement, notTake);
    }
}