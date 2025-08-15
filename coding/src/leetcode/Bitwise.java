package leetcode;

public class Bitwise {
    public static void main(String[] args) {

    }

    // Problem 163
    // https://leetcode.com/problems/single-number/
    public static int singleNumber(int[] nums) {
        int res = 0;

        for (int num : nums)
            res ^= num;

        return res;
    }

    // Problem 231
    // https://leetcode.com/problems/power-of-two/
    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
