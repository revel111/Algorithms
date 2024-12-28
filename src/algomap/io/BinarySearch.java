package algomap.io;

public class BinarySearch {
    public static void main(String[] args) {

    }

    // Problem 704
    // https://leetcode.com/problems/binary-search/
    public int search(int[] nums, int target) {
        int i = 0, j = nums.length - 1;

        while(i <= j) {
            int mid = (j + i) / 2;

            if (nums[mid] == target)
                return mid;
            else if (target < nums[mid])
                j = mid - 1;
            else
                i = mid + 1;
        }

        return -1;
    }

    // Problem 35
    // https://leetcode.com/problems/search-insert-position/
    public int searchInsert(int[] nums, int target) {
        int i = 0, j = nums.length - 1, mid = 0;

        while (i <= j) {
            mid = (i + j) / 2;

            if (nums[mid] == target)
                return mid;
            else if (target < nums[mid])
                j = mid - 1;
            else
                i = mid + 1;
        }

        return nums[mid] > target ? mid : mid + 1;
    }

    // Problem 278
    // https://leetcode.com/problems/first-bad-version/
    public int firstBadVersion(int n) {
        int i = 1, j = n, bad = 1;

        while (i <= j) {
            int mid = i + (j - i) / 2;

            if (isBadVersion(mid)) {
                bad = mid;
                j = mid - 1;
            } else
                i = mid + 1;
        }

        return bad;
    }

    // MOCK FUNCTION
    private boolean isBadVersion(int version) {
        return version >= 1;
    }

    // Problem 367
    // https://leetcode.com/problems/valid-perfect-square/
    public boolean isPerfectSquare(int num) {
        int i = 1, j = num;

        while (i <= j) {
            int mid = (i + j) / 2;

            long sqrt = (long) mid * mid;
            if (sqrt == num)
                return true;
            else if (sqrt < num)
                i = mid + 1;
            else
                j = mid - 1;
        }

        return false;
    }


}
