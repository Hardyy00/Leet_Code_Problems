class Solution {
    Map<Long, Long> map = new HashMap<>();
    public int integerReplacement(int n) {
        
        return (int) memo(n);
        
    }

    private long memo(long n){

        if(n==1){
            return 0;
        }

        if(map.containsKey(n)){
            return map.get(n);
        }



        if(n % 2 == 0){

            long val = 1 + memo(n/2);
            map.put(n, val);

            return val;
        }

        long add = 1 + memo(n + 1);
        long sub = 1 + memo(n-1);

        map.put(n , Math.min(add, sub));

        return Math.min(add, sub);
    }
}