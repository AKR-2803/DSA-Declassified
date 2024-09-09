import java.util.Map;
import java.util.HashMap;

public class ScrambledString {
    public static void main(String[] args) {
        String S1 = "great";
        String S2 = "rgate"; // expected output: true

        System.out.println(isScramble(S1, S2));
    }

    private static boolean isScramble(String S1, String S2) {
        return solveMemo(S1, S2, new HashMap<>());
    }

    private static boolean solveMemo(String a, String b, Map<String, Boolean> mp) {
        if (a.compareTo(b) == 0) {
            return true;
        }

        if (a.length() <= 1) {
            return false;
        }

        String mapKey = a + " " + b;

        if (mp.containsKey(mapKey)) {
            return mp.get(mapKey);
        }

        int n = a.length();
        boolean flag = false;

        for (int i = 1; i <= n - 1; i++) {
            boolean c1 = solveMemo(a.substring(0, i), b.substring(0, i), mp)
                    && solveMemo(a.substring(i, n), b.substring(i, n), mp);
            boolean c2 = solveMemo(a.substring(0, i), b.substring(n - i, n), mp)
                    && solveMemo(a.substring(i, n), b.substring(0, n - i), mp);

            if (c1 || c2) {
                flag = true;
            }
        }

        mp.put(mapKey, flag);
        return flag;
    }
}