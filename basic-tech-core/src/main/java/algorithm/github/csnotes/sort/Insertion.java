package algorithm.github.csnotes.sort;

/**
 * Created by FrankCooper
 * Date 2019/3/31 11:35
 * Description
 */
public class Insertion<T extends Comparable<T>> extends Sort<T> {


    private static Sort handler = new Insertion<Integer>();

    @Override
    public void sort(T[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0 && less(nums[j], nums[j - 1]); j--) {
                swap(nums, j, j - 1);
            }
        }

    }


    public static void main(String[] args) {

        Integer[] nums = {50, 20, 10, 40, 30};
        handler.sort(nums);


    }
}
