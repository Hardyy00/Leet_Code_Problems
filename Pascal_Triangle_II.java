import java.util.List;
import java.util.ArrayList;


class Solution {
    public List<Integer> getRow(int rowIndex) {
        
        List<Integer> list = new ArrayList<>();
        
        int var = 1;
        int column =  rowIndex + 1;
        int i=0;
        
        while(i<column){
            list.add(var);
            var = (int)(((long)(rowIndex - i)*(long)var)/(i+1));
            i++;
        }
        
        return list;
    }
} 
