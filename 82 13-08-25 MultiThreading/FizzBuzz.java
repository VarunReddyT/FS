// There are a group of kids playing a game in a circle to test their arithmetic division skills.
// The game starts with Kid 1, who says the number 1 to begin. Then, in clockwise order, each kid 
// takes a turn and says the next number, but with the following rules:
//  - If the number is divisible by 3 (but not by 5), the kid says "Hi".
//  - If the number is divisible by 5 (but not by 3), the kid says "Hello".
//  - If the number is divisible by both 3 and 5, the kid says "HiHello".
//  - Otherwise, the kid simply says the number itself.

// The same game rules are now implemented in a multi-threaded environment.
// We are given an instance of a HiHelloGame class, which has four methods:

//  - hi(printHi) → Prints "Hi" when the number is divisible by 3 only.
//  - hello(printHello) → Prints "Hello" when the number is divisible by 5 only.
//  - hiHello(printHiHello) → Prints "HiHello" when the number is divisible by both 3 and 5.
//  - number(printNumber) → Prints the number itself when none of the above conditions are met.

// The same instance of HiHelloGame will be shared across four threads:

// Thread A: Calls hi() to output "Hi".
// Thread B: Calls hello() to output "Hello".
// Thread C: Calls hiHello() to output "HiHello".
// Thread D: Calls number() to output the number.

// Your task is to modify the HiHelloGame class so that it outputs the correct sequence for numbers 
// from 1 to N in the correct order according to the rules above.

// Input Format:
// -------------
// Line-1: An integer N.

// Output Format:
// --------------
// Print a string array[].

// Constraints:
// • 1 <= n <= 10^4
 
 
// Sample Input-1:
// ---------------
// 5

// Sample Output-1:
// ----------------
// 1 2 Hi 4 Hello


// Sample Input-2:
// ---------------
// 15

// Sample Output-2:
// ----------------
// 1 2 Hi 4 Hello Hi 7 8 Hi Hello 11 Hi 13 14 HiHello 

import java.util.*;

class HiHelloGame{
    int n;
    int curr;
    HiHelloGame(int n){
        this.n = n;
        this.curr = 1;
    }
    
    public synchronized void hi() throws InterruptedException{
        while(curr <= n){
            if(curr%3 == 0 && curr%5 != 0){
                System.out.print("Hi ");
                curr++;
                notifyAll();
            }
            else{
                wait();
            }
        }
    }
    public synchronized void hello() throws InterruptedException{
        while(curr <= n){
            if(curr%3 != 0 && curr%5 == 0){
                System.out.print("Hello ");
                curr++;
                notifyAll();
            }
            else{
                wait();
            }
        }
    }
    public synchronized void hiHello() throws InterruptedException{
        while(curr <= n){
            if(curr%3 == 0 && curr%5 == 0){
                System.out.print("HiHello ");
                curr++;
                notifyAll();
            }
            else{
                wait();
            }
        }
    }
    public synchronized void number() throws InterruptedException{
        while(curr <= n){
            if(curr%3 != 0 && curr%5 != 0){
                System.out.print(curr + " ");
                curr++;
                notifyAll();
            }
            else{
                wait();
            }
        }
    }
}

public class FizzBuzz{
    public static void main(String[] args) throws InterruptedException{
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HiHelloGame game = new HiHelloGame(n);
        Thread t1 = new Thread(()->{
            try{
                game.hi();
            }
            catch(InterruptedException e){
                System.out.println(e);
            }
        });
        Thread t2 = new Thread(()->{
            try{
                game.hello();
            }
            catch(InterruptedException e){
                System.out.println(e);
            }
        });
        Thread t3 = new Thread(()->{
            try{
                game.hiHello();
            }
            catch(InterruptedException e){
                System.out.println(e);
            }
        });
        Thread t4 = new Thread(()->{
            try{
                game.number();
            }
            catch(InterruptedException e){
                System.out.println(e);
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        sc.close();
    }
}