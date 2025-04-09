// d) Write a JAVA Program to implement a segment tree with its operations In Hyderabad 
// after a long pandemic gap, the Telangana Youth festival Is Organized at HITEX.
// In HITEX, there are a lot of programs planned. During the festival in order to maintain the rules 
// of Pandemic, they put a constraint that one person can only attend any one of the programs in one 
// day according to planned days. Now it’s your aim to implement the "Solution" class in such a way 
// that you need to return the maximum number of programs you can attend according to given 
// constraints.
// Explanation: You have a list of programs ‘p’ and days ’d’, where you can attend only one program 
// on one day. Programs [p] = [first day, last day], p is the program's first day and the last day.
// Input Format:
// Line-1: An integer N, number of programs.
// Line-2: N comma separated pairs, each pair(f_day, l_day) is separated by 
// space. Output Format:
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

class Segment{
    int[] tree;
    int n;
    Segment(int size){
        this.n = size;
        tree = new int[4*n];
        build(0,0,n-1);
    }
    public void build(int node, int tl, int tr){
        if(tl == tr){
            tree[node] = tl;
        }
        else{
            int tm = (tl+tr)/2;
            build(2*node+1, tl, tm);
            build(2*node+2, tm+1, tr);
            tree[node] = Math.min(tree[2*node+1], tree[2*node+2]);
        }
    }
    public int query(int node, int tl, int tr, int start, int end){
        if(start > tr || end < tl){
            return Integer.MAX_VALUE;
        }
        if(start <= tl && end >= tr){
            return tree[node];
        }
        int tm = (tl+tr)/2;
        int left = query(2*node+1, tl, tm, start, end);
        int right = query(2*node+2, tm+1, tr, start, end);
        return Math.min(left, right);
    }
    public void update(int node, int tl, int tr, int index, int value){
        if(tl == tr){
            tree[node] = value;
            return;
        }
        int tm = (tl+tr)/2;
        if(index <= tm){
            update(2*node+1, tl, tm, index, value);
        }
        else{
            update(2*node+2, tm+1, tr, index, value);
        }
        tree[node] = Math.min(tree[2*node+1],tree[2*node+2]);
    }
}
public class Segment_1d {
    public static int getMaxPrograms(int[][] arr, int n, int d){
        Arrays.sort(arr, Comparator.comparingInt(a -> a[1]));
        Segment st = new Segment(n);
        int res = 0;
        for(int[] program : arr){
            int start = program[0];
            int end = program[1];
            int earlistDay = st.query(0, 0, d-1, start-1, end-1);
            if(earlistDay != Integer.MAX_VALUE){
                res++;
                st.update(0, 0, d-1, earlistDay, Integer.MAX_VALUE);
            }
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int[][] arr = new int[n][2];
        String[] input = sc.nextLine().split(",");
        int d = 0;
        for(int i = 0;i<n;i++){
            String[] days = input[i].split(" ");
            arr[i][0]  = Integer.parseInt(days[0]);
            arr[i][1]  = Integer.parseInt(days[1]);
            d = Math.max(d,arr[i][1]);
        }
        System.out.println(getMaxPrograms(arr,n,d));
        sc.close();
    }
}
