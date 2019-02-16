package algorithm.utils;

/**
 * Created by FrankCooper
 * Date 2019/2/16 12:44
 * Description
 */
public class SortUtils {


    /**
     * 快排
     *
     * @param numbers
     * @param start
     * @param end
     */
    public static void quickSort(int[] numbers, int start, int end) {
        if (start < end) {
            int base = numbers[start];
            int i = start, j = end;
            while (i <= j) {
                while (numbers[i] < base && (i < end)) {
                    i++;
                }
                while (numbers[j] > base && (j > start)) {
                    j--;
                }

                if (i <= j) {
                    swap(numbers, i, j);
                    i++;
                    j--;
                }
            }
            if (start < j) {
                quickSort(numbers, start, j);
            }
            if (end > i) {
                quickSort(numbers, i, end);
            }

        }


    }

    private static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;

    }



    public static void main(String[] args) {

        int[] numbers = {20, 40, 50, 10, 60,};
        SortUtils.quickSort(numbers, 0, numbers.length - 1);


    }


}
