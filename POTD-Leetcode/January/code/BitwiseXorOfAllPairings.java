class Solution {
    public int xorAllNums(int[] nums1, int[] nums2) {
        boolean n1Even = (nums1.length % 2 == 0);
        boolean n2Even = (nums2.length % 2 == 0);

        // both even, return `0`
        if(n1Even && n2Even){
            return 0;
        }

        int xor1 = 0;
        int xor2 = 0;

        // both odd find both
        if(!n1Even && !n2Even){
            for(int num : nums1){
                xor1 ^= num;
            }

            for(int num : nums2){
                xor2 ^= num;
            }
            return (xor1 ^ xor2);
        }

        // nums1 odd, nums2 even
        if(!n1Even){
            xor1 = 0;

            for(int num : nums2){
                xor2 ^= num;
            }
        
            return (xor1 ^ xor2);
        }

        // last case, nums2 odd, nums1 even    
        xor2 = 0;

        for(int num : nums1){
            xor1 ^= num;
        }
    
        return (xor1 ^ xor2);
    }
}