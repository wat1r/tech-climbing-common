package basic.lo;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/12/15 14:25
 * @description:
 */
public class LongTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }





   private static void test(){
	int  d= 1;
	
   }

    private static void test1() {
        Long a = 10000L;
        Long b = 10000L;
        System.out.println(a == b);
        Integer c = 127;
        Integer d = 127;
        System.out.println(c == d);
        c= 128;
        d = 128;
        System.out.println(c == d);
    }

    private static void test2(){
        Integer num = 0;
        System.out.println(num ==0 );
    }
}
