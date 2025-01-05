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