# POTD 01-13-2025

## 3223. Minimum Length of String After Operations [[Problem](https://leetcode.com/problems/minimum-length-of-string-after-operations/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/MinLengthOfStringAfterOperations.java)]

<!-- ![Easy](https://img.shields.io/badge/Easy-green?style=for-the-badge)  -->
![Medium](https://img.shields.io/badge/Medium-yellow?style=for-the-badge)  
<!-- ![Hard](https://img.shields.io/badge/Hard-red?style=for-the-badge) -->

#### **Tags:** [`Hash Table`](https://leetcode.com/tag/hash-table/) [`String`](https://leetcode.com/tag/string/) [`Counting`](https://leetcode.com/problem-list/counting/) 

## Intuition

- **Key Observations:** 
    1. Frequency of characters determines the final outcome, no matter the order! 
    2. If a character appears **less than 3** times, no operation is needed.
    3. For character appearing more than 3 times, consider this:

    > "KEEP REMOVING 2 until frequency becomes less than 3"

    ```
    ----before------after---
    | s     | freq |  s  | freq
    aaa     |  3   |  a  | 1
    aaaa    |  4   | aa  | 2
    aaaaa   |  5   |  a  | 1
    aaaaaa  |  6   | aa  | 2
    aaaaaaa |  7   |  a  | 1
    ```

- Observe that for **odd frequencies**, the final frequency reduces to `1`, and for **even frequencies** reduces to `2`. This was obvious because:
    - **ODD FREQUENCY**: For any odd number `3, 5, 7...etc` => when we keep decrementing it by `2`, eventually they will reduce to `1`. For e.g. `9-2 => 7 => 7-2 => 5 => 5-2 => 3 => 3-2 => 1 [stop as value became less than `3`]`
    - **EVEN FREQUENCY**: For any even number `4, 6, 8...etc` => when we keep decrementing it by `2`, eventually they will reduce to `2`. For e.g. `8-2 => 6 => 6-2 => 4 => 4-2 => 2 [stop as value became less than `3`]`

- Finally, we just count total of the updated frequencies, which will be the **minimum length** after operations. 

## Approach

- Count the frequency of each character in the string using an array.
```java
 // frequency array for 26 lowercase english letters
int[] freq = new int[26];

// count the frequency of each character
for (int i = 0; i < sLen; i++) {
    char ch = s.charAt(i);
    freq[ch - 'a'] += 1;
}
```
- Iterate over the frequency array:
    - If the frequency is less than 3, add it directly to the result (`minLength`).
    - Else, reduce **odd frequencies** to `1` and **even frequencies** to `2`, and update the result, i.e. `minLength`
```java
for (int i = 0; i < freq.length; i++) {
    if (freq[i] < 3) {
        // frequencies < 3 are added directly
        minLength += freq[i];
        continue;
    }

    // for frequencies > 3,  reduce odd frequencies to `1`, even frequencies to `2`
    freq[i] = (freq[i] % 2 != 0) ? 1 : 2;
    minLength += freq[i];
}
```

- Return the final sum of the updated frequencies, i.e. `minLength`

### Complexity Analysis
- **Time Complexity: _O(n)_**
    - `n`: String length
- **Space Complexity: _O(1)_**
    - Space taken by `freq` array is constant (size 26)

#### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/MinLengthOfStringAfterOperations.java)

```java
class Solution {
    public int minimumLength(String s) {
        int sLen = s.length();

        // for lengths < 3, no operations are possible, it is already at minimum possible length
        if (sLen < 3) {
            return sLen;
        }

        // frequency array for 26 lowercase english letters
        int[] freq = new int[26];

        // count the frequency of each character
        for (int i = 0; i < sLen; i++) {
            char ch = s.charAt(i);
            freq[ch - 'a'] += 1;
        }

        // return the minimum length after operations
        return updateFreq(freq);
    }

    private int updateFreq(int[] freq) {
        int minLength = 0; // total length after performing operations, which will be the minimum length

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] < 3) {
                // frequencies < 3 are added directly
                minLength += freq[i];
                continue;
            }
            
            // for frequencies > 3,  reduce odd frequencies to `1`, even frequencies to `2`
            freq[i] = (freq[i] % 2 != 0) ? 1 : 2;
            minLength += freq[i];
        }

        return minLength;
    }
}
```