package algorithm.leetcode;

import algorithm.utils.SortUtils;
import com.alibaba.fastjson.JSON;

import java.util.*;

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


    /**
     * 20. 有效的括号
     *
     * @param s
     * @return 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     */
    public boolean isValid(String s) {

        HashMap<Character, Character> ctrlMap = new HashMap<Character, Character>() {
            {
                put(')', '(');
                put(']', '[');
                put('}', '{');
            }
        };
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (ctrlMap.containsKey(s.charAt(i))) {
                char top = stack.isEmpty() ? '#' : stack.pop();
                if (top != ctrlMap.get(s.charAt(i))) {
                    return false;
                }
            } else {
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }

    /**
     * @param n
     * @return 解析
     * https://blog.csdn.net/wutingyehe/article/details/51155231
     */
    public List<Integer> grayCode(int n) {

        ArrayList<Integer> result = new ArrayList<Integer>();
        if (n < 0) {
            return result;
        }
        if (n == 0) {
            result.add(0);
            return result;
        }
        if (n == 1) {
            result.add(0);
            result.add(1);
            return result;
        }
        List<Integer> list = grayCode(n - 1);
        for (int i = 0; i < list.size(); i++) {
            result.add(list.get(i));
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            result.add(((int) Math.pow(2, n - 1)) + list.get(i));
        }
        return result;
    }

    /**
     * 4. 寻找两个有序数组的中位数
     *
     * @param nums1
     * @param nums2
     * @return 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     * <p>
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     * <p>
     * 你可以假设 nums1 和 nums2 不会同时为空
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {


        return 0.0;
    }


    /**
     * 11. 盛最多水的容器
     *
     * @param height
     * @return 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1, maxarea = 0;
        while (left < right) {
            maxarea = Math.max(maxarea, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxarea;
    }


    /**
     * 14. 最长公共前缀
     *
     * @param strs
     * @return 编写一个函数来查找字符串数组中的最长公共前缀。
     * <p>
     * 如果不存在公共前缀，返回空字符串 ""。
     * <p>
     * 四种解法
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }
        return prefix;
    }


    /**
     * 54. 螺旋矩阵
     *
     * @param matrix
     * @return 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<Integer>();
        }
        int tR = 0, tC = 0;
        int dR = matrix.length - 1, dC = matrix[0].length - 1;
        List<Integer> resList = new ArrayList<>();
        while (tR <= dR && tC <= dC) {
            printEdge(matrix, tR++, tC++, dR--, dC--, resList);
        }
        return resList;
    }

    private void printEdge(int[][] matrix, int tR, int tC, int dR, int dC, List<Integer> resList) {
        if (tR == dR) {//子矩阵只有一行
            for (int i = tC; i <= dC; i++) {
                resList.add(matrix[tR][i]);
            }
        } else if (tC == dC) {//子矩阵只有一列
            for (int i = tR; i <= dR; i++) {
                resList.add(matrix[i][tC]);
            }
        } else {
            int curC = tC;
            int curR = tR;
            while (curC != dC) {
                resList.add(matrix[tR][curC]);
                curC++;
            }
            while (curR != dR) {
                resList.add(matrix[curR][dC]);
                curR++;
            }
            while (curC != tC) {
                resList.add(matrix[dR][curC]);
                curC--;
            }
            while (curR != tR) {
                resList.add(matrix[curR][tC]);
                curR--;
            }
        }

    }

    /**
     * 46. 全排列
     *
     * @param nums
     * @return 给定一个没有重复数字的序列，返回其所有可能的全排列。
     * 全排列算法思路解析
     * https://blog.csdn.net/summerxiachen/article/details/60579623
     * <p>
     * https://blog.csdn.net/a754112602/article/details/81109663
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        dfs(res, nums, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, int[] nums, int j) {
        if (j == nums.length) {
            List<Integer> temp = new ArrayList<Integer>();
            for (int num : nums) {
                temp.add(num);
            }
            res.add(temp);
        }
        for (int i = j; i < nums.length; i++) {
            swap(nums, i, j);
            dfs(res, nums, j + 1);
            swap(nums, i, j);
        }
    }

    private void swap(int[] nums, int m, int n) {
        int temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }


    /**
     * 680. 验证回文字符串 Ⅱ
     *
     * @param s
     * @return 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
     * <p>
     * 使用双指针，一个指向头，一个指向尾，如果两者不相同了，则可以将尾部的往前挪一个，或者首部的往后挪一个，判断这两种情况
     * 这两种已经相当于删除了一个字符，如果不是回文字符串，说明删除一个字符满足不了要求
     */
    public boolean validPalindrome(String s) {

        if (s.length() == 0) {
            return false;
        }
        int left = 0, right = s.length() - 1;
        char[] chas = s.toCharArray();
        while (left < right) {
            if (chas[left] != chas[right]) {
                return isPalindrome(chas, left + 1, right) || isPalindrome(chas, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    public boolean isPalindrome(char[] chas, int left, int right) {
        while (left < right) {
            if (chas[left] != chas[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    /**
     * 125. 验证回文串
     *
     * @param s
     * @return 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     */
    public boolean isPalindrome(String s) {
        char[] chas = s.toLowerCase().toCharArray();
        StringBuffer ctrlStr = new StringBuffer();
        for (int i = 0; i < chas.length; i++) {
            ctrlStr.append(onlyNumAndAlphabet(chas[i]));
        }
        return isPalindromeWithoutCase(ctrlStr.toString().toCharArray(), 0, ctrlStr.toString().length() - 1);
    }

    public String onlyNumAndAlphabet(char cha) {
        StringBuffer sb = new StringBuffer();
        if ((cha >= '0' && cha <= '9') || (cha >= 'a' && cha <= 'z')) {
            sb.append(cha);
        }
        return sb.toString();
    }

    public boolean isPalindromeWithoutCase(char[] chas, int left, int right) {
        while (left < right) {
            if (chas[left] != chas[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    /**
     * 235. 二叉搜索树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * <p>
     * 也是使用递归来做，但是这里多了一个条件可以辅助我们去判断。因为这个条件是二叉搜索树，二叉搜索树是排序的，如果说两个需要比较的节点的值都比当前根节点小的话，那么就在左子树中搜索，如果说都大于，那么就在右子树中搜索。如果说一个小于当前根节点，一个大于当前根节点的话，就可以确定当前的根节点就是最近公共祖先。因为上一道题，无法判断需要走哪条分支，所以说两个分支都要走，然后比较两个分支的结果
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if ((root.val - p.val) * (root.val - q.val) <= 0) {
            return root;
        } else if (root.val > p.val) {
            root = lowestCommonAncestor(root.left, p, q);
        } else {
            root = lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }

    /**
     * 104. 二叉树的最大深度
     *
     * @param root
     * @return 给定一个二叉树，找出其最大深度。
     * <p>
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }


    /**
     * 231. 2的幂
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        int count = 0;
        while (n != 0) {
            count += (n & 1);
            n >>= 1;
        }
        return count == 1;
    }


    //---------------------------排列类-开始----------------------------//

    /**
     * //441. 排列硬币
     *
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {
        if (n == 0) {
            return 0;
        }
        if (n <= 2 && n > 0) {
            return 1;
        }
        int sum = 0, row = 0, count = 0;
        while (sum <= n) {
            row += 1;
            count++;
            sum += row;
        }
        return (sum == n) ? count : count - 1;
    }


    /**
     * 46题
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute46(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        dfs46(result, new ArrayList<>(), nums);

        return result;
    }

    private void dfs46(List<List<Integer>> result, ArrayList<Integer> levelList, int[] nums) {
        if (levelList.size() == nums.length) {
            result.add(new ArrayList<>(levelList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (levelList.contains(nums[i])) {
                continue;
            }
            levelList.add(nums[i]);
            dfs46(result, levelList, nums);
            levelList.remove(levelList.size() - 1);//此处根据index进行移除

        }

    }

    /**
     * 47题
     *
     * @param nums
     * @return
     */
    boolean[] visited;

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        visited = new boolean[nums.length];
        dfs47(result, new ArrayList<>(), nums);
        return result;
    }

    private void dfs47(List<List<Integer>> result, ArrayList<Integer> levelList, int[] nums) {
        if (levelList.size() == nums.length) {
            result.add(new ArrayList<>(levelList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || (i - 1) >= 0 && visited[i - 1] && nums[i] == nums[i - 1]) {
                continue;
            }
            visited[i] = true;
            levelList.add(nums[i]);
            dfs47(result, levelList, nums);
            visited[i] = false;
            levelList.remove(levelList.size() - 1);
        }

    }


    //---------------------------排列类-结束----------------------------//


    //==========================面试算法 LeetCode 刷题班-开始============================//
    //7. 哈希表与字符串

    /**
     * 409. 最长回文串
     *
     * @param s
     * @return
     */
    public int longestPalindrome409(String s) {
        char[] chas = s.toCharArray();
        HashMap<Character, Integer> ctrlMap = new HashMap<>();
        for (int i = 0; i < chas.length; i++) {
            if (!ctrlMap.containsKey(chas[i])) {
                ctrlMap.put(chas[i], 1);
            } else {
                Integer value = ctrlMap.get(chas[i]);
                value++;
                ctrlMap.put(chas[i], value);
            }
        }
        int res = 0, flag = 0;
        for (Character c : ctrlMap.keySet()) {
            Integer value = ctrlMap.get(c);
            if (value % 2 == 0) {
                res += value;
            } else {
                res += (value - 1);
                flag = 1;
            }
        }
        return res + flag;
    }


    /**
     * 290. 单词模式
     *
     * @param pattern
     * @param str
     * @return 双 map的模式是为了验证:
     * 输入: pattern = "abba", str = "dog dog dog dog"
     * 输出: false
     */
    public boolean wordPattern(String pattern, String str) {
        if (pattern != null && str != null && pattern.length() == 0 && str.length() == 0) {
            return true;
        }
        if (pattern == null || pattern.length() == 0 || str == null || str.length() == 0) {
            return false;
        }
        String[] strArr = str.split(" ");
        if (pattern.length() != strArr.length) {
            return false;
        }
        HashMap<Character, String> baseMap = new HashMap<>();
        HashMap<String, Character> mirrorMap = new HashMap<>();
        for (int i = 0; i < strArr.length; i++) {
            char c = pattern.charAt(i);
            String s = strArr[i];
            if (!baseMap.containsKey(c)) {
                baseMap.put(c, s);
            } else {
                if (!baseMap.get(c).equals(s)) {
                    return false;
                }
            }
            if (!mirrorMap.containsKey(s)) {
                mirrorMap.put(s, c);
            } else {
                if (mirrorMap.get(s) != c) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 49. 字母异位词分组
     *
     * @param strs
     * @return 方法一：排序数组分类
     * 思路
     * <p>
     * 当且仅当它们的排序字符串相等时，两个字符串是字母异位词。
     * <p>
     * 算法
     * <p>
     * 维护一个映射 ans : {String -> List}，其中每个键 \text{K}K 是一个排序字符串，每个值是初始输入的字符串列表，排序后等于 \text{K}K。
     * <p>
     * 在 Java 中，我们将键存储为字符串，例如，code。 在 Python 中，我们将键存储为散列化元组，例如，('c', 'o', 'd', 'e')。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chas = str.toCharArray();
            Arrays.sort(chas);
            String s = String.valueOf(chas);
            if (!map.containsKey(s)) {
                map.put(s, new ArrayList());
            }
            map.get(s).add(str);
        }
        return new ArrayList<>(map.values());
    }


    /**
     * 3. 无重复字符的最长子串
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int res = 0, i = 0, j = 0;
        int len = s.length();
        while (i < len && j < len) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                res = Math.max(res, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return res;
    }

    /**
     * 3. 无重复字符的最长子串
     *
     * @param s
     * @return 优化的方法
     */
    public int lengthOfLongestSubstringI(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }


    /**
     * 187. 重复的DNA序列
     *
     * @param s
     * @return 所有 DNA 由一系列缩写为 A，C，G 和 T 的核苷酸组成，例如：“ACGAATTCCG”。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
     * <p>
     * 编写一个函数来查找 DNA 分子中所有出现超多一次的10个字母长的序列（子串）。
     */
    public List<String> findRepeatedDnaSequences(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length() - 9; i++) {
            String sequences = s.substring(i, i + 10);
            map.put(sequences, map.getOrDefault(sequences, 0) + 1);
        }
        ArrayList<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            if (e.getValue() > 1) {
                res.add(e.getKey());
            }
        }
        return res;
    }


    /**
     * 76. 最小覆盖子串
     *
     * @param s
     * @param t
     * @return 给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。
     * <p>
     * “双指针滑动窗口”：使用两个指针分别做窗口的左、右边界指针，右边界一直往后滑动，直到窗口中的字符子串能够覆盖t串。这时判断左边界是否多余（有可能窗口中的字符种类数多于t串、或者左边界的这个字符出现的次数多于t串中的出现次数），然后记录此时的窗口长度、起始下标，并进行更新最短长度。然后尝试移动左边界，再重复上面右边界持续扩大直到覆盖t串的操作，当窗口子串覆盖了t串，重复上述操作…
     */
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        int end = 0;
        int length = Integer.MAX_VALUE;
        String rst = "";

        // Initialize source map for validation usage
        int[] source = new int[256];
        int[] target = new int[256];
        for (char c : t.toCharArray()) {
            target[c]++;
        }

        for (int i = 0; i < s.length(); i++) {
            while (end < s.length() && !valid(source, target)) {
                source[s.charAt(end)]++;
                end++;
            }
            if (valid(source, target)) {
                if (end - i < length) {
                    length = Math.min(length, end - i);
                    rst = s.substring(i, end);
                }
            }
            source[s.charAt(i)]--;
        }
        return rst;
    }


    /*
       Validate if the count of source map matches targetMap.
       Use target as base, since it's substring.
   */
    private boolean valid(int[] source, int[] target) {
        for (int i = 0; i < 256; i++) {
            if (source[i] < target[i]) {
                return false;
            }
        }
        return true;
    }


    //==========================面试算法 LeetCode 刷题班-1. 链表============================//


    /**
     * 206. 反转链表
     *
     * @param head
     * @return https://blog.csdn.net/puqutogether/article/details/45487797
     */
    public ListNode reverseList(ListNode head) {
        ListNode current = head, next, prev = null;
        while (current != null) {
            // 记录后继结点
            next = current.next;
            // 后继指针逆向
            current.next = prev;
            // 记录当前结点
            prev = current;
            // 下一结点成为当前结点
            current = next;
        }
        return prev;
    }


    /**
     * 92. 反转链表 II
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {


        return null;
    }


    //==========================面试算法 LeetCode 刷题班-8. 搜索============================//

    /**
     * 200. 岛屿的个数
     *
     * @param grid
     * @return 用一个二维数组代表一张地图，全由“0”和“1”组成，其中“0”代表水域，“1”代表小岛，
     * 小岛“1”被水域“0”所包围，当小岛土地“1”在水平和垂直方向相连接时，认为是同一块土地。求这张地图中小岛的数量。
     */
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int x, int y) {
        if (!validateInput(grid, x, y)) {
            return;
        }
        grid[x][y] = '0';
        for (int i = 0; i < dx.length; i++) {
            dfs(grid, x + dx[i], y + dy[i]);
        }


    }

    private boolean validateInput(char[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length || grid[x][y] == '0') {
            return false;
        }
        return true;
    }

    /**
     * 127. 单词接龙
     * Word Ladder
     * BFS
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        return 0;
    }


    /**
     * 473. 火柴拼正方形
     * @param nums
     * @return
     */
    public boolean makesquare(int[] nums) {

        return false;
    }


    //==========================面试算法 LeetCode 刷题班-结束============================//


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

//        System.out.println(JSON.toJSON(handler.grayCode(2)));


//        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
//        System.out.println(handler.maxArea(height));

//        String[] strs = {"flower", "flow", "flight"};
//        System.out.println(handler.longestCommonPrefix(strs));
//        int[][] matrix = {
//                {1, 2, 3},
//                {4, 5, 6},
//                {7, 8, 9}
//        };
//        System.out.println(JSON.toJSON(handler.spiralOrder(matrix)));
//        System.out.println(handler.validPalindrome("abdca"));
//        System.out.println(handler.isPalindrome("A man, a plan, a canal: Panama"));

//        System.out.println(handler.isPowerOfTwo(5));

//        System.out.println(handler.arrangeCoins(8));

//        int[] nums = {1, 2, 3};
//        System.out.println(JSON.toJSON(handler.permute46(nums)));
//        int[] nums = {1, 1, 2};
//        System.out.println(JSON.toJSON(handler.permuteUnique(nums)));

//        System.out.println(handler.longestPalindrome409("abccccdd"));

//        String pattern = "abba", str = "dog dog dog dog";
//        System.out.println(handler.wordPattern(pattern, str));

//        System.out.println(handler.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));

//        System.out.println(handler.lengthOfLongestSubstringI("pwwkew"));
//        System.out.println(JSON.toJSON(handler.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT")));

//        System.out.println(handler.minWindow("ADOBECODEBANC", "ABC"));

//        char[][] grid = {
//                {'1', '1', '0', '0', '0'},
//                {'0', '1', '0', '0', '1'},
//                {'0', '0', '0', '1', '1'},
//                {'0', '0', '0', '0', '0'},
//                {'0', '0', '0', '0', '1'}
//        };
//        handler.numIslands(grid);

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<String>() {{
            add("hot");
            add("dot");
            add("dog");
            add("lot");
            add("log");
            add("cog");
        }};
        handler.ladderLength(beginWord, endWord, wordList);

    }


}

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}