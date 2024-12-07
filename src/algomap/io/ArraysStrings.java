package algomap.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArraysStrings {
    public static void main(String[] args) {
        int[] array = {-4, -2, 1, 4, 8};
        System.out.println(findClosestNumberToZero(array));
        System.out.println(mergeAlternately("word1", "word2"));
        System.out.println(romanToInt("XVL"));
        System.out.println(isSubsequence("str", "hsjjtllr"));
        System.out.println(maxProfit(array));
        System.out.println(Arrays.toString(new String[]{"flower", "flow", "flight"}));
        System.out.println(summaryRanges(array));
    }

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
}