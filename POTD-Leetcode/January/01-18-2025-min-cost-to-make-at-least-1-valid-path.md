# POTD 01-18-2025

## 1368. Minimum Cost to Make at Least One Valid Path in a Grid [[Problem](https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/MinCostToMakeAtLeast1ValidPath.java)]

<!-- ![Easy](https://img.shields.io/badge/Easy-green?style=for-the-badge)  -->
<!-- ![Medium](https://img.shields.io/badge/Medium-yellow?style=for-the-badge)   -->
![Hard](https://img.shields.io/badge/Hard-red?style=for-the-badge)

#### **Tags:** [`Array`](https://leetcode.com/problem-list/array/) [`Breadth-First Search`](https://leetcode.com/problem-list/breadth-first-search/) [`Graph`](https://leetcode.com/problem-list/graph/)  [`Heap(PriorityQueue)`](https://leetcode.com/problem-list/heap-priority-queue/) [`Matrix`](https://leetcode.com/problem-list/matrix/) [`Shortest Path`](https://leetcode.com/problem-list/shortest-path/)

## Intuition
- We need to find the minimum cost to travel from the top-left to the bottom-right corner of the grid.
- The grid contains costs for moving in different directions, i.e. you can modify the sign on a cell with `cost = 1`
- **Why not DFS?** : DFS would be inefficient for this problem since it doesn't guarantee the shortest path and might explore many unnecessary paths.
- BFS is chosen here because we need the shortest path based on the number of grid moves, not on weights. It efficiently handles grid-based shortest path problems.

<!-- --------------Prompt Tip-------------- -->

> Tip :  DFS: Suitable for finding the **longest path** as it is more focused on exploring deep into a graph.<br>BFS: Suitable for finding the **shortest path** as it explores neighbors level by level.

## Approach
- Intialize `dp` with `Integer.MAX_VALUE` as we want to track the minimum cost in the cells later.
- Insert `(0,0,0)` in the priority queue, meaning starting with cost 0 at cell (0,0) 

    ```java
    // min-heap priority queue to always "expand" the least costly path
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    int[][] dp = new int[m][n]; // to store minimum cost to reach each cell

    // initialize dp array with maximum values
    for (int[] row : dp) {
        Arrays.fill(row, Integer.MAX_VALUE);
    }

    // cost `0` at satrt (0,0)
    pq.add(new int[] {0, 0, 0});
    ```

    - Start from `(0,0)` with cost `0` and explore the grid in all 4 possible directions.
    - Use the priority queue to always expand the **least cost** node.
    - Track the minimum cost to reach each grid cell using a `dp` array
    
    - For each direction, if moving to the neighboring cell results in a lower cost, update the cost and push it to the priority queue.
    - Continue until reaching the bottom-right corner.

- If the priority queue is empty or we've already found the minimum cost to reach the bottom-right corner, return that cost.
    
    ```java
    // if already reached the last cell, return the cost
    if(x == m - 1 && y == n - 1){
        return cost;
    }
    ```

### Complexity Analysis

- **Time Complexity: _O(mn)_**  
    - Given `m x n` grid, each cell is visited once, and priority queue operations (insertions and deletion) takes `O(logp)` time, where `p` is size of priority queue.
- **Space Complexity: _O(mn)_**  
  - `dp` array stores the minimum cost for each cell, so it takes `O(mn)` space.
  - `pq` takes `O(mn)` space in worst case.


#### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/MinCostToMakeAtLeast1ValidPath.java)

```java
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
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

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
```