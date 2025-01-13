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