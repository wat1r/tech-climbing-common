package basic.jvm;

/**
 * Created by FrankCooper
 * Date 2019/1/27 14:20
 * Description
 */
public class JVMReference {

    private Object instance;

    public JVMReference() {
        byte[] m = new byte[20 * 1024 * 1024];
    }

    public static void main(String[] args) {

        JVMReference m1 = new JVMReference();
        JVMReference m2 = new JVMReference();
        m1.instance = m2;
        m2.instance = m1;
        m1 = null;
        m2 = null;
        System.gc();

    }
}
