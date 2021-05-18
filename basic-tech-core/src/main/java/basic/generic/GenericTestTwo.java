package basic.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/5/18 17:37
 * @description:
 */


/**
 * https://blog.csdn.net/ystyaoshengting/article/details/86674481
 */

public class GenericTestTwo {
    static GenericTestTwo handler = new GenericTestTwo();

    public static void main(String[] args) {
        handler.testTwo();
    }


    private void testOne() {
//        Plate<Fruit> p = new Plate<Apple>(new Apple());
        Plate<? extends Fruit> p = new Plate<Apple>(new Apple());
    }


    private void testTwo() {
        List<String> strList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();
        System.out.println(strList.getClass().getName());
        System.out.println(intList.getClass().getName());
    }


    @Data
    @AllArgsConstructor
    class Plate<T> {
        private T item;
    }


    class Fruit {

    }

    @Data
    @NoArgsConstructor
    class Apple extends Fruit {


    }


/*    public void inspect(List<Object> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
        list.add(1); // 这个操作在当前方法的上下文是合法的。
    }
    public void test() {
        List<String> strs = new ArrayList<String>();
        inspect(strs); // 编译错误
    }*/

}
