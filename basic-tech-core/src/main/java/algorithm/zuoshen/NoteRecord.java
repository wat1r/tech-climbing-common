package algorithm.zuoshen;

import java.util.Stack;

/**
 * Created by FrankCooper
 * Date 2019/7/14 11:37
 * Description
 */
public class NoteRecord {
    static NoteRecord handler = new NoteRecord();

    public static void main(String[] args) {

//        handler.getNextGreaterNum(new int[]{2, 1, 2, 4, 3});
        handler.getNextGreaterElements(new int[]{2, 1, 2, 4, 3});
//        handler.cycle();
    }

    private int[] getNextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }
            res[i % n] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % n]);
        }
        return res;
    }


    private void cycle() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        int n = arr.length, index = 0;
        while (true) {
            System.out.println(arr[index % n]);
            index++;
        }

    }

    private int[] getTemperature(int[] T) {
        int n = T.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && T[stack.peek()] <= T[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek();
            stack.push(i);
        }
        return res;
    }


    private int[] getNextGreaterNum(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {//stack不为空且栈顶元素比新来的元素小，弹出栈顶元素
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();//栈是空时，说明后面没有比当前元素nums[i]小的
            stack.push(nums[i]);//当前元素进栈
        }

        return res;
    }


    private static void test1(int[] w, int[] c, int n, int V) {
        int[][] dp = new int[n + 1][V + 1];
        for (int i = 1; i <= n; i++) {
            for (int v = w[i]; v <= V; v++) {
                dp[i][v] = Math.max(dp[i - 1][v], dp[i - 1][v - w[i]] + c[i]);
            }
        }
    }


    private static void test2(int[] w, int[] c, int n, int V) {
        int[] dp = new int[V + 1];
        for (int i = 1; i <= n; i++) {
            for (int v = V; v > -w[i]; v--) {
                dp[v] = Math.max(dp[v], dp[v - w[i]] + c[i]);
            }
        }
    }


}
