import java.util.*;

public class DietPlanPerformance {
    public static int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int windowSum = 0;
        for (int i = 0; i < k; ++i) {
            windowSum += calories[i];
        }
        int points = 0;
        if (windowSum < lower) {
            points--;
        } else if (windowSum > upper) {
            points++;
        }

        for (int i = k; i < calories.length; ++i) {
            windowSum += calories[i] - calories[i - k];
            if (windowSum < lower) {
                points--;
            } else if (windowSum > upper) {
                points++;
            }
        }
        return points;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter calories size");
        int n = sc.nextInt();
        int calories[] = new int[n];
        System.out.println("enter the calroties");
        for (int i = 0; i < n; i++) {
            calories[i] = sc.nextInt();
        }
        System.out.println("enter the days");
        int k = sc.nextInt();
        System.out.println("enter the Lower value");
        int l = sc.nextInt();
        System.out.println("enter the Upper value");
        int u = sc.nextInt();
        System.out.println(dietPlanPerformance(calories, k, l, u));
        sc.close();
    }
}