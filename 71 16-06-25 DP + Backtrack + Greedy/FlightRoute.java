// Gopal would like to go on Tour, and planned a schedule.
// Airport authority has created a new way of issuing tickets.
// Gopal purchased a set of airline tickets, 
// each ticket contains the 'departure from' and 'arrival to'.

// Redesign the Gopal's tour schedule in an order.
// Gopal intially must begin his tour from BZA.
// Hence the tour schedule's start point should begin with BZA. 

// You are given a list of flight tickets which Gopal has purchased.
// Your task is to find out and return the tour schedule that has the least 
// lexical order. Gopal must use all the tickets and only once.

// Note:
// ------
// If there are multiple valid schedules, you should return the schedule 
// that has the smallest lexical order when read as a single string. 
// For example, the schedule ["BZA", "LGA"] has a smaller lexical order than ["BZA", "LGB"].

// All airports are represented by three capital letters.
// You may assume all tickets form at least one valid schedule.

// Input Format:
// -------------
// Single Line of tickets separated by comma, and each ticket separated by space, 
// Gopal's flight tickets.

// Output Format:
// --------------
// Print the schedule, which is smallest lexical order of tour schedule.


// Sample Input-1:
// ----------------
// DEL HYD,BZA DEL,BLR MAA,HYD BLR

// Sample Output-1:
// --------------------
// [BZA, DEL, HYD, BLR, MAA]


// Sample Input-2:
// ------------------
// BZA BLR,BZA CCU,BLR CCU,CCU BZA,CCU BLR

// Sample Output-2:
// ------------------
// [BZA, BLR, CCU, BZA, CCU, BLR]

import java.util.*;

public class FlightRoute{
    public static boolean dfs(HashMap<String,List<String>> map, List<String> res, String currCity, int[] count){
        if(count[0] == 0){
            return true;
        }
        if(!map.containsKey(currCity) || (map.containsKey(currCity) && map.get(currCity).size() == 0)){
            return false;
        }
        List<String> cities = map.get(currCity);
        if(cities == null || cities.isEmpty()){
            return false;
        }
        for(int i = 0;i<cities.size();i++){
            count[0]--;
            String next = cities.get(i);
            cities.remove(i);
            if(dfs(map,res,next,count)){
                res.add(next);
                return true;
            }
            count[0]++;
            cities.add(i,next);
        }
        return false;
    }
    public static List<String> getRoute(String[][] routes, int n){
        List<String> res = new ArrayList<>();
        HashMap<String,List<String>> map = new HashMap<>();
        for(String[] i : routes){
            map.putIfAbsent(i[0], new ArrayList<>());
            map.get(i[0]).add(i[1]);
        }
        for(Map.Entry<String,List<String>> entry : map.entrySet()){
            Collections.sort(entry.getValue());
        }
        res.add("BZA");
        int[] count = {n};
        dfs(map,res,"BZA",count);
        List<String> result = new ArrayList<>();
        result.add("BZA");
        for(int i = res.size()-1;i>0;i--){
            result.add(res.get(i));
        }
        return result;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(",");
        String[][] routes = new String[input.length][2];
        for(int i = 0;i<input.length;i++){
            String[] ticket = input[i].split(" ");
            routes[i][0] = ticket[0];
            routes[i][1] = ticket[1];
        }
        System.out.println(getRoute(routes,input.length));
        sc.close();
    }
}