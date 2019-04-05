package interview.one;

/**
 * Created by FrankCooper
 * Date 2019/4/5 9:38
 * Description
 */
public class Box {
    private String object;

    public void set(String object) {
        this.object = object;
    }

    public String get() {
        return object;
    }

    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray) {
            if (e.compareTo(elem) > 0) {
                ++count;
            }
        }
        return count;
    }
}
