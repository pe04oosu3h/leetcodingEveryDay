class Solution {
    public int uniquePathsIII(int[][] grid) {
        // record starting position and zero count
        int zeroCount = 0;
        int startRow = -1;
        int startCol = -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    startRow = i;
                    startCol = j;
                } else if (grid[i][j] == 0) {
                    zeroCount++;
                }
            }
        }

        // find result using DFS
        return DFS(grid, startRow, startCol, zeroCount);
    }

    private int DFS(int[][] grid, int row, int col, int zeroCount) {
        // base case: out of bound
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == -1) return 0;
        // base case: terminal
        if (grid[row][col] == 2) return zeroCount == -1? 1 : 0;
        // mark as visited and recursion
        int temp = grid[row][col];
        grid[row][col] = -1;
        zeroCount--;
        int totalPaths = DFS(grid, row + 1, col, zeroCount)+ DFS(grid, row - 1, col, zeroCount)+ DFS(grid, row, col + 1, zeroCount)+ DFS(grid, row, col - 1, zeroCount);
        // backtracking / unmarking
        grid[row][col] = temp;
        return totalPaths;
    }
}