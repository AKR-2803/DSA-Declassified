# POTD 01-15-2025

## 2425. Bitwise XOR of All Pairings [[Problem](https://leetcode.com/problems/bitwise-xor-of-all-pairings/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/BitwiseXorOfAllPairings.java)]

<!-- ![Easy](https://img.shields.io/badge/Easy-green?style=for-the-badge)  -->
![Medium](https://img.shields.io/badge/Medium-yellow?style=for-the-badge)  
<!-- ![Hard](https://img.shields.io/badge/Hard-red?style=for-the-badge) -->

#### **Tags:** [`Array`](https://leetcode.com/problem-list/array/) [`Bit Manipulation`](https://leetcode.com/problem-list/bit-manipulation/) 

## Intuition

- The task is to find XOR of all possible pairings from nums1 and nums2

- Remember the properties of XOR:
    ```
    a ^ a = 0
    a ^ 0 = a
    a ^ b = b ^ a [Commutativity]
    (a ^ b) ^ c = a ^ (b ^ c) [Associativity]
    ```
- Consider this example:

```
Consider an integer `a`

a             = a
a^a           = 0
a^a^a = a^0   = a
a^a^a^a = a^a = 0

Observe that the result of XOR for 

- odd no. of times  = a
- even no. of times = 0
```

- This means that an integer `a` occuring:
    - **odd no. of times** in XOR operation reduces to `a` itself
    - **even no. of times** in XOR operation reduces to `0`

- Now, its just a game of odd and even length of the arrays. Consider this example:

```
Consider two integer array A and B:

A = [a,b,c]  |  B = [d,e]

All possible pairings:

-----------------------------------------------------------------------------

=> (ad, ae), (bd, be), (cd, ce)

-----------------------------------------------------------------------------

=> Take XOR between all of them as asked in the problem statement

-----------------------------------------------------------------------------

=> (a^d ^ a^e) ^ (b^d ^ b^e) ^ (c^d ^ c^e)

-----------------------------------------------------------------------------

=> We can rearrange the order because of the properties of XOR we saw earlier

-----------------------------------------------------------------------------

=> (a^a) ^ (b^b) ^ (c^c) ^ (d^d^d) ^ (e^e^e)

=> 0 ^ 0 ^ 0 ^ d ^ e

=> (d ^ e)

```

- Observe that `a`, `b`, and `c` were reduced to zero, because they occured even times, while `d` and `e` reduced to themselves as they occured odd times.

- Observe that frequency of elements of array `A`, i.e. `a`, `b` and `c` depends on length of the other array `B`.
- While, frequency of elements of array `B`, i.e. `d`, `e` depends on length of the other array `A`.

- Try to draw it out on pen and paper and you'll observe this simple fact.

## Approach

- Consider 3 possible cases

    - 1. Both `nums1` and `nums2` have even length, everything occurs even times, XOR reduces to `0`

    ```java
    boolean n1Even = (nums1.length % 2 == 0);
    boolean n2Even = (nums2.length % 2 == 0);

    // both even, return `0`
    if(n1Even && n2Even){
        return 0;
    }
    ```

    - 2. Both of them have odd length, calculate XOR of each array separately, and then take final XOR between both array's XOR values

    ```java
    // both odd find both
    if(!n1Even && !n2Even){
        for(int num : nums1){
            xor1 ^= num;
        }

        for(int num : nums2){
            xor2 ^= num;
        }
        return (xor1 ^ xor2);
    }
    ```

    - 3. One of them has odd length, be careful here about which arrays elements' XOR will reduce to zero. If `nums1` has odd length, then its XOR will be zero, because its elements will occur even times in XOR.

    ```java
     // nums1 odd, nums2 even
     // be careful! nums1 is odd, hence `nums2` elements will survive in XOR!
     // try it out with an example on pen paper to understand clearly
    if(!n1Even){
        xor1 = 0;

        for(int num : nums2){
            xor2 ^= num;
        }
    
        return (xor1 ^ xor2);
    }

    // last case, nums2 odd, nums1 even    
    // be careful! nums2 is odd, hence `nums1` elements will survive in XOR!
    xor2 = 0;

    for(int num : nums1){
        xor1 ^= num;
    }

    return (xor1 ^ xor2);
    ```

### Complexity Analysis

- **Time Complexity: _O(n + m)_**  
    - `n`: nums1.length
    - `m`: nums2.length

- **Space Complexity: _O(1)_**  
  - No extra space used.

#### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/BitwiseXorOfAllPairings.java)

```java
class Solution {
    public int xorAllNums(int[] nums1, int[] nums2) {
        boolean n1Even = (nums1.length % 2 == 0);
        boolean n2Even = (nums2.length % 2 == 0);

        // both even, return `0`
        if(n1Even && n2Even){
            return 0;
        }

        int xor1 = 0;
        int xor2 = 0;

        // both odd find both
        if(!n1Even && !n2Even){
            for(int num : nums1){
                xor1 ^= num;
            }

            for(int num : nums2){
                xor2 ^= num;
            }
            return (xor1 ^ xor2);
        }

        // nums1 odd, nums2 even
        if(!n1Even){
            xor1 = 0;

            for(int num : nums2){
                xor2 ^= num;
            }
        
            return (xor1 ^ xor2);
        }

        // last case, nums2 odd, nums1 even    
        xor2 = 0;

        for(int num : nums1){
            xor1 ^= num;
        }
    
        return (xor1 ^ xor2);
    }
}
```