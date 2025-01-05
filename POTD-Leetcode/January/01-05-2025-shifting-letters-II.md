# POTD 01-05-2025

## 2381. Shifting Letters II [[Problem](https://leetcode.com/problems/shifting-letters-ii/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/ShiftingLettersII.java)]

<!-- ![Easy](https://via.placeholder.com/50x20/00FF00/000000?text=Easy)  -->
![Medium](https://via.placeholder.com/70x20/FFA500/000000?text=Medium)  
<!-- ![Hard](https://via.placeholder.com/50x20/FF0000/000000?text=Hard) -->

#### **Tags:** [`Array`](https://leetcode.com/tag/array/) [`String`](https://leetcode.com/tag/string/)[`Prefix Sum`](https://leetcode.com/tag/prefix-sum/)

## Intuition

- The problem involves shifting characters in a string based on multiple range-based operations. 
- To handle these efficiently, we can use a **prefix sum array** to compute the cumulative shifts for each character. 
- This avoids recalculating shifts for overlapping ranges, ensuring optimal performance.

## Approach

- Create a prefix array `shiftArray` to mark the `start` and `end+1` of each shift operation.
- Increment for right shifts (`+1`) and decrement for left shifts (`-1`).

- use the prefix array `shiftArray` to calculate the cumulative shift for each character by iterating over the array.

- **Normalize the cumulative shift** to fall within `[0, 25]` using `%26` to handle large or negative shifts.
    - How does this work?
        - Convert Character to Index:
        - `a` -> `0`, `b` -> `1`, `c` -> `2` and so on `(ch - 'a')`
        -  Add the shift (delta) to the index.
        - Use mod 26 (`% 26`) to ensure it wraps around within the alphabet.
        - If the result is negative, add 26 to bring it back into the valid range.
        - Convert Back to Character
        - Add `a` to the shifted index to get the new character.
        - To ensure the shift wraps around the alphabet, we take the `total shift %26` (since there are 26 letters).
        - If the shift is negative (from left shifts), add `26` to make it positive.

        ```java
        private char findNextChar(char ch, int delta) {
            // calculate the shifted character
            int shifted = (ch - 'a' + delta) % 26;
            
            // handle negative shifts
            if (shifted < 0) {
                shifted += 26; 
            }

            // convert back to character
            return (char) ('a' + shifted);
        }
        ```
- 
### Complexity Analysis

- **Time Complexity: _O(n + m)_**  
  - `n`: length of the string, processing prefix array and modifying characters.
  - `m`: total shifts, for processing the `shifts` array.

- **Space Complexity: _O(n)_**  
  - `prefix` array

#### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/ShiftingLettersII.java)

```java
class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        int sLen = s.length();

        // use an array to track shifts for each index
        int[] shiftArray = new int[sLen + 1]; // Extra space to handle end+1

        // computer `shiftArray` based on the shifts
        for (int[] shift : shifts) {
            int start = shift[0];
            int end = shift[1];
            int delta = shift[2] == 0 ? -1 : 1;

            shiftArray[start] += delta;     // increment shift at start
            shiftArray[end + 1] -= delta;   // decrement shift after end
        }

        // Accumulate the shifts to get final shift for each index
        int cumulativeShift = 0;
        for (int i = 0; i < sLen; i++) {
            cumulativeShift += shiftArray[i];
            shiftArray[i] = cumulativeShift;
        }

        StringBuilder sb = new StringBuilder(sLen);
        for (int i = 0; i < sLen; i++) {
            int delta = shiftArray[i];
            char ch = findNextChar(s.charAt(i), delta); // adjust the character
            sb.append(ch);
        }

        return sb.toString();
    }

    private char findNextChar(char ch, int delta) {
        // calculate the shifted character
        int shifted = (ch - 'a' + delta) % 26;
        
        // handle negative shifts
        if (shifted < 0) {
            shifted += 26; 
        }
        
        // convert back to character
        return (char) ('a' + shifted);
    }
}
```