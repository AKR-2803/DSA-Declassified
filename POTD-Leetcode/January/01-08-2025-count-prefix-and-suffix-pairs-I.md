# POTD 01-08-2025

## 3042. Count Prefix and Suffix Pairs I [[Problem](https://leetcode.com/problems/count-prefix-and-suffix-pairs-i/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/CountPrefixAndSuffixPairsI.java)]

![Easy](https://via.placeholder.com/50x20/00FF00/000000?text=Easy) 
<!-- ![Medium](https://via.placeholder.com/70x20/FFA500/000000?text=Medium)   -->
<!-- ![Hard](https://via.placeholder.com/50x20/FF0000/000000?text=Hard) -->

#### **Tags:** [`Array`](https://leetcode.com/tag/array/) [`String`](https://leetcode.com/tag/string/) [`Trie`](https://leetcode.com/tag/trie/)  [`Rolling Hash`](https://leetcode.com/tag/rolling-hash/) [`String Matching`](https://leetcode.com/problem-list/string-matching/) [`Hash Function`](https://leetcode.com/tag/hash-function/) 

## Intuition
- Brute Force works because constraints are small.

```
Constraints:
1 <= words.length <= 50
1 <= words[i].length <= 10
```

## Approach

- For each word, compare it with the remaining words (avoiding duplicate checks).
- Check if the first word (`str1`) is both a prefix and suffix of the second word (`str2`).

- **Time Complexity: _O(n^2*k)_**
  - `n`: Number of words in the array.
  - `k`: Average length of the words (checking `startsWith` and `endsWith` takes O(k)).
  
- **Space Complexity: _O(1)_**
  - No extra space used.

#### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/CountPrefixAndSuffixPairsI.java)

```java
class Solution {
    public int countPrefixSuffixPairs(String[] words) {
        int wLen = words.length;
        int cnt = 0;
        
        // brute force all pairs
        for (int i = 0; i < wLen; i++) {
            String str1 = words[i];
            
            for (int j = i + 1; j < wLen; j++) {
                String str2 = words[j];

                // Ensure`str1` is smaller or equal in length
                if (str1.length() <= str2.length()) {
                    // Check if `str1` is both a prefix and suffix of `str2`
                    if (str2.startsWith(str1) && str2.endsWith(str1)) {
                        cnt++;
                    }
                }
            }
        }

        // total count of valid pairs
        return cnt;
    }
}
```