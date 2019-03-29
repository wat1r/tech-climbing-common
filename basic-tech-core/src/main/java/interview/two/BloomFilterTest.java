package interview.two;

/**
 * Created by FrankCooper
 * Date 2019/3/28 20:42
 * Description
 */
public class BloomFilterTest {

    public static void main(String[] args) {
        int[] arr = new int[1000];
        int index = 30000;
        int intIndex = index / 32;
        int bitIndex = index % 32;
        System.out.println(intIndex);
        System.out.println(bitIndex);
        arr[intIndex] = (arr[intIndex] | (1 << bitIndex));


    }
}
