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

}
