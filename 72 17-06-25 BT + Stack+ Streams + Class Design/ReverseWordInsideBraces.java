// Aruna as a type writer, typing a document in her laptop. Suddently her system got
// hacked and whatever she types as words are displaying in reverse and with simple 
// braces. She types only lowercase letters.

// Inorder to get the actual words, Aruna has to reverse each word starting with the
// word which is in innermost most braces and remove those braces.
// Help Aruna to get actual words.

// Constraints:
// ------------
//   - 0 <= word.length <= 2000
//   - Word only contains lower case English characters and parentheses.
//     It's guaranteed that all braces are balanced.


// Input Format:
// -------------
// Line-1: a string represents an encoded word.

// Output Format:
// --------------
// return the original string.


// Sample Input-1:
// ---------------
// (pqrs)

// Sample Output-1:
// ----------------
// srqp


// Sample Input-2:
// ---------------
// (uoy(are)woh)

// Sample Output-2:
// ----------------
// howareyou

// Explanation
// ------------
// Initially "are" will be revesed as "era".
// Then (uoyerawoh) will be reversed.

import java.util.*;
import java.util.LinkedList;

public class ReverseWordInsideBraces{
    public static String getReversedWord(String s){
        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i) == ')'){
                while(!stack.isEmpty() && stack.peek() != '('){
                    queue.offer(stack.pop());
                }
                stack.pop();
                while(!queue.isEmpty()){
                    stack.push(queue.poll());
                }
            }
            else{
                stack.push(s.charAt(i));
            }
        }
        String res = "";
        while(!stack.isEmpty()){
            res = stack.pop() + res;
        }
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(getReversedWord(s));
        sc.close();
    }
}