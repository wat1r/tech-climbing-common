package algorithm.github.csnotes.sort.hustcc;

import java.util.Arrays;

/**
 * Created by FrankCooper
 * Date 2019/5/8 21:38
 * Description
 */
public class QuickSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        return quickSort(arr, 0, arr.length - 1);
    }

    private int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            //排序partitionIndex的左右部分
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = left;
        //起始的index是pivot的右边一个
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            //当i的值小于pivot的值，交换i与index，index++，目的是将小于pivot的值放在index的左边，大于的放在右边
            if (arr[i] < arr[pivot]) {
                swap(arr, index, i);
                index++;
            }
        }
        //当for loop结束后，index停留在大于pivot的有部分的第一个位置，此时要交换pivot与index-1的值，使用pivot划分大小两部分
        swap(arr, pivot, index - 1);
        return index - 1;
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
