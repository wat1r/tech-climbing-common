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
            slow= slow.next;
            fast =fast.next.next;
        }
        return slow == fast;
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
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        handler.twoSumII(numbers, target);
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