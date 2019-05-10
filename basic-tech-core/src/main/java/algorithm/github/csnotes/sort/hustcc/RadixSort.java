package algorithm.github.csnotes.sort.hustcc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by FrankCooper
 * Date 2019/5/10 22:51
 * Description
 */
public class RadixSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        int times = getMaxLen(arr);

        List<ArrayList> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new ArrayList());
        }
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < arr.length; j++) {
                int x = arr[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                list.get(x).add(arr[j]);
            }
            int index = 0;
            for (int j = 0; j < 10; j++) {
                while (list.get(j).size() > 0) {
                    ArrayList<Integer> tempList = list.get(j);
                    arr[index++] = tempList.get(0);
                    tempList.remove(0);
                }
            }
        }
        return arr;
    }

    private int getMaxLen(int[] arr) {
        int max = arr[0];
        for (int value : arr) {
            max = Math.max(max, value);
        }
        int len = 0;
        for (int i = max; i != 0; i /= 10) {
            len++;
        }
        return len;
    }
}
