package github.jcSprout;

/**
 * Created by FrankCooper
 * Date 2019/2/9 12:25
 * Description:volatile 关键字只能保证可见性，顺序性，不能保证原子性。
 * https://github.com/crossoverJie/JCSprout
 */
public class Singleton {
    private static volatile Singleton singleton;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}