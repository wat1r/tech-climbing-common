package algorithm.hustcc;

import algorithm.github.csnotes.sort.Bubble;
import algorithm.github.csnotes.sort.hustcc.*;
import algorithm.utils.SortUtils;
import com.alibaba.fastjson.JSON;
import org.junit.After;
import org.junit.Test;

/**
 * Created by FrankCooper
 * Date 2019/5/9 7:13
 * Description
 */
public class SortTest {

    static int[] arr = {53, 12, 98, 63, 18, 72, 80, 46, 32, 21};


    @Test
    public void testQuickSort() throws Exception {
        QuickSort quickSort = new QuickSort();
        arr = quickSort.sort(arr);
    }


    @Test
    public void testHeapSort() throws Exception {
        HeapSort heapSort = new HeapSort();
        arr = heapSort.sort(arr);
    }


    @Test
    public void testSelectSort() throws Exception {
        SelectSort selectSort = new SelectSort();
        arr = selectSort.sort(arr);
    }

    @Test
    public void testBubbleSort() throws Exception {
        BubbleSort bubbleSort = new BubbleSort();
        arr = bubbleSort.sort(arr);
    }


    @After
    public void printArr() {
        System.out.println(JSON.toJSONString(arr));
    }
}
