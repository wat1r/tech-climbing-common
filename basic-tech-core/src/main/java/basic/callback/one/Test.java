package basic.callback.one;

/**
 * Created by FrankCooper
 * Date 2019/2/8 13:55
 * Description
 */
public class Test {
    public static void main(String[] args) {
        /**
         * new 一个小李
         */
        Li li = new Li();

        /**
         * new 一个小王
         */
        Wang wang = new Wang(li);

        /**
         * 小王问小李问题
         */
        wang.askQuestion("1 + 1 = ?");
    }

}
