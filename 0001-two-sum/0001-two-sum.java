class Solution {
    class Pair{
        int num;
        int index;

        public Pair(int num, int index){
            this.num = num;
            this.index = index;
        }
    }

    public int[] twoSum(int[] nums, int target) {
        
        // return bruteForce(nums, target);

        // return betterSolution(nums, target);

        return optimalSol(nums, target);
    }

    private int[] optimalSol(int[] nums, int target){

        // Optimal Solution : use hashmap to store all , value with their indexes,and at any time
        // try to find the target-nums[i] , if exist then return indexes, handle the case of same element like 6 = 3+3

        // TC : O(N)
        // SC : O(N) 


        Map<Integer,Integer> map = new HashMap<>();

        int n = nums.length;

        for(int i=0;i<n;i++){

            map.put(nums[i], i);
        }

        for(int i=0;i<n;i++){
            
            if(map.containsKey(target-nums[i]) && !map.get(target-nums[i]).equals(i) ) {

                return new int[]{i, map.get(target-nums[i])};
            }
        }

        return null;
    }

    private int[] betterSolution(int[] nums, int target){

        // Sort the array , and also save the index of the elements in the original array,, then use 2 pointers

        // TC : O(NlogN)
        // SC : O(N)

        List<Pair> list = new ArrayList<>();

        int n = nums.length;
        for(int i=0;i<n;i++){
            list.add(new Pair(nums[i], i));
        }

        Collections.sort(list, new Comparator<Pair>(){

            @Override
            public int compare(Pair p1, Pair p2){

                return p1.num - p2.num;
            }
        });

        int i=0;
        int j=n-1;

        while(i<j){

            if(list.get(i).num + list.get(j).num == target) return new int[]{list.get(i).index,list.get(j).index};
            else if(list.get(i).num + list.get(j).num > target) j--;
            else i++;
        }

        return null;
        
    }

    private int[] bruteForce(int[] nums,int target){

        // Brute Force : use nested loops to find such numbers

        // TC : O(N^2)
        // SC : O(1)

        int n = nums.length;

        for(int i=0;i<n;i++){

            for(int j=i+1;j<n;j++){

                if(nums[i]+nums[j] == target) return new int[]{i,j};
            }
        }

        return null;
    }
}