import java.util.*;

public class maxSubarraySum {
    public static int maxSum(int a[], int k) {
        int n = a.length;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum = sum + a[i];
        }
        int answer = sum;
        for (int i = k; i < n; i++) {
            sum = sum + a[i] - a[i - k];
            answer = Math.max(answer, sum);
        }
        return answer;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        System.out.println(maxSum(a, k));
        sc.close();
    }
}