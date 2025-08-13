// You’re building a VIP Shuttle Bay controller:
// VIP shuttles (producers) arrive with passengers and must board at the front of the bay.
// Buses (consumers) depart from the rear, taking the rear-most group.
// The bay has a fixed capacity (maximum groups it can hold).
// If the bay is full, arriving VIPs (producers) must wait.
// If the bay is empty, departing buses (consumers) must wait.
// You must implement a thread-safe bounded blocking queue with:

// 	- BoundedBlockingQueue(int capacity) — set maximum capacity. 
// 	- void enqueue(int x) — add x to the front; block if full.
// 	- int dequeue() — remove and return the rear; block if empty.
// 	- int size() — return current count.

// Multiple producer threads will only call enqueue; multiple consumer threads will only call 
// dequeue. The judge may call size() after the test.

//  Input Format:
//  -------------
// Num of Producers
// Num of Consumers
// OpCodes...space-separated integers
// Args For Ops Needing Args

// OpCodes:
// -----------
// 0 → create the queue. Requires 1 argument (capacity).
// 1 → enqueue. Requires 1 argument (the element).
// 2 → dequeue. No argument; output the returned value.
// 3 → size. No argument; output the current size.

// Output Format:
// --------------
// Print the sequence of results in the order operations are executed:
//  - For every 2 (dequeue), print the dequeued value.
//  - For every 3 (size), print the size at that moment.
 
// Sample Input:
// ---------------
// 3
// 4
// 0 1 1 1 2 2 2 1 3
// 3 1 0 2 3

// Sample Output:
// --------------
// 1 0 2 1

// Explanation:
// -------------
// So the operation stream is:
// create(3)
// enqueue(1)
// enqueue(0)
// enqueue(2)
// dequeue() → outputs one of {1,0,2} depending on interleaving
// dequeue() → outputs one of the remaining two
// dequeue() → outputs the last remaining
// enqueue(3)
// size() → outputs 1 element remaining in the queue 


import java.util.*;
import java.util.LinkedList;

class BlockingQueue{
    private int capacity;
    private LinkedList<Integer> list; 
    BlockingQueue(int n){
        this.capacity = n;
        this.list = new LinkedList<>();
    }
    public void enqueue(int num) throws InterruptedException{
        synchronized(this){
            if(list.size() == capacity){
                wait();
            }
            list.add(num);
            notifyAll();
        }
    }
    public int dequeue() throws InterruptedException {
        synchronized(this){
            if(list.isEmpty()){
                wait();
            }
            int num = list.removeFirst();
            notifyAll();
            return num;
        }
    }
    public int getCapacity() {
        synchronized(this){
            return list.size();
        }
    }
}

public class BlockingQueueSimulation{
    @SuppressWarnings("unused")
    public static void main(String[] args) throws InterruptedException{
        Scanner sc = new Scanner(System.in);
        int p = sc.nextInt();
        int c = sc.nextInt();
        sc.nextLine();
        String[] opCodes = sc.nextLine().split(" ");
        String[] arguments = sc.nextLine().split(" ");
        
        int n = Integer.parseInt(arguments[0]);

        BlockingQueue b = new BlockingQueue(n);

        int[] argIndex = {1};

        Thread producer = new Thread(() -> {
            try {
                for (String opStr : opCodes) {
                    int op = Integer.parseInt(opStr);
                    if (op == 1) {
                        int val = Integer.parseInt(arguments[argIndex[0]++]);
                        b.enqueue(val);
                    }
                }
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (String opStr : opCodes) {
                    int op = Integer.parseInt(opStr);
                    if (op == 2) {
                        int val = b.dequeue();
                        System.out.print(val+ " ");
                    }
                    if(op == 3){
                        System.out.print(b.getCapacity() + " ");
                    }
                }
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        });

        producer.start();
        consumer.start();
        
        producer.join();
        consumer.join();

        sc.close();
    }    
}
