package basic.param;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/12/22 19:02
 * @description:
 */
public class ParamsTest {


    public static void main(String[] args) {

        test1("11", "22", "33");
        test1("44", "55");
    }


    private static void test1(String... strs) {
        System.out.println(String.format("%s，%s %s", strs[0], strs[1], strs[2]));
    }
}
