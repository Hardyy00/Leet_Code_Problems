class Solution {
    public int maxPoints(int[][] points) {


        int maxSize = 1;


        for(int i=0;i<points.length;i++){
            
            Map<Double, Integer> map = new HashMap<>();



            for(int j=i+1;j<points.length;j++){

                
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                double slope = dx == 0 ? Double.POSITIVE_INFINITY : (dy == 0 ? 0 : ((1.0 * dy)/ dx)) ;

                map.put(slope, map.getOrDefault(slope, 0)+1);


                maxSize = Math.max(maxSize, 1 + map.get(slope));

                

                
            }
        }

        return maxSize;
        
    }
}