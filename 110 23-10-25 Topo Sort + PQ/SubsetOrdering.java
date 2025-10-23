/*
Manoj is working on sets and relations.
He is given a set S consist of N integers from 1 to N, without dupliactes.
The set S may contain any shuffled order of 1 to N numbers.
And also P number of subsets also given where each subset of size Q.
Each subset is a subsequence of shuffled set S.

Manoj has to check, using the given subsets can he form the set S uniquely
or not. i.e., the order of numbers in the subsets remains unchanged and
can form only one unique super set using the subsets, and
the unique super set should be S.

Your task is to help Manoj to check whether it is possible to form
the shuffled set S uniquely from the given subsets.

For example:
-----------
If given shuffled set is [2,4,6] and subsets are [2,4] [2,6].
You can form the following sets, [2,4,6] or [2,6,4].

So, you should return false, as the given subsets form more than 1 set.

Simply, there should be always only one unique super set can be formed.
and that set should be equal to S.


Input Format:
-------------
Line-1: An integer N, size of the shuffled array.
Line-2: N space separated integers, shuffled set S.
Line-3: Two space separated integers P and Q, number of subsets, size of subsets
Next P lines: Q space separated integers, non repeated integers from [1-N]

Output Format:
--------------
Print a boolean value, can you form the shuffled set S uniquely or not.


Sample Input-1:
---------------
4
1 3 2 4
3 2
1 2
3 2
3 4

Sample Output-1:
----------------
false

Explanation:
------------
The subsets are [1,2], [3,2] and [3,4] cannot
form the given shuffled set [1,3,2,4].
It can form another set as [1,3,4,2] also.


Sample Input-2:
---------------
4
1 3 2 4
4 2
1 2
3 2
1 3
2 4

Sample Output-2:
----------------
true

Explanation:
------------
The subsets are [1,2], [3,2], [1,3] and [2,4] can uniquely
form the given shuffled set [1,3,2,4].


Sample Input-3:
---------------
5
1 3 5 4 2
3 3
3 4 2
5 4 2
1 3 5

Sample Output-3:
----------------
true

Explanation:
------------
The subsets are [3,4,2], [5,4,2], and [1,3,5] can uniquely
form the given shuffled set [1,3,5,4,2].

*/


import java.util.*;
import java.util.LinkedList;

public class SubsetOrdering{
    static List<List<Integer>> arr = new ArrayList<>();
    public static boolean solve(int[] nums, int n){
        int[] indegree = new int[n+1];
        for(int i = 1;i<=n;i++){
            for(int j : arr.get(i)){
                indegree[j]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1;i<=n;i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }
        int i = 0;
        while(!q.isEmpty() && q.size()==1){
            int top = q.poll();
            if(nums[i] != top){
                return false;
            }
            i++;
            for(int j : arr.get(top)){
                indegree[j]--;
                if(indegree[j] == 0){
                    q.add(j);
                }
            }
        }
        return i == n && q.isEmpty();
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i=0;i<n;i++){
            nums[i] = sc.nextInt();
        }
        int p = sc.nextInt();
        int q = sc.nextInt();

        for(int i=0;i<=n;i++){
            arr.add(new ArrayList<>());
        }

        for(int i = 0;i<p;i++){
            int inp[] = new int[q];
            for(int j = 0; j < q; j++){
                inp[j] = sc.nextInt();
            }
            if(inp.length!=1){
                for(int j = 0; j < q-1; j++){
                    arr.get(inp[j]).add(inp[j+1]);
                }
            }
        }
        System.out.println(solve(nums,n));
        sc.close();
    }
}