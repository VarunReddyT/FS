// Cliff Shaw is working on the singly linked list.
// He is given a list of boxes arranged as singly linked list,
// where each box is printed a positive number on it.

// Your task is to help Mr Cliff to find the given list is equivalent to 
// the reverse of it or not. If yes, print "true", otherwise print "false"

// Input Format:
// -------------
// Line-1: space separated integers, boxes as list.

// Output Format:
// --------------
// Print a boolean a value.


// Sample Input-1:
// ---------------
// 3 6 2 6 3

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// 3 6 2 3 6

// Sample Output-2:
// ----------------
// false


import java.util.*;

class LinkedList{
    int data;
    LinkedList next;
    LinkedList(int data){
        this.data = data;
        this.next = null;
    }
}

public class PalindromeLinkedList{
    public static boolean isPalindrome(LinkedList head){
        LinkedList start = head;
        LinkedList end = head;
        Stack<LinkedList> stack = new Stack<>();
        while(end.next!=null && end != null){
            stack.push(start);
            if(end.next.next == null){
                break;
            }
            start = start.next;
            end = end.next.next;
        }
        while(start.next != null){
            start = start.next;
            LinkedList temp = stack.pop();
            if(temp.data != start.data){
                return false;
            }
        }
        return true;
    }
    public static void buildList(LinkedList head, int val){
        LinkedList temp = head;
        LinkedList node = new LinkedList(val);
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] n1 = sc.nextLine().split(" ");
        int[] arr = new int[n1.length];
        LinkedList head = new LinkedList(Integer.parseInt(n1[0]));
        for(int i = 1;i<n1.length;i++){
            arr[i] = Integer.parseInt(n1[i]);
            buildList(head,arr[i]);
        }
        System.out.println(isPalindrome(head));
        sc.close();
    }
}