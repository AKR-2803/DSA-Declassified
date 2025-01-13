# POTD 12-18-2024

## 1475. Final Prices With a Special Discount in a Shop [[Problem](https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/December/code/FinalPricesSpecialDiscount.java)]

![Easy](https://img.shields.io/badge/Easy-green?style=for-the-badge) 
<!-- ![Medium](https://img.shields.io/badge/Medium-yellow?style=for-the-badge)   -->
<!-- ![Hard](https://img.shields.io/badge/Hard-red?style=for-the-badge) -->

---

#### **Tags:** [`Array`](https://leetcode.com/problem-list/array/) [`Stack`](https://leetcode.com/problem-list/stack/) [`Monotonic Stack`](https://leetcode.com/problem-list/monotonic-stack/)

## Intuition
- The problem requires calculating the final prices after applying a discount. 
- The discount is the next smaller or equal value to the current price. 
- To find this, we use a **monotonic stack** to track indices where discounts may be applied.

## Approach
### 1. Brute Force [TC: _O(n^2)_]

- Traverse the array
- Take the current index
- Find potential discount, if found, apply it

#### Code(Brute-Force):
```java
class Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;

        // modifying inputs is not a good practice, hence we clone it
        int[] result = prices.clone();

        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                // find next immediate minimum element (discount)
                if(prices[j] <= prices[i]){
                    // apply discount
                    result[i] = prices[i] - prices[j];
                    break;
                }
            }
        }
    
        return result;
    }
}

```

### 2. Using Stack [TC: _O(n)_]

- **Stack Initialization**: Use a stack to store indices of prices that may get discounted in the future.
- **Traversal**: Iterate through the prices array.
   - If the current price is **less than or equal** to the price at the top of the stack, pop the index and subtract the current price (valid discount applied).
   - Else, push the current index to the stack, that means can't find discount as of now.
- Return the modified result array.
- The stack ensures we process each element efficiently in a monotonic decreasing order, allowing for quick discount application.

#### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/December/code/FinalPricesSpecialDiscount.java)
```java
class Solution {
    public int[] finalPrices(int[] prices) {
        // Monotonic stack
        Stack<Integer> stack = new Stack<>();
        int[] result = prices.clone();

        for (int i = 0; i < prices.length; i++) {
            // Pop indices and apply discount when the criteria are met
            while (!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
                int popIndex = stack.pop();
                result[popIndex] -= prices[i];
            }
            // Push current index for potential future discount check
            stack.push(i);
        }
        return result;
    }
}
```

### Complexity Analysis
- **Time Complexity: _O(n)_**  
    - Each price is pushed and popped from the stack once.
    - Hence total operations: `2*n = O(n)`

- **Space Complexity: _O(n)_**  
    - The stack in worst case has `n` indices