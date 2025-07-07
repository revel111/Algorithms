package leetcode;

public class Trees {
    private static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {

    }

    // Problem 226
    // https://leetcode.com/problems/invert-binary-tree/
    public static TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    // Problem 104
    // https://leetcode.com/problems/maximum-depth-of-binary-tree/
    public static int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }

    // Problem 543
    // https://leetcode.com/problems/diameter-of-binary-tree/
    private int result = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        this.dfs(root);
        return result;
    }

    public int dfs(TreeNode root) {
        if (root == null)
            return 0;

        int leftHeight = this.dfs(root.left);
        int rightHeight = this.dfs(root.right);

        this.result = Math.max(this.result, leftHeight + rightHeight);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
