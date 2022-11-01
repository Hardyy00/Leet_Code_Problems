class Solution {
    public List<Integer> spiralOrder(int[][] arr) {
        
        List<Integer> myList = new ArrayList<>();
        int minr = 0;
		int minc = 0;
		int maxr = arr.length-1;
		int maxc = arr[0].length-1;
		int count = 0;
		int totalElements = arr.length * arr[0].length;
		while(count<totalElements) {
			
			for(int i=minc;i<=maxc && count<totalElements;i++) {
				myList.add(arr[minr][i]);
				count++;
			}
			minr++;
			
			for(int i=minr;i<=maxr && count<totalElements;i++) {
				myList.add(arr[i][maxc]);
				count++;
			}
			maxc--;
			for(int i=maxc; i>=minc && count<totalElements; i--) {
				myList.add(arr[maxr][i]);
				count++;
			}
			maxr--;
			
			for(int i=maxr;i>=minr && count<totalElements;i--) {
				myList.add(arr[i][minc]);
				count++;
			}
			minc++;
		}
        
        return myList;
        
    }
}