class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        
        // return bruteForce(nums, target);

        // return optimalSol(nums, target);

        return mostOptimal(nums, target);

    }

    private List<List<Integer>> mostOptimal(int[] nums, int target){

        // Use the two sum approach but first fixed two pointer already and then use the two sum approach, 
        // sort the array too.To avoid add the duplicate skip over the elements that are same, do this for
        // all four indexes

        // TC : O(N^3)
        // SC : O(1)

        List<List<Integer>> answer = new ArrayList<>();

        Arrays.sort(nums);
        int n = nums.length;

        for(int i=0;i<n;i++){

            for(int j=i+1;j<n;j++){

                long tar = 1L*target - (1l*nums[i] + 1l*nums[j]);
                int low = j+1;
                int high = n-1;

                while(low < high){

                    long sum = nums[low]*1L + nums[high]*1L;

                    if(sum < tar) low++;
                    else if(sum > tar) high--;
                    else {

                        List<Integer> ls = new ArrayList<>();
                        ls.add(nums[i]);
                        ls.add(nums[j]);
                        ls.add(nums[low]);
                        ls.add(nums[high]);

                        answer.add(ls);

                        int tempStart = low, tempEnd = high;

                        while(low < high && nums[tempStart]==nums[low]) low++;  // skip equal elements
                        while(high > low && nums[tempEnd] == nums[high]) high--;   
                    }
                }

                while(j+1<n && nums[j]==nums[j+1])j++;  // skip equal elements
            }

            while(i+1 < n && nums[i]==nums[i+1]) i++;  // skip equal elements
        }


        return answer;

    }

    private List<List<Integer>> optimalSol(int[] nums, int target){

        // Use the two sum approach but first fixed two pointer already and then use the two sum approach, sort the array too. use hashset to get the unique elements
        // TC : O(Nlog N) + O(N^3)
    
        // SC : O(N) 

        Arrays.sort(nums);

        Set<List<Integer>> set = new HashSet<>();

        int n= nums.length;

        for(int i=0;i<n;i++){

            for(int j=i+1;j<n;j++){

                long tar = 1L*target - (1L*nums[i] + 1L*nums[j]);
                int x = j+1;
                int y = n-1;

                while(x < y){

                    long sum = 1L*nums[x] + 1L*nums[y];

                    if(sum > tar) y--;
                    else if(sum < tar) x++;
                    else{

                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[x]);
                        list.add(nums[y]);

                        x++;
                        y--;

                        set.add(list);
                    }
                }
            }
        }


        return new ArrayList<>(set);

    }

    private List<List<Integer>> bruteForce(int[] nums, int target){


        // Use four loops , to find the elements, and in order to get unique elements, use hashset
        
        // TC : O(N^4)
        // SC : O(N*4)
        Set<List<Integer>> set = new HashSet<>();

        int n = nums.length;
        for(int i=0;i<n;i++){

            for(int j=i+1;j<n;j++){

                for(int k=j+1;k<n;k++){

                    for(int p=k+1;p<n;p++){

                        if(nums[i] + nums[j] + nums[k] + nums[p] == target) {

                            List<Integer> list = new ArrayList<>();

                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(nums[k]);
                            list.add(nums[p]);

                            set.add(list);
                        }
                    }
                }
            }
        }

        return new ArrayList<>(set);
    }
}