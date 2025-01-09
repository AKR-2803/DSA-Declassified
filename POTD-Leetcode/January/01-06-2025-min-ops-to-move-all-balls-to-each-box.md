# POTD 01-06-2025

## 1769. Min. No. of Operations to Move All Balls to Each Box [[Problem](https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/MinOpsToMoveAllBallsToEachBox.java)]

<!-- ![Easy](https://via.placeholder.com/50x20/00FF00/000000?text=Easy)  -->
![Medium](https://via.placeholder.com/70x20/FFA500/000000?text=Medium)  
<!-- ![Hard](https://via.placeholder.com/50x20/FF0000/000000?text=Hard) -->

#### **Tags:** [`Array`](https://leetcode.com/tag/array/) [`String`](https://leetcode.com/tag/string/) [`Prefix Sum`](https://leetcode.com/tag/prefix-sum/) 

## Intuition
- The brute force approach iterates over all pairs of boxes to compute the distance for each.
- Brute force will work in the question as the constraints are small
```
    Constraints:
    1 <= words.length <= 100
    1 <= words[i].length <= 30
```

## Approach

- For each box `i`, calculate the total no. of operations required to move all balls to box `i`:
   - Iterate over all other boxes `j`.
   - If box `j` contains a ball (i.e., `boxes[j] == '1'`), add the distance `abs(i - j)` to the total operations.
- Store the result for box `i` in `res[i]`.
- Return the result array.

### Complexity Analysis
- **Time Complexity: _O(N^2)_**  
    - Iterating over all pairs of boxes.

- **Space Complexity: _O(N)_**  
    - `res` array.

#### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/MinOpsToMoveAllBallsToEachBox.java)

```java
class Solution {
    public int[] minOperations(String boxes) {
        char[] box = boxes.toCharArray();
        int N = box.length; 
        int[] res = new int[N]; // array to store the results

        for (int i = 0; i < N; i++) {
            int ops = 0; // total operations for box `i`

            // Check the distance of all other boxes from the current box
            for (int j = 0; j < N; j++) {
                if (box[j] == '1') {  // If box `j` contains a ball
                    ops += Math.abs(i - j); // Add the distance to the total operations
                }
            }
            res[i] = ops;
        }
        return res;
    }
}
```