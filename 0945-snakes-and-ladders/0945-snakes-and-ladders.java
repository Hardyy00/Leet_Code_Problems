class Solution {
    public int snakesAndLadders(int[][] board) {

        int n = board.length;
        int last = n*n;
        int[] arr = new int[last+1];

        Arrays.fill(arr, (int)1e6);
        arr[1] = 0;

        Queue<Integer> q = new LinkedList<>();

        q.offer(1);

        while(!q.isEmpty()){

            int cell = q.poll();
            // if( cell == 10 ){

            //     System.out.println(cell + " " + arr[cell]);
            // }

            // if(cell==last) continue;

            for(int i = cell + 1;i<= cell + 6 && i <= last ;i++){

                int row = row(i, n);
                int col = col(row, i,n);
                if(i == 5){

                    // System.out.println(row + " " + col);
                }

                if(board[row][col]!=-1){

                    int reach =board[row][col];

                    if(arr[cell] + 1 < arr[reach]){
                        
                        // if(reach ==  25){

                        //     System.out.println(reach + " " +  arr[cell] + " " + cell);
                        // }
                        arr[reach] = arr[cell] + 1;

                        q.offer(reach);
                    }

                }else{

                    if(arr[cell] + 1 < arr[i]){

                        arr[i] = arr[cell] + 1;
                        q.offer(i);
                    }

                }

                // System.out.println(q);
            }
        }

        // System.out.println(Arrays.toString(arr));
        return arr[last]==(int)1e6 ? -1 : arr[last];
        
    }

    private int row(int val, int n ){

        return n  - (int) Math.ceil((double)val/n);
    }

    private int col(int row, int val, int n ){

        if( (n%2==0 && row%2!=0) || (n%2!=0 && row%2==0)){

            int rem = val%n -1;
            if(rem==-1) rem = n-1;

            return rem;
        }

        int rem = val%n;

        if(rem!=0) rem = n - rem;

        return rem;
              
    }

}