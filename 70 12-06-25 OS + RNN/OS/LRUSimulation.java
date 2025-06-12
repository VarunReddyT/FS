// You are building memory management for a smart home hub where apps are loaded on-demand. 
// The hub has limited memory (cache size). If an app is not in memory (cache miss), 
// it is loaded and a page fault occurs. If memory is full, the Least Recently Used 
// (LRU) app is removed.

// Simulate the LRU page replacement and count total page faults.

// Input Format:
// -------------
// - Cache size
// - Space-separated app access sequence (e.g., Thermostat Camera Lights)

// Output Format:
// --------------
// Total page faults
// Final cache contents

// Sample Input:
// -------------
// 3
// Thermostat Camera Lights Thermostat Camera Doorbell Lights Thermostat

// Sample Output:
// --------------
// Total Page Faults: 6
// Final Cache: [Doorbell, Lights, Thermostat]


// Sample Input:
// --------------
// 2
// AC Light Fan AC Heater Light

// Sample Output:
// --------------
// Total Page Faults: 6
// Final Cache: [Heater, Light]

import java.util.*;

public class LRUSimulation{
    public static void getPageFaults(String[] input, int n){
        LinkedHashMap<String,Integer> map = new LinkedHashMap<>();
        int pageFaults = 0;
        int count = 0;
        for(String i : input){
            if(!map.containsKey(i)){
                pageFaults++;
                if(map.size()==n){
                    String lru = "";
                    int min = Integer.MAX_VALUE;
                    for(Map.Entry<String,Integer> entry : map.entrySet()){
                        if(entry.getValue()<min){
                            min = entry.getValue();
                            lru = entry.getKey();
                        }
                    }
                    map.remove(lru);
                }
                map.put(i,++count);
            }
            else{
                map.remove(i);
                map.put(i,++count);
            }
        }
        System.out.println("Total Page Faults: " + pageFaults);
        System.out.print("Final Cache:" + " ");
        for(String key : map.keySet()){
            System.out.print(key + " ");
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] input = sc.nextLine().split(" ");
        getPageFaults(input,n);
        sc.close();
    }
}