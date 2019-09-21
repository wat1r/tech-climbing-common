package algorithm.nowcoder.company;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by FrankCooper
 * Date 2019/4/6 21:27
 * Description
 */
public class Main {


    public static void main(String[] args) throws IOException {

        Main handler = new Main();
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        Scanner sc = new Scanner(System.in);
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        System.out.println(handler.pairMatch(a));

    }

    public int pairMatch(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int max = 0, min = Integer.MAX_VALUE;
        for (int i = 0, j = n - 1; i < n / 2; i++, j--) {
            int temp = nums[i] + nums[j];
            max = Math.max(max, temp);
            min = Math.min(min, temp);
        }
        return max - min;
    }


}
