class Solution {
private:
    bool solve(int index, vector<int> &nums){

        if(index==nums.size()) return true;

        int same = 0;
        int cons = 0;
        bool ans = false;

        for(int i=index;i<index+3 && i<nums.size();i++){

            if(i==index){
                same=cons=1;
            }else if(nums[i]==nums[i-1]){
                same++;
                
            }else if(nums[i]-nums[i-1]==1) cons++;


            if(same>=2 || cons==3){
                cout << "Part at " << i << endl;
                ans = ans || solve(i+1, nums);
            }
 
        }

        return ans;
    }

    bool solve2(int index, vector<int> &nums, vector<int> &dp){

        // TC : O(N*3)
        // SC : O(N) + O(N/2) (recursion stack when partitioned for every 2 element)

        if(index==nums.size()) return true;
        if(dp[index]!=-1) return dp[index]; 

        int same = 0; // same elements
        int cons = 0; // consecutive elements
        bool ans = false;
        // bool calledFor2 = false;

        for(int i=index;i<index+3 && i<nums.size();i++){

            if(i==index){
                same=cons=1;
            }else if(nums[i]==nums[i-1]){
                same++;
                
            }else if(nums[i]-nums[i-1]==1){ 
                cons++;
            }

            else{
                return dp[index]=ans;
            }

            if((same==2 && cons==1)){
                // calledFor2 = true;
                ans = ans || solve2(i+1, nums,dp);

            }else if(same==3|| cons==3){
                // cout << "Index : " << index  << " Part at " << i << endl;
                ans = ans || solve2(i+1, nums,dp);

            }
        }

        return dp[index]=ans;
    }
public:
    bool validPartition(vector<int>& nums) {

        if(nums.size()==1 || (nums.size()==2 && nums[0]!=nums[1])) return false;
        
        int n = nums.size();
        vector<int> dp(n,-1);

        return solve2(0,nums,dp);

    }

};