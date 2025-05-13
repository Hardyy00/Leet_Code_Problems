class Solution {
    public void nextPermutation(int[] nums) {

        // solve(nums);
        solve2(nums);


    }

    private void solve2(int[] nums){

        // Optimized approach : condition at which next permutation is not possible : when all i > i+1
        // so find at which index the condition is first failing,  now from the right side find the first element
        // which is greater than this index element, swap then , now reverse the whole array from that index.
        // all the nums before the 2nd index are smaller than the first index element, so it makes sense for them to 
        // come first , hence reverse the whole array after p+1, so that all the element less than (previous) pth element
        // come first

        // TC : O(N)
        // SC : O(1)

        if(nums.length==1) return;

        int n = nums.length;

        int p = -1;

        for(int i=n-2;i>=0;i--){
            // first index at which condition is failing 
            if(nums[i] < nums[i+1]){
                p = i;
                break;
            }
        }

        if(p==-1){
            // if no such index reverse the whole array
            reverse(0,n-1,nums);
            return;
        }

        for(int i=n-1;i>p;i--){

            // swap first element greater than pth element
            if(nums[i] > nums[p]){

                int temp = nums[p];
                nums[p] = nums[i];
                nums[i] = temp;
                break;
            }
        }

        reverse(p+1,n-1,nums);  // reverse after pth element
    }

    private void reverse(int i, int j, int[] nums){

        while(i<j){

            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    private void solve(int[] nums){
        // TC : O(N! * N)
        // SC : O(N! * N) (for n! permutatios and length of each permutation is n)

        // Brute force : For the sorted version of the array generate all permutations, in those permutations
        // find the index of given permutations , now change this permuatation to index+1 the permutation
        

        int n = nums.length;
        int[] copy = Arrays.copyOfRange(nums,0,n);  // cuz we are mutation the original one
        Arrays.sort(nums);

        List<List<Integer>> per = new ArrayList<>();

        boolean[] visit = new boolean[n];

        // tc : O(n! * n)
        genPermutations(new ArrayList<>(), per,nums,visit);

        for(int i=0;i<per.size();i++){

            if(isEqual(per.get(i),copy)){

                int ans = (i+1)%per.size();
                // change to i+1 (next permutation)
                for(int j=0;j<n;j++){

                    nums[j] = per.get(ans).get(j);
                   
                }

                break;
            }

        }

        // for(var ls : per) System.out.println(ls);
    }

    private boolean isEqual(List<Integer> ls, int[] nums){

        int n= nums.length;

        for(int i=0;i<n;i++) if(!ls.get(i).equals(nums[i])) return false;

        return true;
    }

    private void genPermutations(List<Integer> temp, List<List<Integer>> ls , int[] nums, boolean[] visit){

        if(temp.size()==nums.length){

            ls.add(new ArrayList<>(temp));
            return;
        }

        int n= nums.length;
        for(int i=0;i<n;i++){

            if(visit[i]) continue;

            visit[i] = true;
            temp.add(nums[i]);
            genPermutations(temp,ls,nums,visit);
            visit[i] = false;
            temp.remove(temp.size()-1);
        }
    }
}