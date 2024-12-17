# POTD 12-15-2024

## 1792. Maximum Average Pass Ratio [[Problem](https://leetcode.com/problems/maximum-average-pass-ratio/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD/December/code/MaxAvgPassRatio.java)]

<!--  ![Easy](https://via.placeholder.com/50x20/00FF00/000000?text=Easy)  -->
![Medium](https://via.placeholder.com/70x20/FFA500/000000?text=Medium)  
<!-- ![Hard](https://via.placeholder.com/50x20/FF0000/000000?text=Hard) -->

#### **Tags:** [`Array`](https://leetcode.com/problem-list/array/) [`Greedy`](https://leetcode.com/problem-list/greedy/) [`Heap (Priority Queue)`](https://leetcode.com/problem-list/heap-priority-queue/)

## Intuition

- When adding extra students to a class, the first student has the **highest impact on the pass ratio**. 
- Each subsequent student has a diminishing effect on the improvement of the pass ratio. 
- This is because as the total number of students increases, the gain from adding one more student decreases. 
- [Refer Image](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD/December/12-15-2024-max-avg-pass-ratio.md#reference-image) for more clarity on this

## Approach

###  Priority Queue or MAX Heap

-  **Initialize a Max Heap**
    - Calculate the initial gain for each class when adding one extra passing student (max gain for that class, thereafter gains will diminish) 
    - Store the classes values along with these gains
    - Sort the heap according to gain

    - Let us see how this works in `Java`
    ```java
    PriorityQueue<double[]> heap = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));
    ```
    - `PriorityQueue` in Java is by default a **MIN-heap**
    - We cannot do something like `b[0] - a[0]` in the comparator, as it expects `int` values, so we instead use `Double.compare`
    - `Double.compare(b[0], a[0])` This is a custom comparator which changes(reverses) the default behavior
    - This compares b[0] with a[0] in reverse order.
    - I.E. If `b[0] > a[0]`, it returns a negative value, which makes `b[0]` come before `a[0]`
    - More on [**Double.Compare()**](https://docs.oracle.com/javase/8/docs/api/java/lang/Double.html#compare-double-double-)
    - This will hence create a **MAX-Heap**, where elements are sorted in descending order by `b[0]` which is the `gain`

-   **Now distribute the extra students one by one** 
    - Remove(poll()) max gain class from heap(top of the heap)
    - Calculate the new gain
    - Update the gain and push the class back into the heap

-   **Simply calculate and return the final pass ratio**

### Complexity Analysis

- **Time Complexity: _O((n+e)logn)_**
   - Adding classes to Heap: Each class taken `O(logn)` time to add in the heap, there are `n` classes = `O(nlogn)` time.
   - For each extra student, we remove and insert a value from the heap, which takes `O(logn)` time
   - Assuming `e` total `extraStudents`, time = `O(elogn)`
   - Calculating final pass ratio (traverse through heap once) : `O(n)`

   - Total TC : `O(nlogn) + O(elogn) + O(n)` => which simplifies to => `O((n + e)logn)`

- **Space Complexity: _O(n)_**
   - Heap stores `n` elements for n classes, hence `O(n)`.

### [Code here](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD/December/code/MaxAvgPassRatio.java)

### Reference Image
| Understanding diminishing effect on gain                                             | 
|--------------------------------------------------------------------------------------| 
| <img src="../images/12-15-2024-max-avg-pass-ratio-01.jpg" height=700 width=500 alt="continuous-subarrays-problem"/> |