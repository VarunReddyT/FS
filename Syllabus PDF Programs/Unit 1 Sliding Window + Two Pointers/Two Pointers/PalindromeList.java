// Given the head of a singly linked list, return true if it is a palindrome or false otherwise.

// Example 1:
// Input:
// head = [1,2,2,1]
// Output: 
// true

// Example 2:
// Input: 
// head = [1,2]
// Output: 
// false

import java.util.*;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class Solution {
    // Function to check whether the list is palindrome.
    Node getmid(Node head) {
        Node slow = head;
        Node fast = head.next;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    Node reverse(Node head) {
        Node curr = head;
        Node prev = null;
        Node next = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    boolean isPalindrome(Node head) {
        if (head.next == null) {
            return true;
        }

        Node middle = getmid(head);

        Node temp = middle.next;
        middle.next = reverse(temp);

        Node head1 = head;
        Node head2 = middle.next;

        while (head2 != null) {
            if (head1.data != head2.data) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        temp = middle.next;
        middle.next = reverse(temp);
        return true;
    }
}

public class PalindromeList {
    public Node head = null;
    public Node tail = null;

    public void addNode(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PalindromeList list = new PalindromeList();
        String list2[] = sc.nextLine().split(" ");
        for (int i = 0; i < list2.length; i++)
            list.addNode(Integer.parseInt(list2[i]));
        Solution sl = new Solution();
        System.out.println(sl.isPalindrome(list.head));
        sc.close();
    }
}