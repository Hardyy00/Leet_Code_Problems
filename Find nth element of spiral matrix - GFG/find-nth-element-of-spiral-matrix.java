//{ Driver Code Starts
import java.util.*;

class Find_Given_Element_Of_Spiral_Matrix 
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t > 0)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			int k = sc.nextInt();
			int arr[][] = new int[1000][1000];
			for(int i=0; i<n; i++)
			{
				for(int j=0; j<m; j++ )
				{
					arr[i][j] = sc.nextInt();
				}
			}
			System.out.println(new GfG().findK(arr, n, m, k));
		t--;
		}
	}
}
// } Driver Code Ends


class GfG
{
    /*You are required to complete this method*/
    int findK(int mat[][], int n, int m, int k)
    {
	    int minRow = 0;
	    int minCol = 0;
	    int maxRow = n-1;
	    int maxCol = m-1;
	    int totalEle = n*m;
	    int count = 0;
	    
	    while(count < totalEle){
	        
	        for(int i=minCol;i<=maxCol && count < totalEle;i++){
	            
	            if(count+1 == k)
	                return mat[minRow][i];
	                
	           count++;
	        }
	        
	        minRow++;
	        
	        for(int i=minRow; i<=maxRow && count< totalEle;i++){
	            
	            if(count+1==k)
	                return mat[i][maxCol];
	                
	           count++;
	        }
	        
	        maxCol--;
	        
	        for(int i=maxCol;i>=minCol && count < totalEle;i--){
	            
	            if(count+1==k)
	                return mat[maxRow][i];
	                
	           count++;
	        }
	        
	        maxRow--;
	        
	        for(int i=maxRow;i>=minRow && count<totalEle;i--){
	            
	            if(count+1==k)
	                return mat[i][minCol];
	                
	           count++;
	        }
	        
	        minCol++;
	    }
	    
	    return -1;
    }
}