package algorithm.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        //一开始就是right值当做num进行比较，左边的汪右扩，右边的往左缩，less 和 left的++，more -- right不变
        while (left < more) {
            if (arr[left] < arr[right]) {
                swap(arr, ++less, left++);
            } else if (arr[left] > arr[right]) {
                swap(arr, --more, left);
            } else {
                left++;
            }
        }
        swap(arr, more, right);//定制,最后的一个（right）与大于x的第一个进行交换
        return new int[]{less + 1, more};
    }

    public static int[] netherlandsPartition(int[] arr, int left, int right, int num) {
        int less = left - 1;
        int more = right + 1;
        int cur = left;
        while (cur < more) {
            if (arr[cur] < num) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > num) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
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
        //下面两个中只会发生一个
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
        swap(arr, 0, --size);//n-1位置和0位置交换
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
        //当前节点的左节点2*i+1,右节点2*i+2,父节点是（i-1）/2
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }


    /**
     * 将当前节点依次向下沉
     * o~size-1已经形成了堆
     *
     * @param arr
     * @param index
     * @param size
     */
    private static void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            //看当前节点index的左右两个孩子节点大小，取出最大的下标
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


    /**
     * 希尔排序
     *
     * @param nums
     */
    public static void shellSort(int[] nums) {
        int n = nums.length;
        //gap不断减半，直到为1
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = 0; i < gap; i++) {
                groupSort(nums, n, i, gap);
            }
        }

    }

    /**
     * 对希尔排序中的单个组进行排序
     * <p>
     * 参数说明：
     * a -- 待排序的数组
     * n -- 数组总的长度
     * i -- 组的起始位置
     * gap -- 组的步长
     * <p> int[] numbers = {80, 30, 60, 40, 20, 10, 50, 70};
     * 组是"从i开始，将相隔gap长度的数都取出"所组成的！
     */
    public static void groupSort(int[] a, int n, int i, int gap) {

        for (int j = i + gap; j < n; j += gap) {
            // 如果a[j] < a[j-gap]，则寻找a[j]位置，并将后面数据的位置都后移。
            if (a[j] < a[j - gap]) {
                int tmp = a[j];//记下来j位置的数值
                int k = j - gap;//j之前gap位置的数组
                while (k >= 0 && a[k] > tmp) {//如果k一直大于0，且k对应的值比当前a[j]位置的值大，进逻辑
                    a[k + gap] = a[k];//k+gap的值与k的值互换
                    k -= gap;//k继续缩减
                }
                a[k + gap] = tmp;
            }
        }
    }


    private static void heapSortII(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
    }


    /**
     * 基数排序
     *
     * @param nums
     */
    private static void radixSort(int[] nums) {
        //找到nums中的最大数
        int max = getMax(nums);
        //找到max的位数
        int times = 0;
        while (max > 0) {
            max /= 10;
            times++;
        }
        //创建10个list（每一位有从0到9，一共10个数，每个list数组用来存放每次迭代中，0-9 每个数组中需要装入的数）
        List<ArrayList> list = new ArrayList<>();
        for (int i = 0; i != 10; i++) {
            //在二维数组中把这10个数组加进去，相当于二维数组的行，从0-9的行
            list.add(new ArrayList());
        }

        //进行times次分配和收集
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < nums.length; j++) {
                int x = nums[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                // list.get(x) 是在返回第0的这个行的list上面的数，然后再 add(arr[j]) 是把当前的这个数添加到末尾去
                list.get(x).add(nums[j]);
            }
            int count = 0;
            for (int j = 0; j < 10; j++) {
                while (list.get(j).size() > 0) {
                    // 把list这个二维list中的第j行返回并赋值给tempList
                    //  把tempList这个数组中的第0个位置的元素，赋值给arr[count]
                    //   把tempList这个数组中的第0个位置的元素删除掉，则后面的元素会自动移上来
                    List<Integer> tempList = list.get(j);
                    nums[count] = tempList.get(0);
                    tempList.remove(0);
                    count++;
                }
            }
        }

        System.out.println(JSON.toJSON(nums));

    }

    private static int getMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (Integer i : nums) {
            max = Math.max(max, i);
        }
        return max;
    }


    /**
     * 桶排序
     *
     * @param nums
     */
    private static void bucketSort(int[] nums) {
        int len = nums.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        int bucketNum = (max - min) / len + 1;
        List<List<Integer>> bucketArr = new ArrayList<>();
        for (int i = 0; i < bucketNum; i++) {
            bucketArr.add(new ArrayList<>());
        }
        for (int i = 0; i < len; i++) {
            int bid = (nums[i] - min) / len;
            bucketArr.get(bid).add(nums[i]);
        }
        for (int i = 0; i < bucketArr.size(); i++) {
            Collections.sort(bucketArr.get(i));
        }
        int index = 0;
        for (int i = 0; i < bucketNum; i++) {
            List<Integer> childList = bucketArr.get(i);
            for (Integer item : childList) {
                nums[index++] = item;
            }
        }
        System.out.println(JSON.toJSON(nums));

    }


    public static void main(String[] args) {

//        int[] numbers = {20, 40, 50, 10, 60,};
//        SortUtils.quickSort(numbers, 0, numbers.length - 1);
//        SortUtils.bubbleSort(numbers);
//        SortUtils.selectSort(numbers);
//        SortUtils.insertSort(numbers);

//        SortUtils.testOne();
//        int[] numbers = {80, 30, 60, 40, 20, 10, 50, 70};
//        SortUtils.shellSort(numbers);

        int[] nums = {542, 3521, 13459, 852, 742, 46, 2, 1, 633, 32};
//        radixSort(nums);
//        myRadixSort(nums);
        bucketSort(nums);


    }


}
