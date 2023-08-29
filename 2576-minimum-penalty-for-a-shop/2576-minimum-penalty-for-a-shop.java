class Solution {
    public int bestClosingTime(String customers) {
        
       return solve(customers);
    }

    private int solve(String customers){

        // Using a prefix sum and the suffix sum, to know what is the penalty if
        // i close at ith day.

        // TC : O(3N)
        // SC : O(2N)

        int n = customers.length();

        int[] futureVisit = new int[n+1];  // O TO N RANGE

        // calculate the penalty of future visit, if the shop is closed at ith day
        for(int i=n-1;i>=0;i--){

            char ch = customers.charAt(i);
            int doesVisit = ch == 'Y' ? 1 : 0;  // if current day a customer visit

            futureVisit[i] = doesVisit + futureVisit[i+1];
        }

        int minIndex = 0;
        
        // in past if a customer visited or not
        int[] pastNotVisit = new int[n+1];

        for(int i=1;i<=n;i++){

            char ch = customers.charAt(i-1);
            int doesNotVisit = ch =='N' ? 1 : 0;

            pastNotVisit[i] = doesNotVisit + pastNotVisit[i-1]; 

            if(futureVisit[i] + pastNotVisit[i] < futureVisit[minIndex] + pastNotVisit[minIndex]){

                minIndex = i;
            }
        }

        // int minIndex = 0;

        // for(int i=0;i<=n;i++){

        //     if(futureVisit[i] + pastNotVisit[i] < futureVisit[minIndex] + pastNotVisit[minIndex]){

        //         minIndex = i;
        //     }

        // }

        return minIndex;
    }
}