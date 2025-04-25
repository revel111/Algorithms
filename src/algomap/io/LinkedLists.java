package algomap.io;


public class LinkedLists {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(3, new ListNode(9)));
//        deleteDuplicates(l1);
//        reverseList(l1);
//        mergeTwoLists(l1, new ListNode(2, new ListNode(4, new ListNode(5))));
//        addTwoNumbers(l1, l2);
    }

    private static class ListNode {
        public int val;
        public ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    // Problem 83
    // https://leetcode.com/problems/remove-duplicates-from-sorted-list/
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null)
            if (current.next.val == current.val)
                current.next = current.next.next;
            else
                current = current.next;

        return head;
    }

    // Problem 206
    // https://leetcode.com/problems/reverse-linked-list/
    public static ListNode reverseList(ListNode head) {
        ListNode previous = null;
        ListNode current = head;

        while (current != null) {
            ListNode temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;
        }

        return previous;
    }

    // Problem 21
    // https://leetcode.com/problems/merge-two-sorted-lists/
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode storage = new ListNode();
        ListNode current = storage;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }

            current = current.next;
        }

        current.next = list1 == null ? list2 : list1;

        return storage.next;
    }

    // Problem 141
    // https://leetcode.com/problems/linked-list-cycle/
    // Floyd's cycle detection algorithm
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                return true;
        }

        return false;
    }

    // Problem 876
    // https://leetcode.com/problems/middle-of-the-linked-list/
    // Fast and slow pointer approach
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // Problem 19
    // https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    // Two pointer approach
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode front = dummy;
        ListNode back = dummy;

        for (int i = 0; i <= n; i++)
            front = front.next;

        while (front != null) {
            front = front.next;
            back = back.next;
        }

        back.next = back.next.next;

        return dummy.next;
    }

    // Problem 2
    // https://leetcode.com/problems/add-two-numbers/
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode answer = new ListNode();
        ListNode current = answer;

        int extra = 0;
        do {
            int currentValue = extra;

            if (l1 != null) {
                currentValue += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                currentValue += l2.val;
                l2 = l2.next;
            }

            if (currentValue >= 10) {
                extra = 1;
                currentValue %= 10;
            } else {
                extra = 0;
            }

            current.next = new ListNode(currentValue);
            current = current.next;
        } while (l1 != null || l2 != null);

        if (extra == 1)
            current.next = new ListNode(1);

        return answer.next;
    }
}
