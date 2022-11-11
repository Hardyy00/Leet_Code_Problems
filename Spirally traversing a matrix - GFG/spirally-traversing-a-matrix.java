//{ Driver Code Starts
import java.io.*;
import java.util.*;
class GFG
{
    public static void main(String args[])throws IOException
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while(t-- > 0)
        {
            int r = sc.nextInt();
            int c = sc.nextInt();
            
            int matrix[][] = new int[r][c];
            
            for(int i = 0; i < r; i++)
            {
                for(int j = 0; j < c; j++)
                 matrix[i][j] = sc.nextInt();
            }
            Solution ob = new Solution();
            ArrayList<Integer> ans = ob.spirallyTraverse(matrix, r, c);
            for (Integer val: ans) 
                System.out.print(val+" "); 
            System.out.println();
        }
    }
}
// } Driver Code Ends


class Solution
{
    static ArrayList<Integer> spirallyTraverse(int mat[][], int r, int c)
    {
        ArrayList<Integer> list = new ArrayList<>();
        
        int minRow = 0;
        int maxRow = r-1;
        int minCol = 0;
        int maxCol = c-1;
        
        int totalEle = r*c;
        int count = 0;
        while(count < totalEle ){
            
            for(int i=minCol;i<=maxCol && count < totalEle;i++){
                list.add(mat[minRow][i]);
                count++;
            }
            
            minRow++;
            
            for(int i=minRow;i<=maxRow && count<totalEle;i++){
                list.add(mat[i][maxCol]);
                count++;
            }
            
            maxCol--;
            
            for(int i=maxCol;i>=minCol && count<totalEle ;i--){
                list.add(mat[maxRow][i]);
                count++;
            }
            
            maxRow--;
            
            for(int i=maxRow;i>=minRow && count < totalEle ;i--){
                list.add(mat[i][minCol]);
                count++;
            }
            minCol++;
        }
        
        
        return list;
    }
}
