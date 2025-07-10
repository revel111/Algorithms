package neetcode150;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class HeapPriorityQueue {
    public static void main(String[] args) {
        System.out.println(lastStoneWeight(new int[]{2, 2}));
    }

    // Problem 703
    // https://leetcode.com/problems/kth-largest-element-in-a-stream/
    private static class KthLargest {

        private final int k;
        private final Queue<Integer> minHeap = new PriorityQueue<>();

        public KthLargest(int k, int[] nums) {
            this.k = k;

            for (int i : nums) {
                this.add(i);
            }
        }

        public int add(int val) {
            if (minHeap.size() < k || val > minHeap.peek()) {
                minHeap.add(val);
                if (minHeap.size() > k) {
                    minHeap.remove();
                }
            }

            return minHeap.peek();
        }
    }

    // Problem 1046
    // https://leetcode.com/problems/last-stone-weight/
    public static int lastStoneWeight(int[] stones) {
        if (stones.length == 1) {
            return stones[0];
        }

        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int stone : stones) {
            maxHeap.add(stone);
        }

        while (maxHeap.size() > 1) {
            int heaviest1 = maxHeap.poll();
            int heaviest2 = maxHeap.poll();

            int diff = heaviest1 - heaviest2;

            if (diff != 0) {
                maxHeap.add(diff);
            }
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }

    // Problem 973
    // https://leetcode.com/problems/k-closest-points-to-origin/
    public static int[][] kClosest(int[][] points, int k) {
        Queue<int[]> maxHeap = new PriorityQueue<>((p1, p2) -> (p2[0] * p2[0] + p2[1] * p2[1]) - (p1[0] * p1[0] + p1[1] * p1[1]));
        int[][] res = new int[k][2];

        for (int[] val : points) {
            maxHeap.add(val);

            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        while (k > 0) {
            res[--k] = maxHeap.poll();
        }

        return res;
    }

    // Problem 215
    // https://leetcode.com/problems/kth-largest-element-in-an-array/
    public static int findKthLargest(int[] nums, int k) {
        Queue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.add(num);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }
}
