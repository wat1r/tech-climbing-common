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


    /**
     * 174. 删除链表中倒数第n个节点 LintCode Easy xiaomi
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = head;
        ListNode fast = head;
        while (n-- > 0) {
            fast = fast.next;
        }
        if (fast == null) {
            return head.next;
        }
        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy;
    }


    /**
     * 165. 合并两个排序链表 LintCode Easy  xiaomi
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 != null ? l1 : l2;
        }
        ListNode head = new ListNode(0);
        ListNode node = head;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                node.next = l2;
                l2 = l2.next;
            } else {
                node.next = l1;
                l1 = l1.next;
            }
            node = node.next;
        }
        if (l1 != null) {
            node.next = l1;
        }
        if (l2 != null) {
            node.next = l2;
        }
        return head.next;
    }

    boolean isBalance = true;

    public boolean isBalanced(TreeNode root) {
        getHeight(root);
        return isBalance;
    }


    public int getHeight(TreeNode root) {
        if (!isBalance || root == null) {
            return 0;
        }
        int l = getHeight(root.left);
        int r = getHeight(root.right);
        if (Math.abs(l - r) > 1) {
            isBalance = false;
        }
        return Math.max(l, r) + 1;

    }


    /**
     * binary search
     *
     * @param nums
     * @param target
     * @return
     */
    public int binarySearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int res = Integer.MAX_VALUE;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                while (mid >= 0 && nums[mid] == target) {
                    res = mid;
                    mid--;
                }
                break;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }


    public static void main(String[] args) {

//        handler.climbStairs(3);
//        handler.minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}});
        ListNode l1_1 = new ListNode(1);
        ListNode l1_2 = new ListNode(3);
        ListNode l1_3 = new ListNode(8);
        ListNode l1_4 = new ListNode(11);
        ListNode l1_5 = new ListNode(15);
        l1_1.next = l1_2;
        l1_2.next = l1_3;
        l1_3.next = l1_4;
        l1_4.next = l1_5;
        l1_5.next = null;
        ListNode l2_1 = new ListNode(2);
        l2_1.next = null;
//        handler.mergeTwoLists(l1_1, l2_1);
        handler.binarySearch(new int[]{1, 4, 4, 5, 7, 7, 8, 9, 9, 10}, 1);

        //1->3->8->11->15->null
//        2->null


    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
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

