class Solution {
    public int mySqrt(int x) {
        
        long lower = 0;
        long higher = x;
        
        long ans = 0;
        
        while(lower <= higher){
            long mid = lower + (higher-lower)/2;
            
            if(mid*mid<=x){
                ans = mid;
                lower = mid+1;
            }else{
                higher = mid-1;
            }
        }
        
        return (int)ans;
        
    }
}