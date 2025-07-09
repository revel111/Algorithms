package leetcode;

public class Graphs {
    public static void main(String[] args) {

    }

    private static class SolutionProblem200 {

        private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        public static int numIslands(char[][] grid) {
            int islands = 0;

            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    if (grid[row][col] == '1') {
                        dfs(grid, row, col);
                        islands++;
                    }
                }
            }

            return islands;
        }

        private static void dfs(char[][] grid, int row, int col) {
            if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == '0') {
                return;
            }

            grid[row][col] = '0';
            for (int[] dir : DIRECTIONS) {
                dfs(grid, row + dir[0], col + dir[1]);
            }
        }
    }
}
