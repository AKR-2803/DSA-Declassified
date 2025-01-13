# POTD 12-21-2024

## 2872. Maximum Number of K-Divisible Components [[Problem](https://leetcode.com/problems/maximum-number-of-k-divisible-components/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/December/code/MaxNoOfKDivComponents.java)]

<!-- ![Easy](https://img.shields.io/badge/Easy-green?style=for-the-badge)  -->
<!-- ![Medium](https://img.shields.io/badge/Medium-yellow?style=for-the-badge)   -->
![Hard](https://img.shields.io/badge/Hard-red?style=for-the-badge)

---

#### **Tags:** [`Tree`](https://leetcode.com/problem-list/tree/) [`Depth-First Search`](https://leetcode.com/problem-list/depth-first-search/)


## Intuition
- Given a tree structure with nodes having values. The goal is to maximize the number of valid components we can obtain by removing edges. 
- A component is considered valid if the sum of its nodes' values is divisible by a given integer `k`. 
- The tree structure ensures that there are **no cycles**, so when we remove an edge, we split the tree into two disconnected subtrees. 
- To solve the problem, we need to perform a traversal and keep track of the sum of values in each subtree. 
- If a subtree's sum is divisible by `k`, we count it as a valid component.

## Approach
- **Convert Edges array to Adjacency List**: 
   - Build an adjacency list from the `edges` input for efficient traversal.

        ```java
        public void adjList(int[][] edges, List<List<Integer>> adj){
            // For each edge, add both directions to the adjacency list
            for(int[] edge : edges){
                int node1 = edge[0];
                int node2 = edge[1];

                // Add the edge in both directions (tree is undirected)
                adj.get(node1).add(node2);
                adj.get(node2).add(node1);
            }
        }
        ```
   
- **Depth-First Search (DFS)**:
   - Perform DFS traversal starting from the root node (node `0`). For each node, we calculate the sum of values in its subtree, including its own value and the values of its children.
   - If the sum of a subtree is divisible by `k`, we count it as a valid component and reset its sum to `0` to mark it as split.
        ```java
        // If the sum of the current subtree is divisible by `k`, it can be a valid component
        if(subtreeSum % k == 0){
            result += 1; // valid component count
            return 0;    // reset the sum for this component to split it
        }
        ```

        - **Counting Valid Components**: During DFS, if a subtree's sum is divisible by `k`, we increment the count of valid components. Then return `0` for that subtree, meaning it is considered a valid split.
        
        - **Edge Removal**: When a valid component is found, it is conceptually removed (split) by resetting the subtree sum to `0` and continuing the traversal.

### Complexity Analysis
- **Time Complexity: _O(n)_**
  - Adjacency list takes `O(n)` where `n` is the no of nodes.
  - The DFS traversal visits each node and edge once, so it takes `O(n)`.

- **Space Complexity: _O(n)_**
  - Dominated by the adjacency list and the `longValues` array, both of which require `O(n)` space.
  - The recursion stack for DFS also takes `O(n)` space in the worst case.

  
#### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/December/code/MaxNoOfKDivComponents.java)
```java
class Solution {
    int result = 0; 
    
    // Main function to calculate the maximum number of components divisible by k
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        List<List<Integer>> adj = new ArrayList<>(n);

        // initialize the adjacency list
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }

        // convert the edges array into an adjacency list representation
        adjList(edges, adj);
        
        long[] longValues = new long[n];

        // copy values to `longValues` array to avoid overflow issues
        for(int i = 0; i < n; i++){
            longValues[i] = values[i];
        }

        // Perform DFS from the root node (0) with initial parent as (-1)
        dfs(0, -1, adj, longValues, k);

        return result;
    }
    
    // DFS to calculate the subtree sums
    public long dfs(int node, int parent, List<List<Integer>> adj, long[] longValues, int k){
        long subtreeSum = longValues[node]; // Start with current node

        // goto all the neighbours of the current node
        for(int nbr: adj.get(node)){
            if(nbr != parent){ // don't revisit the parent node
                subtreeSum += dfs(nbr, node, adj, longValues, k); // add the sum of the subtree of the neighbour
            }
        }

        // If the sum of the current subtree is divisible by `k`, it can be a valid component
        if(subtreeSum % k == 0){
            result += 1; // valid component count
            return 0;    // reset the sum for this component to split it
        }

        // return sum of the current subtree
        return subtreeSum;
    }    

    // convert the edges array into an adjacency list
    public void adjList(int[][] edges, List<List<Integer>> adj){
        // For each edge, add both directions to the adjacency list
        for(int[] edge : edges){
            int node1 = edge[0];
            int node2 = edge[1];

            // Add the edge in both directions (tree is undirected)
            adj.get(node1).add(node2);
            adj.get(node2).add(node1);
        }
    }
}
```