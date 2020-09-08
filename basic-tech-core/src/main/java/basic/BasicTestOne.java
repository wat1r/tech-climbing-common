package basic;

/**
 * @Date 2020/9/8
 * @Author Frank Cooper
 * @Description
 */
public class BasicTestOne {


    static BasicTestOne handler = new BasicTestOne();


    public static void main(String[] args) {
        handler.testOne();
    }


    private void testOne() {

        Integer res = Integer.MAX_VALUE + 20;
        System.out.println(res);
    }


}
