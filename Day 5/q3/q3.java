/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        for (int i = 1; i < lists.length; i++) {
            if (lists[i] == null && lists[i - 1] != null) lists[i] = lists[i - 1];
            else if (lists[i] != null && lists[i - 1] != null)
                lists[i] = conquer(lists[i - 1], lists[i]);
        }
        return lists[lists.length - 1];
    }
    private ListNode conquer(ListNode list1, ListNode list2) {
        ListNode head = null;
        if (list1.val <= list2.val) {
            head = list1;
            list1 = list1.next;
        } else {
            head = list2;
            list2 = list2.next;
        }
        ListNode temp = head;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                temp.next = list1;
                temp = temp.next;
                list1 = list1.next;
            } else {
                temp.next = list2;
                temp = temp.next;
                list2 = list2.next;
            }
        }
        while (list1 != null) {
            temp.next = list1;
            temp = temp.next;
            list1 = list1.next;
        }
        while (list2 != null) {
            temp.next = list2;
            temp = temp.next;
            list2 = list2.next;
        }
        return head;
    }
}
