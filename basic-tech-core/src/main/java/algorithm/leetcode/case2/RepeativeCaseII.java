package algorithm.leetcode.case2;

import java.util.ArrayList;
import java.util.*;
import java.util.List;

/**
 * Created by FrankCooper
 * Date 2019/3/30 0:00
 * Description
 */
public class RepeativeCaseII {

    private static RepeativeCaseII handler = new RepeativeCaseII();

    /**
     * 19
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
     * 24
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null && pre.next.next != null) {
            ListNode node1 = pre.next;
            ListNode node2 = pre.next.next;
            ListNode next = node2.next;
            node2.next = node1;
            node1.next = next;
            pre.next = node2;
            pre = node1;
        }
        return dummy.next;
    }

    /**
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    /**
     * 78. 子集 Medium
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        subsetsDFS(result, new ArrayList<Integer>(), 0, nums);
        return result;
    }

    private void subsetsDFS(List<List<Integer>> result, ArrayList<Integer> levelList, int depth, int[] nums) {
        //recursive exit
        if (depth >= nums.length) {
            result.add(new ArrayList<>(levelList));
            return;
        }
        //pick nums[depth]
        levelList.add(nums[depth]);
        subsetsDFS(result, levelList, depth + 1, nums);

        //not pick nums[depth]
        levelList.remove(levelList.size() - 1);
        subsetsDFS(result, levelList, depth + 1, nums);
    }


    /**
     * 17. 电话号码的字母组合 Medium
     */
    Map<Character, List<String>> map = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        map.put('2', Arrays.asList("a", "b", "c"));
        map.put('3', Arrays.asList("d", "e", "f"));
        map.put('4', Arrays.asList("g", "h", "i"));
        map.put('5', Arrays.asList("j", "k", "l"));
        map.put('6', Arrays.asList("m", "n", "o"));
        map.put('7', Arrays.asList("p", "q", "r", "s"));
        map.put('8', Arrays.asList("t", "u", "v"));
        map.put('9', Arrays.asList("w", "x", "y", "z"));
        letterCombinationsDFS(result, new ArrayList<String>(), 0, digits.toCharArray());
        return result;
    }

    private void letterCombinationsDFS(List<String> result, ArrayList<String> levelList, int index, char[] digitsChar) {
        if (levelList.size() == digitsChar.length) {
            StringBuffer sb = new StringBuffer();
            for (String str : levelList) {
                sb.append(str);
            }
            result.add(sb.toString());
            return;
        }
        List<String> letters = map.get(digitsChar[index]);
        for (String letter : letters) {
            levelList.add(letter);
            letterCombinationsDFS(result, levelList, index + 1, digitsChar);
            levelList.remove(levelList.size() - 1);
        }

    }


    /**
     * 53. 最大子序和 Easy
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int preMaxSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int num : nums) {
            preMaxSum = Math.max(num, preMaxSum + num);
            maxSum = Math.max(maxSum, preMaxSum);
        }
        return maxSum;
    }


    /**
     * 121. 买卖股票的最佳时机 Easy
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i != prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }

    /**
     * 392. 判断子序列 Medium
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int sIndex = 0;
        int tIndex = 0;
        while (tIndex != t.length() && sIndex != s.length()) {
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                sIndex++;
                tIndex++;
            } else {
                tIndex++;
            }
        }
        return sIndex == s.length();
    }


    /**
     * 122. 买卖股票的最佳时机 II
     *
     * @param prices
     * @return
     */
    public int maxProfitII(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }

    /**
     * 605. 种花问题 Easy
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 1) {
                continue;
            }
            int pre = i == 0 ? 0 : flowerbed[i - 1];
            int next = i == len - 1 ? 0 : flowerbed[i + 1];
            if (pre == 0 && next == 0) {
                flowerbed[i] = 1;
                count++;
            }
        }
        return count >= n;
    }


    /**
     * 665. 非递减数列 Easy
     *
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= nums[i - 1]) {
                continue;
            }
            count++;
            if (i - 2 >= 0 && nums[i - 2] > nums[i]) {
                nums[i] = nums[i - 1];
            } else {
                nums[i - 1] = nums[i];
            }
        }
        return count <= 1;
    }

    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }


    /**
     * 322. 零钱兑换 LeetCode Medium
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i >= coin && dp[i - coin] != -1 && coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
            if (dp[i] == Integer.MAX_VALUE) {
                dp[i] = -1;
            }
        }
        return dp[amount];
    }


    public int coins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

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


    public int coins2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] map = new int[arr.length + 1][aim + 1];
        return process2(arr, 0, aim, map);
    }

    private int process2(int[] arr, int index, int aim, int[][] map) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            int mapValue = 0;
            for (int i = 0; arr[index] * i <= aim; i++) {
                mapValue = map[index + 1][aim - arr[index] * i];
                if (mapValue != 0) {
                    res += mapValue == -1 ? 0 : mapValue;
                } else {
                    res += process2(arr, index + 1, aim - arr[index] * i, map);
                }
            }
        }
        map[index][aim] = res == 0 ? -1 : res;
        return res;
    }




    public static void main(String[] args) {
//        int[] nums = {1, 2, 3};
//        handler.subsets(nums);
//        handler.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
//        System.out.println(handler.isSubsequence("abc", "ahbgdc"));
//        handler.maxProfitII(new int[]{7, 1, 5, 8, 3, 6, 4});
//        handler.canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1);
        handler.checkPossibility(new int[]{3, 4, 2, 3});
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
}


