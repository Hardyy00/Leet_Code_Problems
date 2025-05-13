class Solution {
    public List<List<Integer>> generate(int numRows) {

        // Approach : for each row iterate row-2 times to calculate new element of the row, get those previous elements
        // from previous row
        
        // TC : O(N^2)
        // SC : O(1)

        int n = numRows;
        List<List<Integer>> ans= new ArrayList<>();

        List<Integer> temp = new ArrayList<>(); 

        temp.add(1);

        ans.add(temp);

        for(int i=2;i<=n;i++){

            List<Integer> ls = new ArrayList<>();

            ls.add(1);

            for(int j=1;j<=i-2;j++){

                ls.add(ans.get(ans.size()-1).get(j) + ans.get(ans.size()-1).get(j-1));
            }

            ls.add(1);

            ans.add(ls);
            
        }

        return ans;
    }
}