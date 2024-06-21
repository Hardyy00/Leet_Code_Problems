class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {

        int n= customers.length;
        int maxSum = 0;
        int totSum = 0;

        for(int i=0;i<n;i++){

            if(grumpy[i]==0) {
                totSum += customers[i];
            }
        }

        int originalSum = 0, nonGrumpySum = 0;

        for(int i=0;i<minutes;i++){

            nonGrumpySum += customers[i];

            if(grumpy[i]==0){
                originalSum += customers[i];
            }
        }

        maxSum = Math.max(maxSum , totSum - originalSum + nonGrumpySum);

        for(int i=minutes;i<n;i++){

            nonGrumpySum -= customers[i-minutes];
            nonGrumpySum += customers[i];

            originalSum -= grumpy[i-minutes]==0 ? customers[i-minutes] : 0;

            originalSum += grumpy[i]==0 ? customers[i] : 0;

            maxSum = Math.max(maxSum, totSum - originalSum + nonGrumpySum);
        }

        return maxSum;
        
    }
}