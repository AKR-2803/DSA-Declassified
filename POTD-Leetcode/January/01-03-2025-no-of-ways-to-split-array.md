# POTD 01-03-2025

## 2270. Number of Ways to Split Array [[Problem](https://leetcode.com/problems/number-of-ways-to-split-array/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/NoOfWaysToSplitArray.java)]

<!-- ![Easy](https://img.shields.io/badge/Easy-green?style=for-the-badge)  -->
![Medium](https://img.shields.io/badge/Medium-yellow?style=for-the-badge)  
<!-- ![Hard](https://img.shields.io/badge/Hard-red?style=for-the-badge) -->

#### **Tags:** [`Array`](https://leetcode.com/problem-list/array/) [`Prefix Sum`](https://leetcode.com/problem-list/prefix-sum/) 

## Intuition
- Problem is about getting the sum of a given range. Prefix Sum can help us in that case.
- **Recommended**: A good reference for understanding **prefix sum** approach is [**this article**](https://leetcodethehardway.com/tutorials/basic-topics/prefix-sum) by @wingkwong

## Approach

- Calculate prefix sum for `nums` and store them in `prefix` array.
- Total sum is sum of all values in `nums`, i.e. `totalSum = prefix[n]`
- Sum of first `i` values is given by `sumFirst = prefix[i + 1]`
- Sum of last `n-i-1` values is given by `sumLast`, i.e. `sumLast = totalSum - sumFirst`

- To check:
```
sumFirst >= sumLast
sumFirst >= totalSum - sumFirst
2*sumFirst >= totalSum
```
- Hence the condition to check is reduced to `2*sumFirst >= totalSum`
- For each `i`, check this condition, if true, increment no. of valid splits `validSplits`.

### Complexity Analysis

- **Time Complexity: _O(n)_**  
  - Traversing the array once for prefix sum (`O(n)`), and once for counting valid splits (`O(n)`), where `n` is `nums.length`

- **Space Complexity: _O(n)_**  
  - Space taken by `prefix` array.

#### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/NoOfWaysToSplitArray.java)

```java
class Solution {
    public int waysToSplitArray(int[] nums) {
        int n = nums.length;
        long[] prefix = new long[n + 1]; // long to prevent overflow
        
        // compute prefix sum
        for(int i = 0; i < n; i++){
            prefix[i + 1] = prefix[i] + nums[i];
        }

        long totalSum = prefix[n];
        int validSplits = 0;

        for(int i = 0; i < n - 1; i++){
            long sumFirst = prefix[i + 1];
                
            /*
                sumLast = totalSum - sumFirst;
                To check
                sumFirst >= sumLast
                sumFirst >= totalSum - sumFirst
                2*sumFirst >= totalSum
            */
            if(2*sumFirst >= totalSum){
                validSplits += 1;
            }
        }
        return validSplits;
    }
}
```