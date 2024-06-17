class Solution {

    class Pair{
        int dis;
        int speed;

        public Pair(int d, int s){
            dis =d;
            speed =s;
        }
    }
    public int carFleet(int target, int[] position, int[] speed) {
        
        int n=  position.length;
        List<Pair> ls = new ArrayList<>();

        for(int i=0;i<n;i++){

            ls.add(new Pair(position[i], speed[i]));
        }

        Collections.sort(ls, new Comparator<Pair>(){

            @Override
            public int compare(Pair p1, Pair p2){
                return p1.dis - p2.dis;
            }
            
        });

        Deque<Double> stack = new ArrayDeque<>();

        for(int i=0;i<n;i++){

            double time = (target * 1.0 - ls.get(i).dis) / ls.get(i).speed;

            while(!stack.isEmpty() && time >= stack.peek()){
                stack.pop();
            }

            stack.push(time);
        }

        return stack.size();


    }
}