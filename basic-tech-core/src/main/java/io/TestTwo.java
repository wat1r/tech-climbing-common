package io;

import java.util.*;

/**
 * @Author:Cooper
 * @Date:2019/1/26 11:24
 */
public class TestTwo {

    public static void main(String[] args) {
        System.out.printf("this is a %s\n", "hello");

        testRetainList();

        System.out.println("he");


    }


    public static void testRetainList() {
        Set<String> before = new HashSet<>(Arrays.asList("ES", "HIVE"));
        Set<String> current = new HashSet<>(Arrays.asList("ES", "KUDU"));
//        current = new HashSet<>();
        before.removeAll(current);//获取before对current的差集
        System.out.println("");
    }
}
