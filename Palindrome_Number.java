import java.util.Scanner;

class Solution{
    
    public boolean isPalindrome(int num) {
            
        if(num < 0){
           return false;
        }else{
            
            long sum = 0;
            long copy = num;
            
            while(copy != 0){
                
                sum = sum * 10 + (copy%10);
                copy /= 10;
            }
            
            return sum == num; 
        }
    }
}