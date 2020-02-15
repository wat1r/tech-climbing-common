package algorithm.leetcode.case2;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2019/2/25 23:46
 * Description
 */
public class Main {


    public static void main(String[] args) {
        Main handler = new Main();
        Scanner in = new Scanner(System.in);
//        System.out.println(handler.getMaxMethons(in.next()));
        System.out.println(handler.numDecodings(in.next()));


    }


    public int getMaxMethons(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            if (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6')) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }


    public int numDecodings(String s) {
        char[] chas = s.toCharArray();
        int n = chas.length;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = chas[0] != '0' ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            int oneDigit = chas[i - 1] - '0';
            if (oneDigit >= 1 && oneDigit <= 9) {
                dp[i] += dp[i - 1];
            }
            int twoDigit = (chas[i - 1] - '0') + 10 * (chas[i - 2] - '0');
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

}

