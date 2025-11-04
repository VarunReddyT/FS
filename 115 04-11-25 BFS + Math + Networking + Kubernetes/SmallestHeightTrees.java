// Thomas constructing a Tree of N nodes, nodes labelled as 0, 1, 2, .. ,n-1.
// He is given a list of N-1 edges[], where edge[i]=[v1, v2], is an undirected 
// edge between v1 and v2.

// He can construct the tree by selecting any node as root node. Your task is
// to find out the trees with the smallest height, among all the trees. 
// Print the root nodes of all the smallest height trees in ascending order.

// NOTE: A Tree, is a connected graph without simple cycles.

// Input Format:
// -------------
// Line-1: An integer N, number of nodes.
// Next N-1 lines: Two integers, represent an edge.

// Output Format:
// --------------
// Print the list of root nodes of Smallest Height Trees in ascending order


// Sample Input-1:
// ---------------
// 4
// 0 1
// 2 1
// 1 3

// Sample Output-1:
// ----------------
// [1]

// Explanation:
// -------------
// For Tree Combinations look at the hint.


// Sample Input-2:
// ---------------
// 6
// 3 0
// 1 3
// 3 2
// 4 3
// 5 4

// Sample Output-2:
// ----------------
// [3, 4]


import java.util.*;
import java.util.LinkedList;

public class SmallestHeightTrees{
    public static int getHeight(List<List<Integer>> adj, int n, int i){
        boolean[] visited = new boolean[n];
        visited[i] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        int height = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int k = 0;k<size;k++){
                int temp = queue.poll();
                for(int neigh : adj.get(temp)){
                    if(!visited[neigh]){
                        visited[neigh] = true;
                        queue.offer(neigh);
                    }
                }
            }
            height++;
        }
        return height-1;
    }
    public static List<Integer> getMinHeight(List<List<Integer>> adj, int n){
        int height = Integer.MAX_VALUE;
        List<Integer> res = new ArrayList<>();
        for(int i = 0;i<n;i++){
            int currHeight = getHeight(adj,n,i);
            if(currHeight < height){
                height = currHeight;
                res.clear();
                res.add(i);
            }
            else if(currHeight == height){
                res.add(i);
            }
        }
        Collections.sort(res);
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        for(int i = 0;i<n-1;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        System.out.println(getMinHeight(adj,n));
        sc.close();
    }
}