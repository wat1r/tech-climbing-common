package algorithm.github.csnotes.sort.hustcc;

import java.util.Arrays;

/**
 * Created by FrankCooper
 * Date 2019/5/9 20:55
 * Description
 */
public class SelectSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        // 总共要经过 N-1 轮比较
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            // 每轮需要比较的次数 N-i
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    // 记录目前能找到的最小值元素的下标
                    min = j;
                }
            }
            if (min != i) {
                swap(arr, min, i);
            }
        }
        return arr;
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
