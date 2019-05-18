package algorithm.nowcoder.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by FrankCooper
 * Date 2019/4/6 21:27
 * Description
 */
public class Main {


    public static void main(String[] args) throws IOException {

        Main handler = new Main();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = buffer.readLine().split(" ");
        int[] nums = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            nums[i] = Integer.parseInt(strs[i]);
        }
        System.out.println(handler.getMaxPerimeter(nums));
    }


    /**
     * 凑三角形 iQiYi
     * 三个边长排序，如果长度小的两边加起来大于最长的边，肯定可以组成三角形，
     * 某则，最长的边就要进行切割，切割到a + b - 1, 此时周长为a + b + a + b - 1 =
     * 输出一个整数
     */
    public int getMaxPerimeter(int[] help) {
        Arrays.sort(help);
        int min1 = help[0];
        int min2 = help[1];
        int max = help[2];
        int res = max + min1 + min2;
        if (max >= (min1 + min2)) {
            res = 2 * (min1 + min2) - 1;
        }
        return res;
    }

}
