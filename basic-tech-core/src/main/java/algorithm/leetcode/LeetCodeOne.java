package algorithm.leetcode;

import algorithm.utils.SortUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @Author:Cooper
 * @Date:2019/2/15 11:43
 */
public class LeetCodeOne {

    private static LeetCodeOne handler = new LeetCodeOne();

    /**
     * 136. 只出现一次的数字
     *
     * @param nums
     * @return 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     */
    public int singleNumber(int[] nums) {

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }


    /**
     * 217. 存在重复元素
     *
     * @param nums
     * @return 给定一个整数数组，判断是否存在重复元素。
     * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
     * <p>
     * 解题思路：也是利用了HashMap的key值为set集合，所以我们可以直接使用set。
     * 遍历数组如果发现有重复的就直接返回true，否则将改数值，放入set中
     */
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }
        return false;
    }


    /**
     * 215. 数组中的第K个最大元素
     *
     * @param nums
     * @param k
     * @return 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * <p>
     * 解决方法是先排序，再取用
     */
    public int findKthLargest(int[] nums, int k) {
        SortUtils.quickSort(nums, 0, nums.length - 1);
        return nums[nums.length - k];
    }


    /**
     * 169. 求众数
     *
     * @param nums
     * @return 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     */
    public int majorityElement(int[] nums) {
        SortUtils.quickSort(nums, 0, nums.length - 1);
        return nums[nums.length / 2];
    }


    /**
     * 238. 除自身以外数组的乘积
     *
     * @param nums
     * @return 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     * ******
     * 维持两个数组, left[] and right[]. 分别记录 第i个元素 左边相乘的积left[i] and 右边相乘的积right[i]. 那么结果res[i]即为 left[i]＊right[i].
     * follow up 要求O(1)空间. 利用返回的结果数组,先存right数组. 再从左边计算left,同时计算结果值, 这样可以不需要额外的空间.
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        res[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            res[i] = res[i + 1] * nums[i + 1];
        }
        int left = 1;
        for (int i = 0; i < nums.length; i++) {
            res[i] = left * res[i];
            left = left * nums[i];
        }
        return res;
    }


    /**
     * 88. 合并两个有序数组
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n     给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] res = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                res[k++] = nums1[i++];
            } else {
                res[k++] = nums2[j++];
            }
        }

        while (i < m) {
            res[k++] = nums1[i++];
        }
        while (j < n) {
            res[k++] = nums2[j++];
        }
//        System.out.println(Arrays.toString(res));

    }


    /**
     * 88. 合并两个有序数组 第二种解法
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge88(int[] nums1, int m, int[] nums2, int n) {
        int p = m-- + n-- - 1;
        while (m >= 0 && n >= 0) {
            nums1[p--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        while (n >= 0) {
            nums1[p--] = nums2[n--];
        }
    }


    /**
     * 7. 整数反转
     *
     * @param x
     * @return 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     */
    public int reverse(int x) {

        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
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


    /**
     * 8. 字符串转换整数 (atoi)
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {


        return 0;
    }


    /**
     * 16. 最接近的三数之和
     *
     * @param nums
     * @param target
     * @return 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
     * 使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     * <p>
     * 双指针法，先排序，这很关键
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];
        int baseDiff = Math.abs(target - closest);
        for (int i = 0; i < nums.length - 2; i++) {
            int low = i + 1;
            int high = nums.length - 1;
            while (low < high) {
                int sum = nums[low] + nums[i] + nums[high];
                int newDiff = Math.abs(target - sum);
                if (newDiff == 0) {
                    return sum;
                }
                if (newDiff < baseDiff) {
                    baseDiff = newDiff;
                    closest = sum;
                }
                if (sum > target) {
                    high--;
                } else {
                    low++;
                }

            }
        }
        return closest;
    }


    /**
     * 15. 三数之和
     *
     * @param nums
     * @return 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     */
    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        ArrayList<List<Integer>> resList = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; ) {
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[start] + nums[i] + nums[end];
                if (sum == 0) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[start]);
                    list.add(nums[i]);
                    list.add(nums[end]);
                    resList.add(list);
                    start++;
                    end--;
                    //除去end指针的重复值
                    while (nums[end] == nums[end + 1] && start < end) {
                        end--;
                    }
                    //除去start指针的重复值
                    while (nums[start] == nums[start - 1] && start < end) {
                        start++;
                    }
                } else if (sum > 0) {//3值的和大于0时，重新检测end指针是否重复后降值
                    end--;
                    while (nums[end] == nums[end + 1] && start < end) {
                        end--;
                    }
                } else {//3值的和小于0时，重新检测start指针是否重复后升值
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
     * 53. 最大子序和
     *
     * @param nums
     * @return 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * * 定义状态：
     * dp[i] ： 表示以 nums[i] 结尾的连续子数组的最大和
     * <p>
     * 状态转移方程：
     * dp[i] = max{num[i],dp[i-1] + num[i]}
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        int res = dp[0];
        // 最后这一步，是求一个全局的最优值
        for (int i = 1; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }


    /**
     * 5. 最长回文子串
     *
     * @param s
     * @return 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int len = s.length();
        String res = null;
        int max = 0;
        boolean[][] dp = new boolean[len][len];
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                //这里只考虑了i<=j的情况，因为i>j时均为false
//当i==j,j-i==1,j-i==2时，只要满足s.charAt(i) == s.charAt(j)就是回文字符串
//如果不是这样，还要判断当前回文字符串的子串是不是回文字符串，即dp[i + 1][j - 1])，这就是动
//态规划思想
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]);
                if (dp[i][j] && (j - i + 1) > max) {
                    max = j - i + 1;
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }


    /**
     * 344. 反转字符串
     *
     * @param s 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     *          不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     *          你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     */
    public void reverseString(char[] s) {
        if (s == null) {
            return;
        }
        int left = 0, right = s.length - 1;
        char temp;
        while (left < right) {
            temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }


    /**
     * 292. Nim游戏
     *
     * @param n
     * @return 你和你的朋友，两个人一起玩 Nim游戏：桌子上有一堆石头，每次你们轮流拿掉 1 - 3 块石头。 拿掉最后一块石头的人就是获胜者。你作为先手。
     * 你们是聪明人，每一步都是最优解。 编写一个函数，来判断你是否可以在给定石头数量的情况下赢得游戏。
     */
    public boolean canWinNim(int n) {
        return (n % 4 != 0);
    }


    /**
     * 70. 爬楼梯
     *
     * @param n
     * @return 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * TP，状态方程写出来就是斐波那契数列。
     * dp[i] = dp[i-1] + dp[i-2]
     * i-1的时候跳一步可以到达i
     * i-2的时候跳一步是i-1，这个变成dp[i-1]的子问题了,直接跳两步可以到达i
     */
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 121. 买卖股票的最佳时机
     *
     * @param prices
     * @return 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     * 注意你不能在买入股票前卖出股票。
     */
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            if (prices[i] - min > max) {
                max = prices[i] - min;
            }
        }
        return max;
    }


    /**
     * @param prices
     * @return
     */
    public int maxProfitII(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int res = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peak = prices[i];
            res += peak - valley;
        }
        return res;
    }


    /**
     * 557. 反转字符串中的单词 III
     *
     * @param s
     * @return 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     */
    public String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        int len = words.length;
        if (words.length == 1) {
            return reverseString(s);
        }
        for (int i = 0; i < len - 1; i++) {
            sb.append(reverseString(words[i]) + " ");
        }
        sb.append(reverseString(words[len - 1]));
        return String.valueOf(sb);
    }

    public String reverseString(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
//        int[] nums = {4, 1, 2, 1, 2};
//        System.out.println(handler.singleNumber(nums));
//        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
//        System.out.println(handler.findKthLargest(nums, 4));
//        int[] nums = {2, 2, 1, 1, 1, 2, 2};
//        int[] nums = {-1, 1, 1, 1, 2, 1};
//        System.out.println(handler.majorityElement(nums));
//        int[] nums = {1, 2, 3, 4};
//        System.out.println(Arrays.toString(handler.productExceptSelf(nums)));
//        int[] nums1 = {1, 2, 3, 0, 0, 0};
//        int[] nums2 = {4, 5, 6};
//        handler.merge88(nums1, 3, nums2, 3);

//        System.out.println(handler.reverse(Integer.MAX_VALUE));

//        int[] nums = {-1, 2, 1, -4};
//        handler.threeSumClosest(nums, 1);
//        int[] nums = {-1, 0, 1, 2, -1, -4};
//        System.out.println(JSON.toJSON(handler.threeSum(nums)));

//        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        System.out.println(handler.maxSubArray(nums));

//        String str = "ababa";
//        System.out.println(handler.longestPalindrome(str));

//        handler.reverseString("hello".toCharArray());

//        handler.climbStairs(3);
//        int[] prices = {7, 1, 5, 3, 6, 4};
//        System.out.println(handler.maxProfit(prices));
    }


}
