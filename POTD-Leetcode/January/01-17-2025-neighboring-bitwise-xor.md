# POTD 01-17-2025

## 2683. Neighboring Bitwise XOR [[Problem](https://leetcode.com/problems/neighboring-bitwise-xor/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/NeighboringBitwiseXor.java)]

<!-- ![Easy](https://img.shields.io/badge/Easy-green?style=for-the-badge)  -->
![Medium](https://img.shields.io/badge/Medium-yellow?style=for-the-badge)  
<!-- ![Hard](https://img.shields.io/badge/Hard-red?style=for-the-badge) -->

#### **Tags:** [`Array`](https://leetcode.com/problem-list/array/) [`Bit Manipulation`](https://leetcode.com/problem-list/bit-manipulation/) 

## Intuition

- Remember the properties of XOR:

    ```
    a ^ a = 0
    a ^ 0 = a
    a ^ b = b ^ a [Commutativity]
    (a ^ b) ^ c = a ^ (b ^ c) [Associativity]
    ```

- Say, original = `[a,b,c]`, consider this:

    ```
    original => [a, b, c]
    
    Let's form derived array from original

    derived  => [a^b, b^c, c^a]

    XOR of all values in dervied =>  [a^b ^ b^c ^ c^a] => [a^a ^ b^b ^ c^c] => [0^0^0] => 0!
    ```

- The problem is reduced to verifying if the XOR of all values in derived equals 0.

## Approach

- Traverse `derived` and maintain xor of values in `allxor`
- Return `true` if `allxor == 0`, else `false`;

### Complexity Analysis

- **Time Complexity: _O(n)_**  
    - `n`: derived.length

- **Space Complexity: _O(1)_**  
  - No extra space used

#### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/NeighboringBitwiseXor.java)

```java
class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        int allxor = 0;
        
        // compute XOR of all values in derived 
        for(int num : derived){
            allxor ^= num;
        }

        return allxor == 0; // if `0` then it can be formed from an `original` array
    }
}
```