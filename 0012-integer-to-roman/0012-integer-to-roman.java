class Solution {
    public String intToRoman(int num) {
        

        StringBuilder sb = new StringBuilder();

        sb.append( "M".repeat(num / 1000));
        num = num % 1000;

        int firstDigit = num / 100;

        if(firstDigit == 4){
            sb.append("CD");
            num %= 400;
        }else if(firstDigit == 9){
            sb.append("CM");
            num %= 900;
        }else{
            sb.append("D".repeat(num / 500));;
            num %= 500;
        }

        sb.append("C".repeat(num /100));

        num %= 100;

        firstDigit = num / 10;

        if(firstDigit == 4){
            sb.append("XL");
            num %= 40;
        }else if(firstDigit == 9){
            sb.append("XC");
            num %= 90;
        }else{
            sb.append("L".repeat(num /50));
            num %= 50;
        }

        sb.append("X".repeat(num /10));
        num %= 10;

        if(num==4){
            sb.append("IV");
            num %= 4;
        }else if(num == 9){
            sb.append("IX");
            num %= 9;
        }else {
            sb.append("V".repeat(num / 5));
            num %= 5;
        }

        sb.append("I".repeat(num));

        return sb.toString();

       
        
    }

}