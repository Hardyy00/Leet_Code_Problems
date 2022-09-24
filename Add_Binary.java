class Solution {
    public String addBinary(String a, String b) {
 
         int lengthA = a.length();
         int lengthB = b.length();
 
         if (lengthA > lengthB) {
             b = "0".repeat(lengthA - lengthB) + b;
         } else if (lengthB > lengthA) {
             a = "0".repeat(lengthB - lengthA) + a;
         }
 
         String ans = "";
         int maxLength = Math.max(lengthA, lengthB);
         int carry = 0;
         for (int i = maxLength - 1; i >= 0; i--) {
 
             int val1 = Integer.valueOf(a.charAt(i) + "");
             int val2 = Integer.valueOf(b.charAt(i) + "");
             int sum = val1 + val2 + carry;
 
             ans = ("" + (sum % 2)) + ans;
             carry = sum / 2;
 
         }
 
         if (carry == 1)
             ans = ("" + carry) + ans;
 
         return ans;
     }
 }