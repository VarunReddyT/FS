// You are tasked with simulating a university print shop that schedules jobs using
// priority-based Round Robin scheduling. Each job has a user type (Staff gets 
// higher priority than Student), an arrival time, and a burst time. Jobs of the 
// same priority follow the Round Robin algorithm with a fixed time quantum.

// Input Format:
// -------------
//  - Number of jobs
//  - For each job: JobID ArrivalTime BurstTime UserType
// - Time Quantum

// Output Format:
// --------------
//  - Per-job waiting time and turnaround time
//  - Average Waiting Time
//  - Average Turnaround Time

// Sample Input-1:
// ---------------
// 4
// P1 0 5 Staff
// P2 1 4 Student
// P3 2 3 Staff
// P4 3 2 Student
// 2

// Sample Output-1:
// -----------------
// JobID  WaitingTime  TurnaroundTime
// P1     3            8
// P2     9            13
// P3     2            5
// P4     7            9
// Average Waiting Time: 5.25
// Average Turnaround Time: 8.75


// Sample Input-2:
// --------------
// 3
// J1 0 6 Student
// J2 1 4 Staff
// J3 2 2 Student
// 2

// Sample Output-2:
// ----------------
// JobID  WaitingTime  TurnaroundTime
// J1     6            12
// J2     1            5
// J3     4            6
// Average Waiting Time: 3.67
// Average Turnaround Time: 7.67

import java.util.*;
import java.util.LinkedList;
class Job {
    String id;
    int arrivalTime;
    int burstTime;
    int remainingTime;
    String userType;
    int completionTime;
    int firstSeenTime = -1;

    Job(String id, int arrivalTime, int burstTime, String userType) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.userType = userType;
    }
}

public class PriorityRoundRobin {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numJobs = sc.nextInt();
        List<Job> allJobs = new ArrayList<>();

        for (int i = 0; i < numJobs; i++) {
            String id = sc.next();
            int arrival = sc.nextInt();
            int burst = sc.nextInt();
            String userType = sc.next();
            allJobs.add(new Job(id, arrival, burst, userType));
        }

        int quantum = sc.nextInt();

        simulate(allJobs, quantum);
        sc.close();
    }

    static void simulate(List<Job> allJobs, int quantum) {
        int currentTime = 0;
        int totalJobs = allJobs.size();
        List<Job> completedJobs = new ArrayList<>();
        Queue<Job> queue = new LinkedList<>();

        allJobs.sort(Comparator.comparingInt(j -> j.arrivalTime));
        Set<Job> arrived = new HashSet<>();
        
        while (completedJobs.size() < totalJobs) {
            // Add newly arrived jobs to the queue
            for (Job job : allJobs) {
                if (job.arrivalTime <= currentTime && !arrived.contains(job)) {
                    queue.offer(job);
                    arrived.add(job);
                }
            }

            // Prioritize Staff over Student
            List<Job> tempList = new ArrayList<>();
            while (!queue.isEmpty()) tempList.add(queue.poll());
            tempList.sort((a, b) -> {
                if (a.userType.equals(b.userType)) return 0;
                return a.userType.equals("Staff") ? -1 : 1;
            });

            queue.addAll(tempList);

            if (queue.isEmpty()) {
                currentTime++;
                continue;
            }

            Job current = queue.poll();

            int timeToRun = Math.min(current.remainingTime, quantum);
            currentTime += timeToRun;
            current.remainingTime -= timeToRun;

            // Check for any new arrivals during this burst
            for (Job job : allJobs) {
                if (job.arrivalTime > currentTime - timeToRun && job.arrivalTime <= currentTime && !arrived.contains(job)) {
                    queue.offer(job);
                    arrived.add(job);
                }
            }

            if (current.remainingTime == 0) {
                current.completionTime = currentTime;
                completedJobs.add(current);
            } else {
                queue.offer(current); // Requeue for Round Robin
            }
        }

        // Print per-job stats
        double totalWT = 0, totalTAT = 0;
        System.out.println("JobID  WaitingTime  TurnaroundTime");
        for (Job job : allJobs) {
            int tat = job.completionTime - job.arrivalTime;
            int wt = tat - job.burstTime;
            totalWT += wt;
            totalTAT += tat;
            System.out.printf("%-6s %-13d %d\n", job.id, wt, tat);
        }
        System.out.printf("Average Waiting Time: %.2f\n", totalWT / totalJobs);
        System.out.printf("Average Turnaround Time: %.2f\n", totalTAT / totalJobs);
    }
}
