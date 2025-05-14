class Solution {
    public int[][] merge(int[][] intervals) {
        
        // return solve(intervals);

        return mergeIntervals(intervals);
    }

    private int[][] mergeIntervals(int[][] intervals){

        // Optimal Approach : ALways maintain , a end of interval, if the current interval is less or equal
        // to the end, then merge it, else stop the merge, and first add the current end, and start 
        // a new interval 

        // for grouping , smaller starting intervals , sort them.

        // TC : O(N*logN) + O(N)
        // SC : O(N)

        Arrays.sort(intervals, new Comparator<int[]>(){

            @Override
            public int compare(int[] a1, int[] a2){

                if(a1[0]==a2[0]) return a1[1] - a2[1];  // if same starting index, then merge them according to end time

                return a1[0] - a2[0];
            }
        });

        List<List<Integer>> intervalsList = new ArrayList<>();

        List<Integer> temp= new ArrayList<>();
        temp.add(intervals[0][0]);
        int end = intervals[0][1];

        intervalsList.add(temp);

        int n= intervals.length;

        for(int i=1;i<n;i++){

            // if interval cannot be merged
            if(end < intervals[i][0]){
                intervalsList.get(intervalsList.size()-1).add(end);  // add the end ,to the previous interval
                List<Integer> nextInterval = new ArrayList<>();  // start a new interval
                nextInterval.add(intervals[i][0]);
                intervalsList.add(nextInterval);
                end= intervals[i][1];  // initialise a new interval
            }else{

                end = Math.max(end, intervals[i][1]);  // merge the intervals
            }
        }

        intervalsList.get(intervalsList.size()-1).add(end);  // add the final end

        int intervalsLength = intervalsList.size();

        int[][] answer = new int[intervalsLength][2];

        for(int i=0;i<intervalsLength;i++){

            answer[i][0] = intervalsList.get(i).get(0);
            answer[i][1] = intervalsList.get(i).get(1);
        }

        return answer;


    }

    private int[][] solve(int[][] intervals){

        // Brute Force : first insert a interval and then try to merge all the intervals that can be merged with it
        // TC : O(Nlog N) + O(2N) (i am visiting an element only twice)
        // SC : O(N) (at most n will be the space when intervals can't  be merged)

        // sort the intervals
        Arrays.sort(intervals, new Comparator<int[]>(){

            @Override
            public int compare(int[] p1, int[] p2){

                if(p1[0]==p2[0]) return p1[1] - p2[1];

                return p1[0]-p2[0];
            }
        });

        int n = intervals.length;
        List<List<Integer>> ls = new ArrayList<>();

        
        for(int i=0;i<n;i++){
            // interval to be inserted
            int start = intervals[i][0];
            int end = intervals[i][1];

            // if it is already inserted then skip it , 
            if(!ls.isEmpty() && ls.get(ls.size()-1).get(1) >= end){
                continue;
            }

            // try to merge other intervals
            for(int j=i+1;j<n;j++){

                if(intervals[j][0] <= end){
                    end = Math.max(end,intervals[j][1]);
                }else{
                    break;  // if an interval cannot be merged , break through the loop
                }
            }

            List<Integer> temp = new ArrayList<>();
            temp.add(start);
            temp.add(end);

            ls.add(temp);
        }


        int len= ls.size();

        int[][] ans = new int[len][2];

        for(int i=0;i<len;i++){
            ans[i][0] = ls.get(i).get(0);
            ans[i][1] = ls.get(i).get(1);
        }

        return ans;
    }
}