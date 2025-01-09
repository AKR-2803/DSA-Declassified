class Solution {
    public int prefixCount(String[] words, String pref) {
        int cnt = 0;
        for(String str : words){
            // `str` contains `pref` as prefix 
            if(str.startsWith(pref)){
                cnt += 1; // increment cnt
            }
        }
        return cnt;
    }
}