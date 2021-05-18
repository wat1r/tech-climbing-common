package basic.generic;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/5/18 17:27
 * @description:
 */

import java.util.*;

public class RightTest {
    // 声明一个泛型方法，该泛型方法中带一个T形参
    static <T> void test(Collection<? extends T> from, Collection<T> to) {
        for (T ele : from) {
            to.add(ele);
        }
    }

    public static void main(String[] args) {
        List<Object> ao = new ArrayList<>();
        List<String> as = new ArrayList<>();
        // 下面代码完全正常,因为前一个集合的元素类型是后一个集合元素类型的子类型
        test(as, ao);
    }
}