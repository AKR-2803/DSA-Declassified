class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        // Create a set of vowels for quick lookup
        Set<Character> vowels = new HashSet<>(5);
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        int N = words.length; // Number of words in the array
        int[] validArr = new int[N]; // Array to mark words that start and end with vowels

        // Populate validArr with 1 if the word starts and ends with a vowel, otherwise 0
        for (int i = 0; i < N; i++) {
            if (isValid(words[i], vowels)) { // Check if the word is valid
                validArr[i] = 1; // `1` means valid, else `0`
            }
        }

        // Create a prefix sum array to easily find sum in given 
       int[] prefix = new int[validArr.length + 1];

        // Ref: [https://leetcodethehardway.com/tutorials/basic-topics/prefix-sum]
        prefix[0] = 0; // Initialize the first element of prefix array

        // Compute the prefix sum
        for (int i = 0; i < validArr.length; i++) {
            prefix[i + 1] = prefix[i] + validArr[i];
        }

        int[] ans = new int[queries.length]; // Array to store answers to each query

        // Process each query
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0]; // Start of the range
            int right = queries[i][1]; // End of the range

            // Calculate the no. of valid strings in the range using prefix sum
            ans[i] = prefix[right + 1] - prefix[left];
        }
        return ans;
    }

    // isValid() : to check if a word starts and ends with a vowel
    public boolean isValid(String str, Set<Character> vowels) {
        return vowels.contains(str.charAt(0)) && vowels.contains(str.charAt(str.length() - 1));
    }
}