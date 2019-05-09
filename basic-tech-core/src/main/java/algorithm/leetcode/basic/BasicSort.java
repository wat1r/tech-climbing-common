package algorithm.leetcode.basic;

import com.alibaba.fastjson.JSON;

/**
 * Created by FrankCooper
 * Date 2019/4/29 7:49
 * Description
 */
public class BasicSort {


    /**
     * 选择排序
     *
     * @param arr
     */
    private static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[minIndex] > arr[j] ? j : minIndex;
            }
            swap(arr, minIndex, i);
        }
        System.out.println(JSON.toJSON(arr));
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 堆排序
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //交换当前节点与其父节点的值 建立大根堆O（N）
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        //交换堆顶元素与最后一个 堆顶是最大值
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    /**
     * 调整出一个大根堆，完全二叉树：新的一层从左往右是依次补齐的
     *
     * @param arr
     * @param index
     */
    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[index - 1] / 2) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }

    }

    /**
     * 0 - heapSize-1 这个范围内形成堆 index位置的值变小了，要将index对应的值往下面沉
     *
     * @param arr
     * @param index
     * @param heapSize
     */
    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            //判断左右孩子上的值哪个大
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            //largest!=index
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }


    private static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSortProcess(arr, 0, arr.length - 1);
    }

    private static void mergeSortProcess(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSortProcess(arr, left, mid);
        mergeSortProcess(arr, mid + 1, right);
        merge(arr, left, mid, right);

    }


    private static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int p1 = left, p2 = mid + 1, i = 0;
        while (p1 <= mid && p2 <= right) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[left + j] = help[j];
        }

    }


    public static void main(String[] args) {
        int[] arr = {542, 3521, 13459, 852, 742, 46, 2, 1, 633, 32};
//        selectSort(arr);
        mergeSort(arr);
        System.out.println(JSON.toJSONString(arr));
    }


}
