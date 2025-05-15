class Solution {
    public double myPow(double x, int n) {

    //    return bruteForce(x,n);

    //    return binaryExponentiation(x,n);

        return interativeBinaryExpo(x,n);
    }

    private double interativeBinaryExpo(double x, int n){

        // TC : O(logN)
        // sC : O(1)
        double ans = 1;

        long copyN = n;   // for case -2147483648 , if convert to +ve it will overflow

        if(n < 0) copyN = -copyN;  


        while(copyN>0){
            
            // since the power is carried till ans, we save it here
            if(copyN % 2 !=0){
                ans *= x;
                copyN--;
            }else{

                x = x*x;
                copyN /=2;
            }
        }

        if(n <0) return 1/ans;

        return ans;
    }


    private double binaryExponentiation(double x, int n){

        // TC : O(logN)
        // SC : O(1) or o(logN) (logN bcuz of implicit stack space)

        if(x==0) return 0;
        else if(n==0) return 1;


        if(n <0) return 1/recursion(x,-n);

        return recursion(x,n);
    }

    private double recursion(double x, int n){

        if(x==0) return 0;
        else if(n==0) return 1;
        else if(n==1) return x;

        double power = recursion(x,n/2);

        if((n&1)==1) return power *power * x;

        return power *power;
    }

    private double bruteForce(double x, int n){
         if(x==0) return 0;
        else if(n==0) return 1;

        int sign = x < 0 && (n &1)==1  ? -1 : 1;

        if(n < 0) return sign * (1.0/power(x,-n));

        return sign * power(x,n);
    }

    private double power(double x, int n){

        double ans = 1;

        for(int i=1;i<=n;i++) ans *= x;

        return ans;
    }
}