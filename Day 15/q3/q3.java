import java.util.*;

public class Exercise {
    static class Node {
        int data;
        Node next;
        Node(int data) {this.data = data; this.next = null;}
        Node(int data, Node next) {this.data = data; this.next = next;}
    }

    static class LinkedList {
        Node head;
        LinkedList() {this.head = null;}
        LinkedList(Node head) {this.head = head;}
    }

    public void reverseList(LinkedList list) {
        Node head = list.head;
        Node prev = head;
        if (prev == null) return;
        Node curr = head.next;
        if (curr == null) return;
        prev.next = null;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        list.head = prev;
    }
}
