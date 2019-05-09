package algorithm.github.csnotes.sort.hustcc;

import java.util.Arrays;

/**
 * Created by FrankCooper
 * Date 2019/5/9 8:10
 * Description
 */
public class HeapSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int size = arr.length;
        swap(arr, 0, --size);
        while (size > 0) {
            heapify(arr, 0, size);
            swap(arr, 0, --size);
        }
        return arr;
    }

    /**
     * 调整出一个大根堆，完全二叉树：新的一层从左往右是依次补齐的
     *
     * @param arr
     * @param index
     */
    private void heapInsert(int[] arr, int index) {
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
    private void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            int right = left + 1;
            //看当前节点index的左右两个孩子节点大小，取出最大的下标
            int largest = right < size && arr[right] > arr[left] ?right : left;
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

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
