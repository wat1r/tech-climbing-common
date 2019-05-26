package algorithm.nowcoder.offer;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2019/5/25 16:15
 * Description 专题的合辑
 */
public class TopicProcess {

    private static TopicProcess handler = new TopicProcess();


    /**
     * 剑指offer29，超过数据一半的数 Partition 排序算法
     */
    public int moreThanHalfNum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int index = partition(arr, 0, arr.length - 1);
        int middle = arr.length / 2;
        while (index != middle) {
            if (index > middle) {
                index = partition(arr, 0, index - 1);
            } else {
                index = partition(arr, index + 1, arr.length - 1);
            }
        }
        if (checkMoreThanHalf(arr, arr[middle])) {
            return arr[middle];
        } else {
            return 0;
        }
    }


    private int partition(int[] arr, int left, int right) {
        int index = left;
        for (int i = left; i < right; i++) {
            if (arr[i] <= arr[right]) {
                swap(arr, i, index++);
            }
        }
        swap(arr, right, index);
        return index;
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    private static boolean checkMoreThanHalf(int[] arr, int num) {
        int times = 0;
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            if (num == arr[i]) {
                times++;
            }
        }
        if (times * 2 <= length) {
            return false;
        }
        return true;
    }


    /**
     * 最小的K个数 剑指offer 30
     *
     * @param arr
     * @param k
     * @return
     */
    public ArrayList<Integer> getLeastNumbers(int[] arr, int k) {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        if (arr == null || arr.length == 0 || arr.length < k || k == 0) {
            return resultList;
        }
        int index = partition(arr, 0, arr.length - 1);
        while (index != k - 1) {
            if (index > k - 1) {
                index = partition(arr, 0, index - 1);
            } else {
                index = partition(arr, index + 1, arr.length - 1);
            }
        }
        for (int i = 0; i < k; i++) {
            resultList.add(arr[i]);
        }
        return resultList;
    }

    /**
     * 最小的K个数 剑指offer 30
     *
     * @param arr
     * @param k
     * @return
     */
    public ArrayList<Integer> getLeastNumbers2nd(int[] arr, int k) {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        if (arr == null || arr.length == 0 || arr.length < k || k == 0) {
            return resultList;
        }
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        //交换堆顶元素与最后一个
        int size = arr.length;
        swap(arr, 0, --size);//n-1位置和0位置交换
        while (size > 0) {
            buildMaxHeap(arr, 0, size);
            swap(arr, 0, --size);
        }
        for (int i = 0; i < k; i++) {
            resultList.add(arr[i]);
        }
        return resultList;
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
     * 构建大顶堆
     */
    private void buildMaxHeap(int[] arr, int index, int size) {
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

    /**
     * 最小的K个数 剑指offer 30
     *
     * @param arr
     * @param k
     * @return
     */
    public ArrayList<Integer> getLeastNumbers3rd(int[] arr, int k) {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        if (arr == null || arr.length == 0 || arr.length < k || k == 0) {
            return resultList;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Comparator.reverseOrder());
        for (int i = 0; i < arr.length; i++) {
            if (maxHeap.size() != k) {
                maxHeap.offer(arr[i]);
            } else if (maxHeap.peek() > arr[i]) {
                maxHeap.poll();
                maxHeap.offer(arr[i]);
            }
        }
        resultList.addAll(maxHeap);
        return resultList;
    }


    /**
     * 寻找和为定值的两个数，编程之法2.2，剑指offer41
     *
     * @param arr
     * @param sum
     * @return
     */
    public ArrayList<Integer> findNumbersWithSum(int[] arr, int sum) {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        if (arr == null || arr.length < 2) {
            return resultList;
        }
        int l = 0, r = arr.length - 1;
        long curSum;
        while (l < r) {
            curSum = arr[l] + arr[r];
            if (curSum == sum) {
                resultList.add(arr[l]);
                resultList.add(arr[r]);
                break;
            } else if (curSum < sum) {
                l++;
            } else {
                r--;
            }
        }
        return resultList;
    }


    /**
     * 举一反三：寻找和为定值的两个数的下标 编程之法2.2
     *
     * @param arr
     * @param sum
     * @return
     */
    public int[] findNumbersWithSumIndex(int[] arr, int sum) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int[] sumIndex = new int[2];
        for (int i = 0; i < arr.length; i++) {
            if (hashMap.containsKey(sum - arr[i])) {
                sumIndex[0] = i + 1;
                sumIndex[1] = hashMap.get(sum - arr[i]) + 1;
                break;
            }
            hashMap.put(arr[i], i);
        }
        return sumIndex;
    }


    /**
     * 剑指offer31， 编程之法2.4 利用数组的特性
     *
     * @param arr
     * @return
     */
    public int findGreatestSumOfSubArray(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        int cur = arr[0], res = cur;
        for (int i = 1; i < arr.length; i++) {
            cur += arr[i];
            res = Math.max(res, cur);
            cur = cur > 0 ? cur : 0;
        }
        return res;
    }


    /**
     * 剑指offer31， 编程之法2.4 DP
     *
     * @param arr
     * @return
     */
    public int findGreatestSumOfSubArray2nd(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        int curSum = 0;
        int maxSum = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (curSum >= 0) {
                curSum += arr[i];
            } else {
                curSum = arr[i];
            }
            if (curSum > maxSum) {
                maxSum = curSum;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {

        handler.findGreatestSumOfSubArray(new int[]{1, -2, 3, 10, -4, 7, 2, -5});

    }

}
