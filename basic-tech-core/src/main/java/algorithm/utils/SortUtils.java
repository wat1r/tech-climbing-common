package algorithm.utils;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

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

    public static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;

    }

    /**
     * 改进版的快速排序，递归的方式
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSortRecursive(int[] arr, int left, int right) {
        if (arr == null || arr.length < 2) {
            return;
        }
        if (left < right) {
            //随机快速排序
//            swap(arr, left + (int) (Math.random() * (right - left + 1)), right);
            int[] partition = partition(arr, left, right);
            quickSortRecursive(arr, left, partition[0] - 1);
            quickSortRecursive(arr, partition[1] + 1, right);
        }
    }


    private static int[] partition(int[] arr, int left, int right) {
        int less = left - 1;
        int more = right;//一开始时将right括进去右边的区域
        while (left < more) {
            if (arr[left] < arr[right]) {
                swap(arr, ++less, left++);
            } else if (arr[left] > arr[right]) {
                swap(arr, --more, left);
            } else {
                left++;
            }
        }
        swap(arr, more, right);//定制
        return new int[]{less + 1, more};
    }


    /**
     * 冒泡排序：将最大的数字沉到最底
     *
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int end = arr.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
        System.out.println(JSON.toJSON(arr));
    }


    /**
     * 选择排序：0-n-1 找到min放在0位置，1-n-1找到min放在1位置...
     * 记录下后面的最小数的index与第一个数swap
     *
     * @param arr
     */
    private static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
        System.out.println(JSON.toJSON(arr));
    }


    /**
     * 插入排序：像打扑克的牌
     * O(N^2) 按最差的情况估计时间复杂度
     *
     * @param arr
     */
    private static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {//从i-1开始
                swap(arr, j, j + 1);
            }
        }

        System.out.println(JSON.toJSON(arr));
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
        mergeSortProcess(arr, left, mid);//T(n/2)，左侧递归拍好序列
        mergeSortProcess(arr, mid + 1, right);//T(n/2)，右侧递归拍好序列
        merge(arr, left, mid, right);//借助一个辅助数组将arr排好序 O(N)
        //T(N) =2T(n/2)+O(N)
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int p1 = left, p2 = mid + 1, i = 0;//左右两个指针
        while (p1 <= mid && p2 <= right) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        //下面两个钟只会发生一个
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[left + j] = help[j];//注意下标
        }

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
        //制作大根堆
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        //交换堆顶元素与最后一个
        int size = arr.length;
        swap(arr, 0, --size);
        while (size > 0) {
            heapify(arr, 0, size);
            swap(arr, 0, --size);
        }
    }

    /**
     * 调整出一个大根堆，完全二叉树：新的一层从左往右是依次补齐的
     *
     * @param arr
     * @param index
     */
    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {//当前节点的左节点2*i+1,右节点2*i+2,父节点是（i-1）/2
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }


    /**
     * 将当前节点依次向下沉
     *
     * @param arr
     * @param index
     * @param size
     */
    private static void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            //看当前节点index的左右两个孩子节点大小，取出最大的，
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            //取出最大的与当前的比较，记录下大的
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            //交换当前的与大的，然后将largest的赋给index，继续寻找左孩子节点
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;

        }

    }


    //------------------对数器-开始-----------------//

    //对数器:随机数组发生器
    private static int[] generateRandomArray(int size, int value) {
        int[] arr = new int[(int) ((size + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((value + 1) * Math.random()) - (int) ((value) * Math.random());
        }
        return arr;
    }

    //对数器：绝对正确的方法
    private static void rightMethod(int[] arr) {
        Arrays.sort(arr);
    }


    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void testOne() {
        int testTime = 500;
        int maxSize = 100;
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            selectSort(arr1);
            rightMethod(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

//        int[] arr = generateRandomArray(maxSize, maxValue);
//        printArray(arr);
//        radixSort(arr);
//        printArray(arr);
    }

    //------------------对数器-结束-----------------//

    public static void main(String[] args) {

        int[] numbers = {20, 40, 50, 10, 60,};
//        SortUtils.quickSort(numbers, 0, numbers.length - 1);
//        SortUtils.bubbleSort(numbers);
//        SortUtils.selectSort(numbers);
//        SortUtils.insertSort(numbers);

        SortUtils.testOne();
    }


}
