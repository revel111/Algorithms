package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    // Problem 15
    // https://leetcode.com/problems/3sum/
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int targetIndex = 0; targetIndex < nums.length; targetIndex++) {
            if (targetIndex > 0 && nums[targetIndex] == nums[targetIndex - 1]) {
                continue;
            }

            int left = targetIndex + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[targetIndex] + nums[right] + nums[left];

                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    result.add(List.of(nums[targetIndex], nums[right], nums[left]));
                    do {
                        left++;
                    } while (nums[left] == nums[left - 1] && left < right);
                }
            }
        }

        return result;
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
        int max = 0;

        int left = 0, right = height.length - 1;
        while (left < right) {
            int current = (right - left) * Math.min(height[left], height[right]);
            max = Math.max(current, max);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }

    // Problem 2410
    // https://leetcode.com/problems/maximum-matching-of-players-with-trainers
    public static int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);

        int i = 0, j = 0;
        while (i < players.length && j < trainers.length) {
            if (players[i] <= trainers[j]) {
                i++;
            }

            j++;
        }

        return i;
    }
}
