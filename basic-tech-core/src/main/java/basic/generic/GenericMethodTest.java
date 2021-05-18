package basic.generic;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/5/18 17:25
 * @description:
 */

import java.util.*;

public class GenericMethodTest {
    // 声明一个泛型方法，该泛型方法中带一个T类型形参，
    static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
        for (T o : a) {
            c.add(o);
        }
    }

    public static void main(String[] args) {
        Object[] oa = new Object[100];
        Collection<Object> co = new ArrayList<>();
        // 下面代码中T代表Object类型
        fromArrayToCollection(oa, co);
        String[] sa = new String[100];
        Collection<String> cs = new ArrayList<>();
        // 下面代码中T代表String类型
        fromArrayToCollection(sa, cs);
        // 下面代码中T代表Object类型
        fromArrayToCollection(sa, co);
        Integer[] ia = new Integer[100];
        Float[] fa = new Float[100];
        Number[] na = new Number[100];
        Collection<Number> cn = new ArrayList<>();
        // 下面代码中T代表Number类型
        fromArrayToCollection(ia, cn);
        // 下面代码中T代表Number类型
        fromArrayToCollection(fa, cn);
        // 下面代码中T代表Number类型
        fromArrayToCollection(na, cn);
        // 下面代码中T代表Object类型
        fromArrayToCollection(na, co);
        // 下面代码中T代表String类型，但na是一个Number数组，
        // 因为Number既不是String类型，
        // 也不是它的子类，所以出现编译错误
//    fromArrayToCollection(na, cs);
    }
}