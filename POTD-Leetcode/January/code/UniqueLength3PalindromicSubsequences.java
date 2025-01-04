class Solution {
    public int countPalindromicSubsequence(String s) {
        int sLen = s.length();
    
        // [ch, freq]
        Map<Character, Integer> freqMap = new HashMap<>();

        for(int i = 0; i < sLen; i++){
            char ch = s.charAt(i);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        int cnt = 0;

        for(Map.Entry<Character, Integer> entry : freqMap.entrySet()){
            // if atleast 2 of these charcters are present, count its palindromes
            if(entry.getValue() > 1){
                // keep adding no. of palindromes
                cnt += countPalindromes(s, entry.getKey());
            }
        }

        return cnt;
    }
    
    // because we only check for characters than occur atleast 2 times
    // we are guaranteed to find `startIndex` and `endIndex`  
    private int countPalindromes(String s, char ch){
        int startIndex = 0;
        int endIndex = s.length() - 1;

        while(s.charAt(startIndex) != ch){
            startIndex++;
        }

        while(s.charAt(endIndex) != ch){
            endIndex--;
        }

        return findUniqueCharactersInString(s.substring(startIndex + 1, endIndex));
    }

    // find no. of unique characters in the given substring
    public int findUniqueCharactersInString(String str){
        int strLen = str.length();
        Set<Character> hs = new HashSet<>();

        for(int i = 0; i < strLen; i++){
            hs.add(str.charAt(i));
        }

        return hs.size();
    }
}