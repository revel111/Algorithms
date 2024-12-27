package algomap.io;

public class TwoPointers {
    public static void main(String[] args) {

    }

    // Problem 977
    // https://leetcode.com/problems/squares-of-a-sorted-array/t
    public int[] sortedSquares(int[] nums) {
        int i = 0, j = nums.length - 1;
        int[] result = new int[nums.length];
        int k = nums.length - 1;

        while (i <= j) {
            int left = nums[i] * nums[i];
            int right = nums[j] * nums[j];

            if (left < right) {
                result[k] = right;
                j--;
            } else {
                result[k] = left;
                i++;
            }
            k--;
        }

        return result;
    }

    // Problem 344
    // https://leetcode.com/problems/reverse-string/
    public void reverseString(char[] s) {
        int i = 0, j = s.length - 1;

        while (i <= j) {
            char temp = s[j];
            s[j--] = s[i];
            s[i++] = temp;
        }
    }

    
}
