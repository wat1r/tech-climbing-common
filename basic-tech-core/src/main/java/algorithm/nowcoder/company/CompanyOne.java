package algorithm.nowcoder.company;

import java.util.*;

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


    /**
     * 六一儿童节 PDD 这题与455. 分发饼干 Easy 贪心
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


    /**
     * iQiYi，判断题
     */
    public void getCorrectAndWrongAnswer() {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        String[] ss = s.split(" ");
        int n = Integer.parseInt(ss[0]);
        int t = Integer.parseInt(ss[1]);
        int a = Integer.parseInt(ss[2]);
        int res = Math.min(n - t, n - a) + Math.min(t, a);
        System.out.println(res);
    }

    /**
     * iQiYi 去重复字符
     *
     * @param str
     */
    public void removeRepitiveCharacter(String str) {
        char[] chas = str.toCharArray();
        int[] help = new int[256];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chas.length; i++) {
            if (help[chas[i] - 'A'] == 0) {
                help[chas[i] - 'A'] = 1;
                sb.append(chas[i]);
            }
        }
        System.out.println(sb.toString());
    }


    /**
     * iQiYi 排序
     * <p>
     * 1、输入以后，复制一个一样的数组
     * 2、对一个排序，另一个不变
     * 3、对比两个数组有什么区别，对比每一位，有不同则加一
     */
    public int moveAndSort(int[] arr) {
        int[] mirror = Arrays.copyOf(arr, arr.length);
        Arrays.sort(mirror);
        int times = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != mirror[i]) {
                times++;
            }
        }
        return times;
    }


    /**
     * iQiYi 字符串价值
     * 每次最大的值减一，得到的价值最小。
     *
     * @param str
     * @param k
     * @return
     */
    public int getMinValueOfStr(String str, int k) {
        char[] chas = str.toCharArray();
        HashMap<Character, Integer> hashmap = new HashMap();
        for (char c : chas) {
            hashmap.putIfAbsent(c, 0);
            hashmap.put(c, hashmap.get(c) + 1);
        }
        List<Integer> valueList = new ArrayList<>(hashmap.values());
        int[] help = new int[valueList.size()];
        for (int i = 0; i < valueList.size(); i++) {
            help[i] = valueList.get(i);
        }
        for (int i = 0; i < k; i++) {
            Arrays.sort(help);
            help[help.length - 1]--;
        }
        int sum = 0;
        for (int i = 0; i < help.length; i++) {
            sum += help[i] * help[i];
        }
        return sum;
    }


    /**
     * 凑三角形 iQiYi
     * 三个边长排序，如果长度小的两边加起来大于最长的边，肯定可以组成三角形，
     * 某则，最长的边就要进行切割，切割到a + b - 1, 此时周长为a + b + a + b - 1 =
     * 输出一个整数
     * 要sout结果
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


    public static void main(String[] args) {

//        long[] nums = {10, 15, -9, 6, 80, -60};
//        handler.getMaxMuti(nums, 6);

//        handler.bigIntMuti();
//        handler.multiply("2345", "6789");

        handler.removeRepitiveCharacter("banana");

    }


}
