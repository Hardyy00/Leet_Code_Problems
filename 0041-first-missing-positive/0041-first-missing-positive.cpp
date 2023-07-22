class Solution {
public:
    int firstMissingPositive(vector<int>& nums) {

        // TC : O(N)
        // SC : O(N)

        unordered_map<int, int> map;

        int maxi = 0;

        for(int i : nums){
            map[i] =1;

            maxi = max(maxi, i);
        }



        for(int i=1;i<=maxi;i++){

            if(!map.count(i)) return i;
        }

        return maxi+1;
        
        
    }
};