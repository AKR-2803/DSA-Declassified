class Solution {
    public int countPrefixSuffixPairs(String[] words) {
        int wLen = words.length;
        int cnt = 0;
        
        // brute force all pairs
        for (int i = 0; i < wLen; i++) {
            String str1 = words[i];
            
            for (int j = i + 1; j < wLen; j++) {
                String str2 = words[j];

                // Ensure`str1` is smaller or equal in length
                if (str1.length() <= str2.length()) {
                    // Check if `str1` is both a prefix and suffix of `str2`
                    if (str2.startsWith(str1) && str2.endsWith(str1)) {
                        cnt++;
                    }
                }
            }
        }

        // total count of valid pairs
        return cnt;
    }
}