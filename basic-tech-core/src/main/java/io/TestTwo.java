package io;

import java.util.*;

/**
 * @Author:Cooper
 * @Date:2019/1/26 11:24
 */
public class TestTwo {

    public static void main(String[] args) {
        System.out.printf("this is a %s\n", "hello");

//        testRetainList();

        t1();
        System.out.println("he");


    }


    public static void testRetainList() {
        Set<String> before = new HashSet<>(Arrays.asList("ES", "HIVE"));
        Set<String> current = new HashSet<>(Arrays.asList("ES", "KUDU"));
//        current = new HashSet<>();
        before.removeAll(current);//获取before对current的差集
        System.out.println("");
    }


    public static void t1() {
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("5");
        list1.add("6");

        List<String> list2 = new ArrayList<String>();
        list2.add("2");
        list2.add("3");
        list2.add("7");
        list2.add("8");


        ArrayList<String> unionSet = new ArrayList<>(list1);//交集
        unionSet.retainAll(list2);
        ArrayList<String> diff1 = new ArrayList<>(list1);
        diff1.removeAll(list2);
        ArrayList<String> diff2 = new ArrayList<>(list2);
        diff2.removeAll(list1);



//        list1.removeAll(list2);
        // 交集
//        list1.retainAll(list2);
// 缺点是，不能返回一个新的集合，而是改变了原来的集合list1，只保留了交集部分

// 无重复并集
//        list6.removeAll(list5);
//        list5.addAll(list6);

// 可重复并集
//        list7.addAll(list8);

// 差集 12 - 11
//        list12.removeAll(list11);
         System.out.println("");
    }
}
