package algorithm.zuoshen.One;

/**
 * Created by FrankCooper
 * Date 2019/3/13 0:09
 * Description
 */
public class ZuoShenOne {

    private static ZuoShenOne handler = new ZuoShenOne();


    public int coins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    /**
     * 使用arr[index-N-1]这些面值的钱组成aim，返回总的方法数
     *
     * @param arr
     * @param index
     * @param aim
     * @return
     */
    private int process1(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; arr[index] * i <= aim; i++) {
                res += process1(arr, index + 1, aim - arr[index] * i);
            }
        }
        return res;
    }


    public static void main(String[] args) {

        int[] arr = {5, 10, 25};
        handler.coins1(arr, 15);


    }
}
