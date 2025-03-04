// You are a database integrity engineer working for a global cloud company. 
// Your team maintains a distributed database network, where each server either:
//     - Stores equivalent data to another server (serverX == serverY).
//     - Stores different data from another server (serverX != serverY).

// The transitive consistency rule must be followed:
//     - If A == B and B == C, then A == C must be true.
//     - If A == B and B != C, then A != C must be true.

// Your task is to analyze the given constraints and determine whether they 
// follow transitive consistency. If all relations are consistent, return true; 
// otherwise, return false

// Input Format:
// -------------
// Space separated strnigs, list of relations

// Output Format:
// --------------
// Print a boolean value, whether transitive law is obeyed or not.


// Sample Input-1:
// ---------------
// a==b c==d c!=e e==f

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// a==b b!=c c==a

// Sample Output-2:
// ----------------
// false

// Explanation:
// ------------
// {a, b} form one equivalence group.
// {c} is declared equal to {a} (c == a), which means {a, b, c} should be equivalent.
// However, b != c contradicts b == a and c == a.

// Sample Input-3:
// ---------------
// a==b b==c c!=d d!=e f==g g!=d

// Sample Output-3:
// ----------------
// true


import java.util.*;

public class CityRegions2{
    public static void initParent(int[] rank, int[] parent){
        for(int i = 0;i<26;i++){
            parent[i] = i;
            rank[i] = i;
        }
    }
    public static void union(int[] parent, int[] rank, int x, int y){
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if(rootX == rootY){
            return;
        }
        if(rank[rootX] > rank[rootY]){
            parent[rootY] = rootX;
        }
        else if(rank[rootY] > rank[rootX]){
            parent[rootX] = rootY;
        }
    }
    public static int find(int[] parent, int x){
        if(parent[x] != x){
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
    public static boolean solve(String[] input){
        int[] rank = new int[26];
        int[] parent = new int[26];
        initParent(rank,parent);
        ArrayList<String> notEqual = new ArrayList<>();
        for(String s : input){
            if(s.charAt(1) != '!'){
                union(parent,rank,s.charAt(0)-'a',s.charAt(3)-'a');
            }
            else{
                notEqual.add(s);
            }
        }
        for(String s : notEqual){
            int parent1 = find(parent,s.charAt(0)-'a');
            int parent2 = find(parent,s.charAt(3)-'a');
            if(parent1 == parent2){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        System.out.println(solve(input));
        sc.close();
    }
}