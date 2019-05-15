package algorithm.github.csnotes.sort.hustcc;

import java.util.Arrays;

/**
 * Created by FrankCooper
 * Date 2019/5/14 22:28
 * Description
 */
public class ShellSort implements IArraySort {
    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        int n = arr.length;
        int temp = 0;
        int j = 0;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                temp = arr[i];
                for (j = i - gap; j >= 0; j -= gap) {
                    if (temp < arr[j]) {//如果 arr[i] < arr[j]，则寻找arr[i]位置，并将后面数据的位置都后移。
                        arr[j + gap] = arr[j];
                    } else {
                        break;
                    }
                }
                arr[j + gap] = temp;
            }
        }
        return arr;
    }
}
