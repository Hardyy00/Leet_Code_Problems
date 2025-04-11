class Solution {
    public int countSymmetricIntegers(int low, int high) {
        
        if(high <=10) return 0;
        
        int num = low;
        int count = 0;
        
        while(num <= high){
            
            if(num >=100 && num <=999) num= 1000;
            
            String temp = num + "";
            
            int h = temp.length()/2;
            int s1 = 0;
            int s2 = 0;
            
            for(int i=0;i<h;i++) s1 += temp.charAt(i)-'0';
            
            for(int i=h;i<temp.length();i++) s2 += temp.charAt(i) - '0';
            
            if(s1 == s2) count++;
            
            num++;
            
            
        }
        
        return count;
        
    }
}