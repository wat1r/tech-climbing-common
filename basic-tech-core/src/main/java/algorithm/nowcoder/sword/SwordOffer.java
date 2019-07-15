package algorithm.nowcoder.sword;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by FrankCooper
 * Date 2019/7/14 20:58
 * Description
 */
public class SwordOffer {

    static SwordOffer handler = new SwordOffer();


    public String replaceSpace(StringBuffer str) {
        if (str == null || str.toString() == "") {
            return "";
        }
        char[] chas = str.toString().toCharArray();
        int space = 0;
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] == ' ') {
                space++;
            }
        }

        int dest = chas.length + space * 2;
        char[] res = new char[dest];
        int originalIndex = chas.length - 1;
        int destIndex = dest - 1;
        while (originalIndex < dest && originalIndex >= 0) {
            if (chas[originalIndex] == ' ') {
                res[destIndex--] = '0';
                res[destIndex--] = '2';
                res[destIndex--] = '%';
            } else {
                res[destIndex--] = chas[originalIndex];
            }
            --originalIndex;
        }
        return String.valueOf(res);
    }


    public int NumberOf1(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n &= n - 1;
        }
        return res;
    }


    public int Fibonacci(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    public int JumpFloor(int target) {
        int[] dp = new int[target + 1];
        dp[0] = 0;
        dp[1] = 1;
        if (target <= 1) return dp[target];
        dp[2] = 2;
        for (int i = 3; i <= target; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[target];
    }


    public double Power(double base, int exponent) {
        if (exponent == 0) return 1;
        if (exponent == 1) return base;
        double res = Power(base, exponent >> 1);
        res *= res;
        if ((exponent % 2) != 0) {
            res *= base;
        }
        return res;
    }


    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> resList = new ArrayList<>();
        Stack<ListNode> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            resList.add(stack.pop().val);
        }
        return resList;
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {


        return null;
    }


    public static void main(String[] args) {

//        handler.replaceSpace(new StringBuffer("We Are Happy"));
//        handler.NumberOf1(9);
        handler.Fibonacci(2);
    }
    //winter


    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


}
