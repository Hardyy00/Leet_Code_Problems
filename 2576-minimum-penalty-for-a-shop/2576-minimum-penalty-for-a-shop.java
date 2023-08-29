class Solution {
    public int bestClosingTime(String customers) {
        
        int n = customers.length();

        int[] futureVisit = new int[n+1];

        for(int i=n-1;i>=0;i--){

            char ch = customers.charAt(i);
            int doesVisit = ch == 'Y' ? 1 : 0;

            futureVisit[i] = doesVisit + futureVisit[i+1];
        }


        int[] pastNotVisit = new int[n+1];

        for(int i=1;i<=n;i++){

            char ch = customers.charAt(i-1);
            int doesNotVisit = ch =='N' ? 1 : 0;

            pastNotVisit[i] = doesNotVisit + pastNotVisit[i-1]; 
        }

        int minIndex = 0;

        for(int i=0;i<=n;i++){

            if(futureVisit[i] + pastNotVisit[i] < futureVisit[minIndex] + pastNotVisit[minIndex]){

                minIndex = i;
            }

        }

        return minIndex;
    }
}