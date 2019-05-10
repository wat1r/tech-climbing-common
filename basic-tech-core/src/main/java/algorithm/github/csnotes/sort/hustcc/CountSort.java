package algorithm.github.csnotes.sort.hustcc;

import java.util.Arrays;

/**
 * Created by FrankCooper
 * Date 2019/5/10 22:26
 * Description
 */
public class CountSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        int max = getMax(arr);
        return countSort(arr, max);
    }

    private int[] countSort(int[] arr, int max) {
        int[] bucket = new int[max + 1];
        for (int i : arr) {
            bucket[i]++;
        }
        int index = 0;
        //i作为bucket的下标
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i] > 0) {
                arr[index++] = i;
                bucket[i]--;
            }
        }
        return arr;
    }
    private int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }
}
