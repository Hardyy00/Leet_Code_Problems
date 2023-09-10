class Solution {
    int mod = (int) 1e9 + 7;
    public int countOrders(int n) {

        long count = 1;
        for(long i=1;i<=n;i++){
            count = (count%mod) *  (((2*i)%mod-1) * i)%mod;

        }

        return (int)count%mod;
        
    }
}