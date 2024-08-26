public class Knapsack01 {
    static int knapSack(int W, int wt[], int val[], int n) {
        // return knapSackRec(wt, val, W, n);
        // int[][] dp = new int[n + 1][W + 1];

        // for(int[] row : dp){
        // Arrays.fill(row, -1);
        // }
        // return knapSackMemo(wt, val, W, n, dp);

        int[][] dp2 = new int[n + 1][W + 1];
        return knapSackTab(wt, val, W, n, dp2);
    }

    // Tabulation
    // replace `n` -> `i` and `W` -> `j`
    static int knapSackTab(int[] wt, int[] val, int W, int n, int[][] dp) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {

                if (wt[i - 1] <= j) {
                    dp[i][j] = Math.max(val[i - 1] + dp[i - 1][j - wt[i - 1]], dp[i - 1][j]);
                }

                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][W];
    }

    // Memoization
    static int knapSackMemo(int[] wt, int[] val, int W, int n, int[][] dp) {
        // base condition
        // smallest input [n == 0] [W == 0]
        if (n == 0 || W == 0) {
            return 0;
        }

        if (dp[n][W] != -1) {
            return dp[n][W];
        }

        if (wt[n - 1] <= W) {
            // take it
            int profitWhenITookIt = val[n - 1] + knapSackMemo(wt, val, W - wt[n - 1], n - 1, dp);

            // or leave it
            int profitWhenILeftIt = knapSackMemo(wt, val, W, n - 1, dp);

            // but get the maximum profit from whatever you do
            return dp[n][W] = Math.max(profitWhenITookIt, profitWhenILeftIt);
        }

        // Code reached here means => [wt[n - 1] > W]
        // i.e. the weight of the item is greater than the current bag capacity
        return dp[n][W] = knapSackMemo(wt, val, W, n - 1, dp);
    }

    // Recursion
    static int knapSackRec(int[] wt, int[] val, int W, int n) {
        // base condition
        // smallest input [n == 0] [W == 0]
        if (n == 0 || W == 0) {
            return 0;
        }

        if (wt[n - 1] <= W) {
            // take it
            int itemTaken = val[n - 1] + knapSackRec(wt, val, W - wt[n - 1], n - 1);

            // or leave it
            int itemLeft = knapSackRec(wt, val, W, n - 1);

            // but get the maximum profit from whatever you do
            return Math.max(itemTaken, itemLeft);
        }

        // Code reached here means => [wt[n - 1] > W]
        // i.e. the weight of the item is greater than the current bag capacity
        return knapSackRec(wt, val, W, n - 1);
    }
}