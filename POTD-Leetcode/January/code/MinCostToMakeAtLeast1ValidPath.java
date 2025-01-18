class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;    // total rows
        int n = grid[0].length; // total columns
        
        // min-heap priority queue to always "expand" the least costly path
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[][] dp = new int[m][n]; // to store minimum cost to reach each cell

        // initialize dp array with maximum values
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // cost `0` at satrt (0,0)
        pq.add(new int[] {0, 0, 0});

        // directions: right, left, down, up
        int[][] dir = {
            {0,  1},
            {0, -1},
            {1,  0},
            {-1, 0}
        };

        // BFS traversal
        while (!pq.isEmpty()) {
            int[] curr = pq.poll(); // Get cell with the least cost

            int cost = curr[0];
            int x = curr[1];
            int y = curr[2];

            // if already reached the last cell, return the cost
            if(x == m - 1 && y == n - 1){
                return cost;
            }

            // explore all 4 directions
            for (int i = 0; i < 4; i++) {
                int newx = x + dir[i][0];
                int newy = y + dir[i][1];
                
                // check if new position is within bounds of the grid
                if (newx >= 0 && newx < m && newy >= 0 && newy < n) {
                    // calculate new cost for moving to this position
                    int newCost = cost + (grid[x][y] != i + 1 ? 1 : 0);
                    
                    // if the new cost is "lower", update `dp` and add to `queue`
                    if (newCost < dp[newx][newy]) {
                        dp[newx][newy] = newCost;
                        pq.offer(new int[] {newCost, newx, newy});
                    }
                }
            }
        }

        // return minimum cost to reach the bottom-right corner [m-1][n-1]
        return dp[m - 1][n - 1];
    }
}