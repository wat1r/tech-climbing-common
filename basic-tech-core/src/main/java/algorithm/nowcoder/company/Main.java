package algorithm.nowcoder.company;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by FrankCooper
 * Date 2019/4/6 21:27
 * Description
 */
public class Main {


    public static void main(String[] args) {

        Main handler = new Main();

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();// n个学生
        int[] h = new int[n];// 学生
        for (int i = 0; i < h.length; i++) {
            h[i] = sc.nextInt();
        }

        int m = sc.nextInt();// m个巧克力
        int[] w = new int[m];// 巧克力
        for (int i = 0; i < w.length; i++) {
            w[i] = sc.nextInt();
        }
        int result = handler.countChildrenByAllocatingChocolate(n, h, m, w);
        System.out.println(result);
        sc.close();

    }


    /**
     * 六一儿童节 PDD
     *
     * @param n h数组的size
     * @param h 每个小朋友需要的巧克力大大小的数组 h[i]
     * @param m w数组的size
     * @param w w数组，表示巧克力的重量 w[j]
     * @return
     */
    public int countChildrenByAllocatingChocolate(int n, int[] h, int m, int[] w) {
        Arrays.sort(h);
        Arrays.sort(w);
        int res = 0;
        for (int i = 0, j = 0; i < n && j < m; ) {
            if (w[j] >= h[i]) {
                i++;
                res++;
            }
            j++;
        }
        return res;
    }
}
