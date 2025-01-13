# POTD 12-26-2024

## 494. Target Sum [[Problem](https://leetcode.com/problems/target-sum/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/December/code/TargetSum.java)]

<!-- ![Easy](https://img.shields.io/badge/Easy-green?style=for-the-badge)  -->
![Medium](https://img.shields.io/badge/Medium-yellow?style=for-the-badge)  
<!-- ![Hard](https://img.shields.io/badge/Hard-red?style=for-the-badge) -->

#### **Tags:** [`Array`](https://leetcode.com/problem-list/array/) [`Dynamic Programming`](https://leetcode.com/problem-list/dynamic-programming/) [`Backtracking`](https://leetcode.com/problem-list/backtracking/) 

## Intuition
- Each element in the array can either be positive (+num) or negative (-num) to the target.
- When you have such choices (decision-making), think of DP.
- And avoid repetitive calculations by storing results for subproblems.

## Approach

### 1. Recursion [TC: _O(2^n)_]

- For each number choose either negative or positive.
- Each recursive call will make two calls.
- Exponential time complexity

### Complexity Analysis
- **Time Complexity: _O(2^n)_**
    - Each recursive call will make two calls.

- **Space Complexity: _O(n)_**
    - `n` elements in the array, hence in worst case recursion stack will have `n` elements

#### [Code (Recursion)](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/December/code/TargetSum.java)
```java
class Solution {
    int totalWays = 0;

    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        findWaysRec(nums, 0, 0, target);

        return totalWays;
    }

    // Recursion => TC: O(2^n) very bad
    public void findWaysRec(int[] nums, int currentIdx, int currentSum, int target){
        if(currentIdx == nums.length){
            if(currentSum == target){
                totalWays += 1;
            }
        }

        else{
            // take negative 
            findWaysRec(nums, currentIdx + 1, currentSum - nums[currentIdx], target);

            // take positive
            findWaysRec(nums, currentIdx + 1, currentSum + nums[currentIdx], target);
        }
    }
}
```

### 2. Memoization [TC: _O(n * totalSum)_]

- DP Memoization will help in storing the values of subproblems so that we don't need to recalculate them over and over unnecessarily.  
- Take 2D `dp` array to store the no. of ways for `[n][possible_sum]`
- What can be the `possible_sum`?

    - Consider `nums = [2,1,5]` => `totalSum = 2+1+5 = 8` 

    - `Sum with positive signs = +2 + 1 + 5 = +8`  (highest possible sum)
    - `Sum with negative signs = -2 - 1 - 5 = -8`  (lowest possible sum)

    - The range of possible sums we can get from the array is `[-8, +8]`

    - `Total possible values` = `[-8,-7,-6...-1,0,+1,+2...+8]` = `17 values` [8 positive values, 8 negative values and 1 zero]

    - `Total possible values` = `2 * 8 + 1` = `2 * totalSum + 1`

- Hence, define `dp` array for `dp[n][2*totalSum + 1]`

- **Subproblem**: Any cell in the dp array `dp[n][sum]` says for `n` elements these are the no. of ways to get `sum`.

##### **Handling Negative index**

- We have possible sum as negative too, say `-8`. 
- But we cannot have array with negative index, like `dp[n][-8]`
- To handle this, we move the entire range from  by `totalSum`.
- **OLD RANGE + totalSum = NEW RANGE**
- `[-8,+8] + 8 = [0, 16]`
- Hence **New Range** = `[0, 16]`, where
    - index `0` correponds to -8
    - index `1` correponds to -7
    - and so on...
- Code part handling this:
```java
// handling negative indices
if(dp[n][currentSum + totalSum] != Integer.MIN_VALUE){
    return dp[n][currentSum + totalSum];
}
```

### Complexity Analysis

- **Time Complexity: _O(n * totalSum)_**  
  - For each of the `n` items, we evaluate up to `2 * totalSum + 1` possible sums.  
  - Total combinations = `n * (2 * totalSum + 1)` = `O(n * totalSum)`.

- **Space Complexity: _O(n * totalSum)_**  
  - dp array required size `dp[n][2 * totalSum + 1]`.  
  - Recursion stack depth is `O(n)`, but its space is dominated by the dp array.

#### [Code(Memoization)](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/December/code/TargetSum.java)

```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;

        // memoization
        int totalSum = 0;

        for(int num : nums){
            totalSum += num;
        }
        
        // For sum range [-8,+8] => new range => [0, 16] indices of the `dp` array
        int[][] dp = new int[n][2 * totalSum + 1];
        
        for(int[] row : dp){
            Arrays.fill(row, Integer.MIN_VALUE);
        }

        return findWaysMemo(nums, 0, 0, target, dp, totalSum);  
    }

    // memoization => O(n*totalSum)
    public int findWaysMemo(int[] nums, int n, int currentSum, int target, int[][] dp, int totalSum){
        if(n == nums.length){
            if(currentSum == target){
                return 1; // found 1 way to reach this target
            } else{
                return 0; // did not find a way
            }
        }

        // handling negative indices `currentSum + totalSum` 
        // if we already calculated this subproblem before, just return its value
        if(dp[n][currentSum + totalSum] != Integer.MIN_VALUE){
            return dp[n][currentSum + totalSum];
        }

        // take positive sign
        int positive = findWaysMemo(nums, n + 1, currentSum + nums[n], target, dp, totalSum);

        // take negative sign
        int negative = findWaysMemo(nums, n + 1, currentSum - nums[n], target, dp, totalSum);

        // store total ways from both choices
        dp[n][currentSum + totalSum] = positive + negative;

        return dp[n][currentSum + totalSum];
    }
}
```