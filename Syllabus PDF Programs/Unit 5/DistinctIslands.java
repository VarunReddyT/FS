// 2. Number of Distinct Islands:
// Number of Distinct Islands states that given a n x m binary matrix. An island is a group of 1‘s 
// (representing land) connected 4-directionally (horizontal or vertical).
// An island is considered to be the same as another if and only if one island can be translated (and not 
// rotated or reflected) to equal the other.
// Example-1:
// Input
// Output: 1
// Example-2:
// Input: [[1,1,0,1,1],[1,0,0,0,0],[0,0,0,0,1],[1,1,0,1,1]]
// Output: 3

import java.util.*;
public class DistinctIslands {
    static class UnionFind{
        int[] parent;
        UnionFind(int n){
            parent = new int[n];
            for(int i = 0;i<n;i++){
                parent[i] = i;
            }
        }

        int find(int x){
            if(parent[x] != x){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX != rootY){
                parent[rootY] = rootX;
            }
        }
    }
    static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public static int numDistinctIslands(int[][] grid){
        int rows = grid.length;
        int cols = grid[0].length;
        Set<String> distinctIslands = new HashSet<>();
        UnionFind uf = new UnionFind(rows*cols);

        for(int i = 0;i<rows;i++){
            for(int j = 0;j<cols;j++){
                if(grid[i][j] == 1){
                    List<int[]> islandCoords = new ArrayList<>();

                    dfs(grid, i, j, i, j, islandCoords);

                    int baseIndex = islandCoords.get(0)[0] * cols + islandCoords.get(0)[1];

                    for(int[] coord : islandCoords){
                        int currentIndex = coord[0] * cols + coord[1];
                        uf.union(baseIndex, currentIndex);
                    }

                    islandCoords.sort((a,b)->{
                        if(a[0] == b[0]){
                            return Integer.compare(a[1], b[1]);
                        }
                        return Integer.compare(a[0], b[0]);
                    });

                    int baseX = islandCoords.get(0)[0];
                    int baseY = islandCoords.get(0)[1];

                    StringBuilder shape = new StringBuilder();
                    for(int[] coord : islandCoords){
                        shape.append(coord[0] - baseX).append(":").append(coord[1] - baseY).append(",");
                    }
                    distinctIslands.add(shape.toString());
                }
            }
        }
        return distinctIslands.size();
    }
    static void dfs(int[][] grid, int i, int j, int baseX, int baseY, List<int[]> islandCoords){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0){
            return;
        }
        grid[i][j] = 0;
        islandCoords.add(new int[]{i, j});
        for(int[] dir : dirs){
            dfs(grid, i + dir[0], j + dir[1], baseX, baseY, islandCoords);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        int[][] grid = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                grid[i][j] = sc.nextInt();
        int result = numDistinctIslands(grid);
        System.out.println(result);
        sc.close();
    }
}
