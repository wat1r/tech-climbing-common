package interview;

import com.alibaba.fastjson.JSON;

/**
 * Created by FrankCooper
 * Date 2019/3/27 22:23
 * Description 手撕代码系列
 */
public class TestOne {


    private static TestOne handler = new TestOne();


    /**
     * 手写一个冒泡排序
     *
     * @param arr
     * @return
     */
    private int[] bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    private void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }


    /**
     * 手写一个快排
     *
     * @param arr
     */
    private void quickSort(int[] arr, int left, int right) {
        if (arr == null || arr.length < 2) {
            return;
        }
        if (left < right) {
            int[] partition = partition(arr, left, right);
            quickSort(arr, 0, partition[0] - 1);
            quickSort(arr, partition[1] + 1, right);
        }

    }

    private int[] partition(int[] arr, int left, int right) {
        int less = left - 1;
        int more = right;
        while (left < more) {
            if (arr[left] < arr[right]) {
                swap(arr, ++less, left++);
            } else if (arr[left] > arr[right]) {
                swap(arr, left, --more);
            } else {
                left++;
            }
        }
        swap(arr, more, right);
        System.out.println(JSON.toJSONString(arr));
        return new int[]{less + 1, more};
    }


    private int[] quickSortByPivot(int[] arr) {
        return quickSortByPivot(arr, 0, arr.length - 1);

    }

    private int[] quickSortByPivot(int[] arr, int left, int right) {

        if (left < right) {
            int partitionIndex = partitionByPivot(arr, left, right);
            quickSortByPivot(arr, left, partitionIndex - 1);
            quickSortByPivot(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    private int partitionByPivot(int[] arr, int left, int right) {
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            //当i对应的值大于pivot的值，交换i 与 index
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        //最小区域的最后一个与pivot交换
        swap(arr, pivot, index - 1);
        return index - 1;
    }


    public static void main(String[] args) {
//        System.out.println(JSON.toJSONString(handler.bubbleSort(new int[]{60, 30, 80, 20, 90, 10})));
        int[] arr = new int[]{60, 30, 80, 20, 90, 10};
//        handler.quickSort(arr, 0, arr.length - 1);
        handler.quickSortByPivot(arr, 0, arr.length - 1);
        System.out.println(JSON.toJSONString(arr));
    }
}
