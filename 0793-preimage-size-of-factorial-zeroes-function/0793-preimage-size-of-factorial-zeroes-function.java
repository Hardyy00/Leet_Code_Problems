class Solution {
    public int preimageSizeFZF(int k) {
        
        long minIndex = binarySearch(k,true);
        long maxIndex = binarySearch(k,false);
        
        long ans = maxIndex - minIndex +1;
        if(minIndex==-1)
            ans = 0;
        
        return (int)ans;
        
    }
    
    private static long binarySearch(int x , boolean toFindMin){
        
        long lower = 0;
        long higher = Long.MAX_VALUE;
        
        long index = -1l;
        
        while(lower<=higher){
            
            long mid = lower + (higher-lower)/2;
            long zeros = getZeros(mid);
            
            if(toFindMin){
                
                if(zeros<x){
                    lower = mid+1;
                }else{
                    if(zeros==x)
                        index = mid;
                    higher = mid-1;
                }
            }else{
                
                if(zeros>x){
                    higher  = mid-1;
                }else{
                    if(zeros==x)
                        index = mid;
                    lower = mid+1;
                }
            }
            
            
        }
        
        return index;
    }
    
    private  static long getZeros(long num){
        
        long zeros = 0;
        
        long i=5;
        while(true){
            
            long div = num/i;
            if(div==0)
                break;
            
            zeros += div;
            i*=5l;
        }
        
        return zeros;
    } 
}