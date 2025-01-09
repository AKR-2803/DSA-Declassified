## My Take on the KMP Algorithm

- The  KMP algorithm is an advanced string matching algorithm that efficiently finds all occurrences of a pattern string `pat` within a text string `text`. 
- It avoids redundant comparisons by leveraging a **Longest Prefix-Suffix (LPS) array**
- **LPS**: Longest Proper Prefix which is also a Suffix.  
- Before understanding LPS, let us look what are **proper prefixes?** . They are prefix that excludes the full string itself.

#### Understanding proper prefixes using an **example for string `abc`**
```
- String s = "abc"
- Prefixes: `["", "a", "ab", "abc"]`  
- Proper Prefixes: `["", "a", "ab"]`  (does not include full string "abc")
- Suffixes: `["", "c", "bc", "abc"]`
```
### **LPS Array**
- Each value `lps[i]` stores the length of the longest proper prefix in `pat(0...i)` that is also a suffix in `pat(0...i)`.

- For Example
```
Consider pat = "abcdabca"

index =>   0 1 2 3 4 5 6 7
pat   =>   a b c d a b c a     
lps   => [ 0 0 0 0 1 2 3 1 ]
```

### What does the **lps** values actually mean? 
- Say `lps[5]`. `lps[5]` says "I have the longest proper prefix in `pat(0...5)` that is also a suffix in `pat(0...5)`"
- `pat(0...5)` is pattern from index `0` to `5`. `pat(0...5) = "acbdab"`.
- Let us write the proper prefixes and suffixes of this string to understand clearly.

```
pat(0...5) :  "abcdab"
                              X
Proper Prefixes : ["", "a", "ab","abc","abcd","abcda"]
                              X
Suffixes        : ["", "b", "ab", "dab", "cdab", "bcdab", "abcdab"]
```

- Clearly, the prefix `"ab"`(marked with an X) is the longest proper prefix that is also a suffix!
- I hope that makes much more sense now, doesn't it?
- Next step is computing this LPS array

## Computing the LPS Array

### **Process**

- Initialize `lps[0] = 0`, `len = 0`, and `i = 1` (start from the second character).
- Traverse until `i < pat.length`:
   - **If `pat[len] == pat[i]` (characters match):**
     - Increment `len` (`len++`) and assign `lps[i] = len`.
     - Move to the next character (`i++`).
   - **If `pat[len] != pat[i]` (characters mismatch):**
     - If `len == 0`, set `lps[i] = 0` and increment `i`.
     - Otherwise, update `len = lps[len - 1]` and recheck.


### Example for computing LPS Array

- Consider pat = "abcdabcad"
- Let us find the LPS array
- **RECOMMENDATION:** If you are trying to understand this properly, it is recommended that you do this parallelly on pen and paper as well.


```
Start

Remember, we are trying to match characters at `len` and `i` at each iteration

-------------------------------------------

i=1, len=0

          len
index =>   0   1   2   3   4   5   6   7  8
               i
pat   =>   a   b   c   d   a   b   c   a  d   

lps   => [ 0                               ]

character mismatch and (len == 0) => set `lps[i] = 0`, increment `i`

-------------------------------------------

i=2, len=0

          len
index =>   0   1   2   3   4   5   6   7  8
                   i
pat   =>   a   b   c   d   a   b   c   a  d     

lps   => [ 0   0                           ]

character mismatch and (len == 0) => set `lps[i] = 0`, increment `i`

-------------------------------------------

i=3, len=0

          len
index =>   0   1   2   3   4   5   6   7  8
                       i
pat   =>   a   b   c   d   a   b   c   a  d     

lps   => [ 0   0   0                       ]


character mismatch and (len == 0) => set `lps[i] = 0`, increment `i`

-------------------------------------------

i=4, len=0

          len
index =>   0   1   2   3   4   5   6   7  8
                           i
pat   =>   a   b   c   d   a   b   c   a  d  

lps   => [ 0   0   0   0                   ]


character match => len++, lps[i] = len, then i++

-------------------------------------------

i=5, len=1

              len
index =>   0   1   2   3   4   5   6   7  8
                               i
pat   =>   a   b   c   d   a   b   c   a  d  

lps   => [ 0   0   0   0   1               ]


character match => len++, lps[i] = len, then i++

-------------------------------------------

i=6, len=2

                  len
index =>   0   1   2   3   4   5   6   7  8
                                   i
pat   =>   a   b   c   d   a   b   c   a  d  

lps   => [ 0   0   0   0   1   2           ]


character match => len++, lps[i] = len, then i++


-------------------------------------------

i=7, len=3

                      len
index =>   0   1   2   3   4   5   6   7   8
                                       i
pat   =>   a   b   c   d   a   b   c   a   d  

lps   => [ 0   0   0   0   1   2   3        ]


character mismatch => len!=0 => set len = lps[len - 1]

-------------------------------------------

i=7, len=0

          len
index =>   0   1   2   3   4   5   6   7  8
                                       i
pat   =>   a   b   c   d   a   b   c   a  d    

lps   => [ 0   0   0   0   1   2   3       ]


character match => len++, lps[i] = len, then i++


-------------------------------------------

i=8, len=1

              len
index =>   0   1   2   3   4   5   6   7  8
                                          i
pat   =>   a   b   c   d   a   b   c   a  d   

lps   => [ 0   0   0   0   1   2   3   1   ]


character mismatch => len!=0 => set len = lps[len - 1]

-------------------------------------------

i=8, len=0

          len
index =>   0   1   2   3   4   5   6   7  8
                                          i
pat   =>   a   b   c   d   a   b   c   a  d   

lps   => [ 0   0   0   0   1   2   3   1   ]


character mismatch and (len == 0) => set `lps[i] = 0`, increment `i`

-------------------------------------------


i=9, len=1

          len
index =>   0   1   2   3   4   5   6   7  8  9
                                             i
pat   =>   a   b   c   d   a   b   c   a  d   

lps   => [ 0   0   0   0   1   2   3   1  0 ]


condition violated => (i < pat.length()) => loop breaks

END
-------------------------------------------
Final state

lps => [0, 0, 0, 0, 1, 2, 3, 1, 0]
-------------------------------------------

```


