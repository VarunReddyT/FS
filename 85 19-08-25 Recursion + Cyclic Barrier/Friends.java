import java.util.*;
import java.util.concurrent.*;

class Friend extends Thread{
    int id;
    CyclicBarrier cb;
    long timeTaken;
    long[][] times;
    ArrayList<Double> finalTimes;

    Friend(int id, CyclicBarrier cb, long[][] times, ArrayList<Double> finalTimes){
        this.id = id;
        this.cb = cb;
        this.timeTaken = 0;
        this.times = times;
        this.finalTimes = finalTimes;
    }

    void solvePuzzle(int puzzle) throws InterruptedException {
        System.out.println("Friend " + id + " is solving puzzle " + puzzle + ".");
        long startTime = System.currentTimeMillis();
        Thread.sleep((long) (Math.random() * 4000));
        timeTaken += System.currentTimeMillis() - startTime;

        times[id-1][puzzle-1] = System.currentTimeMillis() - startTime;

        System.out.println("Friend " + id + " has solved puzzle " + puzzle + ".");
    }

    public void run(){
        try{
            for(int i = 0;i<3;i++){
                solvePuzzle(i+1);
                cb.await();
                
                if(id == 1){
                    long maxTime = 0;
                    for(int j = 0; j < 4; j++){
                        if(times[j][i] > maxTime){
                            maxTime = times[j][i];
                        }
                    }
                    // System.out.println("Maximum time for cyclic barrier : " + maxTime + " milliseconds.");
                    finalTimes.add((double)maxTime/1000);
                    // System.out.println(Arrays.deepToString(times));
                }
                cb.await();
            }
            System.out.println("Friend " + id + " has completed all puzzles");
            cb.await();
            if(id == 1){
                System.out.println("Final times for each puzzle: " + finalTimes);
            }

        }
        catch(Exception e){
            System.out.println(e.getCause());
        }
    }
}

public class Friends {
    public static void main(String[] args) {
        int friends = 4;

        CyclicBarrier cb = new CyclicBarrier(4);
        long[][] times = new long[friends][3];
        ArrayList<Double> finalTimes = new ArrayList<>();

        for(int i = 0;i<friends;i++){
            new Friend(i+1,cb,times,finalTimes).start();
        }

    }
}
