package algorithm.zuoshen.one;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by FrankCooper
 * Date 2019/3/13 0:09
 * Description
 */
public class ZuoShenOne {

    private static ZuoShenOne handler = new ZuoShenOne();


    public int coins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    /**
     * 使用arr[index-N-1]这些面值的钱组成aim，返回总的方法数
     *
     * @param arr
     * @param index
     * @param aim
     * @return
     */
    private int process1(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; arr[index] * i <= aim; i++) {
                res += process1(arr, index + 1, aim - arr[index] * i);
            }
        }
        return res;
    }


    /**
     * 左神P31，最大值减去最小值小于或等于num的子数组的数量
     *
     * @param arr
     * @param num
     * @return
     */
    public int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        int L = 0, R = 0;
        int res = 0;
        while (L < arr.length) {
            while (R < arr.length) {
                //qmin更新进最小值的R
                while (!qmin.isEmpty() && qmin.peekLast() >= arr[R]) {
                    qmin.pollLast();
                }
                qmin.addLast(R);
                //qmax更新进最大值的R
                while (!qmax.isEmpty() && qmax.peekLast() <= arr[R]) {
                    qmax.pollLast();
                }
                qmax.addLast(R);
                //如果当前的qmax中的值减去qmin中的值大于num，其所有的子数组都不会满足条件，break掉
                if (arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num) {
                    break;
                }
                R++;
            }
            //检查qmin的下标是否过期
            if (qmin.peekFirst() == L) {
                qmin.pollFirst();
            }
            //检查qmax的下标是否过期
            if (qmax.peekFirst() == L) {
                qmax.pollFirst();
            }
            res += R - L;
            L++;
        }
        return res;
    }


    /**
     * P355 未排序数组中累加和为给定值的最长子数组
     *
     * @param arr
     * @param k
     * @return
     */
    public int maxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //k-->对应的的sum值，value-->index
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int res = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                res = Math.max(res, i - map.get(sum - k));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return res;
    }

    /**
     * 左神 矩阵最短路径和
     *
     * @param matrix
     * @param i
     * @param j
     * @return
     */
    public int walk(int[][] matrix, int i, int j) {
        if (i == matrix.length - 1 && j == matrix[0].length - 1) {
            return matrix[i][j];
        }
        if (i == matrix.length - 1) {
            return matrix[i][j] + walk(matrix, i, j + 1);
        }
        if (j == matrix[0].length - 1) {
            return matrix[i][j] + walk(matrix, i + 1, j);
        }
        int right = matrix[i][j] + walk(matrix, i, j + 1);
        int down = matrix[i][j] + walk(matrix, i + 1, j);
        return matrix[i][j] + Math.min(right, down);
    }


    /**
     * 回文，生成DP
     *
     * @param chas
     * @return
     */
    private int[][] getDp(char[] chas) {
        int n = chas.length;
        int[][] dp = new int[n][n];
        for (int j = 1; j < n; j++) {
            dp[j - 1][j] = chas[j - 1] == chas[j] ? 0 : 1;
            for (int i = j - 2; i > -1; i--) {
                if (chas[i] == chas[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp;
    }

    /**
     * 214. 最短回文串 Hard
     *
     * @param s
     * @return 注意题目要求在前面添加
     */
    public String shortestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] chas = s.toCharArray();
        int[][] dp = getDp(chas);
        int n = chas.length;
        char[] resChar = new char[n + dp[0][n - 1]];
        int l = 0, r = n - 1;
        int resL = 0, resR = resChar.length - 1;
        while (l <= r) {
            if (chas[l] == chas[r]) {
                resChar[resL++] = chas[l++];
                resChar[resR--] = chas[r--];
            } else if (dp[l][r - 1] <= dp[l + 1][r]) {
                resChar[resL++] = chas[r];
                resChar[resR--] = chas[r--];
            } else {
                resChar[resL++] = chas[l];
                resChar[resR--] = chas[l++];
            }
        }
        return String.valueOf(resChar);
    }


    /**
     * 72. 编辑距离 Hard P217
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        char[] chas1 = word1.toCharArray();
        char[] chas2 = word2.toCharArray();
        int row = chas1.length + 1;
        int col = chas2.length + 1;
        int[][] dp = new int[row][col];
        //init first col
        for (int i = 0; i < row; i++) {
            dp[i][0] = i;
        }
        //init first row
        for (int j = 0; j < col; j++) {
            dp[0][j] = j;
        }
        //general case
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (chas1[i - 1] == chas2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
            }
        }
        return dp[row - 1][col - 1];
    }


    public static ReturnType process(Node head) {
        //basecase
        if (head == null) {
            return new ReturnType(0, 0);
        }
        ReturnType leftReturnType = process(head.left);
        ReturnType rightReturnType = process(head.right);

        int includeHeadDistance = leftReturnType.maxDistance + 1 + rightReturnType.maxDistance;
        int p1 = leftReturnType.maxDistance;
        int p2 = rightReturnType.maxDistance;

        int resultDistance = Math.max(Math.max(p1, p2), includeHeadDistance);
        int hitself = Math.max(leftReturnType.h, rightReturnType.h) + 1;//h 是决策中需要的信息，树的深度
        return new ReturnType(resultDistance, hitself);
    }

    /**
     * P169 二叉树节点间的最大距离问题
     *
     * @param head
     * @return
     */
    public static int getMaxDistance(Node head) {
        return process(head).maxDistance;
    }


    /**
     * 最大活跃度
     *
     * @param head
     * @return
     */
    public static int getMaxHuo(HuoNode head) {
        return Math.max(huoProcess(head).laiHuo, huoProcess(head).buLaiHuo);
    }


    public static HuoReturnData huoProcess(HuoNode head) {
        int laiHuo = head.huo;
        int bulaiHuo = 0;
        for (int i = 0; i < head.nexts.size(); i++) {
            HuoNode next = head.nexts.get(i);
            HuoReturnData nextData = huoProcess(next);
            laiHuo += nextData.buLaiHuo;
            bulaiHuo += Math.max(nextData.laiHuo, nextData.buLaiHuo);
        }
        return new HuoReturnData(laiHuo, bulaiHuo);
    }

    /**
     * Manacher Main
     *
     * @param chas
     * @return
     */
    private int[] maxLcpsLength(char[] chas) {
        int[] pArr = new int[chas.length];
        int pR = -1;
        int index = 0;
        int max = Integer.MIN_VALUE;
        int center = 0;
        for (int i = 0; i < chas.length; i++) {
            pArr[i] = pR > i ? Math.min(pR - i, pArr[2 * index - i]) : 1;
            while (i + pArr[i] < chas.length && i - pArr[i] > -1) {
                if (chas[i + pArr[i]] == chas[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                index = i;
            }
            if (pArr[i] > max) {
                max = pArr[i];
                center = i;
            }
        }
        return new int[]{center, max - 1};
    }

    /**
     * 生成带符号的字符char[] bcbaa-->#b#c#b#a#a#
     *
     * @param str
     * @return
     */
    private char[] manacherString(String str) {
        char[] chas = str.toCharArray();
        char[] resChar = new char[chas.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < resChar.length; i++) {
            resChar[i] = (i & 1) == 0 ? '#' : chas[index++];
        }
        return resChar;
    }


    public String longestPalindrome(String str) {

        char[] chas = manacherString(str);
        //返回的参数0:最大回文的中心点，1：最大回文半径
        int[] params = maxLcpsLength(chas);
        StringBuilder sb = new StringBuilder();
        for (int i = params[0] - params[1]; i <= params[0] + params[1]; i++) {
            if (chas[i] == '#') {
                continue;
            }
            sb.append(chas[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {

//        int[] arr = {5, 10, 25};
//        handler.coins1(arr, 15);
//        int[] arr = {1, 2, 3, 3};
//        int k = 6;
//        handler.maxLength(arr, k);
//        int[][] matrix = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
//        handler.getDp("ABCDABA".toCharArray());

//        handler.shortestPalindrome("ABCDABA");
//        handler.shortestPalindrome("abb");
//        String word1 = "horse", word2 = "ros";
//        System.out.println(handler.minDistance(word1, word2));
//        System.out.println(String.valueOf(handler.maxLcpsLength("babad")));
        handler.longestPalindrome("babad");

    }

}

class ReturnType {
    public int maxDistance;
    public int h;


    public ReturnType(int maxDistance, int h) {
        this.maxDistance = maxDistance;
        this.h = h;
    }
}

class Node {
    int val;
    Node left;
    Node right;

    Node(int x) {
        val = x;
    }
}

class HuoNode {
    public int huo;
    public List<HuoNode> nexts;

    public HuoNode(int huo) {
        this.huo = huo;
        this.nexts = new ArrayList<>();
    }
}

class HuoReturnData {
    public int laiHuo;
    public int buLaiHuo;

    public HuoReturnData(int laiHuo, int buLaiHuo) {
        this.laiHuo = laiHuo;
        this.buLaiHuo = buLaiHuo;
    }
}
