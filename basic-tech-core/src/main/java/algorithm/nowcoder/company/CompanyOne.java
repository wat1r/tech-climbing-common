package algorithm.nowcoder.company;

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


    public static void main(String[] args) {

        long[] nums = {10, 15, -9, 6, 80, -60};
        handler.getMaxMuti(nums, 6);

    }


}
