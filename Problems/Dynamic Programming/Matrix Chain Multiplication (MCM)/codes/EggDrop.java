import java.util.Arrays;

public class EggDrop {
    public static void main(String[] args) {
        int n = 2, f = 10; // expected output: 4
        System.out.println(eggDrop(n, f));
    }

    private static int eggDrop(int n, int f) {
        // return solve(n, f);
        int[][] dp = new int[n + 1][f + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // return solveMemo(n, f, dp);
        return solveMemoOpt(n, f, dp);
    }

    // n->e->no. of eggs | f->no. of floors
    static int solveMemoOpt(int e, int f, int[][] dp) {
        // if 0 floors, means 0 ways to check
        // if 1 floor, check by dropping an egg, if it breaks critical floor is 0th
        // floor
        // if it doesn't break, critical floor is 1st floor
        // f = 0 => 0 ways, and f = 1 => 1 ways!
        if (f == 0 || f == 1) {
            return f;
        }

        // if only 1 egg, in worst case, we need to check dropping the egg from
        // 1st floor to fth floor, i.e. `f` ways!
        if (e == 1) {
            return f;
        }

        // if no egg, we can't find the critical floor, return 0 ways
        if (e == 0) {
            return 0;
        }

        if (dp[e][f] != -1) {
            return dp[e][f];
        }

        int minMovesInWorstCase = Integer.MAX_VALUE;

        for (int k = 1; k <= f; k++) {
            int eggBreak;
            int eggNotBreak;

            if (dp[e - 1][k - 1] != -1) {
                eggBreak = dp[e - 1][k - 1];
            } else {
                eggBreak = solveMemoOpt(e - 1, k - 1, dp);
                dp[e - 1][k - 1] = eggBreak;
            }

            if (dp[e][f - k] != -1) {
                eggNotBreak = dp[e][f - k];
            } else {
                eggNotBreak = solveMemoOpt(e, f - k, dp);
                dp[e][f - k] = eggNotBreak;
            }

            int tempAns = 1 + Math.max(eggBreak, eggNotBreak);

            minMovesInWorstCase = Math.min(minMovesInWorstCase, tempAns);
        }
        return dp[e][f] = minMovesInWorstCase;
    }

    // n->e->no. of eggs | f->no. of floors
    private static int solveMemo(int e, int f, int[][] dp) {
        // if 0 floors, means 0 ways to check
        // if 1 floor, check by dropping an egg, if it breaks crtitcal floor is 0th
        // floor
        // if it doesn't break, criticl floor is 1st floor
        // f = 0 => 0 ways, and f = 1 => 1 ways!
        if (f == 0 || f == 1) {
            return f;
        }

        // if only 1 egg, in worst case, we need to check dropping the egg from
        // 1st floor to fth floor, i.e. `f` ways!
        if (e == 1) {
            return f;
        }

        // if no egg, we can't find the critical floor, return 0 ways
        if (e == 0) {
            return 0;
        }

        if (dp[e][f] != -1) {
            return dp[e][f];
        }

        int minMovesInWorstCase = Integer.MAX_VALUE;

        for (int k = 1; k <= f; k++) {
            // egg breaks, but we need critical floor, we check below floors, `k-1`
            // but we lost 1 egg, hence `e-1`
            int eggBreak = solveMemo(e - 1, k - 1, dp);

            // egg did not break, to find critical floor, check above floors
            // if you are on kth floor, no. of above floors = `f-k`
            // we did not lose any egg, hence `e` will be `e` only.
            int eggNotBreak = solveMemo(e, f - k, dp);

            // we need minimum no. of moves in "worst case scenario"
            // hence we take maximum(worst case) of the two
            // situations(egg break and egg not break)
            // `1 attempt` + add remaining!
            int tempAns = 1 + Math.max(eggBreak, eggNotBreak);

            minMovesInWorstCase = Math.min(minMovesInWorstCase, tempAns);
        }
        return dp[e][f] = minMovesInWorstCase;
    }

    private static int solve(int e, int f) {
        // if 0 floors, means 0 ways to check
        // if 1 floor, check by dropping an egg, if it breaks critical floor is 0th
        // floor
        // if it doesn't break, critical floor is 1st floor
        // f = 0 => 0 ways, and f = 1 => 1 ways!
        if (f == 0 || f == 1) {
            return f;
        }

        // if only 1 egg, in worst case, we need to check dropping the egg from
        // 1st floor to fth floor, i.e. `f` ways!
        if (e == 1) {
            return f;
        }

        // if no egg, we can't find the critical floor, return 0 ways
        if (e == 0) {
            return 0;
        }

        int minMovesInWorstCase = Integer.MAX_VALUE;

        for (int k = 1; k <= f; k++) {
            // egg breaks, but we need critical floor, we check below floors, `k-1`
            // but we lost 1 egg, hence `e-1`
            int eggBreak = solve(e - 1, k - 1);

            // egg did not break, to find critical floor, check above floors
            // if you are on kth floor, no. of above floors = `f-k`
            // we did not lose any egg, hence `e` will be `e` only.
            int eggNotBreak = solve(e, f - k);

            // we need minimum no. of moves in "worst case scenario"
            // hence we take maximum(worst case) of the two
            // situations(egg break and egg not break)
            // `1 attempt` + add remaining!
            int tempAns = 1 + Math.max(eggBreak, eggNotBreak);

            minMovesInWorstCase = Math.min(minMovesInWorstCase, tempAns);
        }
        return minMovesInWorstCase;
    }

}
