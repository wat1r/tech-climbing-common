package algorithm.leetcode.basic;

import com.alibaba.fastjson.JSON;

/**
 * Created by FrankCooper
 * Date 2019/4/29 7:49
 * Description
 */
public class BasicSort {

    public static void main(String[] args) {
        int[] arr = {542, 3521, 13459, 852, 742, 46, 2, 1, 633, 32};
        selectSort(arr);

    }


    /**
     * 选择排序
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


}
