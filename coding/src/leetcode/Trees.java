package leetcode;

public class Trees {

    public static void main(String[] args) {
        System.out.println(isSameTree(new TreeNode(10, new TreeNode(5), new TreeNode(15)), new TreeNode(10, new TreeNode(5, null, new TreeNode(15)), null)));
    }

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

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    // Problem 543
    // https://leetcode.com/problems/diameter-of-binary-tree/
    private static class SolutionProblem543 {
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

    // Problem 100
    // https://leetcode.com/problems/same-tree/
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if ((p != null && q != null && p.val != q.val) ||
                (p != null && q == null) ||
                (q != null && p == null)) {
            return false;
        } else if (p == null && q == null) {
            return true;
        }

        boolean leftResult = isSameTree(p.left, q.left);
        boolean rightResult = isSameTree(p.right, q.right);

        return leftResult && rightResult;
    }

    // Problem 700
    // https://leetcode.com/problems/search-in-a-binary-search-tree/
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null) {
            if (root.val == val) {
                return root;
            } else if (root.val < val) {
                root = root.right;
            } else {
                root = root.left;
            }
        }

        return null;
    }
}
