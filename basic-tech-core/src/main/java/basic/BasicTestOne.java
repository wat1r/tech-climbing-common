package basic;

import org.apache.commons.lang3.StringUtils;

/**
 * @Date 2020/9/8
 * @Author Frank Cooper
 * @Description
 */
public class BasicTestOne {


    static BasicTestOne handler = new BasicTestOne();


    public static void main(String[] args) {
//        handler.testOne();
        handler.testStrip();
    }


    private void testOne() {

        Integer res = Integer.MAX_VALUE + 20;
        System.out.println(res);
    }


    private void testStrip() {
        String s = "  i love u   ";
        String res = StringUtils.strip(s);
        System.out.printf("%s\n", s);
        String str = "[asdf,dsafj[als[dfk]j;ldkfj;sald]";
        String unit = StringUtils.strip(str, "[]d");
        System.out.printf("%s\n", unit);
    }


}
