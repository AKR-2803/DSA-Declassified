# POTD 12-28-2024

## 689. Maximum Sum of 3 Non-Overlapping Subarrays [[Problem](https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/December/code/MaxSumOf3NonOverlappingSubarrays.java)]

 <!-- ![Easy](https://via.placeholder.com/50x20/00FF00/000000?text=Easy)  -->
<!-- ![Medium](https://via.placeholder.com/70x20/FFA500/000000?text=Medium)   -->
![Hard](https://via.placeholder.com/50x20/FF0000/000000?text=Hard)

#### **Tags:** [`Array`](https://leetcode.com/problem-list/array/) [`Dynamic Programming`](https://leetcode.com/problem-list/dynamic-programming/)

## Intuition
- Using a sliding window approach, we can calculate the sum of consecutive subarrays. 
- And by keeping track of the best sums for one, two, and three subarrays at each step, we can build the solution incrementally.

## Approach
- Use a sliding window to calculate the sums of three consecutive subarrays of size `k`. Update the sums as the window slides.
   
- Maintain variables to store:
     - The maximum sum of the first subarray (`max1`) and its index (`index1`).
     - The maximum sum of the first two subarrays combined (`max12`) and their indices (`index12_1`, `index12_2`).
     - The maximum sum of all three subarrays combined (`max123`) and their indices (stored in `result`).

- For each possible starting point `i` of the first subarray:
    - Update the sliding window sums for the three subarrays.
    - Update `max1` and its index if the current `sum1` is greater.
    - Update `max12` and the indices of the two subarrays if `max1 + sum2` is greater.
    - Update `max123` and the indices of all three subarrays if `max12 + sum3` is greater.

### Complexity Analysis
1. **Time Complexity: _O(n)_**:
   - Self explanatory.

2. **Space Complexity: _O(1)_**
   - No extra space used.


### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/December/code/MaxSumOf3NonOverlappingSubarrays.java)

```java
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;

        // Sums for the three sliding windows
        int sum1 = 0, sum2 = 0, sum3 = 0;

        // Maximum sums for individual and combined subarrays
        int max1 = 0, max12 = 0, max123 = 0;

        // Indices to store the starting positions of subarrays
        int index1 = 0, index12_1 = 0, index12_2 = k;
        int[] result = {0, k, 2 * k}; // Default result with initial indices

        // Calculate initial sums for the first three subarrays
        for (int i = 0; i < k; i++) {
            sum1 += nums[i];
            sum2 += nums[i + k];
            sum3 += nums[i + 2 * k];
        }

        // Initialize maximum sums
        max1 = sum1;
        max12 = sum1 + sum2;
        max123 = sum1 + sum2 + sum3;

        // Iterate through the array for all possible starting points
        for (int i = 0; i <= n - 3 * k; i++) {
            // Update sums for sliding windows if not the first iteration
            if (i > 0) {
                sum1 = sum1 - nums[i - 1] + nums[i + k - 1];
                sum2 = sum2 - nums[i + k - 1] + nums[i + 2 * k - 1];
                sum3 = sum3 - nums[i + 2 * k - 1] + nums[i + 3 * k - 1];
            }

            // Update max1 and its index
            if (sum1 > max1) {
                max1 = sum1;
                index1 = i;
            }

            // Update max12 and its indices
            if (max1 + sum2 > max12) {
                max12 = max1 + sum2;
                index12_1 = index1;
                index12_2 = i + k;
            }

            // Update max123 and result indices
            if (max12 + sum3 > max123) {
                max123 = max12 + sum3;
                result = new int[]{index12_1, index12_2, i + 2 * k};
            }
        }

        // Return the indices of the three subarrays
        return result;
    }
}
```