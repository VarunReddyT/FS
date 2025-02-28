import java.util.*;

class LinkedList{
    int data;
    LinkedList next;
    LinkedList(int data){
        this.data = data;
        this.next = null;
    }
}

public class PalindromeLinekdList1{
    public static LinkedList reverse(LinkedList head){
        LinkedList mid = head;
        LinkedList end = head;
        while(end.next != null && end != null){
            if(end.next.next == null){
                break;
            }
            mid = mid.next;
            end = end.next.next;
        }
        LinkedList curr = mid.next;
        LinkedList prev= null;
        while(curr != null){
            LinkedList next = curr.next;
            curr.next = prev;
            prev= curr;
            curr = next;
        }
        return prev;
    }
    public static boolean isPalindrome(LinkedList head){
        LinkedList start = head;
        LinkedList mid = reverse(head);
        while(mid != null){
            if(start.data != mid.data){
                return false;
            }
            start = start.next;
            mid = mid.next;
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