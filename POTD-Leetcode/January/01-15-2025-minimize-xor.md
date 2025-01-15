# POTD 01-15-2025

## 2429. Minimize XOR [[Problem](https://leetcode.com/problems/minimize-xor/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/MinimizeXor.java)]

<!-- ![Easy](https://img.shields.io/badge/Easy-green?style=for-the-badge)  -->
![Medium](https://img.shields.io/badge/Medium-yellow?style=for-the-badge)  
<!-- ![Hard](https://img.shields.io/badge/Hard-red?style=for-the-badge) -->

#### **Tags:** [`Greedy`](https://leetcode.com/problem-list/greedy/) [`Bit Manipulation`](https://leetcode.com/problem-list/bit-manipulation/) 

## Intuition

- The task is to minimize the XOR value between two numbers, `x` and `num1`, while ensuring that `x` has the same number of set bits (1's) as `num2`. 

## Approach

- First, count the set bits, you can use `Integer.bitCount()` provided in Java.
- Calculate the difference `diff` between the set bits of `num1` and `num2`.

- There might be 3 cases depending on the `diff` value:
   - **Case 1**: If `diff == 0`:
     - `num1` already has the same no. of set bits as `num2`. The XOR will be minimized when `x = num1`.
   - **Case 2**: If `diff > 0`:
     - `num1` has more set bits than required. Use `num1 = num1 & (num1 - 1)` repeatedly to clear the rightmost set bit until the no. of set bits matches `num2`.
   - **Case 3**: If `diff < 0`:
     - `num1` has less set bits than required. Use `num1 = num1 | (num1 + 1)` repeatedly to set the rightmost unset bit until the no. of set bits matches `num2`.

3. **Return the Adjusted `num1`**:
   - After matching the set bits, the adjusted `num1` is the minimized XOR result.


### Complexity Analysis

- **Time Complexity: _O(logN)_**  
    - We know that the total digits of a number in binary system will be `logN(base 2) + 1`
    
- **Space Complexity: _O(1)_**  
  - No extra space used.

#### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/MinimizeXor.java)


```java
class Solution {
    public int minimizeXor(int num1, int num2) {
        // count the number of set bits (1's) in `num1` and `num2`
        int setBitsCount1 = Integer.bitCount(num1);
        int setBitsCount2 = Integer.bitCount(num2);

        // calculate the bit difference in the no. of set bits
        int diff = setBitsCount1 - setBitsCount2;

        // case 1: `num1` and `num2` OR `x` have the same number of set bits
        if (diff == 0) {
            return num1; // (`x` XOR `num1`) will be `0` (minimum) when `x` equals `num1`
        }

        // case 2: `num1` has more set bits than `num2` or `x`
        if (diff > 0) {
            // remove extra set bits from `num1` until it has the same no. of set bits as `num2`
            while (diff != 0) {
                num1 = num1 & (num1 - 1);  // clear the rightmost set bit
                diff--; // decrease the bit difference
            }
            return num1; // return num1 with adjusted set bits
        }

        // case 3: else `num1` has less set bits than `num2` or `x`
        while (diff != 0) {
            num1 = num1 | (num1 + 1);   // set the rightmost 0 bit to 1
            diff++; // increase the bit difference
        }
        return num1;
    }
}
```