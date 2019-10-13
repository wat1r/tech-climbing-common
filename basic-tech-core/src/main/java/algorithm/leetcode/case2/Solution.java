package algorithm.leetcode.case2;

import java.util.Random;

/**
 * Created by FrankCooper
 * Date 2019/2/25 23:46
 * Description
 */
public class Solution {
    private int[] srcNums = null;
    private int[] tmpNums = null;

    private Random random = new Random();

    public Solution(int[] nums) {
        srcNums = nums.clone();
        tmpNums = nums;
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return srcNums;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        int n = tmpNums.length;
        for (int i = 0; i < n; i++) {
            int rand = randRange(i, n);
            swap(tmpNums, i, rand);
        }
        return tmpNums;
    }

    private int randRange(int min, int max) {
        return random.nextInt(max - min) + min;
    }


    private void swap(int[] nums, int m, int n) {
        int temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }


}

