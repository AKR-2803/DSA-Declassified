class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        int allxor = 0;
        
        // compute XOR of all values in derived 
        for(int num : derived){
            allxor ^= num;
        }

        return allxor == 0; // if `0` then it can be formed from an `original` array
    }
}