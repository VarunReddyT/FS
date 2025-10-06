// You are working on a CPU workload optimizer for a gaming computer.
// The processor executes a series of N operations, each represented by a numeric ID.
// Every operation takes exactly 1 unit of time to execute.

// However, due to thermal throttling, if the same operation type is executed 
// repeatedly, the CPU must cool down for K time units before executing that 
// same operation ID again. Different operations can be executed during 
// the cooling period.

// Your goal is to find the minimum total time required for the CPU to execute 
// all operations without overheating.

// Input Format
// ------------
// Line-1: Two integers N and K representing the cool-down time between two identical operations.
// Line-2: N space seperated integers representing operation IDs.

// Output Format
// -------------
// Return a single integer — the minimum total time needed to finish all operations.

// Constraints
// -----------
// 1 ≤ number of operations ≤ 10 000
// 0 ≤ k ≤ 100
// Operation IDs are positive integers


// Sample Input:
// -------------
// 4 2
// 1 2 1 1

// Sample Output:
// --------------
// 7


// Explanation:
// ------------
// The CPU runs operations in the order below (using _ to denote idle/
// cool-down slots): 
//         1 → 2 → idle → 1 → idle → idle → 1
//             Total = 7 units

import java.util.*;

public class MinTimeCPUExecution{
    public static int getMinTime(int[] arr, int n, int k){
        int res = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i : arr){
            map.put(i,map.getOrDefault(i,0)+1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i : map.values()){
            pq.add(i);
        }
        
        while(!pq.isEmpty()){
            List<Integer> temp = new ArrayList<>();
            int window = k+1;
            while(window > 0 && !pq.isEmpty()){
                int rem = pq.poll();
                if(rem - 1 > 0){
                    temp.add(rem-1);
                }
                res++;
                window--;
            }
            for(int rem : temp){
                pq.offer(rem);
            }
            if(!pq.isEmpty()){
                res += window;
            }
        }
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        System.out.println(getMinTime(arr,n,k));
        sc.close();
    }
}