package algorithm.lintcode.one;

import java.util.Arrays;

/**
 * Created by FrankCooper
 * Date 2019/5/30 23:12
 * Description
 */
public class LintCodeExplorerI {
    private static LintCodeExplorerI handler = new LintCodeExplorerI();


    private void reverseString(char[] chas, int from, int to) {
        while (from < to) {
            char temp = chas[from];
            chas[from++] = chas[to];
            chas[to--] = temp;
        }
    }

    /**
     * 字符串旋转
     *
     * @param src 目标字符
     * @param n   字符长度
     * @param m   要旋转的字符的长度 0-m
     * @return
     */
    private String leftRotateString(String src, int n, int m) {
        char[] chas = src.toCharArray();
        reverseString(chas, 0, m - 1);
        reverseString(chas, m, n - 1);
        reverseString(chas, 0, n - 1);
        return Arrays.toString(chas);
    }


    /**
     * 344. 反转字符串 LeetCode Easy
     *
     * @param s
     */
    public void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        int left = 0, right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left++] = s[right];
            s[right--] = temp;
        }
    }


    public static void main(String[] args) {
        String str = "abcdef";
        System.out.println(handler.leftRotateString(str, str.length(), 3));
    }


}
