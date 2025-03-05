// There are N people in a private party. Initially all are strangers to each other,
// and the people are identified by unique ID from 0 to N-1.

// In the party, whenever two persons (person-A and person-B) become friends, they 
// took a photo. Each of the photo has some information, photos[i]=[T-i, P-j,P-k],
// here T-i indicates time of the photo taken, P-j person with ID 'j', and 
// P-k indicates person with ID 'k'.

// Friendship is symmetric[i.e., If P-j is friend of P-k, then P-k is a friend of P-j].
// Additionally, if person-A is "a friend of person-B OR a friend of someone who is 
// friend of person-B", then person-A is friend of person-B.

// You are given L photos information, Your task is to find the earliest time 
// for which every person became friend with every other person in the party.
// If there is no such earliest time, return -1.


// Input Format:
// -------------
// Line-1: Two space separated integers, N and L.
// Next L lines: Three space separated integers, log[i], 0<=i<L.

// Output Format:
// --------------
// Print an integer result.


// Sample Input-1:
// ---------------
// 6 8
// 5 0 1
// 7 3 4
// 12 2 3
// 21 1 5
// 34 2 4
// 37 0 3
// 42 1 2
// 93 4 5

// Sample Output-1:
// ----------------
// 37


// Sample Input-2:
// ---------------
// 7 6
// 2 0 3
// 5 1 5
// 8 2 5
// 7 3 6
// 9 4 6
// 6 4 5

// Sample Output-2:
// ----------------
// 9


import java.util.*;

public class EarliestTime{
    public static void initParent(int[] parent,int n){
        for(int i = 0;i<n;i++){
            parent[i] = i;
        }
    }
    public static void union(int[] parent,int[] rank, int x, int y, Set<Integer> set){
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if(rootX == rootY){
            return;
        }
        if(rank[rootX] > rank[rootY]){ 
            parent[rootY] = rootX;
            set.remove(rootY);
        }
        else if(rank[rootY] > rank[rootX]){
            parent[rootX] = rootY;
            set.remove(rootX);
        }
        else{
            parent[rootX] = rootY;
            set.remove(rootX);
            rank[rootY]++;
        }
    }
    public static int find(int[] parent, int x){
        if(parent[x] != x){
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
    public static int solve(ArrayList<int[]> arr, int n, int l){
        int[] parent = new int[n];
        initParent(parent,n);
        int[] rank = new int[n];
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i<n;i++){
            set.add(i);
        }
        for(int[] curr : arr){
            union(parent,rank,curr[1],curr[2],set);
            if(set.size() == 1){
                return curr[0];
            }
        }
        return -1;
        
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        ArrayList<int[]> arr = new ArrayList<>();
        for(int i = 0;i<l;i++){
            arr.add(new int[]{sc.nextInt(),sc.nextInt(),sc.nextInt()});
        }
        Collections.sort(arr,(a,b)->(a[0]-b[0]));
        System.out.println(solve(arr,n,l));
        sc.close();
    }
}