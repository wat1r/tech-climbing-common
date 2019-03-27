package interview;

/**
 * Created by FrankCooper
 * Date 2019/3/27 23:00
 * Description
 */
public class DoubleCheckedLocking {
    private volatile static DoubleCheckedLocking instance;

    public static DoubleCheckedLocking getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLocking.class) {
                if (instance == null) {
                    instance = new DoubleCheckedLocking();
                }
            }
        }
        return instance;
    }
}
