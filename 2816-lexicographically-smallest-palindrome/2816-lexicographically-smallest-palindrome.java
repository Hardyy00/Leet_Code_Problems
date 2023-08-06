class Solution {
    public String makeSmallestPalindrome(String s) {
        
        int i = 0;
        int j = s.length()-1;
        
        char[] arr = new char[s.length()];
        
        while(i<=j){
            
            char c1 = s.charAt(i);
            char c2 = s.charAt(j);
            
            if(c1<c2){
                arr[i]=arr[j]=c1;
            }else{
                arr[i]=arr[j]=c2;
            }
            
            i++;
            j--;
        }
        
        return new String(arr);
        
    }
}