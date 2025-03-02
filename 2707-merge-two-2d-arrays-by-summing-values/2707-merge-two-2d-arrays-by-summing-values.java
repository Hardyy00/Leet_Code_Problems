class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        
        int i = 0;
        int j = 0;
        int count = 0;
        
        while(i<nums1.length &&  j<nums2.length){
            
            if(nums1[i][0]<nums2[j][0]){
                i++;
            }else if(nums1[i][0]>nums2[j][0]){
                j++;
            }else{
                i++;
                j++;
            }
            
            count++;
        }
        
        while(i<nums1.length){
            count++;
            i++;
        }
        
        while(j<nums2.length){
            count++;
            j++;
        }
        
        i=j=0;
        
        int[][] ans = new int[count][2];
        int index =0;
        
        while(i<nums1.length && j<nums2.length){
            
            if(nums1[i][0] < nums2[j][0]){
                ans[index][0] = nums1[i][0];
                ans[index][1] = nums1[i][1];
                i++;
                // index++;
            }else if(nums1[i][0] > nums2[j][0]){
                ans[index][0] = nums2[j][0];
                ans[index][1] = nums2[j][1];
                // index++;
                j++;
            }else{
                
                ans[index][0] = nums1[i][0];
                ans[index][1] = nums1[i][1] + nums2[j][1];
                i++;
                j++;
            }
            
            index++;
        }
        
        while(i<nums1.length){
            ans[index] = nums1[i];
            index++;
            i++;
        }
        
        while(j<nums2.length){
            ans[index] = nums2[j];
            index++;
            j++;
        }
        
        return ans;
    }
}