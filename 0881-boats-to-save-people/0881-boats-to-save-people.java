class Solution {
    public int numRescueBoats(int[] people, int limit) {
        
        Arrays.sort(people);
    
        int boats = 0;
        int i=0;
        int j=people.length-1;
        
            
        while(i<=j){
            
            if(people[j]+people[i]>limit){
                j--;
            }else{
                i++;
                j--;
            }
            
            boats++;
        }
        
        return boats;
        
    }
}