import java.util.*;

public class CountingBitsDP {
    public static int[] countBits(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i / 2] + (i % 2);
        }
        return dp;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] result = countBits(n);

        for (int num : result) {
            System.out.print(num + " ");
        }

        s.close();
    }
}