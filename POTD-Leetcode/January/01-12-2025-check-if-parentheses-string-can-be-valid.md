# POTD 01-12-2025

## 2116. Check if a Parentheses String Can Be Valid [[Problem](https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/CheckIfParenthesesStringCanBeValid.java)]

<!-- ![Easy](https://via.placeholder.com/50x20/00FF00/000000?text=Easy)  -->
![Medium](https://via.placeholder.com/70x20/FFA500/000000?text=Medium)  
<!-- ![Hard](https://via.placeholder.com/50x20/FF0000/000000?text=Hard) -->

#### **Tags:** [`String`](https://leetcode.com/tag/string/) [`Stack`](https://leetcode.com/problem-list/stack/) [`Greedy`](https://leetcode.com/problem-list/greedy/) 

## Intuition

- `s`: Firstly, a valid string will always have pairs of `(` and `)`, meaning the length of the string must be even.

- `s`: Secondly, each `(` will be matched to `)`, hence we must have equal number of `(` and `)` in the string

- `locked`: Character `0` gives us the **flexibility** to change the bracket type from `(` to `)` or vice-versa. `1` says character is locked.

- For the string to be valid, the no. of opening brackets should be matched with closing brackets, if NOT, check  if you can use flexibility to match it.

## Approach: Matching Using Forward-Backward Pass

- Forward Pass(Left-to-Right):
    - If a `0` is encountered, `flex++`, otherwise     
    - Track open brackets `(` using a counter (openCount).
    - If a `)` is encountered:
        - First try to pair it with an existing `(` (openCount--).
        - If no `(` is available, use flexibility (flex--).
        - If neither is possible, means imbalance is unresolvable return `false`
    ```java
    int openCount = 0;  // tracks the count of '(' brackets
    int flex = 0;       // tracks flexibility (count of '0' in locked)

    // forward pass: check validity from left to right
    for (int i = 0; i < sLen; i++) {
        char chS = s.charAt(i);
        char chL = locked.charAt(i);

        // flexibility increases with '0'
        if (chL == '0') {
            flex++;
        }
        // if locked (`1`) current character is '(', increment openCount
        else if (chS == '(') {
            openCount++;
        }
        // if locked (`1`) and current character is ')', try to balance
        else {
            // first try to balance with `(`
            if (openCount > 0) {
                openCount--;
            }         
            
            // if no `(` available, use flexibility now to create `(`  
            else if (flex > 0) {
                flex--;
            } 
            
            // found `)` but dont have any open brackets `(` or flexibility left, hence invalid
            else {
                return false;  // cannot balance
            }
        }
    }
    ```
- Backward Pass(Right-to-Left):
    - Reset `flex = 0`
    - Initialize `closeCount = 0` and now track close bracket `)`
    ```java
    int closeCount = 0; // tracks the count of ')' brackets
    flex = 0;           // reset flexibility for the second pass

    // backward pass: check validity from right to left
    for (int i = sLen - 1; i >= 0; i--) {
        char chS = s.charAt(i);
        char chL = locked.charAt(i);

        // flexibility increases with '0'
        if (chL == '0') {
            flex++;
        }
        // if locked (`1`) current character is ')', increment closeCount
        else if (chS == ')') {
            closeCount++;
        }
        // if locked (`1`) and current character is '(', try to balance
        else {
            // first try to balance with `)`
            if (closeCount > 0) {
                closeCount--;
            }         
            
            // if no `)` available, use flexibility now to create `)`  
            else if (flex > 0) {
                flex--;
            } 
            
            // found `(` but dont have any open brackets `)` or flexibility left, hence invalid
            else {
                return false;  // cannot balance
            }
        }
    }
    ```

- If both passes complete without returning `false`, the string is valid, return `true`
    
### Complexity Analysis
- **Time Complexity: _O(n)_**  
    - Traversing twice: `O(2n) = O(n)`, where `n` is the length of the string `s`

- **Space Complexity: _O(1)_**  
    - No extra space used.

#### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/CheckIfParenthesesStringCanBeValid.java)

```java
class Solution {
    public boolean canBeValid(String s, String locked) {
        int sLen = s.length();

        // odd-length strings can never be valid
        if (sLen % 2 != 0) {
            return false;
        }

        int openCount = 0;  // tracks the count of '(' brackets
        int flex = 0;       // tracks flexibility (count of '0' in locked)

        // forward pass: check validity from left to right
        for (int i = 0; i < sLen; i++) {
            char chS = s.charAt(i);
            char chL = locked.charAt(i);

            // flexibility increases with '0'
            if (chL == '0') {
                flex++;
            }
            // if locked (`1`) current character is '(', increment openCount
            else if (chS == '(') {
                openCount++;
            }
            // if locked (`1`) and current character is ')', try to balance
            else {
                // first try to balance with `(`
                if (openCount > 0) {
                    openCount--;
                }         
                
                // if no `(` available, use flexibility now to create `(`  
                else if (flex > 0) {
                    flex--;
                } 
                
                // found `)` but dont have any open brackets `(` or flexibility left, hence invalid
                else {
                    return false;  // cannot balance
                }
            }
        }

        // if unmatched '(' exceed flexibility, invalid string
        if (openCount > flex) {
            return false;
        }

        int closeCount = 0; // tracks the count of ')' brackets
        flex = 0;           // reset flexibility for the second pass

        // backward pass: check validity from right to left
        for (int i = sLen - 1; i >= 0; i--) {
            char chS = s.charAt(i);
            char chL = locked.charAt(i);

            // flexibility increases with '0'
            if (chL == '0') {
                flex++;
            }
            // if locked (`1`) current character is ')', increment closeCount
            else if (chS == ')') {
                closeCount++;
            }
            // if locked (`1`) and current character is '(', try to balance
            else {
                // first try to balance with `)`
                if (closeCount > 0) {
                    closeCount--;
                }         
                
                // if no `)` available, use flexibility now to create `)`  
                else if (flex > 0) {
                    flex--;
                } 
                
                // found `(` but dont have any open brackets `)` or flexibility left, hence invalid
                else {
                    return false;  // cannot balance
                }
            }
        }

        // return true if both passes validate successfully!
        return true;
    }
}
```