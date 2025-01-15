class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        // uncomment the approach you want to use
        // return usingSet(A, B);
        // return usingBitSet(A, B);
        return usingFrequencyArray(A, B);
    }

    // approach 1: Using Set
    private int[] usingSet(int[] A, int[] B) {
        int n = A.length;
        int[] C = new int[n]; // result array

        Set<Integer> setA = new HashSet<>(); // track elements seen in A
        Set<Integer> setB = new HashSet<>(); // track elements seen in B

        for (int i = 0; i < n; i++) {
            setA.add(A[i]); // add current element from A to setA
            setB.add(B[i]); // add current element from B to setB

            int common = 0; // counter for common elements

            // count common elements between setA and setB
            for (int elemA : setA) {
                if (setB.contains(elemA)) {
                    common += 1;
                }
            }

            C[i] = common;
        }
        return C; 
    }

    // approach 2: using BitSet
    private int[] usingBitSet(int[] A, int[] B) {
        int n = A.length;
        int[] C = new int[n]; // result array

        // BitSet to track seen elements (size 2*50 + 1), constraints specify max length is 50
        BitSet bs = new BitSet(101); 

        int common = 0; // counter for common elements

        for (int i = 0; i < n; i++) {
            int aa = A[i]; // current element from A
            int bb = B[i]; // current element from B

            // mark A[i] in BitSet (odd index)
            bs.set((2 * aa) - 1);
            if (bs.get(2 * aa)) { // check if B[i] already set
                common++;
            }

            // Mark B[i] in BitSet (even index)
            bs.set(2 * bb);
            if (bs.get((2 * bb) - 1)) { // check if A[i] already set
                common++;
            }

            C[i] = common;
        }
        return C;
    }

    // approach 3: using frequency array
    private int[] usingFrequencyArray(int[] A, int[] B) {
        int n = A.length;
        int[] C = new int[n]; // result array
        int[] freq = new int[n + 1]; // frequency array to track element occurrences

        int common = 0; // counter for common elements

        for (int i = 0; i < n; i++) {
            // increment frequency for A[i]
            freq[A[i]]++;
            if (freq[A[i]] == 2) { // element appears in both arrays
                common++;
            }

            // increment frequency for B[i]
            freq[B[i]]++;
            if (freq[B[i]] == 2) { // element appears in both arrays
                common++;
            }

            C[i] = common;
        }
        return C;
    }
}
