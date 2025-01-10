class Solution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        // Array to store the maximum frequency of each character across all strings in `words2`
        int[] maxFreq = new int[26];

        // calculate maxFreq of each character across all strings in `words2`
        for (String word : words2) {
            int[] currentWordFreq = new int[26];

            for (char ch : word.toCharArray()) {
                currentWordFreq[ch - 'a']++;

                // Update maxFreq for each character
                maxFreq[ch - 'a'] = Math.max(maxFreq[ch - 'a'], currentWordFreq[ch - 'a']);
            }
        }

        // storing universal strings
        List<String> universalWords = new ArrayList<>();

        for (String word : words1) {
            // frequency array for the current word in words1
            int[] wordFreq = new int[26];

            for (char ch : word.toCharArray()) {
                wordFreq[ch - 'a']++;
            }

            // check if the word is universal
            if (isUniversal(wordFreq, maxFreq)) {
                universalWords.add(word);
            }
        }
        return universalWords;
    }

    // method to check if a word is universal
    private boolean isUniversal(int[] wordFreq, int[] maxFreq) {
        // verify if `wordFreq` satisfies the `maxFreq` for all characters
        for (int i = 0; i < 26; i++) {
            
            // `maxFreq[i]` is minimum frequency we need for `wordFreq[i]` 
            if (maxFreq[i] > wordFreq[i]) {
                return false;
            }
        }
        return true;
    }
}
