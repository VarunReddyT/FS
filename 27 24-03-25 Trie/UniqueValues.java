// Imagine you're managing a busy warehouse where every product is delivered in 
// pairs to ensure proper stocking. However, due to a mix-up at the shipping dock, 
// two unique product IDs ended up without their matching pair, while all other 
// products arrived as complete pairs. Your task is to identify these two solitary 
// product IDs.

// You're given list of product IDs. In this list, every product ID appears exactly 
// twice except for two IDs that appear only once. Return these two unique product 
// \IDs in any order.

// You must design an algorithm that runs in linear time and uses only constant 
// extra space.


// Example 1:
// ----------
// Input: 
// 101 102 101 103 102 105  
// Output: 
// [103, 105] 
 
// Explanation: The IDs 103 and 105 appear only once, while all other IDs appear 
// twice. [105, 103] is also an acceptable answer.

// Example 2:
// -----------
// Input: 121 136
// Output: [121, 136] 

import java.util.*;

public class UniqueValues{
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        String inp [] = sc.nextLine().split(" ");
        int arr [] = new int[inp.length];
        for(int i = 0; i < inp.length ; i++){
            arr[i] = Integer.parseInt(inp[i]);
        }
        System.out.println(find(arr));
        sc.close();
    }
    static String find(int arr[]){
        Set<Integer>set = new HashSet<>();
        for(int i :arr){
            if(set.contains(i)){
               set.remove(i); 
            }
            else{
                set.add(i);
            }
        }
        
        return set.toString();
    }
}