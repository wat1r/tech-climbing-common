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


    /**
     * 剑指offer42 编程之法1.1举一反三
     *
     * @param str
     * @return
     */
    public String reverseSentence(String str) {
        char[] chas = str.toCharArray();
        reverseString(chas, 0, chas.length - 1);
        int begin = 0;
        //读到空格，翻转begin到空格前一个位置的字符（i-1），翻转结束后将空格后一个位置的值（i+1）赋给begin
        for (int i = 1; i < chas.length; i++) {
            if (chas[i] == ' ') {
                reverseString(chas, begin, i - 1);
                begin = i + 1;
            }
        }
        if (begin < chas.length) {
            reverseString(chas, begin, chas.length - 1);
        }
        return String.valueOf(chas);
    }


    /**
     * 151. 翻转字符串里的单词 Medium
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        char[] chas = s.toCharArray();
        reverseString(chas, 0, chas.length - 1);
        int begin = 0;
        String streamStr = String.valueOf(chas);
        String[] arr = streamStr.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            char[] tempChas = arr[i].toCharArray();
            reverseString(tempChas, 0, tempChas.length - 1);
            sb.append(String.valueOf(tempChas));
            if (i != arr.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }




    public static void main(String[] args) {
        String str = "abcdef";
//        System.out.println(handler.leftRotateString(str, str.length(), 3));
//        System.out.println(handler.reverseSentence("I am a student."));
//        System.out.println(handler.reverseWords("a good   example"));
        System.out.println(handler.reverseWords("  hello world!  "));


    }


}
