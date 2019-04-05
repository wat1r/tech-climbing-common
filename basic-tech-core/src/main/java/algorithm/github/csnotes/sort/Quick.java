package algorithm.github.csnotes.sort;

import com.alibaba.fastjson.JSON;

/**
 * Created by FrankCooper
 * Date 2019/3/31 11:35
 * Description
 */
public class Quick<T extends Comparable<T>> extends Sort<T> {


    private static Sort handler = new Quick<Integer>();

    @Override
    public void sort(T[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);


    }

    private void quickSort(T[] nums, int left, int right) {
        if (left < right) {
            int[] p = partition(nums, left, right);
            quickSort(nums, left, p[0] - 1);
            quickSort(nums, p[1] + 1, right);
        }
    }

    private int[] partition(T[] nums, int left, int right) {
        int less = left - 1;
        int more = right;
        while (left < more) {
            if (less(nums[left], nums[right])) {
                swap(nums, ++less, left++);
            } else if (less(nums[right], nums[left])) {
                swap(nums, --more, left);
            } else {
                left++;
            }
        }
        swap(nums, more, right);
        return new int[]{less + 1, more};
    }


    public static void main(String[] args) {

        Integer[] nums = {70, 20, 10, 80, 30, 90, 50, 60, 40};
        handler.sort(nums);
        System.out.println(JSON.toJSON(nums));

//        System.out.println( handler.less(8, 6));


    }
}
