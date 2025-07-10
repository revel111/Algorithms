package neetcode150;


public class LinkedLists {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(3)));
//        deleteDuplicates(l1);
//        reverseList(l1);
//        mergeTwoLists(l1, new ListNode(2, new ListNode(4, new ListNode(5))));
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

    // Problem 2095
    // https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
    public ListNode deleteMiddle(ListNode head) {
        if (head.next == null)
            return null;

        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = slow;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = slow.next;

        return head;
    }

    // Problem 19
    // https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    // Two pointer approach
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode storage = new ListNode(0, head);
        ListNode slow = storage, fast = head;

        while (n > 0) {
            fast = fast.next;
            n--;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;

        return storage.next;
    }

    // Problem 2
    // https://leetcode.com/problems/add-two-numbers/
    // I know it looks ugly(and remainder calculation is a bit redundant), but still it's:
    // O(1) for space (we don't count storage variable)
    // O(n) for time where n is size of l1 + size of l2
    //
    // tbh I am just proud I solved it, I took a look at a better solution after all.
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode storage = new ListNode();
        ListNode current = storage;
        int remainder = 0;

        while (l1 != null && l2 != null) {
            int val = remainder + l1.val + l2.val;

            if (val >= 10) {
                remainder = 1;
                current.next = new ListNode(val % 10);
            } else {
                remainder = 0;
                current.next = new ListNode(val);
            }

            current = current.next;

            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int val = remainder + l1.val;

            if (val >= 10) {
                remainder = 1;
                current.next = new ListNode(val % 10);
            } else {
                remainder = 0;
                current.next = new ListNode(val);
            }
            current = current.next;

            l1 = l1.next;
        }


        while (l2 != null) {
            int val = remainder + l2.val;

            if (val >= 10) {
                remainder = 1;
                current.next = new ListNode(val % 10);
            } else {
                remainder = 0;
                current.next = new ListNode(val);
            }
            current = current.next;

            l2 = l2.next;
        }

        if (remainder == 1)
            current.next = new ListNode(1);

        return storage.next;
    }
}
