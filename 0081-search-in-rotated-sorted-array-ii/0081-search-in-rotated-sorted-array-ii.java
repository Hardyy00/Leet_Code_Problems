class Solution {
    public boolean search(int[] nums, int target) {

        // return solve1(nums, target);

        return solve2(nums, target);
        
        
    }

    private boolean solve2(int[] nums, int target){

        int low = 0;
        int high = nums.length-1;

        while(low<=high){

            int mid = high + (low - high)/2;

            if(nums[mid]== target) return true;

            if(nums[mid]==nums[low] && nums[mid]==nums[high]){
                low++;
                high--;
                continue;
            }

            if(nums[low]<=nums[mid]){

                if(target>=nums[low] && target<=nums[mid]){

                    high = mid -1;
                }else{

                    low = mid +1;
                }
            }else if(nums[mid]<=nums[high]){

                if(target>=nums[mid] && target<=nums[high]){

                   low = mid+1;
                }else{

                   high = mid-1;
                }
            }
        }

        return false;
        
    }

    private boolean solve1(int[] nums , int target){

        // Tc : O(N*Log N) + Log N
        // Sc : O(1)

        Arrays.sort(nums);

        int low = 0;

        int high = nums.length-1;

        while(low <= high){

            int mid = high + (low - high)/2;

            if(nums[mid]== target) return true;

            else if (nums[mid]< target) low = mid + 1;
            
            else high = mid-1;
        }

        return false;
    }
}