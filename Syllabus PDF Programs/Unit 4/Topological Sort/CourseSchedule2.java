import java.util.*;
import java.util.LinkedList;

public class CourseSchedule2 {
    public int[] findOrder(int numCourses, int[][] prerequisites){
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0;i<numCourses;i++){
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];
        for(int[] i : prerequisites){
            int course = i[0];
            int prereq = i[1];
            graph.get(prereq).add(course);
            indegree[course]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0;i<numCourses;i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }
        int[] ts = new int[numCourses];
        int i = 0;
        while(!q.isEmpty()){
            int node = q.poll();
            ts[i++] = node;
            for(int neighbour : graph.get(node)){
                indegree[neighbour]--;
                if(indegree[neighbour] == 0){
                    q.offer(neighbour);
                }
            }
        }
        if(i<numCourses){
            return new int[]{};
        }
        return ts;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CourseSchedule2 cs = new CourseSchedule2();
        int numCourses = sc.nextInt();
        int m = sc.nextInt();
        int[][] prerequisites = new int[m][2];
        for (int i = 0; i < m; i++) {
            prerequisites[i][0] = sc.nextInt();
            prerequisites[i][1] = sc.nextInt();
        }
        System.out.println(cs.findOrder(numCourses, prerequisites));
        sc.close();
    }
}
