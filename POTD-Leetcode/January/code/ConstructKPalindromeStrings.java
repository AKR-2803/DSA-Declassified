class Solution {
    public boolean canConstruct(String s, int k) {
        if(k > s.length()){
            return false;
        }
        
        // single character is always a palindrome
        if(k == s.length()){
            return true;
        }

        int[] freq = new int[26];
        
        // update frequency of each character
        for(char ch : s.toCharArray()){
            freq[ch - 'a'] += 1;
        }

        int oddFrequencies = 0;

        // find no. of characters with odd frequencies
        for(int i = 0; i < freq.length; i++){
            if(freq[i] % 2 != 0){
                oddFrequencies += 1;
            }
        }

        return k >= oddFrequencies;
    }
}