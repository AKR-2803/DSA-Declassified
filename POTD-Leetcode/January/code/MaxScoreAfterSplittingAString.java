class Solution {
    public int maxScore(String s) {
        int totalOnes = 0;

        // count the total no. of `1`s in the entire string
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                totalOnes += 1;
            }
        }

        int leftZeros = 0;         // no. of '0's in the left substring
        int rightOnes = totalOnes; // initially, all `1`s are in the right substring
        int maxScore = 0;          // maximum score
        int len = s.length();

        // iterate through the string to calculate scores for valid splits
        // we stop at `len - 1` because both substrings need to be "non-empty"
        for (int i = 0; i < len - 1; i++) {
            if (s.charAt(i) == '0') {
                leftZeros++; // Increment `leftZeros` when encountering a '0'
            } else {
                rightOnes--; // Decrement `rightOnes` when encountering a '1' (moving to left)
            }
            // update maxScore
            maxScore = Math.max(maxScore, leftZeros + rightOnes);
        }
        return maxScore;
    }
}