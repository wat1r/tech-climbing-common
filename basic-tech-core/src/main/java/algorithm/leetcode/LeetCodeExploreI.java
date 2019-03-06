package algorithm.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * 229. 求众数 II
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
        validate(count,n,prev,resList);
        return resList;
    }

    private void validate(int count, int n, int prev, List<Integer> resList) {
        if (!resList.contains(prev) && count > (n / 3)) {
            resList.add(prev);
        }
    }


    public static void main(String[] args) {

            int[] nums = {3,2,3};
        System.out.println(JSON.toJSON(handler.majorityElementII(nums)));



    }
}
