package github.jcSprout;

/**
 * Created by FrankCooper
 * Date 2019/2/9 12:11
 * Description
 */
public class MultiThread {


    public static void testOne() {
        synchronized (MultiThread.class) {
            System.out.println("Synchronize");
        }
    }


    public static void main(String[] args) {
        testOne();
    }

}
