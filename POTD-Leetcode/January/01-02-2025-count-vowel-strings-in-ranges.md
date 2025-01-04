# POTD 01-02-2025

## 2559. Count Vowel Strings in Ranges [[Problem](https://leetcode.com/problems/count-vowel-strings-in-ranges/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/CountVowelStringsInRanges.java)]

<!-- ![Easy](https://via.placeholder.com/50x20/00FF00/000000?text=Easy)  -->
![Medium](https://via.placeholder.com/70x20/FFA500/000000?text=Medium)  
<!-- ![Hard](https://via.placeholder.com/50x20/FF0000/000000?text=Hard) -->

#### **Tags:** [`Array`](https://leetcode.com/problem-list/array/) [`String`](https://leetcode.com/problem-list/string/) [`Prefix Sum`](https://leetcode.com/problem-list/prefix-sum/) 

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

#### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/CountVowelStringsInRanges.java)

```java
class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        // Create a set of vowels for quick lookup
        Set<Character> vowels = new HashSet<>(5);
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        int N = words.length; // no. of words in the array
        int[] validArr = new int[N]; // array to mark words that start and end with vowels

        // populate `validArr` with 1 if the word starts and ends with a vowel, otherwise 0
        for (int i = 0; i < N; i++) {
            if (isValid(words[i], vowels)) { // Check if the word is valid
                validArr[i] = 1; // `1` means valid, else `0`
            }
        }

        // create a prefix sum array to easily find sum in given 
       int[] prefix = new int[validArr.length + 1];

        // ref: [https://leetcodethehardway.com/tutorials/basic-topics/prefix-sum]
        prefix[0] = 0; // Initialize the first element of prefix array

        // Compute the prefix sum
        for (int i = 0; i < validArr.length; i++) {
            prefix[i + 1] = prefix[i] + validArr[i];
        }

        int[] ans = new int[queries.length]; // array to store answers to each query

        // process each query
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];  // start of the range
            int right = queries[i][1]; // end of the range

            // calculate answer using prefix sum
            ans[i] = prefix[right + 1] - prefix[left];
        }
        return ans;
    }

    // method to check if a string starts and ends with a vowel
    public boolean isValid(String str, Set<Character> vowels) {
        return vowels.contains(str.charAt(0)) && vowels.contains(str.charAt(str.length() - 1));
    }
}
```