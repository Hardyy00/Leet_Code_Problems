class Solution {
    public List<List<Integer>> combinationSum(int[] arr, int target) {

        // TC: O(2^T *k) k is the average length of the each combination and since i
        // can a element multiple times so the t > n

        List<List<Integer>> master = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        fill(arr,list,master,0,target);

        return master;
        
    }

    private static void fill(int[] arr ,List<Integer> list,List<List<Integer>> master , int index,int target){

        if(target==0){
            master.add(new ArrayList<>(list));
            return;
        }

        if(index>=arr.length){
            return;
        }

        // pick the element
        if(arr[index]<=target){
            list.add(arr[index]);
            fill(arr,list,master,index,target-arr[index]);
            list.remove(list.size()-1);
        }

        // not pick
        fill(arr,list,master,index+1,target);
    }
}