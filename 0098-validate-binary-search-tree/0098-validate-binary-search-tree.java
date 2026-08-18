class Solution {

    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long min, long max) {

        // Empty subtree is valid
        if (node == null) {
            return true;
        }

        // Current node must be strictly within the allowed range
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // Left subtree: values must be less than node.val
        // Right subtree: values must be greater than node.val
        return validate(node.left, min, node.val)
            && validate(node.right, node.val, max);
    }
}