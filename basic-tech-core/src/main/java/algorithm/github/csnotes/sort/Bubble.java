package algorithm.github.csnotes.sort;

/**
 * Created by FrankCooper
 * Date 2019/3/31 11:35
 * Description
 */
public class Bubble<T extends Comparable<T>> extends Sort<T> {


    private static Sort handler = new Bubble<Integer>();

    @Override
    public void sort(T[] nums) {
        int len = nums.length;
        for (int i = len - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (less(nums[j + 1], nums[j])) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }


    public static void main(String[] args) {

        Integer[] nums = {50, 20, 10, 40, 30};
        handler.sort(nums);


    }
}
