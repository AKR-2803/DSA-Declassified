class Solution {
    public int minimumLength(String s) {
        int sLen = s.length();

        // for lengths < 3, no operations are possible, it is already at minimum possible length
        if (sLen < 3) {
            return sLen;
        }

        // frequency array for 26 lowercase english letters
        int[] freq = new int[26];

        // count the frequency of each character
        for (int i = 0; i < sLen; i++) {
            char ch = s.charAt(i);
            freq[ch - 'a'] += 1;
        }

        // return the minimum length after operations
        return updateFreq(freq);
    }

    private int updateFreq(int[] freq) {
        int minLength = 0; // total length after performing operations, which will be the minimum length

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] < 3) {
                // frequencies < 3 are added directly
                minLength += freq[i];
                continue;
            }
            
            // for frequencies > 3,  reduce odd frequencies to `1`, even frequencies to `2`
            freq[i] = (freq[i] % 2 != 0) ? 1 : 2;
            minLength += freq[i];
        }

        return minLength;
    }
}