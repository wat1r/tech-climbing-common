package algorithm.github.csnotes.sort;

/**
 * Created by FrankCooper
 * Date 2019/3/31 11:35
 * Description
 */
public class Selection<T extends Comparable<T>> extends Sort<T> {


    private static Sort handler = new Selection<Integer>();

    @Override
    public void sort(T[] nums) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (less(nums[j], nums[minIndex])) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
    }


    public static void main(String[] args) {

        Integer[] nums = {50, 20, 10, 40, 30};
        handler.sort(nums);


    }
}
