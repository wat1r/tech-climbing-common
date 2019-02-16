package algorithm.leetcode;

import algorithm.utils.SortUtils;

import java.util.Arrays;
import java.util.HashSet;

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

        System.out.println(handler.reverse(Integer.MAX_VALUE));

    }


}
