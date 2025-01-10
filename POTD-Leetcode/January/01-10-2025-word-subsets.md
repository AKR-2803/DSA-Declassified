# POTD 01-10-2025

## 916. Word Subsets [[Problem](https://leetcode.com/problems/word-subsets/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/WordSubsets.java)]

<!-- ![Easy](https://via.placeholder.com/50x20/00FF00/000000?text=Easy)  -->
![Medium](https://via.placeholder.com/70x20/FFA500/000000?text=Medium)  
<!-- ![Hard](https://via.placeholder.com/50x20/FF0000/000000?text=Hard) -->

#### **Tags:** [`Array`](https://leetcode.com/tag/array/) [`String`](https://leetcode.com/tag/string/) [`Hash Table`](https://leetcode.com/problem-list/hash-table/) 

## Intuition

- The question is pretty straightforward, it asks us to verify which words in array `words1` are universal or not.

- Lets call the arrays `words1` -> `A` and `words2` -> `B`

- Now observe this example:
    ```
    A = ["aabc", "ef", "xaaaabcd", "abccdefaa"];
    B = ["aaac", "aab", "ccbd"];
    ```

- What characters do we need in any string in `A` to be universal?

- Let us observe the frequencies of all characters in strings in `B`
    ```
    aaac => {a: 3, c: 1}
    aab =>  {a: 2, b: 1}
    ccbd => {c: 2, b: 1, d:1}

    For any string to be universal, it has to have
        3 `a`   => max frequency of `a` among all strings in `B`
        2 `c`   => max frequency of `c` among all strings in `B`
        1 `b`   => max frequency of `b` among all strings in `B`
        1 `d`   => max frequency of `d` among all strings in `B`
    
    ----------------------------------------------
    Observe that strings in `A` that follow this requirement are: "xaaaabcd" and "abccdefaa"
    Hence, these two are universal strings!
    ```

- Hence we need to update the maximum frequencies of characters among all strings in `B`

- Now, we check if the required number of each character is present in any string in `A`. If so, it is considered **universal.**








## Approach

- For each word in `words2`, calculate **max frequency of each character among all strings.**
    ```java
        // Array to store the maximum frequency of each character across all strings in `words2`
        int[] maxFreq = new int[26];

        // calculate maxFreq of each character across all strings in `words2`
        for (String word : words2) {
            int[] currentWordFreq = new int[26];

            for (char ch : word.toCharArray()) {
                currentWordFreq[ch - 'a']++;

                // Update maxFreq for each character
                maxFreq[ch - 'a'] = Math.max(maxFreq[ch - 'a'], currentWordFreq[ch - 'a']);
            }
        }
    ```

- Now traverse through each word in `words1`, make its character frequency array, and check if the string is universal
    ```java
    for (String word : words1) {
        // frequency array for the current word in words1
        int[] wordFreq = new int[26];

        for (char ch : word.toCharArray()) {
            wordFreq[ch - 'a']++;
        }
        // check if the word is universal
        if (isUniversal(wordFreq, maxFreq)) {
            universalWords.add(word);
        }
    }
    ```
- `isUniversal` method: When the `maxFreq[i] > wordFreq[i]`, means there are NOT enough no. of current character in this word to become **universal.** 

    ```java
    // method to check if a word is universal
    private boolean isUniversal(int[] wordFreq, int[] maxFreq) {    
        // verify if `wordFreq` satisfies the `maxFreq` for all characters
        for (int i = 0; i < 26; i++) {
            // `maxFreq[i]` is minimum frequency we need for `wordFreq[i]` 
            if (maxFreq[i] > wordFreq[i]) {
                return false;
            }
        }
        return true;
    }
    ```

- **Time Complexity: _O(N + M)_**
    - `N`: Total characters across all strings in `words1`
    - `M`: Total characters across all strings in `words2`
- **Space Complexity: _O(n + m)_**
    - `n`: words1.length
    - `m`: words2.length

#### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/WordSubsets.java)

```java
class Solution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        // Array to store the maximum frequency of each character across all strings in `words2`
        int[] maxFreq = new int[26];

        // calculate maxFreq of each character across all strings in `words2`
        for (String word : words2) {
            int[] currentWordFreq = new int[26];

            for (char ch : word.toCharArray()) {
                currentWordFreq[ch - 'a']++;

                // update maxFreq for each character
                maxFreq[ch - 'a'] = Math.max(maxFreq[ch - 'a'], currentWordFreq[ch - 'a']);
            }
        }

        // storing universal strings
        List<String> universalWords = new ArrayList<>();

        for (String word : words1) {
            // frequency array for the current word in words1
            int[] wordFreq = new int[26];

            for (char ch : word.toCharArray()) {
                wordFreq[ch - 'a']++;
            }

            // check if the word is universal
            if (isUniversal(wordFreq, maxFreq)) {
                universalWords.add(word);
            }
        }

        return universalWords;
    }

    // method to check if a word is universal
    private boolean isUniversal(int[] wordFreq, int[] maxFreq) {
        // verify if `wordFreq` satisfies the `maxFreq` for all characters
        for (int i = 0; i < 26; i++) {
            // `maxFreq[i]` is minimum frequency we need for `wordFreq[i]` 
            if (maxFreq[i] > wordFreq[i]) {
                return false;
            }
        }
        return true;
    }
}
```