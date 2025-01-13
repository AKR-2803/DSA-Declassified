# POTD 01-04-2025

## 1930. Unique Length-3 Palindromic Subsequences [[Problem](https://leetcode.com/problems/unique-length-3-palindromic-subsequences/description/)][[Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/UniqueLength3PalindromicSubsequences.java)]

<!-- ![Easy](https://img.shields.io/badge/Easy-green?style=for-the-badge)  -->
![Medium](https://img.shields.io/badge/Medium-yellow?style=for-the-badge)  
<!-- ![Hard](https://img.shields.io/badge/Hard-red?style=for-the-badge) -->

#### **Tags:** [`Hash Table`](https://leetcode.com/tag/hash-table/) [`String`](https://leetcode.com/tag/string/) [`Bit Manipulation`](https://leetcode.com/tag/bit-manipulation/) [`Prefix Sum`](https://leetcode.com/tag/prefix-sum/)

## Intuition

- A palindrome of length 3 will start and end with the same character. 
- By identifying characters that occur at least twice. 
- We can find all possible palindromes by analyzing the unique characters between their first and last occurrences. Read the approach and the example below for better understanding

## Approach

- Create a frequency map to count occurrences of each character in the string `s`. 

- For each eligible character(character appearing atleast 2 times) `ch`:
   - Find its first (`startIndex`) and last (`endIndex`) positions in `s`.
   - Extract the substring between these positions.

    - Count the unique characters in the extracted substring. Each unique character contributes to a palindrome of the form `ch + uniqueChar + ch`.

    - For example,     
    ```
    - Example Walkthrough for s = "aabca". We can only use `a` as it occurs (>1) times `a` occurs at index => 0, 1, 4
    - Substring between indices 0 and 4 is "abc".
    - (a  "abc"  a), we can take each of the unique character between them, and make a palindrome
    - Unique palindromes: "aaa", "aba", "aca"

    - Substring between indices 1 and 4 is "bc".
    - (a "bc" a)
    - Unique palindromes: "aba", "aca". (But if we took this, we'd miss "aaa" from earlier!)  
- **TO ENSURE NO PALINDROME IS MISSED**: Taking the extremes ensures no palindrome is missed.
- Add the counts of unique palindromes for all eligible characters.

### Complexity Analysis

- **Time Complexity: _O(n)_**  
    - `n` is the length of the string 
    - `O(n)` to compute the frequency map and find first/last positions of characters.
    - For each elegible character, traverse O(n) in worst case => O(26 * n) = O(n)
    - `O(n)` for extracting the substring and storing unique characters in a set.

- **Space Complexity: _O(n)_**  
    - `O(26) = O(1)` for the frequency map and unique characters set, maximum 26 letters possible. 
    - `O(n)` : Set and substring storage.

#### [Code](https://github.com/AKR-2803/DSA-Declassified/blob/main/POTD-Leetcode/January/code/UniqueLength3PalindromicSubsequences.java)

```java
class Solution {
    public int countPalindromicSubsequence(String s) {
        int sLen = s.length();
    
        // [ch, freq]
        Map<Character, Integer> freqMap = new HashMap<>();

        for(int i = 0; i < sLen; i++){
            char ch = s.charAt(i);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        int cnt = 0;

        for(Map.Entry<Character, Integer> entry : freqMap.entrySet()){
            // if atleast 2 of these charcters are present, count its palindromes
            if(entry.getValue() > 1){
                // keep adding no. of palindromes
                cnt += countPalindromes(s, entry.getKey());
            }
        }

        return cnt;
    }
    
    // because we only check for characters than occur atleast 2 times
    // we are guaranteed to find `startIndex` and `endIndex`  
    private int countPalindromes(String s, char ch){
        int startIndex = 0;
        int endIndex = s.length() - 1;

        while(s.charAt(startIndex) != ch){
            startIndex++;
        }

        while(s.charAt(endIndex) != ch){
            endIndex--;
        }

        return findUniqueCharactersInString(s.substring(startIndex + 1, endIndex));
    }

    // find no. of unique characters in the given substring
    public int findUniqueCharactersInString(String str){
        int strLen = str.length();
        Set<Character> hs = new HashSet<>();

        for(int i = 0; i < strLen; i++){
            hs.add(str.charAt(i));
        }

        return hs.size();
    }
}
```