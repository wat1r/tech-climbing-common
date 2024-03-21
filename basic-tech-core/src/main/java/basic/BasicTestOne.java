package basic;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/9/8
 * @Author Frank Cooper
 * @Description
 */
public class BasicTestOne {


    static BasicTestOne handler = new BasicTestOne();


    public static void main(String[] args) {
//        handler.testOne();
//        handler.testStrip();
        handler.testSubList();
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


    private void testSubList() {
        List<String> parentList = new ArrayList<String>();

        for (int i = 0; i < 5; i++) {
            parentList.add(String.valueOf(i));
        }

        List<String> subList = parentList.subList(1, 3);
        for (String s : subList) {
            System.out.println(s);//output: 1, 2
        }
        subList.clear();

        System.out.println(parentList.size());//output: 3
//        List<String> subList = parentList.subList(1, 3);
    }

}
