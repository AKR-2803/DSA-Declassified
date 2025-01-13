# POTD 12-16-2024


## 3264. Final Array State After K Multiplication Operations I [[Problem](https://leetcode.com/problems/final-array-state-after-k-multiplication-operations-i/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/December/code/FinalArrayStateAfterK.java)]

 ![Easy](https://img.shields.io/badge/Easy-green?style=for-the-badge) 
<!-- ![Medium](https://img.shields.io/badge/Medium-yellow?style=for-the-badge)   -->
<!-- ![Hard](https://img.shields.io/badge/Hard-red?style=for-the-badge) -->

#### **Tags:** [`Array`](https://leetcode.com/problem-list/array/) [`Math`](https://leetcode.com/problem-list/math/) [`Heap(PriorityQueue)`](https://leetcode.com/problem-list/heap-priority-queue/) [`Simulation`](https://leetcode.com/problem-list/simulation/)

## Intuition

- The problem requires us to repeatedly update the minimum element in the array for a given no. of operations. 
- To efficiently extract the minimum value and its index, a **Min-Heap** (PriorityQueue) can be used.

- Min heap ensures minimum value is selected efficiently.
- In case of ties (same value), the earlier index (first occurrence) is prioritized.
- How does this work in Java?
    - Using custom compartor, we can handle how the values will be sorted in the priority queue.

```java
PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> {
    if(a[0] != b[0]){
        return a[0] - b[0]; // when differnt values, go for smaller value
    } else{
        return a[1] - b[1]; // when same values, go for smaller index (value appearing first)
    }
});
```

## Approach
-  **Use a Min-Heap:**
   - Store pairs of values `[num, index]` in the heap.
   - Use a custom comparator to prioritize:
     - Smaller values.
     - For ties, the smaller index (value appearing first).

- **Perform `k` Operations:**
   - For each operation:
     - Extract the `minimum` element from the heap.
     - Update the corresponding value in the array:  
     - Push the updated value back into the heap.
    
    ```java
    for(int i = 0; i < k; i++){
        int[] pairMin = heap.poll();    // extract min element
        int idx = pairMin[1];

        nums[idx] *= multiplier;        // multiply by `k`
        
        heap.offer(new int[]{nums[idx], idx});  // re-insert the updated value back into heap
    }
    ```

### Complexity Analysis

- **Time Complexity: _O(klogn)_**  
    - Getting the minimum and reinserting into the heap both take **O(logn)** time.  
    - Performing `k` operations gives **O(klogn)**.

- **Space Complexity: _O(n)_**  
    - The heap stores all the array elements.

### [Full Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/December/code/FinalArrayStateAfterK.java)

```java
class Solution {
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> {
            if(a[0] != b[0]){
                return a[0] - b[0]; // when differnt values, go for lesser one
            } else{
                return a[1] - b[1]; // when same value, go for lesser index (value appearing first)
            }   
        });

        for(int i = 0; i < nums.length; i++){
            heap.offer(new int[]{ nums[i], i});
        }

        // take min element each time for `k` times
        for(int i = 0; i < k; i++){
            int[] pairMin = heap.poll();
            int idx = pairMin[1];

            nums[idx] *= multiplier;
            
            heap.offer(new int[]{nums[idx], idx});
        }
        return nums;
    }
}
```