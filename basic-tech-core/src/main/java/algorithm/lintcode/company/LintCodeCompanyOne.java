package algorithm.lintcode.company;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2019/7/7 18:29
 * Description
 */
public class LintCodeCompanyOne {
    private static LintCodeCompanyOne handler = new LintCodeCompanyOne();


    /**
     * JD 111. 爬楼梯 Easy
     */
    public int climbStairs(int n) {
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    /**
     * JD 110. 最小路径和 Easy
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * 487. 姓名去重    NetEase Easy
     *
     * @param names
     * @return
     */
    public List<String> nameDeduplication(String[] names) {
        List<String> resList = new ArrayList<>();
        if (names == null || names.length == 0) {
            return resList;
        }
        Set<String> set = new HashSet<>();
        for (String name : names) {
            set.add(name.toLowerCase());
        }
        resList = new ArrayList<>(set);
        return resList;
    }


    public static void main(String[] args) {

//        handler.climbStairs(3);
        handler.minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}});


    }


    static class Stack {

        private Queue<Integer> data = new LinkedList<>();
        private Queue<Integer> help = new LinkedList<>();

        /*
         * @param x: An integer
         * @return: nothing
         */
        public void push(int x) {
            data.add(x);
        }

        /*
         * @return: nothing
         */
        public void pop() {
            if (data.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            while (data.size() > 1) {
                help.add(data.poll());
            }
            int res = data.poll();
            swap();
        }

        /*
         * @return: An integer
         */
        public int top() {
            if (data.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            while (data.size() > 1) {
                help.add(data.poll());
            }
            int res = data.poll();
            help.add(res);
            swap();
            return res;
        }

        /*
         * @return: True if the stack is empty
         */
        public boolean isEmpty() {
            return data.size() == 0;
        }

        private void swap() {
            Queue<Integer> temp = data;
            data = help;
            help = temp;
        }


    }


}

