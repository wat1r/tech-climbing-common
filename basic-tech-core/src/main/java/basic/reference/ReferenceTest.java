package basic.reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Created by FrankCooper
 * Date 2019/5/29 21:50
 * Description
 */
public class ReferenceTest {
    static ReferenceTest handler = new ReferenceTest();

    public static void main(String[] args) {
        handler.testOne();
        System.out.println("--------------------------");
        handler.testTwo();
    }



    public void testOne(){
        SoftReference<String> sr = new SoftReference<String>(new String("hello"));
        System.out.println(sr.get());
        System.gc();                //通知JVM的gc进行垃圾回收
        System.out.println(sr.get());
    }

    public void testTwo(){
        WeakReference<String> sr = new WeakReference<String>(new String("hello"));
        System.out.println(sr.get());
        System.gc();                //通知JVM的gc进行垃圾回收
        System.out.println(sr.get());
    }

}
