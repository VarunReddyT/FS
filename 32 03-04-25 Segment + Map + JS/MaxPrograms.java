// In Hyderabad after a long pandemic gap, the Telangana Youth festival is 
// Organized at HITEX. In HITEX, there are a lot of programs planned. During 
// the festival in order to maintain the rules of Pandemic, they put a 
// constraint that one person can only attend any one of the programs in 
// one day according to planned days.

// Now it’s your aim to implement the "Solution" class in such a way that 
// you need to return the maximum number of programs you can attend according 
// to given constraints.

// Explanation:
// You have a list of programs 'p' and days 'd', where you can attend only 
// one program on one day. Programs [p] = [first day, last day], 
// p is the program's first day and the last day.


// Input Format:
// -------------
// Line-1: An integer N, number of programs.
// Line-2: N comma separated pairs, each pair(f_day, l_day) is separated by space.

// Output Format:
// --------------
// An integer, the maximum number of programs you can attend.


// Sample Input-1:
// ---------------
// 4
// 1 2,2 4,2 3,2 2

// Sample Output-1:
// ----------------
// 4

// Sample Input-2:
// ---------------
// 6
// 1 5,2 3,2 4,2 2,3 4,3 5

// Sample Output-2:
// ----------------
// 5

import java.util.*;

class SegmentTree{
    int[] t;
    int n;
    SegmentTree(int[] arr){
        this.n = arr.length;
        t = new int[4*n];
        build(arr,0,0,n-1);
    }
    public void build(int[] arr,int v, int tl, int tr){
        if(tl == tr){
            t[v] = arr[tl];
        }
        else{
            int tm = (tl+tr)/2;
            build(arr,v*2+1,tl,tm);
            build(arr,v*2+2,tm+1,tr);
            t[v] = Math.min(t[2*v+1],t[2*v+2]);
        }
    }
    public int query(int v, int tl, int tr, int l, int r){
        if(r<tl || l>tr){
            return Integer.MAX_VALUE;
        }
        if(l<=tl && r>=tr){
            return t[v];
        }
        int tm = (tl+tr)/2;
        return Math.min(query(2*v+1,tl,tm,l,r),query(2*v+2,tm+1,tr,l,r));
    }
    public void update(int v, int tl, int tr, int index, int value){
        if(tl==tr){
            t[v] = value;
            return;
        }
        int tm = (tl+tr)/2;
        if(index<=tm){
            update(2*v+1,tl,tm,index,value);
        }
        else{
            update(2*v+2,tm+1,tr,index,value);
        }
        t[v] = Math.min(t[2*v+1],t[2*v+2]);
    }
}

public class MaxPrograms{
    public static int getMaxPrograms(int[][] arr, int n, int d){
        Arrays.sort(arr,Comparator.comparingInt(a->a[1]));
        int[] days = new int[d+1];
        SegmentTree st = new SegmentTree(days);
        int res = 0;
        for(int i = 0;i<n;i++){
            int[] curr = arr[i];
            for(int j = curr[0];j<=curr[1];j++){
                if(st.query(0,0,n,j,j)==0){
                    st.update(0,0,n,j,1);
                    res++;
                    break;
                }
            }
        }
        return res;
    }
    public static void main(String[] args){
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