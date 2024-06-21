class Solution {
    public boolean isHappy(int n) {

        Set<Integer> set = new HashSet<>();

        while(n !=1){

            if(set.contains(n)){
                return false;
            }else{
                set.add(n);
            }

            int digitSum = 0;

            while(n > 0){
                int d = n % 10;
                n /= 10;

                digitSum += d*d;
            }

            n = digitSum;

        }

        return true;
        
    }
}