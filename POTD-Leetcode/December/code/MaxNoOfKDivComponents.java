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