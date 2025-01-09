class Solution {
    public List<String> stringMatching(String[] words) {
        // Brute force (uncomment for use)
        // return bruteForce(words);

        // KMP algorithm
        int n = words.length;
        List<String> res = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            String strI = words[i];

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    String strJ = words[j];

                    // Check if strI is a substring of strJ using KMP
                    if (!res.contains(strI) && isSubstringKMP(strI, strJ)) {
                        res.add(strI);
                    }
                }
            }
        }

        return res;
    }

    // KMP Algorithm for substring matching
    public boolean isSubstringKMP(String pat, String text) {
        int patLen = pat.length();
        int textLen = text.length();
        
        if (patLen > textLen) {
            return false;
        }

        int[] lps = new int[patLen];
        computeLPS(pat, lps);

        int i = 0; // text pointer
        int j = 0; // pattern pointer

        while (i < textLen) {
            if (text.charAt(i) == pat.charAt(j)) {
                i++;
                j++;

                if (j == patLen) {
                    return true; // Pattern found
                }
            } else {
                if (j == 0) {
                    i++;
                } else {
                    j = lps[j - 1];
                }
            }
        }

        return false;
    }

    // Compute the LPS array for KMP
    public void computeLPS(String pat, int[] lps) {
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < pat.length()) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len == 0) {
                    lps[i] = 0;
                    i++;
                } else {
                    len = lps[len - 1];
                }
            }
        }
    }

    // Brute Force approach
    public List<String> bruteForce(String[] words) {
        int n = words.length;
        List<String> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String strI = words[i];

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    String strJ = words[j];
                    if (strI.contains(strJ) && !res.contains(strJ)) {
                        res.add(strJ);
                    }
                }
            }
        }

        return res;
    }
}