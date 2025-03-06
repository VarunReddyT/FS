import java.util.*;

public class ArrayRotation {
    public static void rotateArray(int[] arr, int k) {
        if (arr == null || arr.length <= 1 || k % arr.length == 0)
            return;
        int n = arr.length;
        k = k % n;
        reverse(arr, 0, n - 1); // Reverse the whole array
        reverse(arr, 0, k - 1); // Reverse the first k elements
        reverse(arr, k, n - 1); // Reverse the remaining elements
    }

    public static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            // Swap elements at start and end positions
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            // Move the pointers towards the center
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int k = sc.nextInt();
        rotateArray(arr, k);
        System.out.println(Arrays.toString(arr));
        sc.close();
    }
}