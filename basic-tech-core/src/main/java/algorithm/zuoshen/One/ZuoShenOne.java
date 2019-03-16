package algorithm.zuoshen.One;

import java.util.LinkedList;

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


    /**
     * 左神P31，最大值减去最小值小于或等于num的子数组的数量
     *
     * @param arr
     * @param num
     * @return
     */
    public int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        int L = 0, R = 0;
        int res = 0;
        while (L < arr.length) {
            while (R < arr.length) {
                //qmin更新进最小值的R
                while (!qmin.isEmpty() && qmin.peekLast() >= arr[R]) {
                    qmin.pollLast();
                }
                qmin.addLast(R);
                //qmax更新进最大值的R
                while (!qmax.isEmpty() && qmax.peekLast() <= arr[R]) {
                    qmax.pollLast();
                }
                qmax.addLast(R);
                //如果当前的qmax中的值减去qmin中的值大于num，其所有的子数组都不会满足条件，break掉
                if (arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num) {
                    break;
                }
                R++;
            }
            //检查qmin的下标是否过期
            if (qmin.peekFirst() == L) {
                qmin.pollFirst();
            }
            //检查qmax的下标是否过期
            if (qmax.peekFirst() == L) {
                qmax.pollFirst();
            }
            res += R - L;
            L++;
        }
        return res;
    }

    public static void main(String[] args) {

        int[] arr = {5, 10, 25};
        handler.coins1(arr, 15);


    }
}
