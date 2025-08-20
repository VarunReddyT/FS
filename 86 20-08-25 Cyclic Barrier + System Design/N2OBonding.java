// You are given multiple threads, each representing an atom: Nitrogen (N) or 
// Oxygen (O). Your task is to synchronize these threads so they form Laughing Gas 
// (nitrous oxide - N₂O) molecules. Each molecule must be formed by exactly 
// 2 Nitrogen atoms and 1 Oxygen atom. Threads must:

//     -Pass a barrier only in complete groups of three (2×N + 1×O).
//   - 'Bond' immediately once a complete trio is present, before any atom from 
//     the next molecule proceeds.

// You will implement synchronization so that if we split the output stream into 
// groups of 3 characters, each group contains exactly N, N, O in any order.

// Constraints:
// ------------
// - The input is a string atoms made only of characters 'N' and 'O'.
// - Let k be the number of molecules. Then the string must contain exactly 2k 'N' and k 'O'.
// - 1 ≤ k ≤ 20 (i.e., up to 60 threads).

// Input Fromat:
// -------------
// A single line string atoms of 'N' and 'O', e.g., NOONNN.

// Output Fromat:
// --------------
// A single line string that represents a valid bonding sequence. Every 3-character
// chunk must contain 2 'N' and 1 'O'. Order within each chunk may vary 
// (e.g., NNO, NON, ONN). If the input is not having  exactly 2k*N and k*O letters
// print "Invalid"


// Sample Input:
// -------------
// NOONNN

// Sample Output:
// --------------
// N2O - 1 is formed
// N2O - 2 is formed


// Sample Input:
// -------------
// NOONNNNNNNOO

// Sample Output:
// --------------
// N2O - 1 is formed
// N2O - 2 is formed
// N2O - 3 is formed
// N2O - 4 is formed


import java.util.*;
import java.util.concurrent.*;

class N2O extends Thread{
    Semaphore n;
    Semaphore o;
    int id;
    CyclicBarrier cb;
    char c;
    static int count = 0;
    N2O(int id, char c, CyclicBarrier cb, Semaphore n, Semaphore o){
        this.id = id;
        this.c = c;
        this.cb = cb;
        this.n = n;
        this.o = o;
    }
    public void run(){
        try{
            if(c == 'N'){
                n.acquire();
            }
            else if(c == 'O'){
                o.acquire();
            }

            int idx = cb.await();

            if(idx == 0){
                count++;
                System.out.println("N2O - " + count + " is formed");

                n.release(2);
                o.release(1);
            }
        }
        catch(Exception e){
            System.out.println("Invalid");
        }
    }
}
public class N2OBonding{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        
        int ncount = 0;
        int ocount = 0;
        for(char c : s.toCharArray()){
            if(c=='N'){
                ncount++;
            }
            else if(c=='O'){
                ocount++;
            }
        }
        if(ocount*2 != ncount){
            System.out.println("Invalid");
            sc.close();
            return;
        }
        
        Semaphore n = new Semaphore(2);
        Semaphore o = new Semaphore(1);

        CyclicBarrier cb = new CyclicBarrier(3);
        
        for(int i = 0;i<s.length();i++){
            new N2O(i+1,s.charAt(i),cb,n,o).start();
        }

        sc.close();
    }
}