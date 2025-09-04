// /*
// Mr Ashoka planted N trees in a land around the Flag Post which is at the center 
// of the land, i.e., (0,0) position. You will be given the positions of N trees
// as trees[], where tree[i]=[Xi,Yi], where Xi, Yi are the positions of i-th tree
// with respect to X-axis and Y-axis. And you are also an integer C.

// The distance between any two trees on the land plane is the Euclidean distance 
// (i.e., sqrt((x1 - x2)^2 + (y1 - y2)^2).

// Your task is to return positions of the C trees Nearest to the Flag post. 
// The answer is supposed to be sorted based on distance, if the distances 
// are same keep the original order of the trees[].


// Input Format:
// -------------
// Line-1: Two space separate integers, N and C
// Next N Lines: Two space separated integers, x,y

// Output Format:
// --------------
// Print the positionf of C Nearest Trees.

// Sample Input-1:
// ---------------
// 4 4
// -3 -3
// 3 -3
// 3 3
// -3 3

// Sample Output-1:
// ----------------
// [-3, -3] [3, -3] [3, 3] [-3, 3]


// Sample Input-2:
// ---------------
// 4 3
// 2 -1
// 1 2
// 2 4
// 3 2

// Sample Output-2:
// ----------------
// [2, -1] [1, 2] [3, 2]


import java.util.*;

public class NearestTrees{
    public static ArrayList<int[]> getNearestTrees(int[][] arr, int n, int c){
        TreeMap<Double,ArrayList<int[]>> map = new TreeMap<>();
        ArrayList<int[]> res = new ArrayList<>();
        
        for(int[] i : arr){
            double dist = Math.sqrt((i[0] * i[0]) + (i[1] * i[1]));
            map.putIfAbsent(dist, new ArrayList<>());
            map.get(dist).add(i);
        }
        for(Map.Entry<Double,ArrayList<int[]>> entry : map.entrySet()){
            if(res.size() == c){
                break;
            }
            for(int[] i : entry.getValue()){
                if(res.size()==c){
                    break;
                }
                res.add(i);
            }
        }
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();
        int[][] arr = new int[n][2];
        for(int i = 0;i<n;i++){
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        ArrayList<int[]> res = getNearestTrees(arr,n,c);
        for(int[] i : res){
            System.out.println(i[0] + " " + i[1]);
        }
        sc.close();
    }
}