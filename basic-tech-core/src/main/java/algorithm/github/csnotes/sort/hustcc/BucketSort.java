package algorithm.github.csnotes.sort.hustcc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by FrankCooper
 * Date 2019/5/10 22:37
 * Description
 */
public class BucketSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        return bucketSort(arr, arr.length);
    }
    private int[] bucketSort(int[] arr, int len) {
        int max = arr[0], min = arr[0];
        for (int value : arr) {
            max = Math.max(max, value);
            min = Math.min(min, value);
        }
        int bucketNum = (max - min) / len + 1;
        List<List<Integer>> bucketArr = new ArrayList<>();
        for (int i = 0; i < bucketNum; i++) {
            bucketArr.add(new ArrayList<>());
        }
        for (int i = 0; i < arr.length; i++) {
            int bid = (arr[i] - min) / len;
            bucketArr.get(bid).add(arr[i]);
        }
        for (int i = 0; i < bucketArr.size(); i++) {
            Collections.sort(bucketArr.get(i));
        }
        int index = 0;
        for (int i = 0; i < bucketNum; i++) {
            List<Integer> child = bucketArr.get(i);
            for (int value : child) {
                arr[index++] = value;
            }
        }
        return arr;
    }
}
