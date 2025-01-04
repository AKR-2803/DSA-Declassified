# POTD 01-01-2025

## 1422. Maximum Score After Splitting a String [[Problem](https://leetcode.com/problems/maximum-score-after-splitting-a-string/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/MaxScoreAfterSplittingAString.java)]

<!-- ![Easy](https://via.placeholder.com/50x20/00FF00/000000?text=Easy)  -->
![Medium](https://via.placeholder.com/70x20/FFA500/000000?text=Medium)  
<!-- ![Hard](https://via.placeholder.com/50x20/FF0000/000000?text=Hard) -->

#### **Tags:** [`String`](https://leetcode.com/problem-list/string/) [`Prefix Sum`](https://leetcode.com/problem-list/prefix-sum/) 

## Intuition
- Valid string is string with starting and ending vowel.
- Let `1` : string is valid, `0`: string invalid.
- For each string in `words`, we can store `1` if string is valid, `0` if invalid.
- For example:
```
index    =>    0     1     2    3    4
words    => ["aba","bcb","ece","aa","e"]
validArr => [  1,    0,    1,   1,   1 ] 
```

- Now, given any query, say [2,5]. Answer to this query is the sum in `validArr` from index 2 to index 5 (both inclusive).
- Hence, **the problem is reduced to** finding the sum of values in an array in a given range.
- Prefix Sum can help us achieve this!
- **Recommended**: A good reference for understanding **prefix sum** approach is [**this article**](https://leetcodethehardway.com/tutorials/basic-topics/prefix-sum) by @wingkwong

## Approach

- For each valid string in `words`, store `1` in `validArr` at the corresponding index.
```java
// Populate validArr with 1 if the word starts and ends with a vowel, otherwise 0
for (int i = 0; i < N; i++) {
    if (isValid(words[i], vowels)) { // Check if the word is valid
        vowelArr[i] = 1; // `1` means valid, else `0`
    }
}
```


- Now that we have `validArr`, we just need to find sum in the ranges given in `queries`
- To do this efficiently, we use **PREFIX SUM**
- We calculate the prefix sum of `validArr`

```java
// Create a prefix sum array to easily find sum in given 
int[] prefix = new int[validArr.length + 1];

prefix[0] = 0; 

// Compute the prefix sum
for (int i = 0; i < validArr.length; i++) {
    prefix[i + 1] = prefix[i] + validArr[i];
}
```
- The sum for any given range `[left, right]` in `validArr` will be given by `prefix[right + 1] - prefix[left]` [**WHY?**](https://leetcodethehardway.com/tutorials/basic-topics/prefix-sum)

- For each query in `queries`, compute the answer and store it in an `ans` array.
```java
int[] ans = new int[queries.length]; // Array to store answers to each query

// Process each query
for (int i = 0; i < queries.length; i++) {
    int left = queries[i][0]; // Start of the range
    int right = queries[i][1]; // End of the range

    // Calculate the no. of valid strings in the range using prefix sum
    ans[i] = prefix[right + 1] - prefix[left];
}
```

### Complexity Analysis

- **Time Complexity: _O(N + Q)_**  
  - `N`: words.length
  - `Q`: queries.length

- **Space Complexity: _O(N + Q)_**  
  - Space taken by:
    - `vowelArr`: O(N)
    - `prefix`:O(N + 1) 
    - `ans`: O(Q)

#### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/MaxScoreAfterSplittingAString.java)

```java
class Solution {
    public int maxScore(String s) {
        int totalOnes = 0;

        // count the total no. of `1`s in the entire string
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                totalOnes += 1;
            }
        }

        int leftZeros = 0;         // no. of '0's in the left substring
        int rightOnes = totalOnes; // initially, all `1`s are in the right substring
        int maxScore = 0;          // maximum score
        int len = s.length();

        // iterate through the string to calculate scores for valid splits
        // we stop at `len - 1` because both substrings need to be "non-empty"
        for (int i = 0; i < len - 1; i++) {
            if (s.charAt(i) == '0') {
                leftZeros++; // Increment `leftZeros` when encountering a '0'
            } else {
                rightOnes--; // Decrement `rightOnes` when encountering a '1' (moving to left)
            }
            // update maxScore
            maxScore = Math.max(maxScore, leftZeros + rightOnes);
        }
        return maxScore;
    }
}
```