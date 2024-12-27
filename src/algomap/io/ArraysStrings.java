package algomap.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArraysStrings {
    public static void main(String[] args) {
//        int[] array = {-4, -2, 1, 4, 8};
//        System.out.println(findClosestNumberToZero(array));
//        System.out.println(mergeAlternately("word1", "word2"));
//        System.out.println(romanToInt("XVL"));
//        System.out.println(isSubsequence("str", "hsjjtllr"));
//        System.out.println(maxProfit(array));
//        System.out.println(Arrays.toString(new String[]{"flower", "flow", "flight"}));
//        System.out.println(summaryRanges(array));
//        System.out.println(Arrays.toString(productExceptSelf(array)));
    }

    // Problem 2239
    // https://leetcode.com/problems/find-closest-number-to-zero/
    public static int findClosestNumberToZero(int[] nums) {
        int closest = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            if (Math.abs(current) < Math.abs(closest)) {
                closest = current;
            } else if (Math.abs(current) == Math.abs(closest)) {
                closest = Math.max(current, closest);
            }
        }

        return closest;
    }

    // Problem 1768
    // https://leetcode.com/problems/merge-strings-alternately/
    public static String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Math.max(word1.length(), word2.length()); i++) {
            if (i < word1.length())
                sb.append(word1.charAt(i));
            if (i < word2.length())
                sb.append(word2.charAt(i));
        }

        return sb.toString();
    }

    // Problem 13
    // https://leetcode.com/problems/roman-to-integer/
    public static int romanToInt(String s) {
        int result = 0;

        Map<Character, Integer> map = new HashMap<>() {
            {
                put('I', 1);
                put('V', 5);
                put('X', 10);
                put('L', 50);
                put('C', 100);
                put('D', 500);
                put('M', 1000);
            }
        };

        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (i < length - 1 && map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                result += map.get(s.charAt(i + 1)) - map.get(s.charAt(i));
                i++;
            } else {
                result += map.get(s.charAt(i));
            }
        }

        return result;
    }

    // Problem 392
    // https://leetcode.com/problems/is-subsequence/
    public static boolean isSubsequence(String s, String t) {
        int counter = 0;

        for (int i = 0; i < t.length(); i++) {
            if (counter == s.length())
                return true;

            if (t.charAt(i) == s.charAt(counter))
                counter++;
        }

        return counter == s.length();
    }

    // Problem 121
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    public static int maxProfit(int[] prices) {
        int lowest = prices[0];
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            int current = prices[i];
            if (current < lowest)
                lowest = current;

            if (current - lowest > profit)
                profit = current - lowest;
        }

        return profit;
    }

    // Problem 14
    // https://leetcode.com/problems/longest-common-prefix
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";

        int lowestLength = strs[0].length();

        for (int i = 1; i < strs.length; i++) {
            int current = strs[i].length();
            if (current < lowestLength)
                lowestLength = current;
        }

        int i;
        for (i = 0; i < lowestLength; i++) {
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != strs[0].charAt(i))
                    return strs[0].substring(0, i);
            }
        }

        return strs[0].substring(0, i);
    }

    // Problem 228
    // https://leetcode.com/problems/summary-ranges/
    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int start = nums[i];

            while (i < nums.length - 1 && nums[i] + 1 == nums[i + 1])
                i++;

            if (start != nums[i]) {
                result.add(start + "->" + nums[i]);
            } else {
                result.add(String.valueOf(start));
            }
        }

        return result;
    }

    // Problem 228
    // https://leetcode.com/problems/product-of-array-except-self/
    public static int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] res = new int[length];
        int left = 1;
        int right = 1;

        for (int i = 0; i < length; i++) {
            res[i] = left;
            left *= nums[i];
        }

        for (int i = length - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }

        return res;
    }

    // Problem 14
    // https://leetcode.com/problems/merge-intervals/
    public static int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        for (int[] interval : intervals) {
            if (res.isEmpty() || res.get(res.size() - 1)[1] < interval[0]) {
                res.add(interval);
            } else {
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], interval[1]);
            }
        }

        return res.toArray(new int[res.size()][]);
    }

    public enum DIR {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }

    // Problem 54
    // https://leetcode.com/problems/spiral-matrix/
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        DIR dir = DIR.RIGHT;
        int leftBound = -1, rightBound = matrix[0].length, downBound = matrix.length, upperBound = 0;
        int i = 0, j = 0;

        while (res.size() != matrix[0].length * matrix.length) {
            switch(dir) {
                case DIR.RIGHT -> {
                    while (j < rightBound)
                        res.add(matrix[i][j++]);
                    rightBound--;
                    i++;
                    j--;
                    dir = DIR.DOWN;
                }
                case DIR.LEFT -> {
                    while (j > leftBound)
                        res.add(matrix[i][j--]);
                    leftBound++;
                    i--;
                    j++;
                    dir = DIR.UP;
                }
                case DIR.UP -> {
                    while (i > upperBound)
                        res.add(matrix[i--][j]);
                    upperBound++;
                    i++;
                    j++;
                    dir = DIR.RIGHT;
                }
                case DIR.DOWN -> {
                    while (i < downBound)
                        res.add(matrix[i++][j]);
                    downBound--;
                    i--;
                    j--;
                    dir = DIR.LEFT;
                }
            }
        }

        return res;
    }
}