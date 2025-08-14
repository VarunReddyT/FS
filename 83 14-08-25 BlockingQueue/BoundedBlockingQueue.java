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

public class BoundedBlockingQueue {
    private final int capacity;
    private final Deque<Integer> q = new ArrayDeque<>();
    private final Object lock = new Object();

    public BoundedBlockingQueue(int capacity) {
        //implement this
        this.capacity = capacity;
    }

    // FIFO: add to TAIL; block if full
    public void enqueue(int x) throws InterruptedException {
        synchronized (lock) {
            //implement this
            if(q.size() == capacity){
                return;
            }
            q.addLast(x);
        }
    }

    // FIFO: remove from HEAD; block if empty
    public int dequeue() throws InterruptedException {
        synchronized (lock) {
            //implement this
            if(q.size() == 0){
                return 0;
            }
            int num = q.removeFirst();
            return num;
        }
    }

    public int size() {
        synchronized (lock) {
            //implement this
            return q.size();
        }
    }
    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Line 1: number of producers (kept for format)
        if (!sc.hasNextLine()){
            sc.close();
            return;
        }
        int numProducers = Integer.parseInt(sc.nextLine().trim());

        // Line 2: number of consumers (kept for format)
        if (!sc.hasNextLine()){
            sc.close();
            return;
        }
        int numConsumers = Integer.parseInt(sc.nextLine().trim());

        // Line 3: opcodes (0=create, 1=enqueue, 2=dequeue, 3=size)
        if (!sc.hasNextLine()){
            sc.close();
            return;
        }
        String opsLine = sc.nextLine().trim();
        String[] opsStr = opsLine.isEmpty() ? new String[0] : opsLine.split("\\s+");
        int[] ops = new int[opsStr.length];
        for (int i = 0; i < opsStr.length; i++) ops[i] = Integer.parseInt(opsStr[i]);

        // Line 4: arguments for op=0 and op=1 only, in encounter order (may be empty)
        int[] argsForOps = new int[0];
        if (sc.hasNextLine()) {
            String argsLine = sc.nextLine().trim();
            if (!argsLine.isEmpty()) {
                String[] argsStr = argsLine.split("\\s+");
                argsForOps = new int[argsStr.length];
                for (int i = 0; i < argsStr.length; i++) argsForOps[i] = Integer.parseInt(argsStr[i]);
            }
        }

        // Process the operations
        BoundedBlockingQueue queue = null;
        int argIdx = 0;
        List<Integer> outputs = new ArrayList<>();

        for (int op : ops) {
            switch (op) {
                case 0: { // create
                    if (argIdx >= argsForOps.length)
                        throw new IllegalArgumentException("Missing capacity for create (op=0)");
                    int capacity = argsForOps[argIdx++];
                    queue = new BoundedBlockingQueue(capacity);
                    break;
                }
                case 1: { // enqueue
                    if (queue == null) throw new IllegalStateException("Queue not created before enqueue");
                    if (argIdx >= argsForOps.length)
                        throw new IllegalArgumentException("Missing value for enqueue (op=1)");
                    int val = argsForOps[argIdx++];
                    queue.enqueue(val);
                    break;
                }
                case 2: { // dequeue -> output
                    if (queue == null) throw new IllegalStateException("Queue not created before dequeue");
                    int val = queue.dequeue();
                    outputs.add(val);
                    break;
                }
                case 3: { // size -> output
                    if (queue == null) throw new IllegalStateException("Queue not created before size");
                    outputs.add(queue.size());
                    break;
                }
                default:
                    throw new IllegalArgumentException("Unknown opcode: " + op);
            }
        }

        // Print outputs in order, space-separated
        for (int i = 0; i < outputs.size(); i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(outputs.get(i));
        }
        System.out.println();
        sc.close();
    }
}
