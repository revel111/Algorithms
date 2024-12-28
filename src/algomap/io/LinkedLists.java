package algomap.io;

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class LinkedLists {
    public static void main(String[] args) {

    }

    // Problem 83
    // https://leetcode.com/problems/remove-duplicates-from-sorted-list
    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null)
            if (current.next.val == current.val)
                current.next = current.next.next;
            else
                current = current.next;

        return head;
    }
}
