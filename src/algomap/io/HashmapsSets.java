package algomap.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class HashmapsSets {
    public static void main(String[] args) {
//        System.out.println(numJewelsInStones("ad", "asdada"));
//        System.out.println(containsDuplicate(new int[]{1, 2, 3, 4}));
//        System.out.println(canConstruct("aa", "ab"));
//        System.out.println(isAnagram("anagram", "nagaram"));
    }

    // Problem 771
    // https://leetcode.com/problems/jewels-and-stones/
    public static int numJewelsInStones(String jewels, String stones) {
        List<String> stonesSet = new ArrayList<>(List.of(stones.split("")));

        stonesSet.retainAll(new HashSet<>(List.of(jewels.split(""))));

        return stonesSet.size();
    }

    // Problem 217
    // https://leetcode.com/problems/contains-duplicate/
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> checked = new HashSet<>();

        for (int current : nums) {
            if (checked.contains(current)) {
                return true;
            }
            checked.add(current);
        }

        return false;
    }

    // Problem 383
    // https://leetcode.com/problems/ransom-note/
    public static boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (char current : magazine.toCharArray())
            map.put(current, map.getOrDefault(current, 0) + 1);

        for (char current : ransomNote.toCharArray()) {
            if (map.containsKey(current)) {
                int count = map.get(current) - 1;
                map.put(current, count);

                if (count == -1)
                    return false;
            } else {
                return false;
            }
        }

        return true;
    }

    // Problem 383
    // https://leetcode.com/problems/valid-anagram/
    public static boolean isAnagram(String s, String t) {
        Map<Character, Integer> map1 = fill(s);
        Map<Character, Integer> map2 = fill(t);

        return map1.equals(map2);
    }

    private static Map<Character, Integer> fill(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for(char current : s.toCharArray())
            map.put(current, map.getOrDefault(current, 0) + 1);

        return map;
    }

    // Problem 1
    // https://leetcode.com/problems/two-sum/
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++)
            map.put(nums[i], i);

        for(int i = 0; i < nums.length; i++) {
            int x = target - nums[i];

            if (map.containsKey(x) && map.get(x) != i)
                return new int[] {i, map.get(x)};
        }

        return new int[]{};
    }

    // Problem 49
    // https://leetcode.com/problems/group-anagrams/
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sortedS = new String(charArray);

            map.putIfAbsent(sortedS, new ArrayList<>());
            map.get(sortedS).add(s);
        }

        return new ArrayList<>(map.values());
    }

    // Problem 169
    // https://leetcode.com/problems/majority-element/
    public int majorityElement(int[] nums) {
        int max = 0, currentMax = 0;
        int sMax = 0, sCurrentMax = 0;

        Arrays.sort(nums);
        for (int num : nums) {
            if (num != sCurrentMax) {
                currentMax = 0;
                sCurrentMax = num;
            }

            if (++currentMax > max) {
                max = currentMax;
                sMax = sCurrentMax;
            }
        }

        return sMax;
    }

    // Problem 128
    // https://leetcode.com/problems/longest-consecutive-sequence
    public int longestConsecutive(int[] nums) {
        int counter = 0, currentCounter = 0, previous = Integer.MIN_VALUE;

        Arrays.sort(nums);
        for (int num : nums) {
            if (num - 1 == previous)
                currentCounter++;
            else if (num == previous)
                continue;
            else
                currentCounter = 1;

            if (currentCounter > counter)
                counter = currentCounter;

            previous = num;
        }

        return counter;
    }
}
