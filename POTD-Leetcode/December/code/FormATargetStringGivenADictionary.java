class Solution {
    public int numWays(String[] words, String target) {
        final int MOD = 1_000_000_007; // Modulo for the result to prevent overflow
        int numWords = words.length;
        int wordLength = words[0].length();
        int targetLength = target.length();

        // Edge case: if the length of a word is less than the target, impossible to form
        if (wordLength < targetLength) return 0;

        // `charFrequency[i][c]` stores the frequency of character `c` at index `i` in all words
        int[][] charFrequency = new int[wordLength][26];
        for (int i = 0; i < wordLength; ++i) {
            for (int j = 0; j < numWords; ++j) {
                charFrequency[i][words[j].charAt(i) - 'a'] += 1;
            }
        }

        // `dp[i][j]` represents the number of ways to form the first `i+1` characters of target
        // using the first `j+1` indices of the words
        int[][] dp = new int[targetLength][wordLength];

        for (int i = 0; i < targetLength; ++i) {
            int targetCharIndex = target.charAt(i) - 'a';
            for (int j = i; j < wordLength - (targetLength - 1 - i); ++j) { // Ensure valid remaining indices
                if (i == 0) {
                    // For the first character of the target, directly take the frequency
                    dp[i][j] = charFrequency[j][targetCharIndex];
                } else {
                    long sum = 0;
                    for (int k = i - 1; k < j; ++k) {
                        sum += dp[i - 1][k];
                    }
                    dp[i][j] = (int) ((sum * charFrequency[j][targetCharIndex]) % MOD);
                }
            }
        }

        // Calculate the final answer by summing up all valid ways to form the entire target
        int result = 0;
        for (int i = targetLength - 1; i < wordLength; ++i) {
            result = (result + dp[targetLength - 1][i]) % MOD;
        }

        return result;
    }
}