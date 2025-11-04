package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class HashmapsSets {
    public static void main(String[] args) {
//        System.out.println(numJewelsInStones("ad", "asdada"));
//        System.out.println(containsDuplicate(new int[]{1, 2, 3, 4}));
//        System.out.println(canConstruct("aa", "ab"));
//        System.out.println(isAnagram("anagram", "nagaram"));
//        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
//        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
//        System.out.println(Arrays.toString(findXSum(new int[]{1, 1, 2, 2, 3, 4, 2, 3}, 6, 2)));
//        System.out.println(Arrays.toString(findXSum(new int[]{17, 1, 13, 5, 8, 11, 17, 3, 7, 2, 7}, 4, 2)));
        System.out.println(carFleet(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3}));
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
        if (s.length() != t.length()) {
            return false;
        }

        int[] nums = new int[26];
        for (int i = 0; i < s.length(); i++) {
            nums[s.charAt(i) - 'a'] += 1;
            nums[t.charAt(i) - 'a'] -= 1;
        }

        for (int num : nums) {
            if (num != 0) {
                return false;
            }
        }

        return true;
    }

    // Problem 1
    // https://leetcode.com/problems/two-sum/
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++)
            map.put(nums[i], i);

        for (int i = 0; i < nums.length; i++) {
            int x = target - nums[i];

            if (map.containsKey(x) && map.get(x) != i)
                return new int[]{i, map.get(x)};
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

    // Problem 128
    // https://leetcode.com/problems/longest-consecutive-sequence
    public static int longestConsecutive(int[] nums) {
        int res = 0;
        Map<Integer, Integer> storage = new HashMap<>();

        for (int num : nums) {
            if (!storage.containsKey(num)) {
                int left = storage.getOrDefault(num - 1, 0);
                int right = storage.getOrDefault(num + 1, 0);

                int sum = left + right + 1;

                res = Math.max(sum, res);

                storage.put(num, sum);
                storage.put(num - left, sum);
                storage.put(num + right, sum);
            }
        }

        return res;
    }

    // Problem 347
    // https://leetcode.com/problems/top-k-frequent-elements
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        List<Integer>[] freqs = new List[nums.length + 1];

        for (int i = 0; i < freqs.length; i++) {
            freqs[i] = new ArrayList<>();
        }

        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            freqs[entry.getValue()].add(entry.getKey());
        }

        int[] answer = new int[k];
        int counterForK = 0;
        for (int i = freqs.length - 1; i > 0 && counterForK < k; i--) {
            for (int num : freqs[i]) {
                if (counterForK == k) {
                    return answer;
                }
                answer[counterForK++] = num;
            }
        }

        return answer;
    }

    // Problem 3318
    // https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-i/
    public static int[] findXSum(int[] nums, int k, int x) {
        int[] answer = new int[nums.length - k + 1];

        for (int i = 0; i < answer.length; i++) {
            Map<Integer, Integer> counter = new HashMap<>();
            List<List<Integer>> freqs = new ArrayList<>();
            int upperBound = i + k - 1;

            for (int j = 0; j <= upperBound + 1; j++) {
                freqs.add(new ArrayList<>());
            }

            for (int j = i; j <= upperBound; j++) {
                counter.put(nums[j], counter.getOrDefault(nums[j], 0) + 1);
            }

            for (var entry : counter.entrySet()) {
                freqs.get(entry.getValue()).add(entry.getKey());
            }

            for (int j = freqs.size() - 1, counterForX = 0; j > 0 && counterForX < x; j--) {
                Collections.sort(freqs.get(j));
                for (int backI = freqs.get(j).size() - 1; backI >= 0; backI--) {
                    if (counterForX == x) {
                        break;
                    }

                    answer[i] += freqs.get(j).get(backI) * j;
                    counterForX++;
                }
            }
        }

        return answer;
    }

    // Problem 853
    // https://leetcode.com/problems/car-fleet/
    // hard as fuck
    public static int carFleet(int target, int[] position, int[] speed) {
        Map<Integer, Integer> sorted = new TreeMap<>();
        int answer = 0;

        for (int i = 0; i < position.length; i++) {
            sorted.put(target - position[i], speed[i]);
        }

        double slowest = 0;
        for (var entry : sorted.entrySet()) {
            double curr = (double) entry.getKey() / entry.getValue();

            if (curr > slowest) {
                answer++;
                slowest = curr;
            }
        }

        return answer;
    }
}
