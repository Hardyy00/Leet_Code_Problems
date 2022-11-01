class Solution {
     public void nextPermutation(int[] arr) {

       int p = -1;
		int q =-1;
		
		for(int i=arr.length-2;i>=0;i--) {
			
			if(arr[i]<arr[i+1]) {
				p=i;
				break;
			}
		}
		
		if(p==-1) {
			reverse(arr,0);
			return;
			
		}
		
		for(int i=arr.length-1;i>p;i--) {
			
			if(arr[i]>arr[p]) {
				q=i;
				break;
			}	
		}
		
		int temp = arr[p];
		arr[p] = arr[q];
		arr[q] = temp;
		
		reverse(arr,p+1);
		
		
    }

    private static void reverse(int[] arr , int i) {
		int j = arr.length-1;
		
		while(i<j) {
			
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			
			i++;
			j--;
		}
	}
}