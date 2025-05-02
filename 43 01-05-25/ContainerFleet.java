// You are managing a fleet of exploratory spacecraft, each carrying containers 
// composed of two types of critical resources: 
// fuel units (represented by '0') and oxygen tanks (represented by '1'). 
// You're given a list 'containers', where each container is represented by a 
// binary string indicating its resource composition, 
// along with two integers: 'fuelLimit' (maximum allowed fuel units) and 
// 'oxygenLimit' (maximum allowed oxygen tanks).

// Your goal is to select the largest possible subset of containers such that the 
// total number of fuel units does not exceed 'fuelLimit' and the total number of 
// oxygen tanks does not exceed 'oxygenLimit'.

// Input format:
// -------------
// Line 1: Space seperated strings, represents the container strings
// Line 2: Two space separated integers, represents fuelLimit & oxygenLimit

// Output format:
// --------------
// An integer, largest possible subset of containers.


// Example 1:
// ----------
// Input=
// 10 0001 111001 1 0
// 5 3

// Output=
// 4

// Explanation: The largest subset meeting the constraints is {"10", "0001", "1", "0"}.
// - Total fuel units = 5 (within limit)
// - Total oxygen tanks = 3 (within limit)
// Container "111001" cannot be included as it exceeds the oxygen tank limit.


// Example 2:
// ----------
// Input=
// 10 0 1
// 1 1

// Output=
// 2

// Explanation: The largest subset meeting the constraints is {"0", "1"}.
// - Total fuel units = 1
// - Total oxygen tanks = 1


// Constraints:
// - 1 <= containers.length <= 600
// - 1 <= containers[i].length <= 100
// - 'containers[i]' consists only of digits '0' and '1'.
// - 1 <= fuelLimit, oxygenLimit <= 100


import java.util.*;
public class ContainerFleet{
    public static int helper(int arr[][], int[][][] dp, int index,int f, int o){
        if(index==arr.length) return 0;
        if(dp[index][f][o]!=-1) return dp[index][f][o];
        else if(arr[index][0]<=f && arr[index][1]<=o) return dp[index][f][o] =  Math.max(1+helper(arr,dp,index+1,f-arr[index][0],o-arr[index][1]),helper(arr,dp,index+1,f,o));
        return dp[index][f][o] = helper(arr,dp,index+1,f,o);
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s[] = sc.nextLine().split(" ");
        int f = sc.nextInt(); //0
        int o = sc.nextInt(); //1
        int arr[][] = new int[s.length][2];
        for(int st=0;st<s.length;st++){
            int count1 = 0;
            for(int i=0;i<s[st].length();i++){
                count1+=s[st].charAt(i)=='1'?1:0;
            }
            arr[st][0] = s[st].length()-count1;
            arr[st][1] = count1;
        }
        int[][][] dp = new int[s.length][f+1][o+1];
        for(int i=0;i<s.length;i++){
            for(int j=0;j<f+1;j++){
                Arrays.fill(dp[i][j],-1);
            }
        }
        
        
        System.out.println(helper(arr,dp,0,f,o));
        sc.close();
        
    }
}

