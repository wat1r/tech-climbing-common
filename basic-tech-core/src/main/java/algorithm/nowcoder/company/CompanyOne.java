package algorithm.nowcoder.company;

import java.util.Scanner;

/**
 * Created by FrankCooper
 * Date 2019/4/5 17:47
 * Description
 */
public class CompanyOne {

    public static CompanyOne handler = new CompanyOne();


    /**
     * 最大乘积 PDD
     *
     * @param nums //在遍历数组是需要记录第一，第二，第三大，和最小，次小的数（负负的正）
     *             // 返回Math.max(max1*max2*max3,max1*min1*min2)
     */
    public static void getMaxMuti(long[] nums, int n) {

        long max1 = 0, max2 = 0, max3 = 0, min1 = 0, min2 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                if (nums[i] > max1) {
                    max3 = max2;
                    max2 = max1;
                    max1 = nums[i];
                } else if (nums[i] > max2) {
                    max3 = max2;
                    max2 = nums[i];
                } else if (nums[i] > max3) {
                    max3 = nums[i];
                } else if (nums[i] < min1) {
                    min2 = min1;
                    min1 = nums[i];
                } else if (nums[i] > min1 && nums[i] < min2) {
                    min2 = nums[i];
                }
            }
        }
        System.out.println(Math.max(max1 * max2 * max3, max1 * min1 * min2));
    }


    private static void bigIntMuti() {

        Scanner in = new Scanner(System.in);
        String num1 = in.nextBigDecimal().toString();
        String num2 = in.nextBigDecimal().toString();
        int[] ret = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ret[i + j] += (ret[i + j + 1] + x * y) / 10;
                ret[i + j + 1] = (ret[i + j + 1] + x * y) % 10;

            }
        }
        String s = "";
        for (int i = 0; i < ret.length; i++) {
            if (i == 0 && ret[i] == 0) continue;
            s += ret[i];
        }
        System.out.println(s);
    }

    public String multiply(String num1, String num2) {
        int[] n1 = new int[num1.length()];
        int[] n2 = new int[num2.length()];
        int[] result = new int[num1.length() + num2.length()];

        for (int i = 0; i < num1.length(); i++) {
            n1[i] = num1.charAt(i) - '0';
        }
        for (int i = 0; i < num2.length(); i++) {
            n2[i] = num2.charAt(i) - '0';
        }
        //逐个相乘，因为你会发现。AB*CD = AC(BC+AD)BD , 然后进位。
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                result[i + j] += n1[i] * n2[j];
            }
        }
        //满10进位，从后往前满十进位
        for (int i = result.length - 1; i > 0; i--) {
            result[i - 1] += result[i] / 10;
            result[i] = result[i] % 10;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length - 1; i++) {
            sb.append(result[i]);
        }
        return sb.toString();
    }


    public static void main(String[] args) {

//        long[] nums = {10, 15, -9, 6, 80, -60};
//        handler.getMaxMuti(nums, 6);

//        handler.bigIntMuti();
        handler.multiply("2345", "6789");

    }


}
