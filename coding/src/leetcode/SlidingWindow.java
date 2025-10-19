package leetcode;

import java.util.HashSet;
import java.util.Set;

public class SlidingWindow {
    public static void main(String[] args) {
        System.out.println(characterReplacement("AABA", 1));
        System.out.println(checkInclusion("adc", "dcda"));
    }

    // Problem 121
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    public static int maxProfitPart1(int[] prices) {
        int minPrice = Integer.MAX_VALUE, maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }

        return maxProfit;
    }

    // Problem 122
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
    public static int maxProfitPart2(int[] prices) {
        int profit = 0, minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            int potentialProfit = prices[i] - minPrice;

            if (potentialProfit > 0) {
                profit += potentialProfit;
                minPrice = prices[i];
            }
        }

        return profit;
    }

    // Problem 3
    // https://leetcode.com/problems/longest-substring-without-repeating-characters/
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> nums = new HashSet<>();
        int left = 0, right = 0, result = 0;

        while (right < s.length()) {
            char curr = s.charAt(right);
            while (nums.contains(curr)) {
                nums.remove(s.charAt(left));
                left++;
            }

            nums.add(curr);
            result = Math.max(result, right - left + 1);
            right++;
        }

        return result;
    }

    // Problem 424
    // https://leetcode.com/problems/longest-repeating-character-replacement/
    public static int characterReplacement(String s, int k) {
        int[] counter = new int[26];
        int currMax = 0, absoluteMax = 0, left = 0;

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);
            int mapCounter = counter[current - 'A'] + 1;
            counter[current - 'A'] = mapCounter;
            currMax = Math.max(mapCounter, currMax);

            while (right - left + 1 - currMax > k) {
                counter[s.charAt(left++) - 'A']--;
            }

            absoluteMax = Math.max(absoluteMax, right - left + 1);
        }

        return absoluteMax;
    }

    // Problem 567
    // https://leetcode.com/problems/permutation-in-string/
    public static boolean checkInclusion(String s1, String s2) {
        int[] counter = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            counter[s1.charAt(i) - 'a']++;
        }

        for (int right = 0, left = 0; right < s2.length(); right++) {
            counter[s2.charAt(right) - 'a']--;

            while (counter[s2.charAt(right) - 'a'] < 0) {
                counter[s2.charAt(left++) - 'a']++;
            }

            if (right - left + 1 == s1.length()) {
                return true;
            }
        }

        return false;
    }
}
