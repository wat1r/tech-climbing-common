package algorithm;

import algorithm.leetcode.two.LeetCodeClassification;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by FrankCooper
 * Date 2019/2/28 23:12
 * Description
 */
public class GreedTest {

    static LeetCodeClassification handler = new LeetCodeClassification();

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }


    @Test
    public void testOne() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] g = stringToIntegerArray(line);
            line = in.readLine();
            int[] s = stringToIntegerArray(line);
            int ret = handler.findContentChildren(g, s);
            String out = String.valueOf(ret);
            System.out.print(out);
        }
    }

}
