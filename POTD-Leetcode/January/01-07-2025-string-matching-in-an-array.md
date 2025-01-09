# POTD 01-07-2025

## 1408. String Matching in an Array [[Problem](https://leetcode.com/problems/string-matching-in-an-array/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/StringMatchingInAnArray.java)]

![Easy](https://via.placeholder.com/50x20/00FF00/000000?text=Easy) 
<!-- ![Medium](https://via.placeholder.com/70x20/FFA500/000000?text=Medium)   -->
<!-- ![Hard](https://via.placeholder.com/50x20/FF0000/000000?text=Hard) -->

#### **Tags:** [`Array`](https://leetcode.com/tag/array/) [`String`](https://leetcode.com/tag/string/) [`String Matching`](https://leetcode.com/problem-list/string-matching/)

## Intuition
- This can be achieved using either a brute force approach or the more efficient **KMP algorithm** for substring matching.

- Although brute force will work in the question as the constraints are small, we would discuss both the approaches here.
    ```
    Constraints:
    1 <= words.length <= 100
    1 <= words[i].length <= 30
    ```

## Resources

- Check [**MY TAKE ON KMP ALGORITHM**](https://github.com/AKR-2803/DSA-Declassified/blob/main/Notes/Strings/KMP.md)
- Few resources to learn more about KMP Algorithm:
    - [**KMP Algorithm by Abdul Bari**](https://www.youtube.com/watch?v=V5-7GzOfADQ)
    - [**KMP Algorithm for Pattern Searching GeeksForGeeks**](https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/)
    - [**Leetcode Discuss**](https://leetcode.com/discuss/general-discussion/1003074/the-knuth-morris-pratt-algorithm-in-my-own-words)

- Few questions to practice KMP algorithm are:
- [28. Find the Index of the First Occurrence in a String](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/) 
![Easy](https://via.placeholder.com/50x20/00FF00/000000?text=Easy) 

- [686. Repeated String Match](https://leetcode.com/problems/repeated-string-match/description/) ![Medium](https://via.placeholder.com/70x20/FFA500/000000?text=Medium) 

- [214. Shortest Palindrome](https://leetcode.com/problems/shortest-palindrome/description/) ![Hard](https://via.placeholder.com/50x20/FF0000/000000?text=Hard)

## Approach 1: Brute Force
- Compare each string with all other strings in the array.
- Check if one string is a substring of another using Java's `.contains()` method.
- Add the substring to the result list if not already present.

- **Time Complexity: _O(n^2 * m)_**
  - `n`: Number of strings in the array.
  - `m`: Average length of the strings.

- **Space Complexity: _O(k)_**
  - `k`: Space used to store the result list.

## Approach 2: KMP Algorithm
- For each string, check if it is a substring of another string using the precomputed LPS (Longest Prefix Suffix) array.
- Avoid duplicates by checking if the substring is already in the result list.

- **Time Complexity: _O(n^2 + n * m)_**
  - `n^2`: Traversal of the string pairs.
  - `n * m`: KMP substring matching.

- **Space Complexity: _O(k + m)_**
  - `k`: Space used to store the result list.
  - `m`: Space used for the LPS array.


#### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/StringMatchingInAnArray.java)

```java
class Solution {
    public List<String> stringMatching(String[] words) {
        // Brute force (uncomment for use)
        // return bruteForce(words);

        // KMP algorithm
        int n = words.length;
        List<String> res = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            String strI = words[i];

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    String strJ = words[j];

                    // Check if strI is a substring of strJ using KMP
                    if (!res.contains(strI) && isSubstringKMP(strI, strJ)) {
                        res.add(strI);
                    }
                }
            }
        }

        return res;
    }

    // KMP Algorithm for substring matching
    public boolean isSubstringKMP(String pat, String text) {
        int patLen = pat.length();
        int textLen = text.length();
        
        if (patLen > textLen) {
            return false;
        }

        int[] lps = new int[patLen];
        computeLPS(pat, lps);

        int i = 0; // text pointer
        int j = 0; // pattern pointer

        while (i < textLen) {
            if (text.charAt(i) == pat.charAt(j)) {
                i++;
                j++;

                if (j == patLen) {
                    return true; // Pattern found
                }
            } else {
                if (j == 0) {
                    i++;
                } else {
                    j = lps[j - 1];
                }
            }
        }

        return false;
    }

    // Compute the LPS array for KMP
    public void computeLPS(String pat, int[] lps) {
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < pat.length()) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len == 0) {
                    lps[i] = 0;
                    i++;
                } else {
                    len = lps[len - 1];
                }
            }
        }
    }

    // Brute Force approach
    public List<String> bruteForce(String[] words) {
        int n = words.length;
        List<String> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String strI = words[i];

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    String strJ = words[j];
                    if (strI.contains(strJ) && !res.contains(strJ)) {
                        res.add(strJ);
                    }
                }
            }
        }

        return res;
    }
}
```