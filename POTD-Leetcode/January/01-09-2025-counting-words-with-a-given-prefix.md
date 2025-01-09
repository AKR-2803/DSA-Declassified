# POTD 01-09-2025

## 2185. Counting Words With a Given Prefix [[Problem](https://leetcode.com/problems/counting-words-with-a-given-prefix/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/CountingWordsWithAGivenPrefix.java)]

![Easy](https://via.placeholder.com/50x20/00FF00/000000?text=Easy) 
<!-- ![Medium](https://via.placeholder.com/70x20/FFA500/000000?text=Medium)   -->
<!-- ![Hard](https://via.placeholder.com/50x20/FF0000/000000?text=Hard) -->

#### **Tags:** [`Array`](https://leetcode.com/tag/array/) [`String`](https://leetcode.com/tag/string/) [`String Matching`](https://leetcode.com/problem-list/string-matching/) 

## Intuition
- Brute Force works because constraints are small.

```
Constraints
1 <= words.length <= 100
1 <= words[i].length, pref.length <= 100
```

## Approach

- For each word in `words` check if it has `pref` as prefix prefix.

- **Time Complexity: _O(n)_**
  - `n`: size of `words` array.
  
- **Space Complexity: _O(1)_**
  - No extra space used.

#### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/CountingWordsWithAGivenPrefix.java)

```java
class Solution {
    public int prefixCount(String[] words, String pref) {
        int cnt = 0;
        for(String str : words){
            // `str` contains `pref` as prefix 
            if(str.startsWith(pref)){
                cnt += 1; // increment cnt
            }
        }
        return cnt;
    }
}
```