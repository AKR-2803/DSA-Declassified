public class EqualSubsetSum {
    public static void main(String[] args) {
        int[] arr = { 3, 34, 4, 12, 5, 2 };
        int n = arr.length;
        int sum = 0;

        // following 4 lines are the only change required from
        // SubsetSum.java
        // [https://github.com/AKR-2803/DSA-Declassified/tree/main/Problems/Dynamic%20Programming/0-1%20Knapsack/Subset%20Sum#subset-sum-link]
        for (int i : arr) {
            sum += i;
        }
        sum = sum / 2;

        // Recursion
        // System.out.println(isSumRec(n, arr, sum));

        // Tabulation
        // boolean[][] dp = new boolean[N + 1][sum + 1];
        // return isSumTab(N, arr, sum, dp);
    }

    // Recursion
    static boolean isSumRec(int N, int[] arr, int sum) {
        if (sum == 0) {
            return true;
        }

        if (N == 0) {
            return false;
        }

        if (arr[N - 1] <= sum) {
            boolean takeElement = isSumRec(N - 1, arr, sum - arr[N - 1]);
            boolean dontTakeElement = isSumRec(N - 1, arr, sum);

            return takeElement || dontTakeElement;
        }
        return isSumRec(N - 1, arr, sum);
    }

    // Tabulation
    static boolean isSumTab(int N, int[] arr, int sum, boolean[][] dp) {
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= sum; j++) {
                if (i == 0) {
                    dp[i][j] = false;
                }
                if (j == 0) {
                    dp[i][j] = true;
                }
            }
        }

        // i -> N (number of elements)
        // j -> `sum` required
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[N][sum];
    }
}