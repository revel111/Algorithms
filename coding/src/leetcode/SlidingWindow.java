package leetcode;

import java.util.HashSet;
import java.util.Set;

public class SlidingWindow {
    public static void main(String[] args) {

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
}
