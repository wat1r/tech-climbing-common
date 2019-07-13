package algorithm.leetcode.case1;

import algorithm.leetcode.one.LeetCodeOne;
import algorithm.leetcode.two.LeetCodeClassification;
import org.omg.CORBA.MARSHAL;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2019/3/10 15:13
 * Description 练习中出现的重复题目，再次联系
 */
public class RepeativeCaseI {

    private static RepeativeCaseI handler = new RepeativeCaseI();


    /**
     * 15. 三数之和 Medium
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        if (nums == null || nums.length < 2) {
            return resList;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; ) {
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[start] + nums[i] + nums[end];
                if (sum == 0) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[start]);
                    list.add(nums[i]);
                    list.add(nums[end]);
                    start++;
                    end--;
                    resList.add(list);
                    while (nums[end] == nums[end + 1] && start < end) {
                        end--;
                    }
                    while (nums[start] == nums[start - 1] && start < end) {
                        start++;
                    }
                } else if (sum > 0) {
                    end--;
                    while (nums[end] == nums[end + 1] && start < end) {
                        end--;
                    }
                } else {
                    start++;
                    while (nums[start] == nums[start - 1] && start < end) {
                        start++;
                    }
                }
            }
            i++;
            while (nums[i] == nums[i - 1] && i < nums.length - 2) {
                i++;
            }
        }
        return resList;
    }


    /**
     * 49. 字母异位词分组 Medium
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> resList = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return resList;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chas = str.toCharArray();
            Arrays.sort(chas);
            String key = String.valueOf(chas);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }
        resList = new ArrayList<>(map.values());
        return resList;
    }


    /**
     * 3. 无重复字符的最长子串 Medium
     *
     * @param s
     * @return two pointers + hash
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int slow = 0, fast = 0, len = s.length();
        int res = 0;
        while (slow < len && fast < len) {
            //快指针不在set说明，fast所在的下标到slow所在的值之间没有重复值
            if (!set.contains(s.charAt(fast))) {
                set.add(s.charAt(fast++));
                res = Math.max(res, fast - slow);
            } else {
                set.remove(s.charAt(slow++));
            }
        }
        return res;
    }


    /**
     * 5. 最长回文子串 Medium
     *
     * @param s
     * @return 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        return null;
    }


    /**
     * 300. 最长上升子序列 Medium
     *
     * @param nums
     * @return #### DP, double for loop, O(n^2)
     * - 找subsequence: 不需要continous, 可以skip candidate
     * - 考虑nums[i]结尾的时候, 在[0, i), dp[i - 1] 里count有多少小于nums[i]
     * - dp[i]: 到i为止 (对于所有 j in [0, i], 记录max length of increasing subsequence
     * - max需要在全局维护: nums是无序的, nums[i]也可能是一个很小的值, 所以末尾dp[i]并不是全局的max, 而只是对于nums[i]的max.
     * - 正因此, 每个nums[i]都要和每个nums[j] 作比较, j < i.
     * - dp[i] = Maht.max(dp[i], dp[j] + 1); j = [0 , i - 1]
     * - 时间复杂度  O(n^2)
     */
    public int lengthOfLIS(int[] nums) {
        //dp[i]: 到i为止 (对于所有 j in [0, i], 记录max length of increasing subsequence
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                //i 位置的数与[0,i]位置之间的数比较，如果大于进逻辑
                if (nums[i] > nums[j]) {
                    //等于dp[i]或者dp[j] + 1（j对应的值比i小）的最大值
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    /**
     * 322. 零钱兑换 Medium
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        //dp[i] 表示使用coins的组成i这个amount值所需要的最少硬币数
        int[] dp = new int[amount + 1];
        dp[0] = 0;//初始化
        for (int i = 1; i <= amount; i++) {
            //一个flag
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                //首先i这个amount需要大于当前拿到的coin，使用当前coin一个dp[i - coin]可以被coins组成，也就是dp[i - coin]!=-1
                if (i >= coin && dp[i - coin] != -1 && coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
            //如果dp[i] ==MAX 说明不能使用coins组成i这个amount
            if (dp[i] == Integer.MAX_VALUE) {
                dp[i] = -1;
            }

        }
        return dp[amount];
    }


    /**
     * 94. 二叉树的中序遍历 Medium
     *
     * @param head
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode head) {
        List<Integer> resList = new ArrayList<>();
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    resList.add(head.val);
                    head = head.right;
                }
            }
        }
        return resList;
    }


    /**
     * 144. 二叉树的前序遍历 Meidum
     *
     * @param root
     * @return 非递归方式前序遍历
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.add(root);
            while (!stack.isEmpty()) {
                root = stack.pop();
                resList.add(root.val);
                if (root.right != null) {
                    stack.push(root.right);
                }
                if (root.left != null) {
                    stack.push(root.left);
                }
            }

        }
        return resList;
    }


    /**
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSumII(int[] numbers, int target) {
        if (numbers == null || numbers.length <= 1) {
            return null;
        }
        int[] resArr = new int[2];
        int l = 0, r = numbers.length - 1;
        int sum = 0;
        while (l < r) {
            sum = numbers[l] + numbers[r];
            if (sum == target) {
                resArr[0] = l + 1;
                resArr[1] = r + 1;
                return resArr;
            } else if (sum > target) {
                r--;
            } else {
                l++;
            }
        }
        return resArr;
    }

    /**
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }


    public ListNode reverseList(ListNode head) {
        ListNode cur = head, pre = null, next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode head = new ListNode(0);
        ListNode node = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
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


    /**
     * 141. 环形链表 Easy
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow == fast;
    }


    /**
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<>();
        if (root == null) {
            return resList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                levelList.add(curNode.val);
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            resList.add(levelList);
        }
        return resList;
    }


    /**
     * 137. 只出现一次的数字 II Medium
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            b = (b ^ num) & ~a;
            a = (a ^ num) & ~b;
            System.out.println(String.format("num:%s####b:%s####a:%s", num, Integer.toBinaryString(b), Integer.toBinaryString(a)));
        }
        return b;
    }

    /**
     * 104. 二叉树的最大深度 Easy
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    /**
     * 24. 两两交换链表中的节点 Medium
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        //终止条件：链表只剩一个节点或者没节点了，没得交换了。返回的是已经处理好的链表
        if (head == null || head.next == null) {
            return head;
        }
        //一共三个节点:head, node, swapPairs(node.next)
        //下面的任务便是交换这3个节点中的前两个节点
        ListNode node = head.next;
        head.next = swapPairs(node.next);
        node.next = head;
        //根据第二步：返回给上一级的是当前已经完成交换后，即处理好了的链表部分
        return node;
    }


    /**
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return isBinaryTree(root).isB;
    }

    private ReturnNode isBinaryTree(TreeNode root) {
        if (root == null) {
            return new ReturnNode(0, true);
        }
        ReturnNode left = isBinaryTree(root.left);
        ReturnNode right = isBinaryTree(root.right);
        if (!left.isB || !right.isB) {
            return new ReturnNode(0, false);
        }
        if (Math.abs(left.depth - right.depth) > 1) {
            return new ReturnNode(0, false);
        }
        return new ReturnNode(Math.max(left.depth, right.depth) + 1, true);
    }


    /**
     * 264. 丑数 II Medium
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        PriorityQueue<Long> queue = new PriorityQueue<>();
        HashSet<Long> hashSet = new HashSet<>();
        hashSet.add(dp[1]);
        for (int i = 2; i <= n; i++) {
            selectFactor(queue, hashSet, dp[i - 1], 2);
            selectFactor(queue, hashSet, dp[i - 1], 3);
            selectFactor(queue, hashSet, dp[i - 1], 5);
            dp[i] = queue.poll();
        }
        return (int) dp[n];
    }

    private void selectFactor(PriorityQueue<Long> queue, HashSet<Long> hashSet, long last, int factor) {
        long potential = last * factor;
        if (!hashSet.contains(potential)) {
            queue.offer(potential);
            hashSet.add(potential);
        }


    }

    /**
     * 121. 买卖股票的最佳时机 LeetCode Easy DP
     *
     * @param prices
     * @return
     */
    public int maxProfitI(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                // 解释：
                //   dp[i][0]
                // = max(dp[-1][0], dp[-1][1] + prices[i])
                // = max(0, -infinity + prices[i]) = 0
                dp[i][1] = -prices[i];
                //解释：
                //   dp[i][1]
                // = max(dp[-1][1], dp[-1][0] - prices[i])
                // = max(-infinity, 0 - prices[i])
                // = -prices[i]
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[n - 1][0];
    }


    public int maxProfitI2nd(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }


    public int maxProfitII(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;//dp[i-1][0]昨天未持有股票的状态
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);//dp[i][0]今天未持有股票的状态  dp[i-1][0]昨天持有股票的状态
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);//dp[i][0]今天持有股票的状态  dp[i-1][0]昨天未持有股票的状态
        }
        return dp_i_0;
    }


    public int maxProfitWithCool(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0;// dp[i-2][0]
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            //今天未持有股票#昨天未支持股票#昨天持有股票
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            //今天持有股票#昨天持有股票#前天未持有股票
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            //昨天未持有股票赋给前天未持有股票
            dp_pre_0 = temp;
        }
        return dp_i_0;
    }

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i] - fee);
        }
        return dp_i_0;
    }


    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int max_k = 2;
        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) {
                    dp[0][k][0] = 0;
                    dp[0][k][1] = -prices[0];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][max_k][0];
    }


    public int maxProfitIV(int max_k, int[] prices) {
        int n = prices.length;
        if (max_k > n / 2) {
            return maxProfitII(prices);
        }
        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) {
                    dp[0][k][0] = 0;
                    dp[0][k][1] = -prices[0];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][max_k][0];
    }


    public int missingNumber1st(int[] nums) {
        int standard = 0;
        for (int i = 0; i <= nums.length; i++) {
            standard += i;
        }
        int current = 0;
        for (int i = 0; i < nums.length; i++) {
            current += nums[i];
        }
        return standard - current;
    }


    public int missingNumber2nd(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res ^= nums[i];
        }
        for (int i = 0; i <= nums.length; i++) {
            res ^= i;
        }
        return res;
    }


    /**
     * 官网 41. 缺失的第一个正数 Hard LeetCode
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive1st(int[] nums) {
        int n = nums.length;

        // 基本情况
        int contains = 0;
        for (int i = 0; i < n; i++)
            if (nums[i] == 1) {
                contains++;
                break;
            }

        if (contains == 0)
            return 1;

        // nums = [1]
        if (n == 1)
            return 2;

        // 用 1 替换负数，0，
        // 和大于 n 的数
        // 在转换以后，nums 只会包含
        // 正数
        for (int i = 0; i < n; i++)
            if ((nums[i] <= 0) || (nums[i] > n))
                nums[i] = 1;

        // 使用索引和数字符号作为检查器
        // 例如，如果 nums[1] 是负数表示在数组中出现了数字 `1`
        // 如果 nums[2] 是正数 表示数字 2 没有出现
        for (int i = 0; i < n; i++) {
            int a = Math.abs(nums[i]);
            // 如果发现了一个数字 a - 改变第 a 个元素的符号
            // 注意重复元素只需操作一次
            if (a == n)
                nums[0] = -Math.abs(nums[0]);
            else
                nums[a] = -Math.abs(nums[a]);
        }

        // 现在第一个正数的下标
        // 就是第一个缺失的数
        for (int i = 1; i < n; i++) {
            if (nums[i] > 0)
                return i;
        }

        if (nums[0] > 0)
            return n;

        return n + 1;
    }

    /**
     * 41. 缺失的第一个正数  LeetCode Hard [bucket sort]
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive2nd(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            //前两个条件是为了判断nums[i]是否在0-n范围内，防止index越界
            //后一个是判断i 这个index（当前的值）和nums[i]-1这个index（标准位置）是否相等，
            //标准位置:某个数nums[i] 落整个array的i-1的位置上
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        //判断第一个出现异常的数字
        for (int i = 0; i < n; i++) {
            if (nums[i] - 1 != i) {
                return i + 1;
            }
        }
        return n + 1;
        //[3, 4, 4, -1, 1]->[1,4,3,4,-1] 异常的时index为1的数nums[1] 其值约为 （1+1=2）但是实际是4
        //[3, 4, -1, 1]  -->[1,-1,3,4]
        //[3,4,-1,-2,1,5,16,0,2,0]-->[1,2,3,4,5,-1,16,0,-2,0]
    }


    public int firstMissingPositive3rd(int[] nums) {
        int n = nums.length;
        boolean[] bArr = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0 && nums[i] <= n) {
                bArr[nums[i] - 1] = true;
            }
        }
        for (int i = 0; i < bArr.length; i++) {
            if (!bArr[i]) {
                return i + 1;
            }
        }

        return n + 1;
    }


    private void swap(int[] nums, int m, int n) {
        int temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }

    public static void main(String[] args) {
        LeetCodeOne leetCodeOne = new LeetCodeOne();
        LeetCodeClassification leetCodeClassification = new LeetCodeClassification();


//        int[] nums = {0, 0, 0};
//        handler.threeSum(nums);

//        leetCodeOne.longestPalindrome("babad");
//        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
//        handler.lengthOfLIS(nums);
//        int[] coins = {1, 2, 5};
//        int amount = 11;
//        int[] coins = {2, 5};
//        int amount = 3;
//        leetCodeClassification.coinChange(coins, amount);
//        int[] numbers = {2, 7, 11, 15};
//        int target = 9;
//        handler.twoSumII(numbers, target);
//        Collections.sort();
//        handler.singleNumber(new int[]{2, 2, 3, 2});
//        handler.maxProfit(new int[]{1, 2, 3, 4, 5});
//        handler.firstMissingPositive1st(new int[]{3,4,-1,-2,1,5,16,0,2,0});
//        handler.firstMissingPositive2nd(new int[]{3, 4, -1, 1});
//        handler.firstMissingPositive2nd(new int[]{3, 4, 4, -1, 1});
//        handler.firstMissingPositive2nd(new int[]{3, 4, -1, -2, 1, 5, 16, 0, 2, 0});
        handler.firstMissingPositive3rd(new int[]{3, 4, -1, 1});

    }

    private class ReturnNode {
        boolean isB;
        int depth;

        public ReturnNode(int depth, boolean isB) {
            this.isB = isB;
            this.depth = depth;
        }
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

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}