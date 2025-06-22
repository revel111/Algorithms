package leetcode;

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

    // Problem 167
    // https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;

        while (i < j) {
            int sum = numbers[i] + numbers[j];

            if (sum == target)
                return new int[]{i + 1, j + 1};
            else if (sum < target)
                i++;
            else
                j--;
        }

        return new int[]{};
    }

    // Problem 125
    // https://leetcode.com/problems/valid-palindrome/
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;

        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i)))
                i++;
            while (i < j && !Character.isLetterOrDigit(s.charAt(j)))
                j--;

            if (i < j && Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
                return false;

            i++;
            j--;
        }

        return true;
    }

    // Problem 11
    // https://leetcode.com/problems/container-with-most-water/
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int maxArea = 0;

        while (i < j) {
            int width = j - i;
            int currentHeight = Math.min(height[i], height[j]);
            int area = currentHeight * width;

            if (area > maxArea)
                maxArea = area;

            if (height[i] < height[j])
                i++;
            else
                j--;
        }

        return maxArea;
    }
}
