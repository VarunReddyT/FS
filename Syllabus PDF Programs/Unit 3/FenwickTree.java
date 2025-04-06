import java.util.*;

class FenWickTree {
    int[] nums;
    int[] BIT;
    int n;

    public FenWickTree(int[] nums) {
        this.nums = nums;
        n = nums.length;
        BIT = new int[n + 1];
        for (int i = 0; i < n; i++) {
            init(i, nums[i]);
        }
    }

    public void init(int i, int val) {
        i++;
        while (i <= n) {
            BIT[i] += val;
            i += (i & -i);
        }
    }

    void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        init(i, diff);
    }

    public int getSum(int i) {
        int sum = 0;
        i++;
        while (i > 0) {
            sum += BIT[i];
            i -= (i & -i);
        }
        return sum;
    }

    public int sumRange(int i, int j) {
        return getSum(j) - getSum(i - 1);
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int q = scan.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scan.nextInt();
        }
        FenWickTree ft = new FenWickTree(nums);
        while (q-- > 0) {
            int opt = scan.nextInt();
            if (opt == 1) {
                int s1 = scan.nextInt();
                int s2 = scan.nextInt();
                System.out.println(ft.sumRange(s1, s2));
            } else {
                int ind = scan.nextInt();

                int val = scan.nextInt();
                ft.update(ind, val);
            }
        }
        scan.close();
    }
}