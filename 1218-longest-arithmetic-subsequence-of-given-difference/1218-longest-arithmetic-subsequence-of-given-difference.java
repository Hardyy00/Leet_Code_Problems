class Solution {
    public int longestSubsequence(int[] arr, int difference) {

        int n = arr.length;
        return las(n, difference, arr);
        
    }

    int las(int n, int diff, int[] arr){

        // we calculate , at every index, what can be the length of the largest subsequence 
        // having arr[i] as its last element, since suppose you are what can be the length
        // of las with 5 as it last element , when the diff is 2, for a subsequence to ocuur
        // 3 must be in the array (5-2=3), only then we can make a subsequence, therefore
        // we need to make only one call of dp[arr[i]-k] , but arr[i] can be negative
        // we take tabulation

        // Tabulation
        // TC : O(N)
        /// SC : O(N)

        Map<Integer,Integer> map = new HashMap<>();
        
        int maxi = 0;

        for(int i=0;i<n;i++){
            
            map.put(arr[i], 1 + map.getOrDefault(arr[i]-diff , 0));

            maxi = Math.max(maxi, map.get(arr[i]));
        }

        return maxi;
    }

    
}