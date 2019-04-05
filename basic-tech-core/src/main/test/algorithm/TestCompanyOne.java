package algorithm;

import algorithm.nowcoder.company.CompanyOne;
import org.junit.Test;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by FrankCooper
 * Date 2019/4/5 18:05
 * Description
 */
public class TestCompanyOne {

    @Test
    public void testGetMaxMuti() {
        int n = (int) (Math.random() * 100);
        long[] array = new long[n];
        int min = Integer.MIN_VALUE, max = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            array[i] = min + (int) (Math.random() * (max + 1 - min));
        }
        CompanyOne.getMaxMuti(array, n);
    }
}
