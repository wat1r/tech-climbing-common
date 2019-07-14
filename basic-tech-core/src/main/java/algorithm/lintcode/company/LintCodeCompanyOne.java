package algorithm.lintcode.company;

import basic.callback.one.Li;
import com.alibaba.fastjson.JSON;
import org.apache.poi.ss.formula.functions.Na;

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


    /**
     * @param root
     * @return
     */
    List<List<Integer>> results = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return results;
        }
        helper(root, 0);
        return results;
    }


    public void helper(TreeNode node, int level) {
        if (results.size() == level) {
            results.add(new ArrayList<>());
        }
        results.get(level).add(node.val);
        if (node.left != null) {
            helper(node.left, level + 1);
        }
        if (node.right != null) {
            helper(node.right, level + 1);
        }
    }


    public void sortIntegers2(int[] A) {
        quickSortRecursive(A, 0, A.length - 1);
    }

    private void quickSortRecursive(int[] A, int left, int right) {
        if (A == null || A.length < 2) {
            return;
        }
        if (left < right) {
            int[] partition = partition(A, left, right);
            quickSortRecursive(A, left, partition[0] - 1);
            quickSortRecursive(A, partition[1] + 1, right);
        }

    }

    private int[] partition(int[] A, int left, int right) {
        int less = left - 1;
        int more = right;
        while (left < more) {
            if (A[left] < A[right]) {
                swap(A, ++less, left++);
            } else if (A[left] > A[right]) {
                swap(A, --more, left);
            } else {
                left++;
            }
        }
        swap(A, more, right);
        return new int[]{less + 1, more};
    }

    private void swap(int[] nums, int m, int n) {
        int temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }


    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        //init first column
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }
        //init first row
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                break;
            }
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }


    public int singleNumber(int[] A) {
        int res = A[0];
        for (int i = 1; i < A.length; i++) {
            res ^= A[i];
        }
        return res;
    }


    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            res = Math.max(res, sum);
        }
        return res;
    }

    List<Integer> inOrderList = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return inOrderList;
        }
        inOrder(root);
        return inOrderList;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        inOrderList.add(root.val);
        inOrder(root.right);
    }


    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preOrderList = new ArrayList<>();
        if (root == null) {
            return preOrderList;
        }
        java.util.Stack<TreeNode> stack = new java.util.Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            preOrderList.add(root.val);
            if (root.right != null) {
                stack.add(root.right);
            }
            if (root.left != null) {
                stack.add(root.left);
            }
        }
        return preOrderList;
    }


    public List<Integer> inorderTraversal2nd(TreeNode root) {
        List<Integer> inorderList = new ArrayList<>();
        if (root == null) {
            return inorderList;
        }
        java.util.Stack<TreeNode> stack = new java.util.Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.add(root);
                root = root.left;
            } else {
                root = stack.pop();
                inorderList.add(root.val);
                root = root.right;
            }
        }
        return inorderList;
    }


    public int reverseInteger(int n) {
        int res = 0;
        while (n != 0) {
            int pop = n % 10;
            n /= 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            res = res * 10 + pop;
        }
        return res;
    }

    private int longestLen = 0;

    public int longestConsecutive(TreeNode root) {
        helper(root);
        return longestLen;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        int len = 1;
        if (root.left != null && root.val + 1 == root.left.val) {
            len = Math.max(len, left + 1);
        }
        if (root.right != null && root.val + 1 == root.right.val) {
            len = Math.max(len, right + 1);
        }
        longestLen = Math.max(longestLen, len);
        return len;
    }


    public boolean validPalindrome(String s) {
        return isPalindrome(s, true) || isPalindrome(s, false);
    }

    private boolean isPalindrome(String s, boolean flag) {
        int delete = 0;
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                delete++;
                int i = flag ? left++ : right--;
            }
        }
        return delete < 2;
    }

    public int fibonacci(int n) {
        int[] dp = new int[n + 1];
        if (n == 1) {
            return 0;
        }
        if (n == 2 || n == 3) {
            return 1;
        }
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;
        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    /**
     * 1443. 最长AB子串 LintCode Easy
     * do it again
     *
     * @param S
     * @return
     */
    public int getAns(String S) {
        // Write your code here
        int l = S.length();
        int[] numA = new int[l + 1];
        int[] numB = new int[l + 1];
        int a = 0, b = 0;

        for (int i = 0; i < l; i++) {
            if (S.charAt(i) == 'A') a++;
            else b++;
            numA[i + 1] = a;
            numB[i + 1] = b;
        }

        if (a == 0 || b == 0) return 0;

        int res = a > b ? a : b;
        for (res *= 2; res > 0; res -= 2) {
            for (int i = res; i < l + 1; i++) {
                if (numA[i] - numA[i - res] == numB[i] - numB[i - res]) {
                    return res;
                }
            }
        }

        return 0;
    }


    /**
     * @param n
     * @return
     */
    public int dropEggs(int n) {
        return dropEggsSegment(n, 2);
    }


    /**
     * @param n 楼层数
     * @param m 鸡蛋个数
     * @return
     */
    public int dropEggsSegment(int n, int m) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][1] = 1;
        }
        for (int j = 1; j <= n; j++) {
            dp[1][j] = j;
        }

        for (int i = 2; i <= m; i++) {
            for (int j = 2; j <= n; j++) {
                int res = Integer.MAX_VALUE;
                for (int k = 1; k <= j; k++) {
                    //这是个状态转移方程
                    /**
                     * dp[egg-1][drop-1]+1, 表示在egg个鸡蛋, drop层时, 鸡蛋碎了, 因此将问题变成( egg-1 )个鸡蛋, drop-1层的答案 加1
                     * dp[egg][floor-drop]+1 表示在egg个鸡蛋, drop层时, 鸡蛋没碎, 因此问题转换成( egg )个鸡蛋, floor-drop的答案 加1
                     * 在每一次的判断之后, 问题转成两个子问题, 结果取两个子问题的max值,
                     *
                     * 子问题的构成:
                     * 		一个新的丢鸡蛋问题,
                     * 		鸡蛋数: 碎:egg-1,		没碎:egg
                     * 		楼层数: 碎:drop-1,	  	没碎:floor-drop;
                     *
                     * 最终子问题转换成: 鸡蛋数为1, 楼层不定,	的一个子问题
                     *
                     */
                    /**
                     * 一共有floor种丢法, 每一种丢法的时候, 结果取max值
                     * 但是在floor种丢法之间比较时选择最小值, 这样才能保证答案是最小的
                     * 整个过程是从 1个鸡蛋, 1层楼 的子问题开始, 逐渐向高的层数和鸡数迭代, 即由下向上迭代
                     * 当迭代完成后, dp[i][j] 即表示	i个鸡蛋, j层楼, 的丢鸡蛋问题的解, 所以迭代完之后, dp数组是一个丢鸡蛋问题的解集
                     */
                    int temp = Math.max(dp[i][j - k], dp[i - 1][k - 1]) + 1;
                    res = Math.min(res, temp);
                }
                dp[i][j] = res;
            }
        }
        System.out.println(dp[m][n]);
        return dp[m][n];
    }


    /**
     * 197. 排列序号 Medium
     *
     * @param A
     * @return
     */
    public long permutationIndex(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = A.length;
        for (int i = 0; i < len; i++) {
            int count = 0;
            for (int j = i + 1; j < len; j++) {
                if (A[j] < A[i]) {
                    count++;
                }
            }
            map.put(A[i], count);
        }
        long res = 0;
        for (int i = 0; i < len; i++) {
            long factor = 1;
            for (int j = (len - i - 1); j >= 1; j--) {
                factor *= j;
            }
            res += map.get(A[i]) * factor;
        }
        return res + 1;
    }

    public int[] twoSum(int[] numbers, int target) {
        int[] arr = new int[2];
        if (numbers == null || numbers.length == 0) {
            return arr;
        }
        int[] helper = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(helper);
        int l = 0, r = helper.length - 1;
        int left = 0, right = 0;
        while (l < r) {
            int sum = helper[l] + helper[r];
            if (sum == target) {
                left = helper[l];
                right = helper[r--];
            } else if (sum > target) {
                r--;
            } else {
                l++;
            }
        }
        int m = -1, n = -1;
        for (int i = 0; i < numbers.length; i++) {
            if (left == numbers[i] && m == -1) {
                m = i;
            } else if (right == numbers[i] && n == -1) {
                n = i;
            }

        }
        arr[0] = m < n ? m : n;
        arr[1] = m < n ? n : m;
        return arr;
    }


    /**
     * 100. 删除排序数组中的重复数字 LintCode Easy
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int slow = 0, fast = 1;
        while (fast < nums.length) {
            if (nums[slow] == nums[fast]) {
                fast++;
            } else {
                swap(nums, ++slow, fast++);
            }
        }
        return slow + 1;
    }


    /**
     * @param head
     * @param n
     * @return
     */
    public ListNode nthToLast(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        int i = 0;
        while (i++ < n) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    public ListNode reverse(ListNode head) {
        ListNode curr = head, next, pre = null;
        while (curr != null) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int res = 0;
        for (int price : prices) {
            minPrice = Math.min(price, minPrice);
            res = Math.max(res, price - minPrice);
        }
        return res;
    }

    public int maxProfitII(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }

    public int maxProfitIII(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int s1 = -prices[0], s2 = Integer.MIN_VALUE, s3 = Integer.MIN_VALUE, s4 = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; ++i) {
            s1 = Math.max(s1, -prices[i]);
            s2 = Math.max(s2, s1 + prices[i]);
            s3 = Math.max(s3, s2 - prices[i]);
            s4 = Math.max(s4, s3 + prices[i]);
        }
        return Math.max(0, s4);
    }


    public int mountainSequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(res, nums[i]);
        }
        return res;

    }


    public char[] reverseWords(char[] chas) {
        reverseWord(chas);
        String s = String.valueOf(chas);
        String[] arr = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            char[] tempChas = arr[i].toCharArray();
            reverseWord(tempChas);
            sb.append(tempChas);
            if (i != arr.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString().toCharArray();
    }

    public void reverseWord(char[] chas) {
        int l = 0, r = chas.length - 1;
        while (l < r) {
            swap(chas, l++, r--);
        }
    }

    private void swap(char[] chas, int m, int n) {
        char temp = chas[m];
        chas[m] = chas[n];
        chas[n] = temp;
    }

    public void heapify(int[] A) {
        int size = A.length;
        swap(A, 0, --size);
        while (size > 0) {
            heapifySegment(A, 0, size);
            swap(A, 0, --size);
        }

    }

    private void heapifySegment(int[] A, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            int right = left + 1;
            int largest = right < size && A[left] < A[right] ? right : left;
            largest = A[largest] > A[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(A, largest, index);
            index = largest;
            left = 2 * index + 1;
        }

    }


    public int[] optimalUtilization(int[] A, int[] B, int K) {
        int[] arr = new int[2];
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return new int[]{};
        }
        int m = 0, n = B.length - 1;
        int minDiff = Integer.MAX_VALUE;
        while (m < A.length && n >= 0) {
            while (m < A.length && m > 0 && A[m] == A[m - 1]) {
                m++;
            }
            while (n > 0 && n < B.length && B[n] == B[n - 1]) {
                n--;
            }
            int sum = A[m] + B[n];
            if (sum > K) {
                n--;
            } else if (sum == K) {
                arr[0] = m;
                arr[1] = n;
                return arr;
            } else {
                if (minDiff > (K - sum)) {
                    arr[0] = m;
                    arr[1] = n;
                    minDiff = (K - sum);
                }
                m++;
            }
        }
        return arr;
    }

    /**
     * 416. 分割等和子集 LeetCode Medium 01 背包问题
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int n = 0;
        for (int num : nums) {
            n += num;
        }
        if (n % 2 == 1) {
            return false;
        }
        int m = nums.length;
        boolean[][] dp = new boolean[m][n + 1];
        for (int i = 0; i < m; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i] >= 0) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[m - 1][n / 2];
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
//        handler.binarySearch(new int[]{1, 4, 4, 5, 7, 7, 8, 9, 9, 10}, 1);
//        int[] A = {3, 2, 1, 4, 5};
//        handler.sortIntegers2(A);
//        System.out.println(JSON.toJSONString(A));
//        handler.reverseInteger(123);
//        handler.validPalindrome("aba");
        //1->3->8->11->15->null
//        2->null
//        handler.fibonacci(1);
//        handler.getAns("ABAAABBBA");
//        handler.dropEggs(100000);
//        handler.permutationIndex(new int[]{4, 2, 1});
//        handler.twoSum(new int[]{0, 4, 3, 0}, 0);
//        handler.removeDuplicates(new int[]{-10, 0, 1, 2, 3});
//3->2->1->5->null, n = 2
        ListNode k1 = new ListNode(3);
        ListNode k2 = new ListNode(2);
        ListNode k3 = new ListNode(1);
        ListNode k4 = new ListNode(5);
        k1.next = k2;
        k2.next = k3;
        k3.next = k4;
        k4.next = null;
//        handler.nthToLast(k1,2);

//        handler.maxProfitIII(new int[]{3, 3, 5, 0, 0, 3, 1, 4});
//        handler.reverseWords("the blue sky".toCharArray());
//        handler.heapify(new int[]{2, 3, 5, 7, 1});
//        handler.optimalUtilization(new int[]{1, 1, 3, 3, 6, 11, 12, 14, 15, 18}, new int[]{5, 5, 9, 12, 18}, 9);
        handler.canPartition(new int[]{1, 2, 3, 5});

        System.out.println("end");
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

