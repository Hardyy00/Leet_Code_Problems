class Solution {
    String[] ones = {"", "One " , "Two ", "Three " , "Four ", "Five ", "Six " , "Seven ", "Eight ", "Nine ", "Ten " , "Eleven ", "Twelve " , "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen "  };
    String[] tens = {"", "", "Twenty ", "Thirty ","Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety " };

    private String numToWord(int num, String suffix){

        StringBuilder sb = new StringBuilder();
        
        if(num >=100){
            sb.append(ones[num /100 ]);
            sb.append("Hundred ");
        }


        if( (num % 100) > 19 ){
            sb.append(tens[ (num % 100) / 10]);
            sb.append(ones[ num  % 10]);
        }else{
            sb.append(ones[ num %  100 ]);
        }

        if(num > 0){
            sb.append(suffix);
        }

        return sb.toString();

    }

    public String numberToWords(int num) {

        if(num ==0){
            return "Zero";
        }

        StringBuilder sb = new StringBuilder();

        sb.append( numToWord( num / 1000000000,  "Billion "  ) );
        sb.append( numToWord( (num / 1000000) % 1000 , "Million "  ) );
        sb.append( numToWord( (num / 1000) % 1000, "Thousand " ) );
        sb.append( numToWord( num % 1000, "" ) );

        sb.deleteCharAt(sb.length()-1);


        
        return sb.toString();
    }
}
