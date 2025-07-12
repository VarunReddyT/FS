import java.util.*;

public class MiddleElementLL {
    Node head;
    Node tail;

    // Linked list node
    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    // Function to add a node to the linked list
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

    void printMiddle() {
        Node slowPtr = head;
        Node fastPtr = head;
        // System.out.println(head.data);
        while (fastPtr.next != null && fastPtr.next.next != null) {
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
        }
        System.out.println("The middle element is [" + slowPtr.data + "] \n");
    }

    // This function prints the contents of the linked list starting from the given
    // node
    public void printList() {
        Node tnode = head;
        while (tnode != null) {
            System.out.print(tnode.data + "->");
            tnode = tnode.next;
        }
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String list[] = sc.nextLine().split(" ");
        MiddleElementLL llist = new MiddleElementLL();
        for (int i = 0; i < list.length; i++) {
            llist.addNode(Integer.parseInt(list[i]));
        }
        llist.printList();
        llist.printMiddle();
        sc.close();
    }
}