class Solution {
    public List<List<Integer>> generate(int numRows) {
        
        List<List<Integer>> pascalList = new ArrayList<>();
        
        int row = 0;
        int column = 1;
        while(row<numRows){
            
            List<Integer> list = new ArrayList<>();
            int i=0;
            int nextElement =1;
            while(i<column){
                list.add(nextElement);
                nextElement = ((row - i)*nextElement)/(i+1);
                i++;
            }
            
            row++;
            column++;
            pascalList.add(list);
        }
        
        return pascalList;
        
        
    }
}