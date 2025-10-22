import java.util.*;
import java.util.LinkedList;

public class Solution{
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
        while(!q.isEmpty()){
            if(q.size() > 1){
                return false;
            }
            int node = q.poll();
            if(nums[i] != node){
                return false;
            }
            i++;
            for(int j : arr.get(node)){
                indegree[j]--;
                if(indegree[j] == 0){
                    q.add(j);
                }
            }
        }
        return i == n;
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
            int x = sc.nextInt();
            for(int j=0;j<q-1;j++){
                int neighbor = sc.nextInt();
                arr.get(x).add(neighbor);
            }
        }
        System.out.println(solve(nums,n));
        sc.close();
    }
}