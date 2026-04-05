class Solution {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        // both null
        if (t1 == null && t2 == null) return true;

        // one null
        if (t1 == null || t2 == null) return false;

        // values must match + recursive mirror check
        return (t1.val == t2.val) &&
               isMirror(t1.left, t2.right) &&
               isMirror(t1.right, t2.left);
    }
}