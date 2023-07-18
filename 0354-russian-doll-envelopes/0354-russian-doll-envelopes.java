class Solution {
    public int maxEnvelopes(int[][] env) {

        // Tc : O(N*Log N)
        // SC : O(maximum cards that can be stored)

        // sort the matrix, according to widths, but if widths are equal sort in decending order of 
        // heights, this is because even if the increasing heights are of same width, we won't be able 
        // to indentify them and it will lead to wrong answer, therefore sorting them inn decrerasing
        // order, so the envelopes of the same width and greater height, won't get enveloped

        Arrays.sort(env, new Comparator<int[]>(){

            @Override
            public int compare(int[] a1, int[] a2){

                if(a1[0]==a2[0]) return a2[1] - a1[1];

                return a1[0] - a2[0];
             }
        });

        return solve(env);
        
    }

    int solve(int[][] env){

        List<Integer> list = new ArrayList<>();
        int n = env.length;

        list.add(env[0][1]);

        // since the widths are sorted, if last is greater than current height then, we can envelope them,
        // hence adding the height to the list, 
        for(int i=1;i<n;i++){

            int end = list.size()-1;
            if(env[i][1] > list.get(end)){
                list.add(env[i][1]);
            }else{

                // find the least index, where the height can be inserted .. ec 1 4 5, 3 can be inserted
                // like 1 3 5  (in the greater range minimize that index and in lesser range maximize that)
                int index = lowerBound(list,env[i][1]);

                list.set(index,env[i][1]);
            }

            // System.out.println(list);
        }

        return list.size();
    }

    int lowerBound(List<Integer> list,int val){

        int low = 0;
        int high = list.size()-1;

        int ans = 0;

        while(low<=high){

            int mid = high+ (low-high)/2;

            // the the value is greater and equal to val, then save it and dec. the range
            if(list.get(mid)>=val){
                ans = mid;
                high = mid-1;
            }else{
                low = mid+1;        // if less than inncrease the range
            }
        }

        return ans;
    }
}