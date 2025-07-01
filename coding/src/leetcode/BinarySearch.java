package leetcode;

public class BinarySearch {
    public static void main(String[] args) {
//        System.out.println(findMin(new int[] {4, 5, 6, 7, 0, 1, 2}));
//        System.out.println(searchRotated(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(searchRotated(new int[]{1, 3, 5}, 1));
    }

    // Problem 704
    // https://leetcode.com/problems/binary-search/
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            // int mid = (left + right) / 2;
            int mid = left + ((right - left) / 2);

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    // Problem 35
    // https://leetcode.com/problems/search-insert-position/
    public static int searchInsert(int[] nums, int target) {
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
    public static int firstBadVersion(int n) {
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
    private static boolean isBadVersion(int version) {
        return version >= 1;
    }

    // Problem 367
    // https://leetcode.com/problems/valid-perfect-square/
    public static boolean isPerfectSquare(int num) {
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

    // Problem 74
    // https://leetcode.com/problems/search-a-2d-matrix
    // this is my own долбоёб's developed way, skip this shit.
    public static boolean searchMatrix(int[][] matrix, int target) {
        int outerLeft = 0, outerRight = matrix.length - 1;

        while (outerLeft <= outerRight) {
            int outerMid = outerLeft + ((outerRight - outerLeft) / 2);
            int left = 0, right = matrix[outerMid].length - 1;

            int mid = 0;
            while (left <= right) {
                mid = left + ((right - left) / 2);

                if (matrix[outerMid][mid] < target) {
                    left = mid + 1;
                } else if (matrix[outerMid][mid] > target) {
                    right = mid - 1;
                } else {
                    return true;
                }
            }

            if (target < matrix[outerMid][mid]) {
                outerRight = outerMid - 1;
            } else {
                outerLeft = outerMid + 1;
            }
        }

        return false;
    }

    // Problem 153
    // https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return nums[left];
    }

    // Problem 33
    // https://leetcode.com/problems/search-in-rotated-sorted-array/
    public static int searchRotated(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + ((right - left) / 2);

            if (nums[mid] == target) {
                return mid;
            } else if (nums[left] <= nums[mid]) {
                if (nums[mid] < target || target < nums[left]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (nums[mid] > target || target > nums[right]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return -1;
    }
}
