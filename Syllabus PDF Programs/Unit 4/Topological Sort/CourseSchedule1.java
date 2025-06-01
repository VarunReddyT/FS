import java.util.*;
import java.util.LinkedList;
public class CourseSchedule1 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++)
            graph.add(new ArrayList<>());
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            graph.get(prereq).add(course);
            indegree[course]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                queue.offer(i);
        }
        int finishedCourses = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            finishedCourses++;
            for (int neighbor : graph.get(current)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0)
                    queue.offer(neighbor);
            }
        }
        return finishedCourses == numCourses;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CourseSchedule1 cs = new CourseSchedule1();
        System.out.print("Enter number of courses: ");
        int numCourses = sc.nextInt();
        System.out.print("Enter number of prerequisite pairs: ");
        int m = sc.nextInt();
        int[][] prerequisites = new int[m][2];
        System.out.println("Enter prerequisites");
        for (int i = 0; i < m; i++) {
            prerequisites[i][0] = sc.nextInt();
            prerequisites[i][1] = sc.nextInt();
        }
        boolean canFinish = cs.canFinish(numCourses, prerequisites);
        System.out.println(canFinish);
        sc.close();
    }
}