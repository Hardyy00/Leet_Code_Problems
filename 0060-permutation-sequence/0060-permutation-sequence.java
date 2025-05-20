class Solution {
    public String getPermutation(int n, int k) {

        // For every element , try to make permutations like 1 + (2,3,4) , 2 + (1,3,4) ...
        // for these sets find the set for which the kth permutation belongs. i.e k/fact (1to n-1),
        // after this add the first element into the answer, and find the new k into that set.
        // i.e k % n

        // TC : O(N*N)  (for removing in each iteration)
        // SC : O(N)
        
        List<Integer> list = new ArrayList<>();
        StringBuilder  build = new StringBuilder();

        int fact = 1;

        // for the permuation of n-1 numbers
        for(int i=1;i<n;i++){
             fact *= i;
             list.add(i);
        }

        list.add(n);
        k--;

        while(true){
            
            // first element of the current k
            int firstElementInSet = list.get(k/fact);
            build.append(firstElementInSet);  
            list.remove(list.get(k/fact));

            if(list.size()==0) break;

            k =  k % fact;  // change k , cuze we need to find it, in new range
            fact /= list.size();  // reduce the fact too,

        }

        return build.toString();
    }
}