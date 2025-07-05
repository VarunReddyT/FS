// 3. Number of Connected Components in an Undirected Graph:
// Given an undirected graph G with vertices numbered in the range [0, N] and an array
// Edges[][] consisting of M edges, the task is to find the total number of connected components in the 
// graph using Disjoint Set Union algorithm.
// Examples:
// Input: N = 5, Edges[][] = {{1, 0}, {2, 3}, {3, 4}}
// Output: 2
// Input: N = 8, Edges[][] = {{1, 0}, {0, 2}, {3, 5}, {3, 4}, {6, 7}}
// Output: 3

import java.util.*;

public class ConnectedComponents {
    int[] parent;
    int countComponents(int n, int[][] edges){
        parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
        int components = n;
        for(int[] e : edges){
            int p1 = find(e[0]);
            int p2 = find(e[1]);
            if(p1 != p2){
                parent[p1] = p2;
                components--;
            }
        }
        return components;
    }
    private int find(int i){
        if(parent[i] != i){
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        int edges[][] = new int[e][2];
        for (int i = 0; i < e; i++)
            for (int j = 0; j < 2; j++)
                edges[i][j] = sc.nextInt();
        System.out.println(new ConnectedComponents().countComponents(n, edges));
        sc.close();
    }

}