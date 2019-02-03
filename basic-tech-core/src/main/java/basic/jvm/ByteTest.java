package basic.jvm;

/**
 * Created by FrankCooper
 * Date 2019/1/27 16:39
 * Description
 */
public class ByteTest {

    private static final int ONE_MB = 1024 * 1024;

    public static void main(String[] args) {

//        testAllocation();
        testPretenureSizeThreshold();

    }


    /**
     * -verbose:gc
     * -XX:+PrintGCDetails
     * -XX:+UseSerialGC
     * -Xms20M
     * -Xmx20M
     * -Xmn10M
     * -XX:SurvivorRatio=8
     */
    private static void testAllocation() {
        byte[] b1 = new byte[2 * 1024 * 1024];
        byte[] b2 = new byte[2 * 1024 * 1024];
        byte[] b3 = new byte[2 * 1024 * 1024];
        byte[] b4 = new byte[4 * 1024 * 1024];
        System.gc();
    }

    /**
     * -verbose:gc
     * -XX:+PrintGCDetails
     * -XX:+UseSerialGC
     * -Xms20M
     * -Xmx20M
     * -Xmn10M
     * -XX:SurvivorRatio=8
     * -XX:PretenureSizeThreshold=6MB
     */
    private static void testPretenureSizeThreshold() {
        byte[] b1 = new byte[7 * ONE_MB];
    }










}
