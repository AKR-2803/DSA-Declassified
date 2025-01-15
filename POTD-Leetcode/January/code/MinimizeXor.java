class Solution {
    public int minimizeXor(int num1, int num2) {
        // count the number of set bits (1's) in `num1` and `num2`
        int setBitsCount1 = Integer.bitCount(num1);
        int setBitsCount2 = Integer.bitCount(num2);

        // calculate the bit difference in the no. of set bits
        int diff = setBitsCount1 - setBitsCount2;

        // case 1: `num1` and `num2` OR `x` have the same number of set bits
        if (diff == 0) {
            return num1; // (`x` XOR `num1`) will be `0` (minimum) when `x` equals `num1`
        }

        // case 2: `num1` has more set bits than `num2` or `x`
        if (diff > 0) {
            // remove extra set bits from `num1` until it has the same no. of set bits as `num2`
            while (diff != 0) {
                num1 = num1 & (num1 - 1);  // clear the rightmost set bit
                diff--; // decrease the bit difference
            }
            return num1; // return num1 with adjusted set bits
        }

        // case 3: else `num1` has less set bits than `num2` or `x`
        while (diff != 0) {
            num1 = num1 | (num1 + 1);   // set the rightmost 0 bit to 1
            diff++; // increase the bit difference
        }
        return num1;
    }
}