## Pattern Matching Using LPS

- Observe closely that the pattern matching code is strikingly similar to computing the LPS array.

### **Process**
- Initialize pointers `i = 0` (text) and `j = 0` (pattern).
- Traverse the text while `i < text.length`:
   - **If `text[i] == pat[j]` (match):**
     - Increment both `i` and `j`.
     - If `j == pat.length` (pattern found):
       - Record the occurrence start index: `i - j`.
       - Reset `j = lps[j - 1]` for further matches.
   - **If `text[i] != pat[j]` (mismatch):**
     - If `j == 0`, increment `i` (move text pointer).
     - Otherwise, reset `j = lps[j - 1]` (shift pattern pointer).

- Dry-run an example and observe how the characters are being matched.  

### Complexity Analysis

- **Time Complexity: _O(n + m)_**  
    - **_O(m)_**: For computing the LPS array.
    - **_O(n)_**: For searching the pattern in the text.
    - where, `n` is the text length and `m` is the pattern length.- 

- **Space Complexity: _O(m)_**  
  - LPS array has size equal to pattern length, i.e. `m`.


### [Problem: Search Pattern (KMP-Algorithm)](https://www.geeksforgeeks.org/problems/search-pattern0205/1)

#### Given two strings, one is a text string txt and the other is a pattern string pat. The task is to print the indexes of all the occurrences of the pattern string in the text string. Use 0-based indexing while returning the indices. Note: Return an empty list in case of no occurrences of pattern.

## Code

```java
class Solution {
    // find all occurrences of pattern `pat` in text `txt`
    ArrayList<Integer> search(String pat, String txt) {
        int txtLen = txt.length();  // text length
        int patLen = pat.length();  // pattern length
        
        // if the pattern is longer than the text, we can never find it in `text`
        if(txtLen < patLen){
            return new ArrayList<>();
        }
        
        int[] lps = new int[patLen];  // Array to store the LPS (Longest Prefix Suffix) values
        
        // compute the LPS array for the pattern
        computeLPS(pat, lps);

        // list to store indices of occurences
        ArrayList<Integer> res = new ArrayList<Integer>();  
        
        // Call match function to find all occurrences of pattern in the text
        match(pat, txt, lps, res);
        
        return res;  // return the answer list 
   }
    
    // pattern matching using LPS array
    private void match(String pat, String txt, int[] lps, ArrayList<Integer> res){
        int txtLen = txt.length(); 
        int patLen = pat.length();  
        
        int i = 0;  // Pointer for the text
        int j = 0;  // Pointer for the pattern
        
        // traverse the text until all characters are checked
        while(i < txtLen){
            
            // character match, increment both `i` and `j`
            if(txt.charAt(i) == pat.charAt(j)){
                i++;
                j++;
                
                // if entire pattern is matched, add the start index `i-j` to result
                if(j == patLen){
                    res.add(i - j); 
                    j = lps[j - 1];  // use LPS array to skip unnecessary comparisons
                }
            }
            else{
                // character mismatch, check if the `len` is at the start
                if(j == 0){
                    i++;  // if `len` at the start of the pattern, move the `i` forward
                }
                else{
                    j = lps[j - 1];  // otherwise, move the `len` based on the LPS array
                }
            }
        }
    }
    
    // compute the LPS array for the given pattern
    private void computeLPS(String pat, int[] lps){
        
        int patLen = pat.length();  
        int len = 0;
        int i = 1;  // start checking from the second character
        
        lps[0] = 0;  // the first element of LPS array is always `0`
        
        // traverse the pattern and fill the LPS array
        while(i < patLen){
            
            // characters match, extend the prefix-suffix length
            if(pat.charAt(i) == pat.charAt(len)){
                len++;         // Increase the length of current matching prefix
                lps[i] = len;  // Update the LPS array with the new length
                i++;           // Move to the next character in the pattern
            }
            else{
                // characters mismatch
                if(len != 0){
                    // use the LPS array to skip and try matching with a smaller prefix
                    len = lps[len - 1];
                }
                else{
                    lps[i] = 0;  // if no match, set LPS value to `0` and move to the next character
                    i++;
                }
            }
        }
    }
}
```

## Resources

- Few resources to learn more about KMP Algorithm:
    - [**KMP Algorithm by Abdul Bari**](https://www.youtube.com/watch?v=V5-7GzOfADQ)
    - [**KMP Algorithm for Pattern Searching GeeksForGeeks**](https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/)
    - [**Leetcode Discuss**](https://leetcode.com/discuss/general-discussion/1003074/the-knuth-morris-pratt-algorithm-in-my-own-words)

- Few questions to practice KMP algorithm are:
- [28. Find the Index of the First Occurrence in a String](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/) 
![Easy](https://via.placeholder.com/50x20/00FF00/000000?text=Easy) 

- [686. Repeated String Match](https://leetcode.com/problems/repeated-string-match/description/) ![Medium](https://via.placeholder.com/70x20/FFA500/000000?text=Medium) 

- [214. Shortest Palindrome](https://leetcode.com/problems/shortest-palindrome/description/) ![Hard](https://via.placeholder.com/50x20/FF0000/000000?text=Hard)