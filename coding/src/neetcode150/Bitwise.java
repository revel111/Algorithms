package neetcode150;

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
}
