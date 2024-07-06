class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {

      
        // return solve(root, subRoot);

        return solve2(root, subRoot);
    }

    private boolean solve2(TreeNode root, TreeNode subRoot){

        // TC : O(N + M) (where N > n && M > m)
        // SC : O(M)

        StringBuilder sb1 =new StringBuilder(), sb2 = new StringBuilder();
        sb1.append(" ");  // for the cases like 12 null null, and patter 2 null null, we add space in between
        sb2.append(" ");
        serialize(root, sb1);
        serialize(subRoot,sb2);
        String serialMain = sb1.toString() ;
        String serialPattern = sb2.toString();

        int[] lps = generateLPS(serialPattern);

        int i=0, j=0;
        int n= serialMain.length(), m = serialPattern.length();

        while(i < n && j < m){

            if(serialMain.charAt(i)==serialPattern.charAt(j)){
                i++;
                j++;
            }

            if(j==m){
                return true;
            }

            if(i < n &&serialMain.charAt(i) != serialPattern.charAt(j)){

                if(j > 0){
                    j = lps[j-1];
                }else{
                    i++;
                }
            }
        }

        return false;

    }

    private int[] generateLPS(String s){

        int n= s.length();
        int[] lps = new int[n];
        int i=1, len =0;

        while(i < n){

            if(s.charAt(i)==s.charAt(len)){
                len++;
                lps[i]= len;
                i++;
            }else{

                if(len > 0){
                    len = lps[len-1];
                }else{
                    i++;
                }
            }
        }

        return lps;
    }

    private void serialize(TreeNode root, StringBuilder sb){

        if(root==null){
            sb.append("null ");
            return;
        }

        sb.append(root.val + " ");
        serialize(root.left, sb);
        serialize(root.right, sb);
      
    }

    private boolean solve(TreeNode root, TreeNode subRoot){
          // TC : O(N * N) && SC : O(N) (recursion stack)
        // upon finding node with same value as the subtree root apply isSame method
        return preorder(root,subRoot);
    }

    boolean preorder(TreeNode root, TreeNode subRoot){

        if(root==null) return false;

        if(root.val==subRoot.val){ 
            if(isSame(root,subRoot)) return true;
        }

        if(preorder(root.left,subRoot)) return true;

        if(preorder(root.right,subRoot)) return true;

        return false;

    }

    boolean isSame(TreeNode root1,TreeNode root2){

        if(root1==null && root2==null) return true;
        else if(root1==null || root2==null) return false;

        return root1.val==root2.val && isSame(root1.left,root2.left)
                    && isSame(root1.right,root2.right);
    }
}