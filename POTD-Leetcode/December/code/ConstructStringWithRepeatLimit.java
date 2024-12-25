class Solution {
    public String repeatLimitedString(String s, int repeatLimit) {
        Map<Integer, Integer> mp = new HashMap<>();

        // Count occurrences of each character.
        for (int i = 0; i < s.length(); i++) {
            int asc = (int) s.charAt(i);
            mp.put(asc, mp.getOrDefault(asc, 0) + 1);
        }

        // Max-heap to store characters in descending lexicographical order.
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // Push [ascii, count] into the heap.
        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            maxHeap.offer(new int[] {entry.getKey(), entry.getValue()});
        }

        StringBuilder result = new StringBuilder();

        while (!maxHeap.isEmpty()) {
            int[] current = maxHeap.poll();
            int ascii = current[0], count = current[1];

            // Add up to 'repeatLimit' instances of the current character.
            int addLimit = Math.min(count, repeatLimit);
            for (int i = 0; i < addLimit; i++) {
                result.append((char) ascii);
            }

            // If characters remain, check for an alternate character, to not violate the `repeatLimit`
            if (count > addLimit) {
                if (maxHeap.isEmpty()) break; // No other character available.

                int[] next = maxHeap.poll();  // Use one instance of the next character.
                result.append((char) next[0]);
                
                // Return the next character to the heap if it still has occurrences.
                if (--next[1] > 0) {
                    maxHeap.offer(next);
                }

                // Reinsert the current character with remaining count.
                maxHeap.offer(new int[] {ascii, count - addLimit});
            }
        }
        return result.toString();
    }
}