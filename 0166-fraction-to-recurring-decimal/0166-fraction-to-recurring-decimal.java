class Solution {
    public String fractionToDecimal(long numerator, long denominator) {

        StringBuilder sb = new StringBuilder();

        if(numerator < 0 && denominator > 0 ){
            sb.append('-');
        }else if(numerator > 0 && denominator < 0){
            sb.append('-');
        }

        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);

        // System.out.println(numerator + " " + denominator);

        sb.append(numerator / denominator);

        numerator %= denominator;

        if(numerator == 0){
            return sb.toString();
        }

        

        sb.append(".");
        
        Map<Long,Integer> map = new HashMap<>();
        while(numerator != 0){
            if(map.containsKey(numerator)){
                
                return makeAnswer(sb, map, numerator);
            }else{

                map.put(numerator,sb.length());
            }


            numerator *= 10;
            sb.append(numerator / denominator);
            numerator %= denominator;

           
        }

        return sb.toString();
        
    }

    private String makeAnswer(StringBuilder sb, Map<Long,Integer> map, long num){

        int index = map.get(num);

        sb.append(')');
        sb.insert(index, '(');

        return sb.toString();
    }
}