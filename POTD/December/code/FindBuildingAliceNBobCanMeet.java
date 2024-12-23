class Solution {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        //Array of lists to store pending queries for each building
        List<int[]>[] lists = new ArrayList[heights.length];
        
        // Initialize the result array with -1 (meaning no meeting point found)
        int[] res = new int[queries.length];
        Arrays.fill(res, -1);
        
        for (int i = 0; i < queries.length; i++) {
            int aIndex = queries[i][0];  // Alice starting building index
            int bIndex = queries[i][1];  // Bob starting building index

            // Case 1: Alice starts to the right of Bob
            if (aIndex > bIndex) {
                // If Alice can directly move to Bob's building
                if (heights[aIndex] > heights[bIndex]) {
                    res[i] = aIndex;
                } else {
                    // Otherwise, store the query for processing later
                    if (lists[aIndex] == null)
                        lists[aIndex] = new ArrayList<>();
                    lists[aIndex].add(new int[] { heights[bIndex], i });
                }
            }
            // Case 2: Bob starts to the right of Alice
            else if (aIndex < bIndex) {
                // If Bob can directly move to Alice's building
                if (heights[aIndex] < heights[bIndex]) {
                    res[i] = bIndex;
                } else {
                    // Otherwise, store the query for processing later
                    if (lists[bIndex] == null)
                        lists[bIndex] = new ArrayList<>();
                    lists[bIndex].add(new int[] { heights[aIndex], i });
                }
            }
            // Case 3: Both start at the same building
            else {
                res[i] = aIndex;
            }
        }
        
        // priority queue(minHeap) to handle pending queries sorted by heights
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        // Iterate over each building to process pending queries
        for (int i = 0; i < heights.length; i++) {
            // Process queries in the priority queue where height is less than the current building's height
            while (!minHeap.isEmpty() && minHeap.peek()[0] < heights[i]) {
                res[minHeap.poll()[1]] = i;
            }
            
            // Add pending queries for the current building to the priority queue
            if (lists[i] != null) {
                for (int[] arr : lists[i]) {
                    minHeap.add(arr);
                }
            }
        }
        
        return res;
    }
}