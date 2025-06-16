

import java.util.*;

class Job {
    String name;
    int at;
    int bt;
    int p;
    int wt;
    int tt;
    int remt;

    Job(String n, int at, int bt, int p) {
        this.name = n;
        this.at = at;
        this.bt = bt;
        this.remt = bt;
        this.p = p;
    }

    @Override
    public String toString() {
        return name + " " + at + " " + bt + " " + p + " " + wt + " " + tt;
    }
}

public class PriorityRoundRobin {
    public static void main(String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            int n = cin.nextInt();
            Job arr[] = new Job[n];
            for (int i = 0; i < n; i++) {
                arr[i] = new Job(cin.next(), cin.nextInt(), cin.nextInt(), (cin.next().equals("Staff") ? 1 : 0));
            }
            int quantumSecs = cin.nextInt();

            find(arr, quantumSecs);
        }
    }

    static void find(Job arr[], int qs) {
        Arrays.sort(arr, (a, b) -> a.at - b.at);
        int time = 0;
        Queue<Job> highPQ = new LinkedList<>();
        Queue<Job> lowPQ = new LinkedList<>();
        // List<Job> completed = new ArrayList<>();
        int i = 0;
        while (i < arr.length || !highPQ.isEmpty() || !lowPQ.isEmpty()) {

            while (i < arr.length && arr[i].at <= time) {
                if (arr[i].p == 1)
                    highPQ.offer(arr[i]);
                else
                    lowPQ.offer(arr[i]);
                i++;
            }

            Queue<Job> currQ = !highPQ.isEmpty() ? highPQ : lowPQ;

            if (!currQ.isEmpty()) {
                Job job = currQ.poll();
                int execTime = Math.min(qs, job.remt);
                job.remt -= execTime;
                time += execTime;

                // Add newly arrived jobs during execution time
                while (i < arr.length && arr[i].at <= time) {
                    if (arr[i].p == 1)
                        highPQ.offer(arr[i]);
                    else
                        lowPQ.offer(arr[i]);
                    i++;
                }

                if (job.remt == 0) {
                    job.tt = time - job.at;
                    job.wt = job.tt - job.bt;

                } else {
                    currQ.offer(job); // Re-queue the job
                }
            } else {
                // CPU is idle
                time = arr[i].at;
            }
        }

        // System.out.println(completed);
        Arrays.sort(arr, Comparator.comparing(j -> j.name));
        System.out.println("JobID  WaitingTime  TurnaroundTime");
        double totalWT = 0, totalTT = 0;
        for (Job j : arr) {
            System.out.printf("%-6s %-13d %d\n", j.name, j.wt, j.tt);
            totalWT += j.wt;
            totalTT += j.tt;
        }

        System.out.printf("Average Waiting Time: %.2f\n", totalWT / arr.length);
        System.out.printf("Average Turnaround Time: %.2f\n", totalTT / arr.length);
    }
}