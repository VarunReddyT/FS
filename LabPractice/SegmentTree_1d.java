// d) Write a JAVA Program to implement a segment tree with its operations In Hyderabad after 
// a long pandemic gap, the Telangana Youth festival Is Organized at HITEX.
// In HITEX, there are a lot of programs planned. During the festival in order to maintain the rules of
// Pandemic, they put a constraint that one person can only attend any one of the programs in one day
// according to planned days. Now it’s your aim to implement the "Solution" class in such a way that you 
// need to return the maximum number of programs you can attend according to given constraints.
// Explanation: You have a list of programs ‘p’ and days ’d’, where you can attend only one program on 
// one day. Programs [p] = [first day, last day], p is the program's first day and the last day.
// Input Format:
// Line-1: An integer N, number of programs.
// Line-2: N comma separated pairs, each pair(f_day, l_day) is separated by space. 
// Output Format:
// An integer, the maximum number of programs you can attend.
// Sample Input-1:
// 4
// 1 2,2 4,2 3,2 2
// Sample Output-1:
// 4
// Sample Input-2:
// 6
// 1 5,2 3,2 4,2 2,3 4,3 5
// Sample Output-2:
// 5

import java.util.*;

public class SegmentTree_1d {
    class SegmentTreeNode {
        int start, end;
        SegmentTreeNode left, right;
        int val;

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
            this.val = 0;
        }

    }

    SegmentTreeNode root;

    public int maxEvents(int[][] events) {
        if (events == null || events.length == 0) {
            return 0;
        }
        Arrays.sort(events, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        int lastDay = events[events.length - 1][1];
        int firstDay = Integer.MAX_VALUE;
        for (int i = 0; i < events.length; i++) {
            firstDay = Math.min(firstDay, events[i][0]);
        }

        root = buildSegmentTree(firstDay, lastDay);
        int count = 0;
        for (int[] event : events) {
            int earliestDay = query(root, event[0], event[1]);
            if (earliestDay != Integer.MAX_VALUE) {
                count++;
                update(root, earliestDay);
            }
        }
        return count;
    }

    public SegmentTreeNode buildSegmentTree(int start, int end) {
        if (start > end) {
            return null;
        }
        SegmentTreeNode node = new SegmentTreeNode(start, end);
        node.val = start;
        if (start != end) {
            int mid = (start + end) / 2;
            node.left = buildSegmentTree(start, mid);
            node.right = buildSegmentTree(mid + 1, end);
        }
        return node;
    }

    public int query(SegmentTreeNode curr, int start, int end) {
        if (curr.start == start && curr.end == end) {
            return curr.val;
        }
        int mid = curr.start + (curr.end - curr.start) / 2;
        if (mid >= end) {
            return query(curr.left, start, end);
        } else if (mid < start) {
            return query(curr.right, start, end);
        } else {
            return Math.min(query(curr, start, mid), query(curr, mid + 1, end));
        }
    }

    public void update(SegmentTreeNode curr, int val) {
        if (curr.start == curr.end) {
            curr.val = Integer.MAX_VALUE;
        } else {
            int mid = curr.start + (curr.end - curr.start) / 2;
            if (val <= mid) {
                update(curr.left, val);
            } else {
                update(curr.right, val);
            }
            curr.val = Math.min(curr.left.val, curr.right.val);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String str[] = sc.nextLine().split(",");
        int nums[][] = new int[n][2];
        for (int i = 0; i < n; i++) {
            String val[] = str[i].split(" ");
            nums[i][0] = Integer.parseInt(val[0]);
            nums[i][1] = Integer.parseInt(val[1]);
        }
        SegmentTree_1d segment = new SegmentTree_1d();
        int result = segment.maxEvents(nums);
        System.out.println(result);
        sc.close();
    }
}