// You write a love letter to your friend. However, before your friend can read it, 
// someone else takes it and rotates the characters of each word from left to right 
// K times.

// Your task is to determine how many of the words still remain the same even after 
// this rotation.

// Input Format:
// -------------
// input1: A string containing words separated by spaces.
// input2: An integer K indicating the number of right rotations for each word.

// Output Format:
// --------------
// An integer representing the number of words that remain unchanged after K right 
// rotations.


// Sample Input: 
// -------------
// ramram santan nnnn
// 3

// Sample Output:
// --------------
// 2


// Sample Input: 
// -------------
// adasda
// 3

// Sample Output:
// --------------
// 0

import java.util.*;

public class UnchangedWords{
    public static int getValidStrings(String[] input, int n){
        int count = 0;
        for(String i : input){
            int rotations = n%i.length();
            StringBuilder sb = new StringBuilder(i.substring(rotations,i.length()));
            sb.append(i.substring(0,rotations));
            if(i.equals(sb.toString())){
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int n = sc.nextInt();
        System.out.println(getValidStrings(input,n));
        sc.close();
    }
}