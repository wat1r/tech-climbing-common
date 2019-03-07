package algorithm.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2019/3/6 23:16
 * Description
 * 2018 年力扣高频
 * 算法面试题汇总
 */
public class LeetCodeExploreI {
    private static LeetCodeExploreI handler = new LeetCodeExploreI();


    /**
     * 136. 只出现一次的数字 Easy
     *
     * @param nums
     * @return ^运算:相同取0，不同取1
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }

    /**
     * 169. 求众数 Easy
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        quickSortRecursive(nums, 0, nums.length - 1);
        return nums[nums.length / 2];
    }


    private void quickSortRecursive(int[] nums, int left, int right) {
        if (nums == null || nums.length < 2) {
            return;
        }
        while (left < right) {
            int[] partition = partition(nums, left, right);
            quickSortRecursive(nums, left, partition[0] - 1);
            quickSortRecursive(nums, partition[0] + 1, right);
        }

    }

    private int[] partition(int[] nums, int left, int right) {
        int less = left - 1;
        int more = right;
        while (left < more) {
            if (nums[left] < nums[right]) {
                swap(nums, ++less, left++);
            } else if (nums[left] > nums[right]) {
                swap(nums, --more, left);
            } else {
                left++;
            }
        }
        swap(nums, more, right);
        return new int[]{less + 1, more};
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 229. 求众数 II Medium
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElementII(int[] nums) {
        List<Integer> resList = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return resList;
        } else if (nums.length == 1) {
            resList.add(nums[0]);
            return resList;
        }
        Arrays.sort(nums);
        int prev = nums[0];
        int count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            count += nums[i] == prev ? 1 : 0;
            validate(count, n, prev, resList);
            if (nums[i] != prev) {
                prev = nums[i];
                count = 1;
            }
        }
        validate(count, n, prev, resList);
        return resList;
    }

    private void validate(int count, int n, int prev, List<Integer> resList) {
        if (!resList.contains(prev) && count > (n / 3)) {
            resList.add(prev);
        }
    }


    /**
     * 240. 搜索二维矩阵 II Medium
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col > -1) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }


    /**
     * 26. 删除排序数组中的重复项 Easy
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int cur = 0, fast = 1;
        while (fast < nums.length) {
            if (nums[fast] == nums[cur]) {
                fast++;
            } else {
                swap(nums, ++cur, fast++);
            }
        }
        return cur + 1;
    }


    /**
     * 122. 买卖股票的最佳时机 II Easy
     *
     * @param prices
     * @return #### DP, sequence dp + status
     * - 想知道前i天的最大profit, 那么用sequence DP:
     * - dp[i]: represents 前i天的最大profit
     * - 当天的是否能卖, 取决于昨天是否买进, 也就是 `昨天买了或者卖了的状态`: 加状态, dp[i][0], dp[i][1]
     * - `买`的状态 `dp[i][0]` = 1. 今天买入, 昨天卖掉的dp[i-1][1]结果 - price[i]; 2. 今天不买, 跟昨天买的status dp[i-1][0] 结果 比较.
     * - `卖`的状态 `dp[i][1]` = 1. 今天卖出, 昨天买进的dp[i-1][0]结果 + price[i]; 2. 今天不卖, 跟昨天卖的status dp[i-1][1] 结果 比较.
     * - 注意init:
     * - dp[0][0] = dp[0][1] = 0; // 0 days,
     * - dp[1][0] = 0; // sell on 1st day, haven't bought, so just 0 profit.
     * - dp[1][0] = -prices[0]; // buy on 1st day, with cost of prices[0]
     * <p>
     * 注意天数第0天，和index=0的索引的区别
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        dp[0][0] = dp[0][1] = 0;
        dp[1][0] = -prices[0];
        dp[1][1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i - 1]);
        }
        return Math.max(dp[n][0], dp[n][1]);
    }


    /**
     * 189. 旋转数组 Easy
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int[] temp = new int[len];
        for (int i = 0; i < len; i++) {
            temp[(i + k) % len] = nums[i];
        }
        for (int i = 0; i < len; i++) {
            nums[i] = temp[i];
        }
    }

    /**
     * 350. 两个数组的交集 II Easy
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> resList = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num : nums1) {
            map.putIfAbsent(num, 0);
            map.put(num, map.get(num) + 1);
        }
        for (Integer num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                resList.add(num);
                map.put(num, map.get(num) - 1);
            }
        }
        int[] resArr = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            resArr[i] = resList.get(i);
        }
        return resArr;
    }


    /**
     * 66. 加一 Easy
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        if (digits.length == 0) {
            return digits;
        }
        digits[digits.length - 1] += 1;
        //Check index digit.length-1 to 1
        for (int i = digits.length - 1; i > 0; i--) {
            if (digits[i] == 10) {
                digits[i] = 0;
                digits[i - 1] += 1;
            } else {
                return digits;
            }
        }

        //Check index 0. If ==0, set it to 0 and carry over 1
        if (digits[0] == 10) {
            int[] output = new int[digits.length + 1];
            output[0] = 1;
            return output;
        } else {
            return digits;
        }
    }


    /**
     * 283. 移动零 Easy
     *
     * @param nums #### Two Pointers
     *             - Outside pointer that moves in certain condition.
     *             - Save appropirate elements
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 1. 两数之和 Easy
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {



        return null;
    }

    /**
     * 36. 有效的数独 Medium
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {

        return false;
    }



    public static void main(String[] args) {

//        int[] nums = {3, 2, 3};
//        System.out.println(JSON.toJSON(handler.majorityElementII(nums)));

//        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
//        System.out.println(handler.removeDuplicates(nums));
//        int[] prices = {7, 1, 5, 3, 6, 4};
//        System.out.println(handler.maxProfit(prices));
//        int[] nums = {1, 2, 3, 4, 5, 6, 7};
//        handler.rotate(nums, 3);
//        int[] digits = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
//        handler.plusOne(digits);
        int[] nums = {0, 1, 0, 3, 12};
        handler.moveZeroes(nums);
    }
}
