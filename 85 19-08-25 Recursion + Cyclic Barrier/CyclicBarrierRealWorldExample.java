import java.util.concurrent.*;

class Worker extends Thread {
    private int id;
    private CyclicBarrier barrier;

    public Worker(int id, CyclicBarrier barrier) {
        this.id = id;
        this.barrier = barrier;
    }

    private void doWork(int stage) throws InterruptedException {
        System.out.println("Worker " + id + " working on stage " + stage);
        Thread.sleep((long) (Math.random() * 2000)); // simulate work
        System.out.println("Worker " + id + " finished stage " + stage);
    }

    public void run() {
        try {
            for (int stage = 1; stage <= 3; stage++) {
                doWork(stage);

                // wait for all workers to complete the stage
                System.out.println("Worker " + id + " waiting at barrier after stage " + stage);
                barrier.await();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class CyclicBarrierRealWorldExample {
    public static void main(String[] args) {
        int numWorkers = 4;

        // Barrier action: runs once all threads hit the barrier
        Runnable barrierAction = () -> {
            System.out.println("\n✅ All workers finished this stage. Moving to next stage...\n");
        };

        CyclicBarrier barrier = new CyclicBarrier(numWorkers, barrierAction);

        for (int i = 1; i <= numWorkers; i++) {
            new Worker(i, barrier).start();
        }
    }
}
