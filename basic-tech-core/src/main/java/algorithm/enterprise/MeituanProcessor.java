package algorithm.enterprise;


import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/8/13 8:27
 * @description: 关联链接：https://blog.csdn.net/wat1r/article/details/119669619
 */

public class MeituanProcessor {

    //小美的用户名
    static class _1st {
        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            int T = scanner.nextInt();
            String[] arr = new String[T];
            for (int i = 0; i < T; i++) {
                arr[i] = scanner.next();
            }
            String regex = "^([a-zA-Z])([a-zA-Z]*)([0-9]{1,})([a-zA-Z0-9]*)$";
//        String regex = "^([a-zA-Z])([a-zA-Z]*)([0-9]+)([a-zA-Z]*)$";
//        String regex = "^([a-zA-Z])([a-zA-Z]*)([0-9]+)+([a-zA-Z0-9]*)$";
            for (int i = 0; i < T; i++) {
                System.out.printf("%s\n", arr[i]);
                boolean res = Pattern.matches(regex, arr[i]);
                if (res) System.out.println("Accept");
                else System.out.println("Wrong");
            }

        }

    }

    //小美的仓库整理
    static class _2nd {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int[] weights = new int[n];
            for (int i = 0; i < n; i++) {
                weights[i] = scanner.nextInt();
            }
            int[] orders = new int[n];
            for (int i = 0; i < n; i++) {
                orders[i] = scanner.nextInt();
            }
            //process

        }
    }


    static class _9th {

        final static int MOD = 998244353;

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt(), m = sc.nextInt();
            //f[i][j]表示第i个装饰物 价格为j时的方案总数，对于i-1个装饰物 j，i装饰物价格必须是j的倍数 j*k
            //需要累加组合数
            int[][] f = new int[m + 1][n + 1];
            //第1个装饰物在价格确定后，方案数为
            for (int j = 1; j <= n; j++) {
                f[1][j] = 1;
            }
            for (int i = 2; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    for (int k = 1; k * j <= n; k++) {
                        f[i][j * k] += f[i - 1][j];
                        f[i][j * k] %= MOD;
                    }
                }
            }
            int res = 0;
            for (int j = 1; j <= n; j++) {
                res += f[m][j];
                res %= MOD;
            }
            System.out.printf("%d", res);

        }
    }


    //搭配出售
    static class _11th {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt(), d = sc.nextInt();
            int e = sc.nextInt(), f = sc.nextInt(), g = sc.nextInt();
            List<int[]> list = new ArrayList<int[]>() {{
                add(new int[]{e, a});
                add(new int[]{f, b});
                add(new int[]{g, c});
            }};
            list.sort(((o1, o2) -> o2[0] - o1[0]));
            long res = 0;
            for (int i = 0; i < list.size(); i++) {
                int[] cur = list.get(i);
                int value = cur[0];
                int cnt = Math.min(cur[1], d);
                res += (long) value * cnt;
                d -= cnt;
            }
            System.out.printf("%d", res);
        }
    }


    //偏爱字母
    static class _13th {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            char[] chas = sc.next().toCharArray();
            int sum = 0, res = 0;
            for (char c : chas) {
                if (c == 'E') sum++;
                if (c == 'F') sum--;
                res = Math.max(res, sum);
                sum = Math.max(sum, 0);
            }
            System.out.printf("%d", res);
        }
    }
}
