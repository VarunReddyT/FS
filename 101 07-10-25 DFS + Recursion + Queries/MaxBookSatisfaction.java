// The university’s digital library is designing an AI-powered Reading Planner.
// Each book belongs to a certain category and offers a satisfaction score (based 
// on reader feedback) and requires a certain reading time (in hours).

// A reader wants to create a personalized reading plan that:
//     - Maximizes total satisfaction.
//     - Does not exceed the total available reading time T.   
//     - Does not include duplicate books (some books appear in multiple categories).

// Each category offers one or more books with satisfaction and reading time values.

// Input Format:
// -------------
// N M T
// <for M categories:>
// bCount bookID_1 time_1 score_1 bookID_2 time_2 score_2 ...

// where:
// N = number of unique books in the library
// M = number of categories
// T = total time available to read (like a knapsack limit)

// Output Format:
// --------------
// Maximum total satisfaction achievable without exceeding total time T.


// Sample Input:
// -------------
// 8 4 10
// 2 1 2 50 2 3 60
// 2 3 4 80 4 5 90
// 3 5 3 70 6 4 85 7 2 40
// 2 1 5 100 8 3 60

// Sample Output:
// --------------
// 215

// Explanation:
// ------------

// Combination	Books	    Total_Time	Total_Score	        Valid?
// --------------------------------------------------------------
// (2, 4, 7)	3 + 5 + 2	10	        60 + 90 + 40 = 190	✅
// (2, 4, 8)	3 + 5 + 3	11	        60 + 90 + 60 = 210	❌ (too long)
// (2, 6, 7)	3 + 4 + 2	9	        60 + 85 + 40 = 185	✅
// (4, 7, 8)	5 + 2 + 3	10	        90 + 40 + 60 = 190	✅
// (1, 3, 6)	2 + 4 + 4	10	        50 + 80 + 85 = 215  ✅	(Accepted )
// (1, 3, 8)	2 + 4 + 3	9	        50 + 80 + 60 = 190  ✅

import java.util.*;

public class MaxBookSatisfaction{
    public static int getMaxSatisfaction(List<List<int[]>> arr, int n, int m, int t){
        boolean[] visited = new boolean[n+1];
        return solve(arr,0,0,t,visited);
    }
    public static int solve(List<List<int[]>> arr, int idx, int currTime, int time, boolean[] visited){
        if(idx == arr.size()){
            return 0;
        }
        int notTake = solve(arr,idx+1,currTime,time,visited);
        int take = 0;
        for(int[] i : arr.get(idx)){
            if(currTime + i[1] <= time && !visited[i[0]]){
                visited[i[0]] = true;
                take = Math.max(take,i[2] + solve(arr,idx+1,currTime + i[1], time, visited));
                visited[i[0]] = false;
            }
        }
        return Math.max(take,notTake);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int t = sc.nextInt();
        List<List<int[]>> arr = new ArrayList<>();
        for (int i = 0;i<m;i++) {
            int bCount = sc.nextInt();
            List<int[]> temp = new ArrayList<>();
            for (int j= 0;j<bCount;j++) {
                int bookID = sc.nextInt();
                int time = sc.nextInt();
                int score = sc.nextInt();
                temp.add(new int[]{bookID, time, score});
            }
            arr.add(temp);
        }
        System.out.println(getMaxSatisfaction(arr,n,m,t));
        sc.close();
    }
}