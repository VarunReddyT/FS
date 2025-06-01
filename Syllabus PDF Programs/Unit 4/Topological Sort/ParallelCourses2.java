import java.util.*;
import java.util.LinkedList;

public class ParallelCourses2 {
    int minimumSemesters(int n, int[][] relations, int k){
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        int[] indegree = new int[n+1];
        for(int[] rel : relations){
            int u = rel[0];
            int v = rel[0];
            adj.get(u).add(v);
            indegree[v]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1;i<=n;i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }
        int semester = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            int canTake = Math.min(size,k);
            List<Integer> currentSemester = new ArrayList<>();
            for(int i = 0;i<canTake;i++){
                currentSemester.add(queue.poll());
            }
            for(int course : currentSemester){
                for(int neighbour : adj.get(course)){
                    indegree[neighbour]--;
                    if(indegree[neighbour] == 0){
                        queue.offer(neighbour);
                    }
                }
            }
            semester++;
        }
        return semester;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ParallelCourses2 cs = new ParallelCourses2();
        System.out.print("Enter the number of courses: ");
        int N = sc.nextInt();
        System.out.print("Enter the number of relations (prerequisites): ");
        int M = sc.nextInt();
        int[][] relations = new int[M][2];
        System.out.println("Enter the relations (prerequisite pairs in the format 'X Y'):");
        for (int i = 0; i < M; i++) {
            relations[i][0] = sc.nextInt(); 
            relations[i][1] = sc.nextInt();
        }
        int k = sc.nextInt();
        System.out.println(cs.minimumSemesters(N, relations,k));
        sc.close();
    }
}
